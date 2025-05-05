package com.aamaulana.restfullapi.mahasiswa.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MahasiswaResponseDTO {

    Long id;
    String name;
    String nim;
    String jurusan;
}
