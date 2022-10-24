package sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBC {
	
	private static Connection connection;
	private static boolean hasData = false;
	
	public ResultSet displayUsers() throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM user;");
		return resultSet;
		
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:Minesweeper.db");
		initialise();
		
	}

	private void initialise() throws SQLException {
		
		if(!hasData) {
			
			hasData = true;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user';");
			
			if (!resultSet.next()) {
				
				System.out.println("Building the User table with prepopulated values.");
				//need to build the table
				Statement statement2 = connection.createStatement();
				statement2.execute("CREATE TABLE user("
						+ "userid integer primary key,"
						+ "username varchar2(40),"
						+ "password varchar2(40),"
						+ "current_level integer,"
						+ "mine_percentage integer,"
						+ "drill integer,"
						+ "xray integer,"
						+ "health integer);");
				Statement statement3 = connection.createStatement();
				statement3.execute("CREATE TABLE crazy("
						+ "crazyid integer primary key,"
						+ "level integer,"
						+ "minePer integer,"
						+ "secunds integer,"
						+ "time varchar2(10),"
						+ "fkuserid integer,"
						+ "FOREIGN KEY(fkuserid) REFERENCES user(userid));");
				Statement statement4 = connection.createStatement();
				statement4.execute("CREATE TABLE classic("
						+ "classicid integer primary key,"
						+ "name varchar2(40),"
						+ "secunds integer,"
						+ "time varchar2(10),"
						+ "fkuserid integer,"
						+ "FOREIGN KEY(fkuserid) REFERENCES user(userid));)");
				
			}
			
		}
		
	}
	
	public void addUser(String username, String password) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user values(?,?,?,?,?,?,?,?);");
		preparedStatement.setString(2, username);
		preparedStatement.setString(3, password);
		preparedStatement.setInt(4, 0);
		preparedStatement.setInt(5, 10);
		preparedStatement.setInt(6, 3);
		preparedStatement.setInt(7, 3);
		preparedStatement.setInt(8, 10);
		preparedStatement.execute();
		
	}
	
	public ResultSet displayUser(String username) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE username = '" + username + "';");
		return resultSet;
		
	}
	
	public void updateLevel(String username, int level, int minesPercent) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		statement.execute("UPDATE user SET current_level=" + level + ", mine_percentage=" + minesPercent + " where username='" + username + "';");
		
	}
	
	public void updatePowerUps(String username, int drill, int xray, int health) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		statement.execute("UPDATE user SET drill=" + drill + ", xray=" + xray + ", health=" + health + " where username = '" + username + "';");
		
	}
	
	public ResultSet getScore(int userid, String name) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from classic where fkuserid = " + userid + " and name = '" + name + "';");
		return resultSet;
		
	}
	
	public void insertScore(int userid, String name, int sec, String time) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO classic values(?,?,?,?,?);");
		preparedStatement.setString(2, name);
		preparedStatement.setInt(3, sec);
		preparedStatement.setString(4, time);
		preparedStatement.setInt(5, userid);
		preparedStatement.execute();
		
	}
	
	public void updateScore(int userid, String name, int sec, String time) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		statement.execute("UPDATE classic SET secunds=" + sec + ", time='" + time + "' where fkuserid = " + userid + " and name = '" + name + "';");
		
	}
	
	public ResultSet getScore(int userid, int level, int minePer) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from crazy where fkuserid = " + userid + " and level = " + level + " and minePer = " + minePer + ";");
		return resultSet;
		
	}
	
	public ResultSet getScore(int userid, int level) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from crazy where fkuserid = " + userid + " and level = " + level + ";");
		return resultSet;
		
	}
	
	public ResultSet getMaxLevel(int userid) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select max(level) as level from crazy where fkuserid = " + userid + ";");
		return resultSet;
		
	}
	
	public void insertScore(int userid, int level, int minePer, int sec, String time) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO crazy values(?,?,?,?,?,?);");
		preparedStatement.setInt(2, level);
		preparedStatement.setInt(3, minePer);
		preparedStatement.setInt(4, sec);
		preparedStatement.setString(5, time);
		preparedStatement.setInt(6, userid);
		preparedStatement.execute();
		
	}
	
	public void updateScore(int userid, int level, int minePer, int sec, String time) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		statement.execute("UPDATE crazy SET secunds=" + sec + ", time='" + time + "' where fkuserid = " + userid + " and level = " + level + " and minePer = " + minePer + ";");
		
	}
	
	public void changePassword(String username, String password) throws ClassNotFoundException, SQLException {
		
		if (connection == null) {
			
			getConnection();
			
		}
		
		Statement statement = connection.createStatement();
		statement.execute("UPDATE user SET password='" + password + "' WHERE username='" + username + "';");
		
	}
	
}
