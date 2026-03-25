package util;

import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	public static final int SALT_ROUNDS = 12;
	
	//Hash a plain password
	public static String hashPassword(String plainPassword) throws NoSuchAlgorithmException {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt(SALT_ROUNDS));
	}
	
	//Verify plain password against stored hash
	public static boolean  verifyPassword(String plainPassword,String storedHash){
		return BCrypt.checkpw(plainPassword, storedHash);
	}
}
