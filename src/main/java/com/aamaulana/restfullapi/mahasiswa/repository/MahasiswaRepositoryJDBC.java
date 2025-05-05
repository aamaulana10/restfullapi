package com.aamaulana.restfullapi.mahasiswa.repository;


import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import com.aamaulana.restfullapi.mahasiswa.model.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MahasiswaRepositoryJDBC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Mahasiswa> findByNameQuery(String name) {
        String sql = """
                SELECT m.id as m_id, m.name as m_name, m.nim as m_nim,
                    j.id as j_id, j.name as j_name
                FROM mahasiswa m
                JOIN jurusan j ON m.jurusan_id = j.id
                WHERE m.name ILIKE ?
            """;

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, "%" + name + "%");
        }, (rs, rowNum) -> {
            Jurusan jurusan = Jurusan.builder()
                    .id(rs.getLong("j_id"))
                    .name(rs.getString("j_name"))
                    .build();

            return Mahasiswa.builder()
                    .id(rs.getLong("m_id"))
                    .name(rs.getString("m_name"))
                    .nim(rs.getString("m_nim"))
                    .jurusan(jurusan)
                    .build();
        });
    }
}
