package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.User;
import com.forum.units.UserRole;
import com.forum.util.Utility;

public class UserServiceImpl implements UserService {
	public static ArrayList<User> users = new ArrayList<>();
	
	public User createUser(String username, String password, String email, UserRole userRole) {
		if (Utility.isNotNullAndEmpty(username) && Utility.isNotNullAndEmpty(email) && Utility.isNotNullAndEmpty(password) && (userRole != null)) {
			User user = getUser(username);
			if (user != null) {
				System.out.println("Username or email already exists");
				return user;
			}
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setUserRole(userRole);
			user.autoGenerateId();
			user.setCreated();
			users.add(user);
			return user;
		}
		System.out.println("Any specified field can't be empty");
		return null;
	}
	
	public User getUser(String username, String password) {
		User user = getUser(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	private User getUser(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username) || user.getEmail().equals(username))
				return user;
		}
		return null;
	}
}
