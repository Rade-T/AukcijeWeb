package sf322015.osa.service;

import java.util.List;

import sf322015.osa.entity.User;

public interface UserServiceInterface {

	User findOne(Integer id);
	
	List<User> findAll();
	
	User save(User user);
	
	void remove(Integer id);
}
