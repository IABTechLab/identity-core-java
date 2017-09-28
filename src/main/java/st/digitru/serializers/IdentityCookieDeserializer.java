package st.digitru.serializers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import st.digitru.error.SerializationException;
import st.digitru.model.Identity;

public class IdentityCookieDeserializer {

	private ObjectMapper mapper = new ObjectMapper();

	public Identity deserialize(String value) throws SerializationException {
		try {
			byte[] bytes = Base64.getDecoder().decode(URLDecoder.decode(value, "UTF-8"));
			Identity i = mapper.readValue(bytes, Identity.class);
			return i;
		} catch (UnsupportedEncodingException e) {
			throw new SerializationException(e);
		} catch (JsonParseException e) {
			throw new SerializationException(e);
		} catch (JsonMappingException e) {
			throw new SerializationException(e);
		} catch (IOException e) {
			throw new SerializationException(e);
		} finally { }
	}
}
