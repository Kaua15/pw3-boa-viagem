package br.com.etechoracio.boa_viagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.entity.Viagem;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;
import br.com.etechoracio.boa_viagem.repository.ViagemRepository;

@Service
public class GastoService {

	
	@Autowired
	private GastoRepository repository;
	
	@Autowired
	private ViagemRepository viagemRepo;
	
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
		Optional<Viagem> existe = viagemRepo.findById(obj.getViagem().getId());
		if(!existe.isPresent()) {
			throw new RuntimeException("Viagem não encontrada.");
		}	
		if(existe.get().getSaida() != null) {
			throw new RuntimeException("Data de viagem já foi encerrada.");
		}
		
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
