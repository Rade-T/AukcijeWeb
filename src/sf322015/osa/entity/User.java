package sf322015.osa.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import sf322015.osa.dto.UserDTO;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	private Integer id;
	
	@Size(max=30)
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@Size(max=30)
	@Column(name="email", unique=false, nullable=false)
	private String email;
	
	@Size(max=10)
	@Column(name="password", unique=false, nullable=false)
	private String password;
	
	@Column(name="picture", unique=false, nullable=true)
	private String picture;
	
	@Column(name="address", unique=false, nullable=true)
	private String address;
	
	@Size(max=30)
	@Column(name="phone", unique=false, nullable=true)
	private String phone;
	
	@Size(max=15)
	@Column(name="role", unique=false, nullable=false)
	private String role;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="user")
	private Set<Bid> bids = new HashSet<>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="user")
	private Set<Auction> auctions = new HashSet<>();
	
	public User() {
		
	}

	public User(UserDTO user) {
		this.id = user.getId();
		this.name = user.getName();
		this.address = user.getAddress();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.picture = user.getPicture();
		this.role = user.getPhone();
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Set<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(Set<Auction> auctions) {
		this.auctions = auctions;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAddress() {
		return this.address;
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
