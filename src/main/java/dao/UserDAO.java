package dao;

import java.sql.SQLException;
import model.User;

public interface UserDAO {
	int registerUser(User user) throws ClassNotFoundException, SQLException;
	//Fetch by email only - password verification is done in java via BCrypt,
	//not int the SQL query
	User validateUser(String email) throws ClassNotFoundException, SQLException;
}
