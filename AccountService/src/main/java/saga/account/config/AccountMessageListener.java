package saga.account.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saga.account.service.AccountSagaService;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked
 * with the message as the parameter.
 */
@Component
public class AccountMessageListener {

	@Autowired
	AccountSagaService accountSagaService;

	private static final Logger log = LogManager.getLogger(AccountMessageListener.class);

	public void receiveMessage(String message) {
		log.info("[receiveMessage 接收到Queue資料] : <" + message + ">");
		accountSagaService.passwordServiceReply(message);

		// log.info("Message {}",message);
	}
}
