package st.digitru.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Privacy {

	private boolean optout;

	public Privacy() { }

	public Privacy(boolean optout) {
		this.optout = optout;
	}

	public boolean getOptout() {
		return optout;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (optout ? 1231 : 1237);
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
		Privacy other = (Privacy) obj;
		if (optout != other.optout)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Privacy [optout=" + optout + "]";
	}
}
