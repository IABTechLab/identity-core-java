package st.digitru.producer;

import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

import st.digitru.model.Identity;
import st.digitru.model.Privacy;

public class MultithreadIdentityProducer implements IdentityProducer {

	private Base64.Encoder b64;

	private String producer;

	public MultithreadIdentityProducer(String producer) {
		this.b64 = Base64.getEncoder();
		this.producer = producer;
	}

	public MultithreadIdentityProducer() {
		this(null);
	}

	public Identity create() {
		byte[] bytes = new byte[8];
		ThreadLocalRandom random = ThreadLocalRandom.current();
		random.nextBytes(bytes);
		String id = b64.encodeToString(bytes);
		return new Identity(id, Constants.CURRENT_ID_VERSION, producer, null, new Privacy(false));
	}
}
