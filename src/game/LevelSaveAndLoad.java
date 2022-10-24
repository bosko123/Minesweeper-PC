package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sqlite.SQLiteJDBC;

public class LevelSaveAndLoad {
	
	public static void loadLevel(String username) {
		
		SQLiteJDBC jdbc = new SQLiteJDBC();
		
		try {
			
			ResultSet resultSet = jdbc.displayUser(username);
			
			GameWindow.level = resultSet.getInt("current_level");
			GameWindow.minePercentage = resultSet.getInt("mine_percentage");
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.getStackTrace();
			
		}
		
	}
	
	public static void loadPowerUps(String username) {
		
		SQLiteJDBC jdbc = new SQLiteJDBC();
		
		try {
			
			ResultSet resultSet = jdbc.displayUser(username);
			
			GameWindow.allPowerUps[0] = resultSet.getInt("drill");
			GameWindow.allPowerUps[1] = resultSet.getInt("xray");
			GameWindow.health = resultSet.getInt("health");
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void saveLevel(String username) {
		
		SQLiteJDBC jdbc = new SQLiteJDBC();
		
		try {
			
			jdbc.updateLevel(username, GameWindow.level, GameWindow.minePercentage);
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void savePowerUps(String username) {
		
		SQLiteJDBC jdbc = new SQLiteJDBC();
		
		try {
			
			jdbc.updatePowerUps(username, GameWindow.allPowerUps[0], GameWindow.allPowerUps[1], GameWindow.health);
			System.out.println("powerups saved");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void saveBestScoreClassic(String username) {
		
		SQLiteJDBC jdbc = new SQLiteJDBC();
		
		try {
			
			String name = GameWindow.mines + "_" + GameWindow.currentSizeX + "x" + GameWindow.currentSizeY;
			int userid = jdbc.displayUser(username).getInt("userid");
			ResultSet resultSet = jdbc.getScore(userid, name);
			
			if (!resultSet.next()) {
				
				jdbc.insertScore(userid, name, GameWindow.timeInSec, EventHandler.time);
				
			}
			else if (jdbc.getScore(userid, name).getInt("secunds") > GameWindow.timeInSec) {
				
				jdbc.updateScore(userid, name, GameWindow.timeInSec, EventHandler.time);
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void saveBestScoreCrazy(String username) {
		
		SQLiteJDBC jdbc = new SQLiteJDBC();
		
		try {
			
			int userid = jdbc.displayUser(username).getInt("userid");
			ResultSet resultSet = jdbc.getScore(userid, GameWindow.level, GameWindow.minePercentage);
			
			if (!resultSet.next()) {
				
				jdbc.insertScore(userid, GameWindow.level, GameWindow.minePercentage, GameWindow.timeInSec, EventHandler.time);
				
			}
			else if (jdbc.getScore(userid, GameWindow.level, GameWindow.minePercentage).getInt("secunds") > GameWindow.timeInSec) {
				
				jdbc.updateScore(userid, GameWindow.level, GameWindow.minePercentage, GameWindow.timeInSec, EventHandler.time);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
