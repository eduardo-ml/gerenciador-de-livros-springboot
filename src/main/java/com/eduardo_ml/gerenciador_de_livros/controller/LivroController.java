package com.eduardo_ml.gerenciador_de_livros.controller;

import com.eduardo_ml.gerenciador_de_livros.dto.LivroRequestDTO;
import com.eduardo_ml.gerenciador_de_livros.dto.LivroResponseDTO;
import com.eduardo_ml.gerenciador_de_livros.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> registrarLivro(@Valid @RequestBody LivroRequestDTO livroDTO) {
        LivroResponseDTO novoLivro = livroService.registrarLivro(livroDTO);
        return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listarLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroResponseDTO> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO livroDTO) {
        LivroResponseDTO livroAtualizado = livroService.atualizarLivro(id, livroDTO);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        livroService.excluirLivro(id);
        return ResponseEntity.noContent().build();
    }
}
