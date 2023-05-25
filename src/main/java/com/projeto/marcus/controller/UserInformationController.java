package com.projeto.marcus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.marcus.dto.UserInformationDTO;
import com.projeto.marcus.services.UserInformationService;

@RestController
@RequestMapping(value = "/api/informacaocliente")
public class UserInformationController {
	
	private static final Logger log = LoggerFactory.getLogger(UserInformationController.class);
			
	@Autowired
	private UserInformationService userInformationService;
	
	
	@PostMapping
	public ResponseEntity<UserInformationDTO> userInformationCreate(@RequestBody UserInformationDTO userInformationDTO) throws Exception {
		log.info("Controller: Starting the userInformationCreate Method");		
		
		this.userInformationService.savingCustomerData(userInformationDTO);
		
		return new ResponseEntity<UserInformationDTO>(userInformationDTO, HttpStatus.CREATED);
	}
		
	@GetMapping
	@ResponseBody
	public ResponseEntity<UserInformationDTO> findByUserByCpf(@RequestParam String cpf) {
		log.info("Controller: Starting the findByUserByCpf Method");		
		
		UserInformationDTO objectUserInformationDTO = userInformationService.findUserByCpf(cpf);
		
		return new ResponseEntity<UserInformationDTO>(objectUserInformationDTO, HttpStatus.OK);
	}
		

}
