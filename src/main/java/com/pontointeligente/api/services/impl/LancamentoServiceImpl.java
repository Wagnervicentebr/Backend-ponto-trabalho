package com.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pontointeligente.api.entities.Lancamento;
import com.pontointeligente.api.repositories.LancamentoRepository;
import com.pontointeligente.api.services.LancamentoService;

public class LancamentoServiceImpl implements LancamentoService {
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	LancamentoRepository lancamentoRepository;
	
	@Override
	public Page<Lancamento> buscarLancamentoPorIdFuncionarioPageable(Long id, PageRequest pageRequest) {
		log.info("Buscando lancamento de um funcionario pelo id com paginação {}", id, pageRequest);
		return lancamentoRepository.findByFuncionarioId(id, pageRequest);
	}

	@Override
	public Optional<Lancamento> BuscarLancamentoPorId(Long id) {
		log.info("Buscando lancamento pelo id do lançamento {}", id);
		return lancamentoRepository.findById(id);
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Salvando um novo lancamento {}", lancamento);
		return lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("removendo um lancamento por id {}", id);
		lancamentoRepository.deleteById(id);
	}

}
