package com.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontointeligente.api.entities.Funcionario;
import com.pontointeligente.api.repositories.FuncionarioRepository;
import com.pontointeligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Override
	public Optional<Funcionario> buscarFuncionarioPorCpf(String cpf) {
		log.info("Buscando um funcionario pelo CPF {}", cpf);
		return Optional.ofNullable(funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarFuncionarioPorEmail(String email) {
		log.info("Buscando um funcionario pelo EMAIL {}", email);
		return Optional.ofNullable(funcionarioRepository.findByEmail(email));
	}


	@Override
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Salvando um novo funcionario", funcionario);
		return funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
		log.info("Buscando um funcionario pelo ID {}", id);
		return funcionarioRepository.findById(id);
	}


}
