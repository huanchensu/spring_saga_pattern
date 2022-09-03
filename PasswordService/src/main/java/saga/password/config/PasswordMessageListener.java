package saga.password.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saga.password.iservice.PasswordIService;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked
 * with the message as the parameter.
 */
@Component
public class PasswordMessageListener {

	private static final Logger log = LogManager.getLogger(PasswordMessageListener.class);
	
	@Autowired
	private PasswordIService passwordIService;
	public void receiveMessage(String message) {
		
		log.info("[PasswordService 接收到Queue資料] : <" + message + ">");
		passwordIService.createPassword(message);
		
	}
}
