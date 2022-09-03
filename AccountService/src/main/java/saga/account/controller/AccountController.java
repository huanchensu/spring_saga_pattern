package saga.account.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import saga.account.service.AccountService;

@RestController
public class AccountController {

	private static final Logger log = LogManager.getLogger(AccountController.class);

	@Autowired
	AccountService accountService;
	
	@RequestMapping("/createAccount/{name}")
	public String createAccount(@PathVariable String name) {
		log.info("[接收新增帳戶請求] 帳戶姓名:{}",name);
		accountService.createAccount(name);
		return "OK";
	}
}
