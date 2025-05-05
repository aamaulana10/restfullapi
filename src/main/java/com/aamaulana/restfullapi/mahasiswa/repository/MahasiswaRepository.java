package com.aamaulana.restfullapi.mahasiswa.repository;

import com.aamaulana.restfullapi.mahasiswa.model.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {}
