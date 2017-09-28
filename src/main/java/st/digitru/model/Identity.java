package st.digitru.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Identity {

	private String id;

	private int version;

	private int keyv;

	private Privacy privacy;

	public Identity() { }

	public Identity(String id, int version, int keyv, Privacy privacy) {
		this.id = id;
		this.version = version;
		this.keyv = keyv;
		this.privacy = privacy;
	}

	public String getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public int getKeyv() {
		return keyv;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + keyv;
		result = prime * result + ((privacy == null) ? 0 : privacy.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identity other = (Identity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keyv != other.keyv)
			return false;
		if (privacy == null) {
			if (other.privacy != null)
				return false;
		} else if (!privacy.equals(other.privacy))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Identity [id=" + id + ", version=" + version + ", keyv=" + keyv + ", privacy=" + privacy + "]";
	}

}
