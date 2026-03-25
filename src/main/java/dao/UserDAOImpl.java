package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;
import util.DBConnection;

public class UserDAOImpl implements UserDAO {
	Logger logger=Logger.getLogger(UserDAOImpl.class.getName());
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		return new DBConnection().getConnection();

	}

	@Override
	public int registerUser(User user) throws ClassNotFoundException, SQLException {
		String query = "insert into users (name, age, mobileNumber, email, password) VALUES (?,?,?,?,?)";

		try {
			Connection con = getConnection();
			PreparedStatement pstm = con.prepareStatement(query);
				
			pstm.setString(1, user.getName());
			pstm.setInt(2, user.getAge());
			pstm.setString(3, user.getMobileNumber());
			pstm.setString(4, user.getEmail());
			pstm.setString(5, user.getPassword());

			return pstm.executeUpdate();
		}catch(SQLException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Falied to register user: "+user.getEmail(), e);
			throw e;
		}

	}

	@Override
	public User validateUser(String email) throws SQLException, ClassNotFoundException {
		String query = "SELECT id, name, age, mobileNumber, password FROM users where email = ?";
		try {
			Connection con = getConnection();
			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, email);

			ResultSet result = pstm.executeQuery();

			if (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int age = result.getInt("age");
				String mobileNumber = result.getString("mobileNumber");
				String password=result.getString("password");
				return new User(id, name, age, mobileNumber, email, password);
			}
			return null;
		} catch (SQLException | ClassNotFoundException e) {
			logger.log(Level.SEVERE,"Failed to validate user: "+email, e);
			throw e;
		}
	}

}
