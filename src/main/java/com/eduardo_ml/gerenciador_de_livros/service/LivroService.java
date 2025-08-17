package com.eduardo_ml.gerenciador_de_livros.service;

import com.eduardo_ml.gerenciador_de_livros.dto.AutorEmLivroResponseDTO;
import com.eduardo_ml.gerenciador_de_livros.dto.BibliotecaEmLivroResponseDTO;
import com.eduardo_ml.gerenciador_de_livros.dto.LivroRequestDTO;
import com.eduardo_ml.gerenciador_de_livros.dto.LivroResponseDTO;
import com.eduardo_ml.gerenciador_de_livros.exception.ResourceNotFoundException;
import com.eduardo_ml.gerenciador_de_livros.model.AutorModel;
import com.eduardo_ml.gerenciador_de_livros.model.BibliotecaModel;
import com.eduardo_ml.gerenciador_de_livros.model.LivroModel;
import com.eduardo_ml.gerenciador_de_livros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {
    private final LivroRepository repository;
    private final AutorService autorService;
    private final BibliotecaService bibliotecaService;

    public LivroService(LivroRepository repository, AutorService autorService, BibliotecaService bibliotecaService) {
        this.repository = repository;
        this.autorService = autorService;
        this.bibliotecaService = bibliotecaService;
    }

    public LivroResponseDTO registrarLivro(LivroRequestDTO livroDTO) {
        LivroModel livro = paraEntidade(livroDTO);
        LivroModel livroSalvo = repository.save(livro);
        return paraResponseDTO(livroSalvo);
    }

    public List<LivroResponseDTO> listarLivros() {
        List<LivroModel> livros = repository.findAll();
        List<LivroResponseDTO> dtos = new ArrayList<>();
        for (LivroModel livro : livros) {
            dtos.add(paraResponseDTO(livro));
        }
        return dtos;
    }

    public LivroResponseDTO findById(Long id) {
        LivroModel livro = findLivroById(id);
        return paraResponseDTO(livro);
    }

    public LivroResponseDTO atualizarLivro(Long id, LivroRequestDTO livroDTO) {
        LivroModel livroExistente = findLivroById(id);

        AutorModel autor = autorService.findAutorById(livroDTO.getAutorId());
        BibliotecaModel biblioteca = bibliotecaService.findBibliotecaById(livroDTO.getBibliotecaId());

        livroExistente.setNome(livroDTO.getNome());
        livroExistente.setData(livroDTO.getData());
        livroExistente.setAutor(autor);
        livroExistente.setBiblioteca(biblioteca);

        LivroModel livroAtualizado = repository.save(livroExistente);
        return paraResponseDTO(livroAtualizado);
    }

    public void excluirLivro(Long id) {
        findLivroById(id);
        repository.deleteById(id);
    }

    public LivroModel findLivroById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro com id " + id + " não encontrado."));
    }

    private LivroModel paraEntidade(LivroRequestDTO dto) {
        AutorModel autor = autorService.findAutorById(dto.getAutorId());
        BibliotecaModel biblioteca = bibliotecaService.findBibliotecaById(dto.getBibliotecaId());

        LivroModel livro = new LivroModel();
        livro.setNome(dto.getNome());
        livro.setData(dto.getData());
        livro.setAutor(autor);
        livro.setBiblioteca(biblioteca);
        return livro;
    }

    private LivroResponseDTO paraResponseDTO(LivroModel livro) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(livro.getId());
        dto.setNome(livro.getNome());
        dto.setData(livro.getData());

        AutorEmLivroResponseDTO autorDTO = new AutorEmLivroResponseDTO();
        autorDTO.setId(livro.getAutor().getId());
        autorDTO.setNome(livro.getAutor().getNome());
        dto.setAutor(autorDTO);

        BibliotecaEmLivroResponseDTO bibliotecaDTO = new BibliotecaEmLivroResponseDTO();
        bibliotecaDTO.setId(livro.getBiblioteca().getId());
        bibliotecaDTO.setNome(livro.getBiblioteca().getNome());
        dto.setBiblioteca(bibliotecaDTO);

        return dto;
    }
}