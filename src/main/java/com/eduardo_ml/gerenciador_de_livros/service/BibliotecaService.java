package com.eduardo_ml.gerenciador_de_livros.service;

import com.eduardo_ml.gerenciador_de_livros.dto.BibliotecaRequestDTO;
import com.eduardo_ml.gerenciador_de_livros.dto.BibliotecaResponseDTO;
import com.eduardo_ml.gerenciador_de_livros.exception.ResourceNotFoundException;
import com.eduardo_ml.gerenciador_de_livros.model.BibliotecaModel;
import com.eduardo_ml.gerenciador_de_livros.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BibliotecaService {

    private final BibliotecaRepository repository;

    public BibliotecaService(BibliotecaRepository repository) {
        this.repository = repository;
    }

    public List<BibliotecaResponseDTO> listarBibliotecas() {
        List<BibliotecaModel> bibliotecas = repository.findAll();
        List<BibliotecaResponseDTO> dtos = new ArrayList<>();
        for (BibliotecaModel biblioteca : bibliotecas) {
            dtos.add(paraResponseDTO(biblioteca));
        }
        return dtos;
    }

    public BibliotecaResponseDTO buscarPorId(Long id) {
        BibliotecaModel biblioteca = findBibliotecaById(id);
        return paraResponseDTO(biblioteca);
    }

    public BibliotecaResponseDTO registrarBiblioteca(BibliotecaRequestDTO bibliotecaDTO) {
        BibliotecaModel biblioteca = new BibliotecaModel();
        biblioteca.setNome(bibliotecaDTO.getNome());
        BibliotecaModel bibliotecaSalva = repository.save(biblioteca);
        return paraResponseDTO(bibliotecaSalva);
    }

    public BibliotecaResponseDTO atualizarBiblioteca(Long id, BibliotecaRequestDTO bibliotecaDTO) {
        BibliotecaModel bibliotecaExistente = findBibliotecaById(id);
        bibliotecaExistente.setNome(bibliotecaDTO.getNome());
        BibliotecaModel bibliotecaAtualizada = repository.save(bibliotecaExistente);
        return paraResponseDTO(bibliotecaAtualizada);
    }

    public void excluirBiblioteca(Long id) {
        findBibliotecaById(id);
        repository.deleteById(id);
    }

    public BibliotecaModel findBibliotecaById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Biblioteca com id " + id + " não encontrada."));
    }

    private BibliotecaResponseDTO paraResponseDTO(BibliotecaModel biblioteca) {
        BibliotecaResponseDTO dto = new BibliotecaResponseDTO();
        dto.setId(biblioteca.getId());
        dto.setNome(biblioteca.getNome());
        return dto;
    }
}