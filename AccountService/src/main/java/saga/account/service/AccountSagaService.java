package saga.account.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saga.account.data.Account;
import saga.account.data.AccountRepository;
import saga.account.iservice.AccountSagaIService;

@Service
public class AccountSagaService implements AccountSagaIService {

	@Autowired
	private AccountRepository accountRepository;
	private static final Logger log = LogManager.getLogger(AccountSagaService.class);

	public void passwordServiceReply(String message) {
		String[] s = message.split(",");

		if (s[3].equalsIgnoreCase("FAIL")) {
			doRollback(s[2]);
			log.info("name:{} , 新增帳戶失敗", s[2]);
		} else {
			log.info("name:{} , 完成新增帳戶 ", s[2]);
		}
		
		
	}

	private void doRollback(String name) {
		Account ac = accountRepository.findByName(name);
		if (ac == null) {
			return;
		}
		accountRepository.delete(ac);
	}
}
