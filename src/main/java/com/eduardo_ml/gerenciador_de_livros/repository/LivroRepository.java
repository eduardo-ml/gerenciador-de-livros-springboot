package com.eduardo_ml.gerenciador_de_livros.repository;

import com.eduardo_ml.gerenciador_de_livros.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroModel, Long> {
}
