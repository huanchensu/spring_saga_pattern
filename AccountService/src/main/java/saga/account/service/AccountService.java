package saga.account.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saga.account.data.Account;
import saga.account.data.AccountRepository;
import saga.account.data.Message;
import saga.account.data.MessageRepository;
import saga.account.iservice.AccountIService;

@Service
public class AccountService implements AccountIService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private MessageRepository messageRepository;
	private static final Logger log = LogManager.getLogger(AccountService.class);
	public void createAccount(String name) {
		Account ac = new Account();
		ac.setAccount(name);
		accountRepository.save(ac);
		log.info("[將{}帳戶新增至資料庫完成]",name);
		
		Message ms = new Message();
		ms.setDestination("passwordQueue");
		ms.setAction("createPassword");
		ms.setRequest(name);
		messageRepository.save(ms);
		
		
	}
	
}
