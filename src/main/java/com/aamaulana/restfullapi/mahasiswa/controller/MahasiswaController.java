package com.aamaulana.restfullapi.mahasiswa.controller;

import com.aamaulana.restfullapi.common.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<MahasiswaResponseDTO>>> getAllMahasiswa() {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", mahasiswaService.getAllMahasiswa()));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<MahasiswaResponseDTO>> getMahasiswaById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", mahasiswaService.getMahasiswaById(id)));
    }

    @GetMapping("/query={query}")
    public ResponseEntity<ApiResponse<List<MahasiswaResponseDTO>>> getMahasiswaByQuery(@PathVariable String query) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", mahasiswaService.getMahasiswaByQuery(query)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createMahasiswa(@RequestBody MahasiswaRequestDTO request) {
        mahasiswaService.createMahasiswa(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "success create new data Mahasiswa"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateMahasiswa(@PathVariable Long id, @RequestBody MahasiswaRequestDTO requestDTO) {
        mahasiswaService.updateMahasiswa(id, requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "success update Mahasiswa with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMahasiswaById(@PathVariable Long id) {
        mahasiswaService.deleteMahasiswaById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Success delete with id: " + id));
    }
}

