package com.eduardo_ml.gerenciador_de_livros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LivroRequestDTO {

    @NotBlank(message = "O nome do livro não pode estar em branco.")
    private String nome;

    @NotNull(message = "A data do livro não pode ser nula.")
    private Date data;

    @NotNull(message = "O id do autor não pode ser nulo.")
    private Long autorId;

    @NotNull(message = "O id da biblioteca não pode ser nulo.")
    private Long bibliotecaId;
}