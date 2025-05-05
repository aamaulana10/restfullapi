package com.aamaulana.restfullapi.mahasiswa.model;

import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mahasiswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nim;

    @ManyToOne
    @JoinColumn(name = "jurusan_id", referencedColumnName = "id")
    private Jurusan jurusan;
}
