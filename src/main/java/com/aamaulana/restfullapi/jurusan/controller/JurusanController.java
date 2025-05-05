package com.aamaulana.restfullapi.jurusan.controller;

import com.aamaulana.restfullapi.common.response.ApiResponse;
import com.aamaulana.restfullapi.jurusan.dto.JurusanRequestDTO;
import com.aamaulana.restfullapi.jurusan.dto.JurusanResponseDTO;
import com.aamaulana.restfullapi.jurusan.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jurusan")
public class JurusanController {

    @Autowired
    private JurusanService jurusanService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<JurusanResponseDTO>>> getAllJurusan() {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", jurusanService.getAllJurusan()));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<JurusanResponseDTO>> getJurusanById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", jurusanService.getJurusanById(id)));

    }

    @GetMapping("/name={name}")
    public ResponseEntity<ApiResponse<JurusanResponseDTO>> getJurusanByName(@PathVariable String name) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", jurusanService.getJurusanByName(name)));
    }

    @GetMapping("/query={query}")
    public ResponseEntity<ApiResponse<List<JurusanResponseDTO>>> getJurusanByQuery(@PathVariable String query) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", jurusanService.getJurusanByQuery(query)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createJurusan(@RequestBody JurusanRequestDTO request) {
        jurusanService.createJurusan(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", request.toString()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateJurusan(@PathVariable Long id, @RequestBody JurusanRequestDTO request) {
        jurusanService.updateJurusan(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success update jurusan with id" + id, request.toString()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteJurusanById(@PathVariable Long id) {
        jurusanService.deleteJurusanById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success delete with id: " + id, null));
    }
}
