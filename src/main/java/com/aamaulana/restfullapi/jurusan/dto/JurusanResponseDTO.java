package com.aamaulana.restfullapi.jurusan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JurusanResponseDTO implements Serializable {
    Long id;
    String jurusanName;
}
