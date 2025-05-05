package com.aamaulana.restfullapi.jurusan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JurusanRequestDTO {

    @NotBlank(message = "Nama Jurusan cannot be blank")
    String jurusanName;
}
