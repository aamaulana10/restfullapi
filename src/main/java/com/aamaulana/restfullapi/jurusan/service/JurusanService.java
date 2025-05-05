package com.aamaulana.restfullapi.jurusan.service;

import com.aamaulana.restfullapi.jurusan.dto.JurusanRequestDTO;
import com.aamaulana.restfullapi.jurusan.dto.JurusanResponseDTO;
import com.aamaulana.restfullapi.jurusan.mapper.JurusanMapper;
import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import com.aamaulana.restfullapi.jurusan.repository.JurusanRepository;
import com.aamaulana.restfullapi.mahasiswa.mapper.MahasiswaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurusanService {

    @Autowired
    private JurusanRepository jurusanRepository;

    public List<JurusanResponseDTO> getAllJurusan() {
        return jurusanRepository.findAll().stream()
                .map(JurusanMapper::toDTO)
                .toList();
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

    public void createJurusan(JurusanRequestDTO requestDTO) {

        Jurusan jurusan = Jurusan.builder()
                            .name(requestDTO.getJurusanName())
                            .build();

        jurusanRepository.save(jurusan);
    }

    public void deleteJurusanById(Long id) {
        if (!jurusanRepository.existsById(id)) {
            throw new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan.");
        }
        jurusanRepository.deleteById(id);
    }

    public void updateJurusan(Long id, JurusanRequestDTO requestDTO) {
        Jurusan newJurusan = jurusanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan."));

        newJurusan.setName(requestDTO.getJurusanName());
        jurusanRepository.save(newJurusan);
    }
}
