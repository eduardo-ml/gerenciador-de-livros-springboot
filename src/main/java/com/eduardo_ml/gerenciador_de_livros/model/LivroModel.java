package com.eduardo_ml.gerenciador_de_livros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do livro não pode estar em branco.")
    private String nome;

    @NotNull(message = "A data do livro não pode estar nula.")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorModel autor;

    @ManyToOne
    @JoinColumn(name = "biblioteca_id")
    private BibliotecaModel biblioteca;
}