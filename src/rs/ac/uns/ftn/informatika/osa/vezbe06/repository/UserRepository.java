package rs.ac.uns.ftn.informatika.osa.vezbe06.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.osa.vezbe06.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
}
