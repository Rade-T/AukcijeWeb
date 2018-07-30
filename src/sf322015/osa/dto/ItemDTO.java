package sf322015.osa.dto;

import sf322015.osa.entity.Item;

public class ItemDTO {
	
	private Integer Id;
	
	private String name;
	
	private String description;
	
	private String picture;
	
	private boolean sold;
	
	public ItemDTO(Integer id, String name, String description, String picture, boolean sold) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.sold = sold;
	}

	public ItemDTO(Item item) {
		this(
			item.getId(),
			item.getName(),
			item.getDescription(),
			item.getPicture(),
			item.isSold()
		);
	}
	
	public ItemDTO() {
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
