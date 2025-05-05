package com.aamaulana.restfullapi.jurusan.controller;

import com.aamaulana.restfullapi.jurusan.dto.JurusanRequestDTO;
import com.aamaulana.restfullapi.jurusan.dto.JurusanResponseDTO;
import com.aamaulana.restfullapi.jurusan.service.JurusanService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jurusan")
public class JurusanController {

    @Autowired
    private JurusanService jurusanService;

    @GetMapping
    public ResponseEntity<List<JurusanResponseDTO>> getAllJurusan() {
        try {
            return ResponseEntity.ok(jurusanService.getAllJurusan());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/id={id}")
    public ResponseEntity<JurusanResponseDTO> getJurusanById(@PathVariable Long id) {
        System.out.println("id ====> " + id);
        try {
            return ResponseEntity.ok(jurusanService.getJurusanById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/name={name}")
    public ResponseEntity<JurusanResponseDTO> getJurusanByName(@PathVariable String name) {
        System.out.println("name ====> " + name);
        var lowerCaseQuery = name.toLowerCase();
        try {
            return ResponseEntity.ok(jurusanService.getJurusanByName(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/query={query}")
    public ResponseEntity<List<JurusanResponseDTO>> getJurusanByQuery(@PathVariable String query) {
        try {
            return ResponseEntity.ok(jurusanService.getJurusanByQuery(query));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping
    public ResponseEntity<String> createJurusan(@RequestBody JurusanRequestDTO request) {
        try {
            jurusanService.createJurusan(request);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJurusan(@PathVariable Long id, @RequestBody JurusanRequestDTO request) {
        try {
            jurusanService.updateJurusan(id, request);
            return ResponseEntity.ok("success update jurusan with id " + id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJurusanById(@PathVariable Long id) {
        try {
            jurusanService.deleteJurusanById(id);
            return ResponseEntity.ok("Success delete with id: " + id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
        }
    }
}
