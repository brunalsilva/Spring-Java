package br.org.generation.minhalojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.minhalojadegames.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel,Long>
{
	public List<ProdutoModel> findAllByDescricaoContainingIgnoreCase(String descricao);
	public List<ProdutoModel> findAllByNomeContainingIgnoreCase(String nome);
	
}