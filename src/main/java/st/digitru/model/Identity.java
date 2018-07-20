package st.digitru.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Identity {

	private String id;

	private int version;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String producer;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer keyv;

	private Privacy privacy;

	public Identity() { }

	public Identity(String id, int version, String producer, Integer keyv, Privacy privacy) {
		this.id = id;
		this.version = version;
		this.producer = producer;
		this.keyv = keyv;
		this.privacy = privacy;
	}

	public String getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getProducer() {
		return producer;
	}

	public Integer getKeyv() {
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
		result = prime * result + ((keyv == null) ? 0 : keyv.hashCode());
		result = prime * result + ((privacy == null) ? 0 : privacy.hashCode());
		result = prime * result + ((producer == null) ? 0 : producer.hashCode());
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
		if (keyv == null) {
			if (other.keyv != null)
				return false;
		} else if (!keyv.equals(other.keyv))
			return false;
		if (privacy == null) {
			if (other.privacy != null)
				return false;
		} else if (!privacy.equals(other.privacy))
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Identity [id=" + id + ", version=" + version + ", producer=" + producer + ", keyv=" + keyv
				+ ", privacy=" + privacy + "]";
	}

}
