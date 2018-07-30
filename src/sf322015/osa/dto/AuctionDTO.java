package sf322015.osa.dto;

import java.io.Serializable;
import java.util.Date;

import sf322015.osa.entity.Auction;

public class AuctionDTO implements Serializable {
	
	private Integer id;
	
	private Double startPrice;
	
	private Date startDate;
	
	private Date endDate;
	
	private UserDTO user;
	
	private ItemDTO item;
	
	public AuctionDTO() {
		super();
	}

	public AuctionDTO(Integer id, Double startPrice, Date startDate, Date endDate, UserDTO user, ItemDTO item) {
		super();
		this.id = id;
		this.startPrice = startPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.item = item;
	}
	
	public AuctionDTO(Auction auction) {
		this(
			auction.getId(),
			auction.getStartPrice(),
			auction.getStartDate(),
			auction.getEndDate(),
		    (auction.getUser() != null && auction.getUser().getId() != null) ? new UserDTO(auction.getUser()) : new UserDTO(),
		    (auction.getItem() != null && auction.getItem().getId() != null) ? new ItemDTO(auction.getItem()) : new ItemDTO()
		);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public ItemDTO getItem() {
		return item;
	}

	public void setItem(ItemDTO item) {
		this.item = item;
	}
}
