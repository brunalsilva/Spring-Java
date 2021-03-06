package br.org.generation.minhalojadegames.controller;

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

import br.org.generation.minhalojadegames.model.CategoriaModel;
import br.org.generation.minhalojadegames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins="*",allowedHeaders="*")
public class CategoriaController {
			
			@Autowired
			private CategoriaRepository categoriaRepository;
			
			@GetMapping
			public ResponseEntity <List<CategoriaModel>> getAll()
			{ 
				return ResponseEntity.ok(categoriaRepository.findAll());
			}
			
			@GetMapping("/{id}")
			public ResponseEntity <CategoriaModel> getById(@PathVariable Long id) 
			{ 
				return categoriaRepository.findById(id)
						.map(resp -> ResponseEntity.ok(resp))
						.orElse(ResponseEntity.notFound().build());
			}
			
			@GetMapping("/descricao/{descricao}")
			public ResponseEntity <List<CategoriaModel>> getByDescricao (@Valid @PathVariable String descricao)
			{
				return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
			}
			
			@PostMapping
			public ResponseEntity <CategoriaModel> postCategoria (@Valid @RequestBody CategoriaModel categoria)
			{
				return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
			}
			
			@PutMapping
			public ResponseEntity <CategoriaModel> putCategoria (@Valid @RequestBody CategoriaModel categoria)
			{
				return categoriaRepository.findById(categoria.getId())
						.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria)))   
						.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			}
			
			@DeleteMapping(path ={"/{id}"})
			public ResponseEntity<?> delete(@PathVariable Long id)
			{
				   return categoriaRepository.findById(id)
				           .map(record -> 
				           {
				               categoriaRepository.deleteById(id);
				               return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				           })
				           .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			}
}