package dev.controllers.dto;

import java.util.List;

public class CentreDto {
	private String type;
	private List<Double> Coordinates;

	public CentreDto() {
	}

	public CentreDto(String type, List<Double> coordinates) {
		this.type = type;
		Coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Double> getCoordinates() {
		return Coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		Coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "CentreDto{" + "type='" + type + '\'' + ", Coordinates=" + Coordinates + '}';
	}
}
