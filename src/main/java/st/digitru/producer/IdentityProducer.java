package st.digitru.producer;

import java.util.Base64;
import java.util.Random;

import st.digitru.model.Identity;
import st.digitru.model.Privacy;

public class IdentityProducer {

	private Random random;

	private Base64.Encoder b64;

	public IdentityProducer(Random random) {
		this.random = random;
		this.b64 = Base64.getEncoder();
	}

	public IdentityProducer() {
		this(new Random());
	}

	public Identity create() {
		byte[] bytes = new byte[8];
		random.nextBytes(bytes);
		String id = b64.encodeToString(bytes);
		return new Identity(id, Constants.CURRENT_ID_VERSION, 0, new Privacy(false));
	}
}
