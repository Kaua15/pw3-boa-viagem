package br.com.etechoracio.boa_viagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;

public class GastoService {

	@Autowired
	private GastoRepository repository;
	
	public List<Gasto> listarTodos()
	{
		return repository.findAll();

	}
	
	public Gasto buscarPorID(Long id) 
	{
		Optional<Gasto> existe = repository.findById(id);
		return existe.isPresent() ? existe.get() : null;
	}
	
	
	public void excluir(Long id) 
	{
		boolean existe = repository.existsById(id);
		
		if(existe) 
		{
			repository.deleteById(id);
		} 
	}
	
	public Gasto inserir(Gasto obj)
	{
		return repository.save(obj);
	}
	

	public Gasto atualizar(Long id, Gasto gasto) 
	{
		boolean existe = repository.existsById(id);
		if(!existe) 
		{
			return null); 
		}
		
		return repository.save(gasto);
	}
}
