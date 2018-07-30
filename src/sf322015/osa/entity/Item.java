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

import sf322015.osa.dto.ItemDTO;

@Entity
@Table(name="items")
public class Item {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="item_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@Size(max=30)
	@Column(name="description", unique=false, nullable=false)
	private String description;
	
	@Column(name="picture", unique=false, nullable=true)
	private String picture;
	
	@Column(name="sold", unique=false, nullable=false)
	private boolean sold;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="item")
	private Set<Auction> auctions = new HashSet<>();
	
	public Item() {
		
	}

	public Item(ItemDTO item) {
		this.id = item.getId();
		this.name = item.getName();
		this.description = item.getDescription();
		this.picture = item.getPicture();
		this.sold = item.isSold();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}
}
