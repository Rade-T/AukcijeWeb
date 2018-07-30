package sf322015.osa.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sf322015.osa.dto.AuctionDTO;

@Entity
@Table(name="auctions")
public class Auction implements Serializable {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="auction_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="start_price", unique=false, nullable=false)
	private Double startPrice;
	
	@Column(name="start_date", unique=false, nullable=true)
	private Date startDate;
	
	@Column(name="end_date", unique=false, nullable=true)
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="item_id", referencedColumnName="item_id", nullable=false)
	private Item item;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="auction")
	private Set<Bid> bids = new HashSet<>();
	
	public Auction() {
		super();
	}
	
	public Auction(AuctionDTO dto) {
		this.startDate = dto.getStartDate();
		this.endDate = dto.getEndDate();
		this.id = dto.getId();
		this.startPrice = dto.getStartPrice();
		this.user = new User(dto.getUser());
		this.item = new Item(dto.getItem());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
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
}
