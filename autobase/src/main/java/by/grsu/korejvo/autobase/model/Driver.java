package by.grsu.korejvo.autobase.model;

public class Driver {

	private Integer id;
	private String name;
	private String phoneNumber;
	private String exp;
	private String statement;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", exp=" + exp + ", statement="
				+ statement + "]";
	}

}
