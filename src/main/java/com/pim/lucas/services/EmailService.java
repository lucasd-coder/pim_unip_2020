package com.pim.lucas.services;

import org.springframework.mail.SimpleMailMessage;

import com.pim.lucas.domain.Cliente;
import com.pim.lucas.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
