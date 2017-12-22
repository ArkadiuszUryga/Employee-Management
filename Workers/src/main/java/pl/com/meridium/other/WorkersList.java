package pl.com.meridium.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.com.meridium.entity.User;
import pl.com.meridium.repository.UserRepository;

public class WorkersList {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> workersList()	{
		List<User> workers = userRepository.findByranga(1);
		return workers;
	}
	
	
	
	
}
