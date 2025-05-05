package com.aamaulana.restfullapi.mahasiswa.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MahasiswaRequestDTO {

    @NotBlank(message = "Nama Mahasiswa cannot be blank")
    private String name;

    @NotBlank(message = "Nim Jurusan cannot be blank")
    @Size(min = 6, message = "NIM must have minimum 6 characters ")
    private String nim;

    @NotNull(message = "ID jurusan cannot be null")
    private Long jurusanId;
}
