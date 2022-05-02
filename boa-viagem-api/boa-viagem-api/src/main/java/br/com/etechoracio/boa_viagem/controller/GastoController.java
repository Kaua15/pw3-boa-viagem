package br.com.etechoracio.boa_viagem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;
import java.util.List;
import java.util.Optional;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/gastos")
public class GastoController {
	@Autowired
	private GastoRepository repository;
	
	@GetMapping
	public List<Gasto> listarTodos()
	{
		return repository.findAll();

	}
	//fazendo metodo de requisição da tabela gasto por id
	@GetMapping("/{id}")
	public ResponseEntity<Gasto> buscarPorID(@PathVariable Long id) 
	{
		Optional<Gasto> existe = repository.findById(id);
		if(existe.isPresent())
		{
			return ResponseEntity.ok(existe.get());
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) 
	{
		boolean existe = repository.existsById(id);
		
		if(existe) 
		{
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} 
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Gasto> inserir(@RequestBody Gasto obj)
	{
		repository.save(obj);
		return ResponseEntity.ok(obj);
	}
}
