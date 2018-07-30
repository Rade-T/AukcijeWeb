package sf322015.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sf322015.osa.entity.User;
import sf322015.osa.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findOne(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void remove(Integer id) {
		userRepository.delete(id);
	}

}
