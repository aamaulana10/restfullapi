package com.aamaulana.restfullapi.mahasiswa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mahasiswa")
public class MahasiswaController {

    @GetMapping
    ResponseEntity<String> getAllData(){
        return ResponseEntity.ok("get all data mahasiswa");
    }
}
