package saga.cdcservice.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import saga.cdcservice.data.Message;
import saga.cdcservice.data.MessageRepository;

@Component
public class MessageTableListsener implements ApplicationRunner {

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private QueueContorllService queueContorllService;
	
	private static final Logger log = LogManager.getLogger(MessageTableListsener.class);
	@Override
	public void run(ApplicationArguments args) throws Exception {

		while (true) {
			Thread.sleep(1000);

			List<Message> messageList = (List<Message>) messageRepository.findAll();

			if (messageList.isEmpty()) {
				continue;
			}

			for (Message ms : messageList) {
				log.info("[偵測DB messageTable有資料]  將資料pub至:{}",ms.getDestination());
				if (ms.getDestination().equals("passwordQueue")) {
					queueContorllService.sendPassWordMessage(ms.getDestination().concat(",").concat(ms.getAction())
							.concat(",").concat(ms.getRequest()));
				}
				if (ms.getDestination().equals("accountQueue")) {
					queueContorllService.sendAccountMessage(ms.getDestination().concat(",").concat(ms.getAction()).concat(",")
							.concat(ms.getRequest()).concat(",").concat(ms.getResult()));
				}
				messageRepository.delete(ms);
			}
		}

	}

}