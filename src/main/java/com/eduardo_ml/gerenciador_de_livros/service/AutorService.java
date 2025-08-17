package com.eduardo_ml.gerenciador_de_livros.service;

import com.eduardo_ml.gerenciador_de_livros.dto.AutorRequestDTO;
import com.eduardo_ml.gerenciador_de_livros.dto.AutorResponseDTO;
import com.eduardo_ml.gerenciador_de_livros.exception.ResourceNotFoundException;
import com.eduardo_ml.gerenciador_de_livros.model.AutorModel;
import com.eduardo_ml.gerenciador_de_livros.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<AutorResponseDTO> listarAutores() {
        List<AutorModel> autores = repository.findAll();
        List<AutorResponseDTO> dtos = new ArrayList<>();
        for (AutorModel autor : autores) {
            dtos.add(paraResponseDTO(autor));
        }
        return dtos;
    }

    public AutorResponseDTO buscarPorId(Long id) {
        AutorModel autor = findAutorById(id);
        return paraResponseDTO(autor);
    }

    public AutorResponseDTO registrarAutor(AutorRequestDTO autorDTO) {
        AutorModel autor = new AutorModel();
        autor.setNome(autorDTO.getNome());
        AutorModel autorSalvo = repository.save(autor);
        return paraResponseDTO(autorSalvo);
    }

    public AutorResponseDTO atualizarAutor(Long id, AutorRequestDTO autorDTO) {
        AutorModel autorExistente = findAutorById(id);
        autorExistente.setNome(autorDTO.getNome());
        AutorModel autorAtualizado = repository.save(autorExistente);
        return paraResponseDTO(autorAtualizado);
    }

    public void excluirAutor(Long id) {
        findAutorById(id);
        repository.deleteById(id);
    }

    public AutorModel findAutorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor com id " + id + " não encontrado."));
    }

    private AutorResponseDTO paraResponseDTO(AutorModel autor) {
        AutorResponseDTO dto = new AutorResponseDTO();
        dto.setId(autor.getId());
        dto.setNome(autor.getNome());
        return dto;
    }
}