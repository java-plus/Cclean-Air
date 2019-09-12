package dev.controllers.dto;

import java.util.Arrays;

/**
 * @author Guillaume
 *
 */
public class ModificationCommuneIndicateurDto {

	private String[] communes;

	public ModificationCommuneIndicateurDto(String[] communes) {
		super();
		this.communes = communes;
	}

	public ModificationCommuneIndicateurDto() {
		super();
	}

	@Override
	public String toString() {
		return "ModificationCommuneIndicateurDto [communes=" + Arrays.toString(communes) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(communes);
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
		ModificationCommuneIndicateurDto other = (ModificationCommuneIndicateurDto) obj;
		if (!Arrays.equals(communes, other.communes))
			return false;
		return true;
	}

	/**
	 * @return the communes
	 */
	public String[] getCommunes() {
		return communes;
	}

	/**
	 * @param communes the communes to set
	 */
	public void setCommunes(String[] communes) {
		this.communes = communes;
	}

}
