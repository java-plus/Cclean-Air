package dev.controllers.dto;

import dev.entities.Commune;

public class CommuneDto {
	private String nom;
	private Long nbHabitants;
	private String codeInsee;
	private Double latitude;
	private Double longitude;

	public CommuneDto() {
	}

	public CommuneDto(String nom, Long nbHabitants, String codeInsee, Double latitude, Double longitude) {
		this.nom = nom;
		this.nbHabitants = nbHabitants;
		this.codeInsee = codeInsee;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public CommuneDto(Commune commune) {
		this.nom = commune.getNom();
		this.nbHabitants = commune.getNbHabitants();
		this.codeInsee = commune.getCodeInsee();
		this.latitude = commune.getLatitude();
		this.longitude = commune.getLongitude();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getNbHabitants() {
		return nbHabitants;
	}

	public void setNbHabitants(Long nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	public String getCodeInsee() {
		return codeInsee;
	}

	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
