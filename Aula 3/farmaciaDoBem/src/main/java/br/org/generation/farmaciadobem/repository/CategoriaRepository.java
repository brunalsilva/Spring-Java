package br.org.generation.farmaciadobem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.farmaciadobem.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
	
	public List<Categoria> findAllBySecaoContainingIgnoreCase(String secao);
}
