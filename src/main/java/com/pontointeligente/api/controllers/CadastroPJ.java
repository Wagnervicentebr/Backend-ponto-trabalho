package com.pontointeligente.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontointeligente.api.entities.Empresa;
import com.pontointeligente.api.entities.Funcionario;
import com.pontointeligente.api.entities.dtos.CadastroPJDTO;
import com.pontointeligente.api.enuns.PerfilEnum;
import com.pontointeligente.api.response.Response;
import com.pontointeligente.api.services.EmpresaService;
import com.pontointeligente.api.services.FuncionarioService;
import com.pontointeligente.api.utils.SenhaUtil;

@RestController
@RequestMapping("api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJ {
	private static final Logger LOG = LoggerFactory.getLogger(CadastroPJ.class);
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	EmpresaService empresaService;
	
	@PostMapping
	public ResponseEntity<Response<CadastroPJDTO>> CadastroPj(@Valid @RequestBody CadastroPJDTO cadastroPjDTO, 
			BindingResult result) throws NoSuchAlgorithmException {
		
		LOG.info("Cadastro PJ {}", cadastroPjDTO.toString());
		Response<CadastroPJDTO> response = new Response<CadastroPJDTO>();
		
		ValidarDadosExistentes(cadastroPjDTO, result);
		Empresa empresa = this.converterDtoToEmpresa(cadastroPjDTO);
		Funcionario funcionario = this.converterDtoToFuncionario(cadastroPjDTO);
		
		if (result.hasErrors()) {
			LOG.info("Erro validando dados de cadastro PJ {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.persistir(funcionario);
		
		response.setData(this.converterCadastroPJToDTO(funcionario));
		return ResponseEntity.ok(response);
	}

	private CadastroPJDTO converterCadastroPJToDTO(Funcionario funcionario) {
		CadastroPJDTO cadastroDTO = new CadastroPJDTO();
		cadastroDTO.setCnpj(funcionario.getEmpresa().getCnpj());
		cadastroDTO.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		cadastroDTO.setCpf(funcionario.getCpf());
		cadastroDTO.setEmail(funcionario.getEmail());
		cadastroDTO.setNome(funcionario.getNome());
		
		return cadastroDTO;
	}

	private Funcionario converterDtoToFuncionario(@Valid CadastroPJDTO cadastroPjDTO) {
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(cadastroPjDTO.getCpf());
		funcionario.setEmail(cadastroPjDTO.getEmail());
		funcionario.setNome(cadastroPjDTO.getNome());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(SenhaUtil.gerarBcrypt(cadastroPjDTO.getSenha()));
		
		return funcionario;
	}

	private Empresa converterDtoToEmpresa(@Valid CadastroPJDTO cadastroPjDTO) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPjDTO.getCnpj());
		empresa.setRazaoSocial(cadastroPjDTO.getRazaoSocial());
		
		return empresa;
	}

	private void ValidarDadosExistentes(@Valid CadastroPJDTO cadastroPjDTO, BindingResult result) {
		this.empresaService.buscarPorCnpj(cadastroPjDTO.getCnpj())
			.ifPresent(empresa -> result.addError(new ObjectError("Empresa", "CNPJ já existente")));
		
		this.funcionarioService.buscarFuncionarioPorCpf(cadastroPjDTO.getCpf())
			.ifPresent(funcionario -> result.addError(new ObjectError("Funcionario", "CPF já existe")));
		
		this.funcionarioService.buscarFuncionarioPorEmail(cadastroPjDTO.getEmail())
			.ifPresent(funcionario -> result.addError(new ObjectError("Funcionario", "Email já existe")));
	}
	
}
