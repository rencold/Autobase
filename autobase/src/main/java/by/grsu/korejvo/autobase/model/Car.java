package by.grsu.korejvo.autobase.model;

public class Car {

	private Integer id;
	private String number;
	private String brand;
	private String model;
	private String driveUnit;
	private String engine;
	private String transmission;
	private Integer driverId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDriveUnit() {
		return driveUnit;
	}

	public void setDriveUnit(String driveUnit) {
		this.driveUnit = driveUnit;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", number=" + number + ", brand=" + brand + ", model=" + model + ", driveUnit="
				+ driveUnit + ", engine=" + engine + ", transmission=" + transmission + ", driverId=" + driverId + "]";
	}

}
