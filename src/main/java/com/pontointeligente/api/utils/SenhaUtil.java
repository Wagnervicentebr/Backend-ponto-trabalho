package com.pontointeligente.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {
	private static final Logger LOG = LoggerFactory.getLogger(SenhaUtil.class);
	
	public SenhaUtil() {

	}
	
	public static String gerarBcrypt(String senha) {
		if (senha == null) {
			return senha;
		}
		
		LOG.info("Criando o HASH da senha do usuario {}");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(senha);
	}
}
