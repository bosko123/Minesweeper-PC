package main;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import game.GameWindow;
import sqlite.SQLiteJDBC;

public class RegisterUser {
	
	public static File file = new File("users.txt");
	
	public static boolean registerUser(String username, String password, String passwordConfirm) {
		
		//declare local variables
		SQLiteJDBC jdbc = new SQLiteJDBC();
		boolean registerSuccess = false;
		
		try {
			
			//check if user exists
			if (userExists(username)) {
				
				JOptionPane.showMessageDialog(GameWindow.frame, "user already exists", "Error", JOptionPane.DEFAULT_OPTION);
				
			}
			else {
				
				//check if passwords match
				if (password.equals(passwordConfirm)) {
					
					jdbc.addUser(username, getMD5(password));
					registerSuccess = true;
					
				}
				else
					JOptionPane.showMessageDialog(GameWindow.frame, "Passwords doesn't match", "Error", JOptionPane.DEFAULT_OPTION);
				
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} finally {
			
		}
		
		return registerSuccess;
		
	}
	
	public static boolean loginUser(String username, String password) {
		
		//declare local variables
		SQLiteJDBC jdbc = new SQLiteJDBC();
		boolean loginSuccess = false;
		boolean wrongPassword = true;
		
		try {
			
			ResultSet resultSet = jdbc.displayUsers();
			
			//read file
			while (resultSet.next()) {
				
				String uName = resultSet.getString("username");
				String passwd = resultSet.getString("password");
				
				//check if user exists
				if (userExists(username)) {
					
					//check matching passwords
					if (passwd.equals(getMD5(password))) {
						
						loginSuccess = true;
						wrongPassword = false;
						
					}
					
				}
				else {
					
					JOptionPane.showMessageDialog(GameWindow.frame, "User doesn't exist", "Error", JOptionPane.DEFAULT_OPTION);
					
				}
				
			}
			
			if (wrongPassword && userExists(username)) {
				
				JOptionPane.showMessageDialog(GameWindow.frame, "Wrong password", "Error", JOptionPane.DEFAULT_OPTION);
				
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} finally {
			
		}
		
		return loginSuccess;
		
	}

	public static boolean userExists(String username) {
		
		SQLiteJDBC jdbc = new SQLiteJDBC();
		
		try {
			
			ResultSet resultSet = jdbc.displayUsers();
			
			while (resultSet.next()) {
				
				String string = resultSet.getString("username");
				
				if (string.equals(username)) {
					
					return true;
					
				}
				
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} finally {
			
		}
		
		return false;
		
	}
	
	public static void changePassword(String username, String currentPassword, String password, String confirmPassword) {
		
		//declare local variables
		SQLiteJDBC jdbc = new SQLiteJDBC();
		boolean loginSuccess = false;
		boolean wrongPassword = true;
		boolean passwordNotMatch = true;
		
		try {
			
			ResultSet resultSet = jdbc.displayUsers();
			
			//read file
			while (resultSet.next()) {
				
				String uName = resultSet.getString("username");
				String passwd = resultSet.getString("password");
				
				//check if user exists
				if (userExists(username)) {
					
					//check matching passwords
					if (passwd.equals(getMD5(currentPassword))) {
						
						if (password.equals(confirmPassword)) {
							
							jdbc.changePassword(username, getMD5(password));
							passwordNotMatch = false;
							System.out.println("password changed");
							
						}
						
						loginSuccess = true;
						wrongPassword = false;
						
					}
					
				}
				else {
					
					System.out.println("user doesn't exists");
					
				}
				
			}
			
			if (wrongPassword) {
				
				JOptionPane.showMessageDialog(GameWindow.frame, "Wrong Password", "Error", JOptionPane.DEFAULT_OPTION);
				
			}
			else if (passwordNotMatch) {
				
				JOptionPane.showMessageDialog(GameWindow.frame, "Passwords don't match", "Error", JOptionPane.DEFAULT_OPTION);
				
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} finally {
			
		}
		
	}
	
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
}
