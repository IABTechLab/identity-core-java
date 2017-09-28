package st.digitru.serializers;

import java.net.URLDecoder;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import st.digitru.model.Identity;

public class IdentityCookieDeserializer {

	private ObjectMapper mapper = new ObjectMapper();

	public Identity deserialize(String value) {
		try {
			byte[] bytes = Base64.getDecoder().decode(URLDecoder.decode(value, "UTF-8"));
			Identity i = mapper.readValue(bytes, Identity.class);
			return i;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally { }
	}
}
