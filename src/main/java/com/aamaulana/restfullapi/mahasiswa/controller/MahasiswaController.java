package com.aamaulana.restfullapi.mahasiswa.controller;

import com.aamaulana.restfullapi.mahasiswa.dto.MahasiswaRequestDTO;
import com.aamaulana.restfullapi.mahasiswa.dto.MahasiswaResponseDTO;
import com.aamaulana.restfullapi.mahasiswa.service.MahasiswaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mahasiswa")
public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @GetMapping
    public ResponseEntity<List<MahasiswaResponseDTO>> getAllMahasiswa() {
        try {
            return ResponseEntity.ok(mahasiswaService.getAllMahasiswa());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/id={id}")
    public ResponseEntity<MahasiswaResponseDTO> getMahasiswaById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(mahasiswaService.getMahasiswaById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/query={query}")
    public ResponseEntity<List<MahasiswaResponseDTO>> getMahasiswaByQuery(@PathVariable String query) {
        var lowerCaseQuery = query.toLowerCase();
        try {
            return ResponseEntity.ok(mahasiswaService.getMahasiswaByQuery(lowerCaseQuery));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping
    public ResponseEntity<String> createMahasiswa(@RequestBody MahasiswaRequestDTO request) {
        try {
            mahasiswaService.createMahasiswa(request);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMahasiswa(@PathVariable Long id, @RequestBody MahasiswaRequestDTO requestDTO) {
        try {
            mahasiswaService.updateMahasiswa(id, requestDTO);
            return ResponseEntity.ok("success update Mahasiswa with id " + id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMahasiswaById(@PathVariable Long id) {
        try {
            mahasiswaService.deleteMahasiswaById(id);
            return ResponseEntity.ok("Success delete with id: " + id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
        }
    }
}

