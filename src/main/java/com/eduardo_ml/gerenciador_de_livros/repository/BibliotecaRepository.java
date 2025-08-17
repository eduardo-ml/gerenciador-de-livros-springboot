package com.eduardo_ml.gerenciador_de_livros.repository;

import com.eduardo_ml.gerenciador_de_livros.model.BibliotecaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<BibliotecaModel, Long> {
}