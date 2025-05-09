package com.aamaulana.restfullapi.jurusan.service;

import com.aamaulana.restfullapi.common.config.AppConfig;
import com.aamaulana.restfullapi.jurusan.dto.JurusanRequestDTO;
import com.aamaulana.restfullapi.jurusan.dto.JurusanResponseDTO;
import com.aamaulana.restfullapi.jurusan.mapper.JurusanMapper;
import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import com.aamaulana.restfullapi.jurusan.repository.JurusanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class JurusanService {

    @Autowired
    private JurusanRepository jurusanRepository;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AppConfig appConfig;

    public List<JurusanResponseDTO> getAllJurusan() {
        String redisKey = "jurusanList::all";
        String redisData = redisTemplate.opsForValue().get(redisKey);

        System.out.println("jurusan from redis " + redisData);

        // get data from redis if available
        if (redisData != null) {
            try {
                return objectMapper.readValue(
                        redisData,
                        new TypeReference<List<JurusanResponseDTO>>() {}
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed decode JSON from Redis", e);
            }
        }

        // if no data in redis, call from DB
        System.out.println("call data from DB");
        var result = jurusanRepository.findAll().stream()
                .map(JurusanMapper::toDTO)
                .toList();

        try {
            // save new data from redis
            String jsonValue = objectMapper.writeValueAsString(result);
            redisTemplate.opsForValue().set(redisKey, jsonValue, Duration.ofSeconds(appConfig.getRedisTtlInSecond()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed encode JSON for Redis", e);
        }

        return result;
    }

    public JurusanResponseDTO getJurusanById(Long id) {
        return JurusanMapper.toDTO(jurusanRepository.findById(id).orElseThrow());
    }

    public JurusanResponseDTO getJurusanByName(String name) {
        return JurusanMapper.toDTO(jurusanRepository.getJurusanByName(name));
    }

    public List<JurusanResponseDTO>  getJurusanByQuery(String query) {
        return jurusanRepository.getByNameContaining(query).stream()
                .map(JurusanMapper::toDTO)
                .toList();
    }

    @CacheEvict(value = "jurusanList", allEntries = true) // reset redis for jurusanList
    public void createJurusan(JurusanRequestDTO requestDTO) {

        Jurusan jurusan = Jurusan.builder()
                            .name(requestDTO.getJurusanName())
                            .build();

        jurusanRepository.save(jurusan);
    }

    @CacheEvict(value = "jurusanList", allEntries = true)
    public void deleteJurusanById(Long id) {
        if (!jurusanRepository.existsById(id)) {
            throw new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan.");
        }
        jurusanRepository.deleteById(id);
    }

    @CacheEvict(value = "jurusanList", allEntries = true)
    public void updateJurusan(Long id, JurusanRequestDTO requestDTO) {
        Jurusan newJurusan = jurusanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan."));

        newJurusan.setName(requestDTO.getJurusanName());
        jurusanRepository.save(newJurusan);
    }
}
