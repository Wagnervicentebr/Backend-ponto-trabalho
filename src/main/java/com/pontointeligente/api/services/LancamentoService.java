package com.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pontointeligente.api.entities.Lancamento;

public interface LancamentoService {

	

	Page<Lancamento> buscarLancamentoPorIdFuncionarioPageable(Long id, PageRequest pageRequest);
	
	Optional<Lancamento> BuscarLancamentoPorId(Long id);
	
	Lancamento persistir(Lancamento lancamento);
	
	void remover(Long id);
	
	
}
