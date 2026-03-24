package model;

public class User {
	private int id;
	private String name;
	private int age;
	private String mobileNumber;
	private String email;
	private String password;

	public User() {

	}

	public User(int id, String name, int age, String mobileNumber, String email, String password) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;

	}

	public User(String name, int age, String mobileNumber2, String email, String password) {
		super();
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber2;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }

}
