package by.grsu.korejvo.autobase.model;

public class Run {

	private Integer id;
	private String locationFrom;
	private String locationTo;
	private Double distance;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocationFrom() {
		return locationFrom;
	}

	public void setLocationFrom(String locationFrom) {
		this.locationFrom = locationFrom;
	}

	public String getLocationTo() {
		return locationTo;
	}

	public void setLocationTo(String locationTo) {
		this.locationTo = locationTo;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Run [id=" + id + ", locationFrom=" + locationFrom + ", locationTo=" + locationTo + ", distance="
				+ distance + "]";
	}

}
