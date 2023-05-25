package com.projeto.marcus.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.projeto.marcus.constants.UserInformationRabbitMQConstants;
import com.projeto.marcus.dto.UserInformationDTO;
import com.projeto.marcus.model.UserInformation;
import com.projeto.marcus.repository.UserInformationRepository;
import com.projeto.marcus.utilitarios.UserInformationUtil;

@Service
public class UserInformationService {
	
	private static final Logger log = LoggerFactory.getLogger(UserInformationService.class);

	@Autowired
	private RabbitTemplate rabbitTemplateUserInformation;
	
	@Autowired
	private UserInformationRepository userInformationRepository;
	
	/**
	 * este método irá salvar as informações do cliente
	 * @param userInformation
	 * @throws Exception 
	 */
	public void savingCustomerData(UserInformationDTO userInformationDTO) throws Exception {
		log.info("Service: Starting the savingCustomerData Method");
		
		UserInformationDTO userInformationObject = this.getAddress(userInformationDTO);
		userInformationObject.setNome(userInformationDTO.getNome());
		userInformationObject.setCpf(userInformationDTO.getCpf());
		userInformationObject.setTelefone(userInformationDTO.getTelefone());
		userInformationObject.setNumeroEndereco(userInformationDTO.getNumeroEndereco());
		userInformationObject.setDataDeNascimento(userInformationDTO.getDataDeNascimento());
		
		UserInformation userInformation = this.mapperUserInformation(userInformationObject);
		
		userInformationRepository.save(userInformation);
		this.sendMessage(userInformation);
	}
	
	public UserInformationDTO findUserByCpf(String cpf) {
		UserInformation userInformation = userInformationRepository.findByCpf(cpf).get();
		UserInformationDTO userInformationDTO = this.mapperUserInformationDTO(userInformation);
		
		return userInformationDTO;
	}
	
	/**
	 * Este método irá enviar o Objeto CustomerRequest para ficar na fila do RabbitMQ
	 * @param nomeFila
	 * @param userInformation
	 */
	private void sendMessage(UserInformation userInformation) {
		log.info("Service: Starting the sendMessage Method");
		
		this.rabbitTemplateUserInformation.convertAndSend(UserInformationRabbitMQConstants.CUSTOMER_INFORMATION, userInformation);
	}
	
	/**
	 * Convertendo UserInformationDTO para UserInformation
	 * @param userInformationDTO
	 * @return
	 */
	private UserInformation mapperUserInformation(UserInformationDTO userInformationDTO) {
		log.info("Service: Starting the mapperUserInformation Method private");
		
		ModelMapper modelMapper = new ModelMapper();
		UserInformation userInformation = modelMapper.map(userInformationDTO, UserInformation.class);
		
		return userInformation;
	}
	
	/**
	 * Convertendo UserInformation para UserInformationDTO
	 * @param userInformationDTO
	 * @return
	 */
	private UserInformationDTO mapperUserInformationDTO(UserInformation userInformation) {
		log.info("Service: Starting the mapperUserInformationDTO Method private");
		
		ModelMapper modelMapper = new ModelMapper();
		UserInformationDTO userInformationDTO = modelMapper.map(userInformation, UserInformationDTO.class);
		
		return userInformationDTO;
	}
	
	/**
	 * Consultando o endereço do usuário via cep
	 * @param cep
	 * @return
	 * @throws Exception
	 */
	private UserInformationDTO getAddress(UserInformationDTO userInformationDTO) throws Exception {
		log.info("Service: Starting the getAddress Method private");
		
        String enderecoURL = "https://viacep.com.br/ws/" + userInformationDTO.getCep() + "/json/";
        URL url = new URL(enderecoURL);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader((conexao.getInputStream()), "utf-8"));

            String convertJsonString = UserInformationUtil.converteUserInformationJsonEmString(buff);
            Gson gson = new Gson();
            UserInformationDTO returnUserInformationDTO = gson.fromJson(convertJsonString, UserInformationDTO.class);
            
            return returnUserInformationDTO;

        } catch (Exception msgErro) {
            throw  new Exception("Connection error:[" + conexao.getResponseCode() + "]. " + msgErro.toString()); 
        }

    }
	
}

