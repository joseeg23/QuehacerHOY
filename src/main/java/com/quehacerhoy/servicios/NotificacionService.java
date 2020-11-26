/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.servicios;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {
  
    
    @Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void enviar(String cuerpo,String titulo,String mail) {
	
		SimpleMailMessage mensaje = new SimpleMailMessage();
		
		mensaje.setTo(mail);
		mensaje.setFrom("quehacerhoy23@gmail.com");
		mensaje.setSubject(titulo);
		mensaje.setText(cuerpo);
		
		mailSender.send(mensaje);
		
	} 
}
