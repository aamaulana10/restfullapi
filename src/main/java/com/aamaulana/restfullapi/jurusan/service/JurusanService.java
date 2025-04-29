package com.aamaulana.restfullapi.jurusan.service;

import com.aamaulana.restfullapi.jurusan.dto.JurusanDTO;
import com.aamaulana.restfullapi.jurusan.mapper.JurusanMapper;
import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import com.aamaulana.restfullapi.jurusan.repository.JurusanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurusanService {

    @Autowired
    private JurusanRepository jurusanRepository;

    public List<JurusanDTO> getAllJurusan() {
        return jurusanRepository.findAll().stream()
                .map(JurusanMapper::toDTO)
                .toList();
    }

    public Jurusan getJurusanById(Long id) {
        return jurusanRepository.findById(id).orElseThrow();
    }

    public Jurusan getJurusanByName(String name) {
        return jurusanRepository.getJurusanByName(name);
    }

    public List<Jurusan>  getJurusanByQuery(String query) {
        return jurusanRepository.getByNameContaining(query);
    }

    public void createJurusan(Jurusan jurusan) {
        jurusanRepository.save(jurusan);
    }

    public void deleteJurusanById(Long id) {
        if (!jurusanRepository.existsById(id)) {
            throw new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan.");
        }
        jurusanRepository.deleteById(id);
    }

    public Jurusan updateJurusan(Long id, Jurusan jurusan) {
        Jurusan newJurusan = jurusanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan."));

        newJurusan.setName(jurusan.getName());
        return jurusanRepository.save(newJurusan);
    }
}
