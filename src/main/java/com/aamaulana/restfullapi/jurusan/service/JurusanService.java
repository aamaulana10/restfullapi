package com.aamaulana.restfullapi.jurusan.service;

import com.aamaulana.restfullapi.common.config.AppConfig;
import com.aamaulana.restfullapi.common.constant.AppConstant;
import com.aamaulana.restfullapi.common.redis.RedisCacheUtil;
import com.aamaulana.restfullapi.jurusan.dto.JurusanRequestDTO;
import com.aamaulana.restfullapi.jurusan.dto.JurusanResponseDTO;
import com.aamaulana.restfullapi.jurusan.mapper.JurusanMapper;
import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import com.aamaulana.restfullapi.jurusan.repository.JurusanRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurusanService {

    @Autowired
    private JurusanRepository jurusanRepository;

    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Autowired
    AppConfig appConfig;

    public List<JurusanResponseDTO> getAllJurusan() {

        List<JurusanResponseDTO> redisData = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GET_JURUSAN_LIST,
                new TypeReference<List<JurusanResponseDTO>>() {}
        );

        System.out.println("jurusan from redis " + redisData);

        // get data from redis if available
        if (redisData != null) return redisData;

        // if no data in redis, call from DB
        System.out.println("call data from DB");
        var result = jurusanRepository.findAll().stream()
                .map(JurusanMapper::toDTO)
                .toList();

        redisCacheUtil.cacheValue(AppConstant.REDIS_GET_JURUSAN_LIST, result);

        return result;
    }

    public JurusanResponseDTO getJurusanById(Long id) {

        String redisKey = "jurusan::" + id;
        JurusanResponseDTO redisData = redisCacheUtil.getCachedValue(
                AppConstant.REDIS_GET_JURUSAN_LIST,
                JurusanResponseDTO.class
        );

        // get data from redis if available
        if (redisData != null) return redisData;

        // if no data in redis, call from DB
        System.out.println("call data from DB");
        JurusanResponseDTO result = JurusanMapper.toDTO(jurusanRepository.findById(id).orElseThrow());
        redisCacheUtil.cacheValue(redisKey, result);

        return result;
    }

    public JurusanResponseDTO getJurusanByName(String name) {
        return JurusanMapper.toDTO(jurusanRepository.getJurusanByName(name));
    }

    public List<JurusanResponseDTO>  getJurusanByQuery(String query) {
        return jurusanRepository.getByNameContaining(query).stream()
                .map(JurusanMapper::toDTO)
                .toList();
    }

    public void createJurusan(JurusanRequestDTO requestDTO) {

        deleteAllJurusanCache();
        Jurusan jurusan = Jurusan.builder()
                            .name(requestDTO.getJurusanName())
                            .build();

        jurusanRepository.save(jurusan);
    }

    public void deleteJurusanById(Long id) {

        deleteAllJurusanCache(id);

        if (!jurusanRepository.existsById(id)) {
            throw new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan.");
        }
        jurusanRepository.deleteById(id);
    }

    public void updateJurusan(Long id, JurusanRequestDTO requestDTO) {

        deleteAllJurusanCache(id);
        Jurusan newJurusan = jurusanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan."));

        newJurusan.setName(requestDTO.getJurusanName());
        jurusanRepository.save(newJurusan);
    }

    /**
     * Delete all Jurusan list cache.
     */
    public void deleteAllJurusanCache() {
        redisCacheUtil.deleteCache(AppConstant.REDIS_GET_JURUSAN_LIST);
    }

    /**
     * Delete all Jurusan list cache and specific Jurusan by ID.
     * @param id the ID of the Jurusan to delete from cache
     */
    public void deleteAllJurusanCache(Long id) {
        deleteAllJurusanCache();
        redisCacheUtil.deleteCache("jurusan::" + id);
    }
}
