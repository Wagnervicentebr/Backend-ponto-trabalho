package com.pontointeligente.api.services;

import java.util.Optional;

import com.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {

	/**
	 *	Retorna uma funcionario dado um CPF.
	 *
	 * @param cpf
	 * @return Optional<Funcionario>
	 * 
	 */
	Optional<Funcionario> buscarFuncionarioPorCpf(String cpf);

	/**
	 *	Retorna uma funcionario dado um EMAIL.
	 *
	 * @param cpf
	 * @return Optional<Funcionario>
	 * 
	 */
	Optional<Funcionario> buscarFuncionarioPorEmail(String email);
	
	
	/**
	 *	Retorna uma funcionario dado um ID.
	 *
	 * @param id
	 * @return Optional<Funcionario>
	 * 
	 */
	Optional<Funcionario> buscarFuncionarioPorId(Long id);
	
	/**
	 *	Salva um novo funcionario na base.
	 *
	 * @param funcionario
	 * @return Funcionario
	 * 
	 */
	Funcionario persistir(Funcionario funcionario); 
}
