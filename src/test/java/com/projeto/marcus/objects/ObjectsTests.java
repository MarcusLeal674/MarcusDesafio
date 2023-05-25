package com.projeto.marcus.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projeto.marcus.dto.UserInformationDTO;
import com.projeto.marcus.model.UserInformation;

public class ObjectsTests {
	
	private static final Logger log = LoggerFactory.getLogger(ObjectsTests.class);
	
	
	public UserInformationDTO objectUserInformationDTO() {
		log.info("Class ObjectsTests: Starting the objectUserInformationDTO Method");
		
		UserInformationDTO userInformationDTO = new UserInformationDTO();
		userInformationDTO.setBairro("bairroTest");
		userInformationDTO.setCep("53444-430");
		userInformationDTO.setComplemento("complementoTest");
		userInformationDTO.setCpf("982.456.809-90");
		userInformationDTO.setDataDeNascimento("25/05/2023");
		userInformationDTO.setLocalidade("localidadeTest");
		userInformationDTO.setLogradouro("logradouroTest");
		userInformationDTO.setNome("nomeTest");
		userInformationDTO.setNumeroEndereco(15L);
		userInformationDTO.setTelefone("(11) 98874-4590");
		userInformationDTO.setUf("SP");

		return userInformationDTO;
	}
	
	public UserInformation objectUserInformation() {
		log.info("Class ObjectsTests: Starting the objectUserInformationDTO Method");
		
		UserInformation userInformation = new UserInformation();
		userInformation.setId(1L);
		userInformation.setBairro("bairroTest");
		userInformation.setCep("53444-430");
		userInformation.setComplemento("complementoTest");
		userInformation.setCpf("982.456.809-90");
		userInformation.setDataDeNascimento("25/05/2023");
		userInformation.setLocalidade("localidadeTest");
		userInformation.setLogradouro("logradouroTest");
		userInformation.setNome("nomeTest");
		userInformation.setNumeroEndereco(15L);
		userInformation.setTelefone("(11) 98874-4590");
		userInformation.setUf("SP");
		
		return userInformation;
	}

}
