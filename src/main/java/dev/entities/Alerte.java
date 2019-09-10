package dev.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Guillaume Notification spécifique envoyée par un administrateur
 *
 */
@Entity
@Table(name = "ALERTE")
public class Alerte extends Notification implements Serializable {

	private static final long serialVersionUID = -6924586862351078888L;

	/**
	 * Id de l'administrateur qui envoie l'alerte
	 */
	@Column(name = "ale_id_admin")
	private Integer idAdmin;

	public Alerte(ZonedDateTime date, Commune commune, String corpsNotification, Integer idAdmin) {
		super(date, commune, corpsNotification);
		this.idAdmin = idAdmin;
	}

	public Alerte(ZonedDateTime date, Commune commune, String corpsNotification) {
		super(date, commune, corpsNotification);
	}

	@Override
	public String toString() {
		return "Alerte [idAdmin=" + idAdmin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idAdmin == null) ? 0 : idAdmin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alerte other = (Alerte) obj;
		if (idAdmin == null) {
			if (other.idAdmin != null)
				return false;
		} else if (!idAdmin.equals(other.idAdmin))
			return false;
		return true;
	}

	/**
	 * @return the idAdmin
	 */
	public Integer getIdAdmin() {
		return idAdmin;
	}

	/**
	 * @param idAdmin the idAdmin to set
	 */
	public void setIdAdmin(Integer idAdmin) {
		this.idAdmin = idAdmin;
	}

}
