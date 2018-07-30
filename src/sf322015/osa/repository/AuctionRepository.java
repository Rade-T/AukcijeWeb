package sf322015.osa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sf322015.osa.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {

}
