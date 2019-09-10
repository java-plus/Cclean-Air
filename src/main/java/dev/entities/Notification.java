package dev.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Guillaume
 *
 */
@Entity
@Table(name = "NOTIFICATION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Notification implements Serializable {

	private static final long serialVersionUID = -6803321381913354352L;

	/**
	 * Identifiant unique de l'entité, clé primaire de la table associée en bdd
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "not_id")
	protected Integer id;

	/**
	 * Indicateur temporel de la notification
	 */
	@Column(name = "not_date")
	protected ZonedDateTime date;

	/**
	 * Commune concernée par la notification
	 */
	@ManyToOne
	@JoinColumn(name = "not_commune")
	protected Commune commune;

	/**
	 * Corps du message de la notification
	 */
	@Column(name = "not_corps_notification")
	private String corpsNotification;

	public Notification(ZonedDateTime date, Commune commune, String corpsNotification) {
		super();
		this.date = date;
		this.commune = commune;
		this.corpsNotification = corpsNotification;
	}

	public Notification() {
		super();
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", date=" + date + ", commune=" + commune + ", corpsNotification="
				+ corpsNotification + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commune == null) ? 0 : commune.hashCode());
		result = prime * result + ((corpsNotification == null) ? 0 : corpsNotification.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Notification other = (Notification) obj;
		if (commune == null) {
			if (other.commune != null)
				return false;
		} else if (!commune.equals(other.commune))
			return false;
		if (corpsNotification == null) {
			if (other.corpsNotification != null)
				return false;
		} else if (!corpsNotification.equals(other.corpsNotification))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public ZonedDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	/**
	 * @return the commune
	 */
	public Commune getCommune() {
		return commune;
	}

	/**
	 * @param commune the commune to set
	 */
	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	/**
	 * @return the corpsNotification
	 */
	public String getCorpsNotification() {
		return corpsNotification;
	}

	/**
	 * @param corpsNotification the corpsNotification to set
	 */
	public void setCorpsNotification(String corpsNotification) {
		this.corpsNotification = corpsNotification;
	}

}
