package com.projeto.marcus.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import java.util.Optional;

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
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.projeto.marcus.dto.UserInformationDTO;
import com.projeto.marcus.model.UserInformation;
import com.projeto.marcus.objects.ObjectsTests;
import com.projeto.marcus.repository.UserInformationRepository;

@ExtendWith(MockitoExtension.class)
public class UserInformationServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserInformationServiceTest.class);
	private static final String CPF = "982.456.809-90";
	
	@InjectMocks
	private UserInformationService userInformationService;
	
	@Mock
	private RabbitTemplate rabbitTemplateUserInformation;
	
	@Mock
	private UserInformationRepository userInformationRepository;
	
	
	@DisplayName("This savingCustomerDataTest method simulates the saving of User data to later save it in the Database")
	@Test
	public void savingCustomerDataTest() throws Exception {
		log.info("Tests: Starting the savingCustomerDataTest Method");
		
		ObjectsTests objectsTests = new ObjectsTests();
		
		doNothing().when(rabbitTemplateUserInformation).convertAndSend(anyString(), any(UserInformation.class));
		Mockito.when(userInformationRepository.save(any())).thenReturn(any());
		
		userInformationService.savingCustomerData(objectsTests.objectUserInformationDTO());	
		
		Assertions.assertEquals(CPF, objectsTests.objectUserInformationDTO().getCpf());
	}
	
	@DisplayName("This findUserByCpf method looks for User data through cpf")
	@Test
	public void findUserByCpf() {
		log.info("Tests: Starting the findUserByCpf Method");
		
		ObjectsTests objectsTests = new ObjectsTests();
		Optional<UserInformation> optinonal = Optional.of(objectsTests.objectUserInformation());
		
		Mockito.when(userInformationRepository.findByCpf(anyString())).thenReturn(optinonal);
		
		UserInformationDTO UserInformationDTO = userInformationService.findUserByCpf(CPF);	
		
		Assertions.assertEquals(CPF, UserInformationDTO.getCpf());
	}

}
