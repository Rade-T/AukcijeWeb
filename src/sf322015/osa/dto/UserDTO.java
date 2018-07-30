package sf322015.osa.dto;

import sf322015.osa.entity.User;

public class UserDTO {
	
	private Integer Id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String picture;
	
	private String address;
	
	private String phone;
	
	private String role;
	
	public UserDTO(Integer id, String name, String password, String email, String picture, String address, String phone,
			String role) {
		super();
		Id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.picture = picture;
		this.address = address;
		this.phone = phone;
		this.role = role;
	}

	public UserDTO(User user) {
		this(
			user.getId(),
			user.getName(),
			user.getPassword(),
			user.getEmail(),
			user.getPicture(),
			user.getAddress(),
			user.getPhone(),
			user.getRole()
		);
	}
	
	public UserDTO() {
		super();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
