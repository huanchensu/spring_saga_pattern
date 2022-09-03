package saga.cdcservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class QueueContorllService implements QueueContorllIService {

    private static final Logger log = LoggerFactory.getLogger(QueueContorllService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendAccountMessage(String words) {
       
        log.info("Sending the index request through queue message:{}",words);
        rabbitTemplate.convertAndSend("accountQueue", words);
        //rabbitTemplate.convertAndSend("accountQueue", actionmap);
    }
    
    @Override
    public void sendPassWordMessage(String words) {
        
        log.info("Sending the index request through queue message:{}",words);
        rabbitTemplate.convertAndSend("passwordQueue", words);
        //rabbitTemplate.convertAndSend("accountQueue", actionmap);
    }
}
