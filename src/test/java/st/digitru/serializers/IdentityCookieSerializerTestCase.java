package st.digitru.serializers;

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import st.digitru.error.SerializationException;
import st.digitru.model.Identity;
import st.digitru.model.Privacy;

@RunWith(JUnit4.class)
public class IdentityCookieSerializerTestCase {

	@Test
	public void simple() throws SerializationException {
		Identity i1 = new Identity(null, 2, 0, new Privacy(true));
		String serialized = new IdentityCookieSerializer().serialize(i1);
		Identity i2 = new IdentityCookieDeserializer().deserialize(serialized);
		Assert.assertNotNull(i2);
		Assert.assertEquals(i1, i2);
	}

	@Test
	public void optOutNullId() throws SerializationException {
		String encoded = "eyJpZCI6bnVsbCwia2V5diI6MCwicHJpdmFjeSI6eyJvcHRvdXQiOnRydWV9fQ%3D%3D";
		Identity i = new IdentityCookieDeserializer().deserialize(encoded);
		Assert.assertNotNull(i);
		Assert.assertEquals(new Identity(null, 0, 0, new Privacy(true)), i);
	}

	@Test(expected = SerializationException.class)
	public void malformed() throws SerializationException {
		new IdentityCookieDeserializer().deserialize("foobar");
	}

	@Test
	public void ignoredAdditionalFields() throws Exception {
		String encoded = "eyJpZCI6Imp5RUIyVUhTakxvPSIsInZlcnNpb24iOjIsImtleXYiOjQsImZvb2JhciI6NSwicHJpdmFjeSI6eyJvcHRvdXQiOmZhbHNlLCJ4eXoiOiJmb28ifX0%3D";
		Identity i = new IdentityCookieDeserializer().deserialize(encoded);
		Assert.assertEquals(
				new Identity("jyEB2UHSjLo=", 2, 4, new Privacy(false)),
				i);
	}

	@Test
	public void documentationExamples() throws SerializationException {
		// example 1
		String encoded = "eyJpZCI6Imp5RUIyVUhTakxvPSIsInZlcnNpb24iOjIsInByaXZhY3kiOnsib3B0b3V0IjpmYWxzZX19";
		Identity i = new IdentityCookieDeserializer().deserialize(encoded);
		Assert.assertEquals(
				new Identity("jyEB2UHSjLo=", 2, 0, new Privacy(false)),
				i);

		// example 2
		encoded = "eyJpZCI6bnVsbCwidmVyc2lvbiI6MiwicHJpdmFjeSI6eyJvcHRvdXQiOnRydWV9fQ%3D%3D";
		i = new IdentityCookieDeserializer().deserialize(encoded);
		Assert.assertEquals(
				new Identity(null, 2, 0, new Privacy(true)),
				i);

		// example 3
		encoded = "eyJpZCI6InFDajlwZlNiRXVnPSIsInZlcnNpb24iOjIsInByaXZhY3kiOnsib3B0b3V0IjpmYWxzZX19";
		i = new IdentityCookieDeserializer().deserialize(encoded);
		Assert.assertEquals(
				new Identity("qCj9pfSbEug=", 2, 0, new Privacy(false)),
				i);

		// other representations
		String encodedId = "jyEB2UHSjLo=";
		byte[] bytes = Base64.getDecoder().decode(encodedId);
		Assert.assertArrayEquals(
				new byte[] { -113, 33, 1, -39, 65, -46, -116, -70 },
				bytes);
		Assert.assertEquals(
				-5004393905660026481l,
				toLong(bytes));
		Assert.assertEquals("ba8cd241d901218f", Long.toHexString(toLong(bytes)));
	}

	private long toLong(byte[] by) {
		long value = 0;
		for (int i = 0; i < by.length; i++)
		{
		   value += ((long) by[i] & 0xffL) << (8 * i);
		}
		return value;
	}
}
