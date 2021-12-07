package br.org.generation.minhalojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.minhalojadegames.model.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel,Long> //<classe principal,chave primária>
{
	public List<CategoriaModel> findAllByDescricaoContainingIgnoreCase(String descricao);
}