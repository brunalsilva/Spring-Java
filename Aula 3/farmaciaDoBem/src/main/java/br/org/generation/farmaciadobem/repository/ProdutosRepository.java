package br.org.generation.farmaciadobem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.farmaciadobem.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos,Long>{
	
	public List<Produtos> findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Produtos> findAllByNomeAndFarmaceuticaContainingIgnoreCase(String nome, String farmaceutica);
	
}