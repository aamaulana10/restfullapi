package com.aamaulana.restfullapi.jurusan.repository;

import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JurusanRepository extends JpaRepository<Jurusan, Long> {
    Jurusan getJurusanByName(String name);

    List<Jurusan> getByNameContaining(String keyword);
}
