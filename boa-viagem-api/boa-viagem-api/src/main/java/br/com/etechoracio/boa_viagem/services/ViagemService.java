package br.com.etechoracio.boa_viagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.entity.Viagem;
import br.com.etechoracio.boa_viagem.repository.ViagemRepository;

public class ViagemService {

	@Autowired
	private ViagemRepository repository;
	
	public List<Viagem> listarTodos()
	{
		return repository.findAll();
	}
	
	public Viagem buscarPorID(Long id) 
	{
		return repository.findById(id).orElse(null);
	}
	
	public  boolean excluir(Long id) {
		boolean existe = repository.existsById(id);
		
		if(existe) 
		{
			repository.deleteById(id);
		}
		return null;
	}
	
	public Viagem inserir(Viagem obj)
	{
		return repository.save(obj);
	}
	
	public Optional<Viagem> atualizar(Long id, Viagem viagem) 
	{
		boolean existe = repository.existsById(id);
		
		if(!existe) 
		{
			return Optional.empty(); 
		}
		
		return Optional.of(repository.save(viagem));
	}
	
}
