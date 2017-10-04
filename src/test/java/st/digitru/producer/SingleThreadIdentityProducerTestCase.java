package st.digitru.producer;

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import st.digitru.model.Identity;
import st.digitru.model.Privacy;

@RunWith(JUnit4.class)
public class SingleThreadIdentityProducerTestCase {

	protected IdentityProducer producer;

	public SingleThreadIdentityProducerTestCase(IdentityProducer producer) {
		this.producer = producer;
	}

	public SingleThreadIdentityProducerTestCase() {
		this(new SingleThreadIdentityProducer());
	}

	@Test
	public void basics() {
		Identity i = producer.create();
		Assert.assertNotNull(i);
		// IDs should be 8 bytes long
		Assert.assertEquals(8, Base64.getDecoder().decode(i.getId()).length);
		// keyv is only relevant for encrypted IDs
		Assert.assertEquals(0, i.getKeyv());
		// ID version is currently 2
		Assert.assertEquals(2, i.getVersion());
		// privacy.optout defaults to false
		Assert.assertEquals(false, i.getPrivacy().getOptout());
		Assert.assertEquals(
				new Identity(i.getId(), 2, 0, new Privacy(false)),
				i);
	}
}
