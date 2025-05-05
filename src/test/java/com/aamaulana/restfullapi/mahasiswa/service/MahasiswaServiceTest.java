package com.aamaulana.restfullapi.mahasiswa.service;

import com.aamaulana.restfullapi.jurusan.model.Jurusan;
import com.aamaulana.restfullapi.mahasiswa.dto.MahasiswaResponseDTO;
import com.aamaulana.restfullapi.mahasiswa.model.Mahasiswa;
import com.aamaulana.restfullapi.mahasiswa.repository.MahasiswaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MahasiswaServiceTest {

    @Mock
    private MahasiswaRepository mahasiswaRepository;

    @InjectMocks
    private MahasiswaService mahasiswaService;

    private Mahasiswa mahasiswa;

    @BeforeEach
    void setUp() {
        Jurusan jurusan = Jurusan.builder()
                .id(1L)
                .name("Teknik Informatika")
                .build();

        mahasiswa = Mahasiswa.builder()
                .id(1L)
                .name("Gavin")
                .nim("220001")
                .jurusan(jurusan)
                .build();
    }

    @Test
    void testGetMahasiswaById_success() {
        // Arrange
        when(mahasiswaRepository.findById(1L)).thenReturn(Optional.of(mahasiswa));

        // Act
        MahasiswaResponseDTO result = mahasiswaService.getMahasiswaById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Gavin", result.getName());
        assertEquals("220001", result.getNim());
        assertEquals("Teknik Informatika", result.getJurusan());
    }

    @Test
    void testGetMahasiswaById_notFound() {
        // Arrange
        when(mahasiswaRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> mahasiswaService.getMahasiswaById(99L));
    }
}
