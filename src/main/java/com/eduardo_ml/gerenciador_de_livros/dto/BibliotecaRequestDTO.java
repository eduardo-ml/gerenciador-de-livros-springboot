package com.eduardo_ml.gerenciador_de_livros.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BibliotecaRequestDTO {
    @NotBlank(message = "O nome da biblioteca não pode estar em branco.")
    private String nome;
}