package com.projeto.marcus.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projeto.marcus.dto.UserInformationDTO;
import com.projeto.marcus.objects.ObjectsTests;
import com.projeto.marcus.services.UserInformationService;

@ExtendWith(MockitoExtension.class)
public class UserInformationControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserInformationControllerTest.class);
	private static final String CPF = "982.456.809-90";
	
	@InjectMocks
	private UserInformationController userInformationController;
	
	@Mock
	private UserInformationService userInformationService;
	
	
	@DisplayName("This userInformationCreateTest method simulates the creation of User data to later save it in the Database")
	@Test
	public void userInformationCreateTest() throws Exception {
		log.info("Tests: Starting the userInformationCreateTest Method");
		
		ObjectsTests objectsTests = new ObjectsTests();
		
		doNothing().when(userInformationService).savingCustomerData(any(UserInformationDTO.class));
		
		ResponseEntity<UserInformationDTO> returnUserInformationCreate = userInformationController.userInformationCreate(objectsTests.objectUserInformationDTO());
		
		Assertions.assertEquals(HttpStatus.CREATED.value(), returnUserInformationCreate.getStatusCode().value());
		Assertions.assertEquals(CPF, returnUserInformationCreate.getBody().getCpf());		
	}
	
	@DisplayName("This findByUserByCpfTest method looks for User data through cpf")
	@Test
	public void findByUserByCpfTest() {
		log.info("Tests: Starting the findByUserByCpfTest Method");
		
		Mockito.when(userInformationService.findUserByCpf(anyString())).thenReturn(any());
		
		ResponseEntity<UserInformationDTO> returnUserInformation = userInformationController.findByUserByCpf(CPF);
		
		Assertions.assertEquals(HttpStatus.OK.value(), returnUserInformation.getStatusCode().value());
	}

}
