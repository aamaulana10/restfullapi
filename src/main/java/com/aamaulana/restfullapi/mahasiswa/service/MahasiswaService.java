package com.aamaulana.restfullapi.mahasiswa.service;

import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import com.aamaulana.restfullapi.jurusan.repository.JurusanRepository;
import com.aamaulana.restfullapi.mahasiswa.dto.MahasiswaRequestDTO;
import com.aamaulana.restfullapi.mahasiswa.dto.MahasiswaResponseDTO;
import com.aamaulana.restfullapi.mahasiswa.mapper.MahasiswaMapper;
import com.aamaulana.restfullapi.mahasiswa.model.Mahasiswa;
import com.aamaulana.restfullapi.mahasiswa.repository.MahasiswaRepository;
import com.aamaulana.restfullapi.mahasiswa.repository.MahasiswaRepositoryJDBC;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MahasiswaService {

    @Autowired
    MahasiswaRepository mahasiswaRepository;

    @Autowired
    JurusanRepository jurusanRepository;

    @Autowired
    MahasiswaRepositoryJDBC mahasiswaRepositoryJDBC;

    public List<MahasiswaResponseDTO> getAllMahasiswa() {
        return mahasiswaRepository.findAll().stream()
                .map(MahasiswaMapper::toDTO)
                .toList();
    }

    public MahasiswaResponseDTO getMahasiswaById(Long id) {
        return MahasiswaMapper.toDTO(mahasiswaRepository.findById(id).orElseThrow());
    }

    public List<MahasiswaResponseDTO>  getMahasiswaByQuery(String query) {
        return mahasiswaRepositoryJDBC.findByNameQuery(query).stream()
                .map(MahasiswaMapper::toDTO)
                .toList();
    }

    public void createMahasiswa(MahasiswaRequestDTO request) {

        Jurusan jurusan = jurusanRepository.findById(request.getJurusanId())
                .orElseThrow(() -> new RuntimeException("Jurusan not found"));

        Mahasiswa mahasiswa = Mahasiswa.builder()
                .name(request.getName())
                .nim(request.getNim())
                .jurusan(jurusan)
                .build();

       mahasiswaRepository.save(mahasiswa);
    }

    public void deleteMahasiswaById(Long id) {
        if (!mahasiswaRepository.existsById(id)) {
            throw new EntityNotFoundException("Mahasiswa dengan ID " + id + " tidak ditemukan.");
        }
        mahasiswaRepository.deleteById(id);
    }

    public void updateMahasiswa(Long id, MahasiswaRequestDTO request) {
        Mahasiswa newMahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mahasiswa dengan ID " + id + " tidak ditemukan."));

        Jurusan jurusan = jurusanRepository.findById(request.getJurusanId())
                .orElseThrow(() -> new EntityNotFoundException("Jurusan dengan ID " + id + " tidak ditemukan."));


        newMahasiswa.setName(request.getName());
        newMahasiswa.setNim(request.getNim());
        newMahasiswa.setJurusan(jurusan);
        mahasiswaRepository.save(newMahasiswa);
    }
}
