package com.pontointeligente.api.services;

import java.util.Optional;

import com.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	/**
	 *Retorna uma empresa dado um CNPJ.
	 *
	 * @param cnpj
	 * @return Optional<Empresa>
	 * 
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);  
	
	/**
	 * 
	 * Salva uma nova empresa na base de dados
	 * 
	 * @param Empresa
	 * @retrun Empresa
	 * 
	 */
	Empresa persistir(Empresa empresa);
	
	
}
