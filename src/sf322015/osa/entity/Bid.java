package sf322015.osa.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import sf322015.osa.dto.BidDTO;

@Entity
@Table(name="bids")
public class Bid {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="bid_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="price", unique=false, nullable=false)
	private Double price;
	
	@Column(name="date_time", unique=false, nullable=false)
	private Date dateTime;
	
	@ManyToOne
	@JoinColumn(name="auction_id", referencedColumnName="auction_id", nullable=false)
	private Auction auction;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	private User user;
	
	public Bid() {
		
	}
	
	public Bid(BidDTO bid) {
		this.id = bid.getId();
		this.dateTime = bid.getDateTime();
		this.price = bid.getPrice();
		this.auction = new Auction(bid.getAuction());
		this.user = new User(bid.getUser());
	}
	
	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
}
