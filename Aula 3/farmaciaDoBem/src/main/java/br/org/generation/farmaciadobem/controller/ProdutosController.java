package br.org.generation.farmaciadobem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.farmaciadobem.model.Produtos;
import br.org.generation.farmaciadobem.repository.CategoriaRepository;
import br.org.generation.farmaciadobem.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins="*",allowedHeaders="*")
public class ProdutosController {
		
		@Autowired
		ProdutosRepository produtosRepository;
		
		@Autowired
		CategoriaRepository categoriaRepository;
		
		@GetMapping
		public ResponseEntity <List<Produtos>> getAll()
		{ 
			return ResponseEntity.ok(produtosRepository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity <Produtos> getById(@PathVariable Long id) 
		{ 
			return produtosRepository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());
		}
		
		@GetMapping("/nome/{nome}")
		public ResponseEntity <List<Produtos>> getByNome (@Valid @PathVariable String nome)
		{
			return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
		}
		
		@GetMapping("/busca/{nome}/{farmaceutica}")
		public ResponseEntity <List<Produtos>> getByNomeAndFarmaceutica (@Valid @PathVariable String nome, @PathVariable String farmaceutica)
		{
			return ResponseEntity.ok(produtosRepository.findAllByNomeAndFarmaceuticaContainingIgnoreCase(nome,farmaceutica));
		}
		
		@PostMapping
		public ResponseEntity <Produtos> postProduto (@Valid @RequestBody Produtos produto)
		{
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produto));
			 else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		@PutMapping
		public ResponseEntity <Produtos> putProduto (@Valid @RequestBody Produtos produto)
		{
			return produtosRepository.findById(produto.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produto)))   
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		@DeleteMapping(path ={"/{id}"})
		public ResponseEntity<?> delete(@PathVariable Long id)
		{
			   return produtosRepository.findById(id)
			           .map(record -> 
			           {
			               produtosRepository.deleteById(id);
			               return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			           })
			           .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
}
