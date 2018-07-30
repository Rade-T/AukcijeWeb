package sf322015.osa.dto;

import java.util.Date;

import sf322015.osa.entity.Bid;

public class BidDTO {
	
	private Integer id;
	
	private Double price;
	
	private Date dateTime;
	
	private AuctionDTO auction;
	
	private UserDTO user;
	
	public BidDTO() {
		super();
	}
	
	public BidDTO(Integer id, Double price, Date dateTime, AuctionDTO auction, UserDTO user) {
		super();
		this.id = id;
		this.price = price;
		this.dateTime = dateTime;
		this.auction = auction;
		this.user = user;
	}

	public BidDTO(Bid bid) {
		this(
			bid.getId(),
			bid.getPrice(),
			bid.getDateTime(),
			(bid.getAuction() != null && bid.getAuction().getId() != null) ? new AuctionDTO(bid.getAuction()) : new AuctionDTO(),
			(bid.getUser() != null && bid.getUser().getId() != null) ? new UserDTO(bid.getUser()) : new UserDTO()
		);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public AuctionDTO getAuction() {
		return auction;
	}

	public void setAuction(AuctionDTO auction) {
		this.auction = auction;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
