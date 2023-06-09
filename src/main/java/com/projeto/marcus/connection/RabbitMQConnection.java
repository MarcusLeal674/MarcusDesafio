package com.projeto.marcus.connection;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.projeto.marcus.constants.UserInformationRabbitMQConstants;

@Component
public class RabbitMQConnection {

	private static final String NAME_EXCHANGE = "amq.direct";
	private AmqpAdmin amqpAdmin;	
	
	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	private Queue queue(String nameQueue) {
		return new Queue(nameQueue, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(NAME_EXCHANGE);
	}
	
	private Binding relationship(Queue queue, DirectExchange directExchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
	}
	
	/**
	 * Com a anotação PostConstruct assim que a Classe for instanciada pelo spring boot
	 * esse método irá ser chamado
	 * @param nameQueue
	 */
	@PostConstruct
	private void toAddNameQueue() { 
		
		Queue customerRequest = this.queue(UserInformationRabbitMQConstants.CUSTOMER_INFORMATION);
		
		DirectExchange queueSwitch = this.directExchange();
		
		Binding connectioncustomerRequest = this.relationship(customerRequest, queueSwitch);
		
		//Criação das filas do RabbitMQ
		this.amqpAdmin.declareQueue(customerRequest);
		
		this.amqpAdmin.declareExchange(queueSwitch);
		
		this.amqpAdmin.declareBinding(connectioncustomerRequest);	
	}
}
