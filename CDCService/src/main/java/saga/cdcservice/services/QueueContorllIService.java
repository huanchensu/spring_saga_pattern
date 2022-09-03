package saga.cdcservice.services;

/**
 * Created by jt on 1/10/17.
 */
public interface QueueContorllIService {


	void sendPassWordMessage(String words);

	void sendAccountMessage(String words);
}
