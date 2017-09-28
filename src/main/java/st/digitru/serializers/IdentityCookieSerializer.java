package st.digitru.serializers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import st.digitru.model.Identity;

public class IdentityCookieSerializer {

	private ObjectMapper mapper = new ObjectMapper();

	public String serialize(Identity identity) {
		try {
			byte[] raw = mapper.writeValueAsBytes(identity);
			return URLEncoder.encode(Base64.getEncoder().encodeToString(raw), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
