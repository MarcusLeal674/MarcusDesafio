package com.projeto.marcus.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class UserInformationDTO implements Serializable {

	private static final long serialVersionUID = -6592094938723760700L;

	private String nome;

	private String cpf;

	private String telefone;

	private String dataDeNascimento;

	private String cep;

	private Long numeroEndereco;

	private String logradouro;

	private String complemento;

	private String bairro;

	private String localidade;

	private String uf;
	
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
