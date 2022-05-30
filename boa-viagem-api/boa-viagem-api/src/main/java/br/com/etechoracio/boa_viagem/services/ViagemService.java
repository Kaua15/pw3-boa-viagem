package br.com.etechoracio.boa_viagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.entity.Viagem;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;
import br.com.etechoracio.boa_viagem.repository.ViagemRepository;

@Service
public class ViagemService {

	@Autowired
	private ViagemRepository repository;
	
	@Autowired
	private GastoRepository gastoRepository;
	
	public List<Viagem> listarTodos()
	{
		return repository.findAll();
	}
	
	public Optional<Viagem> buscarPorID(Long id) 
	{
		return repository.findById(id);
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
	
	public boolean excluir(Long id) {
		boolean existe = repository.existsById(id);
		if(!existe) 
		{
			return existe;
		}
		
		List<Gasto> gastos = gastoRepository.findByViagemId(id);
		if(!gastos.isEmpty())
		{
			gastoRepository.deleteAll();
		}
		
		repository.deleteById(id);
		return existe;
	}
}
