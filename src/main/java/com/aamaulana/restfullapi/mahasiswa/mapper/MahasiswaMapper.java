package com.aamaulana.restfullapi.mahasiswa.mapper;

import com.aamaulana.restfullapi.mahasiswa.dto.MahasiswaResponseDTO;
import com.aamaulana.restfullapi.mahasiswa.model.Mahasiswa;

public class MahasiswaMapper {

    public static MahasiswaResponseDTO toDTO(Mahasiswa mahasiswa) {
        return MahasiswaResponseDTO.builder()
                .id(mahasiswa.getId())
                .name(mahasiswa.getName())
                .nim(mahasiswa.getNim())
                .jurusan(mahasiswa.getJurusan() != null ? mahasiswa.getJurusan().getName() : null)
                .build();
    }
}
