package com.aamaulana.restfullapi.mahasiswa.dto;

import lombok.Data;

@Data
public class MahasiswaRequestDTO {
    private String name;
    private String nim;
    private Long jurusanId;
}
