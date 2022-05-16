package br.com.etechoracio.boa_viagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;

@Service
public class GastoService {

	
	@Autowired
	private GastoRepository repository;
	
	public List<Gasto> listarTodos()
	{
		return repository.findAll();

	}
	
	public Optional<Gasto> buscarPorID(Long id) 
	{
		return repository.findById(id);
	}
	
	
	public boolean excluir(Long id) 
	{
		boolean existe = repository.existsById(id);
		
		if(existe) 
		{
			repository.findById(id);
		}
		return existe; 
	}
	
	public Gasto inserir(Gasto obj)
	{
		return repository.save(obj);
	}
	

	public Optional<Gasto> atualizar(Long id, Gasto gasto) 
	{
		boolean existe = repository.existsById(id);
		if(!existe) 
		{
			return Optional.empty();
		}
		
		return Optional.of(repository.save(gasto));
	}
}
