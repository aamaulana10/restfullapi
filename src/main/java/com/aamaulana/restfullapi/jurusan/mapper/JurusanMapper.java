package com.aamaulana.restfullapi.jurusan.mapper;

import com.aamaulana.restfullapi.jurusan.dto.JurusanDTO;
import com.aamaulana.restfullapi.jurusan.model.Jurusan;

public class JurusanMapper {

    public static JurusanDTO toDTO(Jurusan jurusan) {
        return JurusanDTO.builder()
                .id(jurusan.getId())
                .jurusanName(jurusan.getName())
                .build();
    }
}
