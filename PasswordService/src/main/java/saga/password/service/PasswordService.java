package saga.password.service;

import org.springframework.beans.factory.annotation.Autowired;

import saga.password.data.Message;
import saga.password.data.MessageRepository;
import saga.password.data.Password;
import saga.password.data.PasswordRepository;
import saga.password.iservice.PasswordIService;

public class PasswordService implements PasswordIService {

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private PasswordRepository passwordRepository;
	
	public void createPassword(String message) {
		String[] s = message.split(",");
		Message ms = new Message();
		ms.setDestination("accountQueue");
		ms.setAction("passwordReply");
		ms.setRequest(s[2]);
		
		if (s[2].equalsIgnoreCase("bruce")||s[2].equalsIgnoreCase("mike")) {
			ms.setResult("Fail");
		}else {
			Password ps = new Password();
			ps.setName(s[2]);
			ps.setPassword("666666");
			passwordRepository.save(ps);
			ms.setResult("OK");;
		}
		saveResultToMessageTable(ms);
		
	};
	private void saveResultToMessageTable(Message ms) {
		messageRepository.save(ms);
	}
	
}