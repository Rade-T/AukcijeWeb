package sf322015.osa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sf322015.osa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
