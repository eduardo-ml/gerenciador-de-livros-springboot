package com.eduardo_ml.gerenciador_de_livros.repository;

import com.eduardo_ml.gerenciador_de_livros.model.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<AutorModel, Long> {
}