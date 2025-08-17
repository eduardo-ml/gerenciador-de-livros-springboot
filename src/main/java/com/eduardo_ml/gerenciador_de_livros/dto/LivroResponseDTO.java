package com.eduardo_ml.gerenciador_de_livros.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class LivroResponseDTO {
    private Long id;
    private String nome;
    private Date data;
    private AutorEmLivroResponseDTO autor;
    private BibliotecaEmLivroResponseDTO biblioteca;
}