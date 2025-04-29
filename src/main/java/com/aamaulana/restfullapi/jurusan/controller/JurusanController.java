package com.aamaulana.restfullapi.jurusan.controller;

import com.aamaulana.restfullapi.jurusan.model.Jurusan;
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
    public ResponseEntity<List<Jurusan>> getAllJurusan() {
        try {
            return ResponseEntity.ok(jurusanService.getAllJurusan());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/id={id}")
    public ResponseEntity<Jurusan> getJurusanById(@PathVariable Long id) {
        System.out.println("id ====> " + id);
        try {
            return ResponseEntity.ok(jurusanService.getJurusanById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/name={name}")
    public ResponseEntity<Jurusan> getJurusanByName(@PathVariable String name) {
        System.out.println("name ====> " + name);
        var lowerCaseQuery = name.toLowerCase();
        try {
            return ResponseEntity.ok(jurusanService.getJurusanByName(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/query={query}")
    public ResponseEntity<List<Jurusan>> getJurusanByQuery(@PathVariable String query) {
        System.out.println("query ====> " + query);
        var lowerCaseQuery = query.toLowerCase();
        try {
            return ResponseEntity.ok(jurusanService.getJurusanByQuery(lowerCaseQuery));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping
    public ResponseEntity<String> createJurusan(@RequestBody Jurusan jurusan) {
        try {
            jurusanService.createJurusan(jurusan);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jurusan> updateJurusan(@PathVariable Long id, @RequestBody Jurusan jurusan) {
        try {
            var result = jurusanService.updateJurusan(id, jurusan);
            return ResponseEntity.ok(result);
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

// ide : bikin aplikasi pencarian ingredient / resep makanan
// unik point selling: user bisa cari resep dengan cara foto bahan bahan nya aja, misal foto dari
// kulkas

// gimmik nya search by foto, jadi praktis
//
