package st.digitru.producer;

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;

import st.digitru.model.Identity;
import st.digitru.model.Privacy;

public abstract class BaseIdentityProducerTestCase {

	protected static final String TEST_PRODUCER = "test";

	protected IdentityProducer producer;

	public BaseIdentityProducerTestCase(IdentityProducer producer) {
		this.producer = producer;
	}

	@Test
	public void basics() {
		Identity i = producer.create();
		Assert.assertNotNull(i);
		// IDs should be 8 bytes long
		Assert.assertEquals(8, Base64.getDecoder().decode(i.getId()).length);
		// keyv is only relevant for encrypted IDs
		Assert.assertEquals(null, i.getKeyv());
		// ID version is currently 2
		Assert.assertEquals(2, i.getVersion());
		// producer field should be set
		Assert.assertEquals(TEST_PRODUCER, i.getProducer());
		// privacy.optout defaults to false
		Assert.assertEquals(false, i.getPrivacy().getOptout());
		Assert.assertEquals(
				new Identity(i.getId(), 2, TEST_PRODUCER, null, new Privacy(false)),
				i);
	}
}
