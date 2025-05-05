package com.aamaulana.restfullapi.jurusan.mapper;

import com.aamaulana.restfullapi.jurusan.dto.JurusanResponseDTO;
import com.aamaulana.restfullapi.jurusan.model.Jurusan;

public class JurusanMapper {

    public static JurusanResponseDTO toDTO(Jurusan jurusan) {
        return JurusanResponseDTO.builder()
                .id(jurusan.getId())
                .jurusanName(jurusan.getName())
                .build();
    }
}
