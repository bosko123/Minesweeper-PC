package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets_settings.FontRegister;
import game.GameWindow;
import sqlite.SQLiteJDBC;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class UserSettingWindow extends JFrame {

	public static JPanel contentPane;
	public static JPasswordField passwordField;
	public static JPasswordField confirmPasswordField;
	public static JTextField textField;
	public static JPasswordField currentPasswordField;
	private JButton btnExit;
	private JTextField txtMines;
	private JTextField txtRows;
	private JTextField txtColumns;
	private JLabel lblHrsminsec;
	private JLabel lblLevel;
	private JLabel lblSubLevel;
	private JLabel lblHrsminsec_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSettingWindow frame = new UserSettingWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserSettingWindow() {
		setTitle("Minesweeper - Profile Settings");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserSettingWindow.class.getResource("/assets/textures/gui/icon1.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 42, 424, 234);
		contentPane.add(panel);
		panel.setLayout(null);
		
		passwordField = new JPasswordField("\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7");
		passwordField.setBounds(120, 103, 180, 20);
		panel.add(passwordField);
		passwordField.setToolTipText("Enter password");
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 25f));
		
		confirmPasswordField = new JPasswordField("\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7");
		confirmPasswordField.setBounds(120, 149, 180, 20);
		panel.add(confirmPasswordField);
		confirmPasswordField.setToolTipText("Confirm Password");
		confirmPasswordField.setForeground(Color.LIGHT_GRAY);
		confirmPasswordField.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 25f));
		
		JLabel lblChangePassword = new JLabel("Change Password:");
		lblChangePassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblChangePassword.setBounds(10, 11, 404, 14);
		panel.add(lblChangePassword);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				
				RegisterUser.changePassword(MainWindow.username, currentPasswordField.getText(), passwordField.getText(), confirmPasswordField.getText());
				
			}
		});
		btnChangePassword.setBounds(120, 180, 180, 23);
		btnChangePassword.setFocusPainted(false);
		btnChangePassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(btnChangePassword);
		
		currentPasswordField = new JPasswordField("\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7\u00A7");
		currentPasswordField.setToolTipText("Enter Current password");
		currentPasswordField.setForeground(Color.LIGHT_GRAY);
		currentPasswordField.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 25f));
		currentPasswordField.setBounds(120, 57, 180, 20);
		currentPasswordField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent event) {
				
				if (currentPasswordField.getText().toLowerCase().equals("§§§§§§§§")) {
					
					currentPasswordField.setText("");
					currentPasswordField.setForeground(Color.BLACK);
					
				}
				
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if (currentPasswordField.getText().equals("")) {
					
					currentPasswordField.setText("§§§§§§§§");
					currentPasswordField.setForeground(Color.LIGHT_GRAY);
					
				}
				
			}
			
		});
		panel.add(currentPasswordField);
		
		JLabel lblCurrentPassword = new JLabel("Current Password:");
		lblCurrentPassword.setBounds(120, 36, 180, 20);
		lblCurrentPassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(lblCurrentPassword);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(120, 82, 180, 20);
		lblPassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(120, 128, 180, 20);
		lblConfirmPassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(lblConfirmPassword);
		
		textField = new JTextField();
		textField.setBounds(0, 0, 0, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblProfileSettings = new JLabel("Profile settings");
		lblProfileSettings.setBounds(10, 11, 424, 20);
		lblProfileSettings.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 16f));
		contentPane.add(lblProfileSettings);
		
		btnExit = new JButton("Exit");
		btnExit.setFocusPainted(false);
		btnExit.setBounds(405, 287, 90, 23);
		contentPane.add(btnExit);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new GameWindow().setVisible(true);
				dispose();
				
			}
		});
		btnExit.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(444, 42, 440, 110);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblClassic = new JLabel("Classic");
		lblClassic.setBounds(10, 11, 420, 14);
		lblClassic.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_1.add(lblClassic);
		
		JLabel lblMines = new JLabel("Mines:");
		lblMines.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMines.setBounds(10, 36, 62, 20);
		lblMines.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_1.add(lblMines);
		
		JLabel lblRows = new JLabel("Rows:");
		lblRows.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRows.setBounds(10, 61, 62, 20);
		lblRows.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_1.add(lblRows);
		
		JLabel lblColumns = new JLabel("Columns:");
		lblColumns.setHorizontalAlignment(SwingConstants.TRAILING);
		lblColumns.setBounds(10, 86, 62, 20);
		lblColumns.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_1.add(lblColumns);
		
		txtMines = new JTextField();
		txtMines.setBounds(82, 36, 60, 20);
		txtMines.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_1.add(txtMines);
		txtMines.setColumns(10);
		
		txtRows = new JTextField();
		txtRows.setBounds(82, 61, 60, 20);
		txtRows.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_1.add(txtRows);
		txtRows.setColumns(10);
		
		txtColumns = new JTextField();
		txtColumns.setBounds(82, 86, 60, 20);
		txtColumns.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_1.add(txtColumns);
		txtColumns.setColumns(10);
		
		JButton btnShow = new JButton("Show");
		btnShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				
				SQLiteJDBC jdbc = new SQLiteJDBC();
				try {
					
					int userid = jdbc.displayUser(MainWindow.username).getInt("userid");
					String name = txtMines.getText() + "_" + txtRows.getText() + "x" + txtColumns.getText();
					
					ResultSet resultSet = jdbc.getScore(userid, name);
					lblHrsminsec.setText("Time: " + resultSet.getString("time"));
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnShow.setBounds(152, 61, 75, 23);
		btnShow.setFocusPainted(false);
		btnShow.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_1.add(btnShow);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(237, 11, 193, 90);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblYourScore = new JLabel("Your Score:");
		lblYourScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourScore.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 16f));
		lblYourScore.setBounds(10, 11, 173, 35);
		panel_3.add(lblYourScore);
		
		lblHrsminsec = new JLabel("Time: hrs:min:sec");
		lblHrsminsec.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrsminsec.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblHrsminsec.setBounds(10, 44, 173, 35);
		panel_3.add(lblHrsminsec);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(444, 166, 440, 110);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCrazyMode = new JLabel("Crazy mode");
		lblCrazyMode.setBounds(10, 11, 420, 14);
		lblCrazyMode.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_2.add(lblCrazyMode);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(237, 11, 193, 90);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		lblHrsminsec_1 = new JLabel("hrs:min:sec");
		lblHrsminsec_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrsminsec_1.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblHrsminsec_1.setBounds(10, 65, 173, 14);
		panel_4.add(lblHrsminsec_1);
		
		lblLevel = new JLabel("Level:");
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setBounds(10, 34, 173, 14);
		lblLevel.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_4.add(lblLevel);
		
		JLabel lblHighestLevel = new JLabel("Highest Level:");
		lblHighestLevel.setBounds(10, 11, 173, 23);
		panel_4.add(lblHighestLevel);
		lblHighestLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighestLevel.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 16f));
		
		lblSubLevel = new JLabel("Mines:");
		lblSubLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubLevel.setBounds(10, 50, 173, 14);
		lblSubLevel.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_4.add(lblSubLevel);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				
				SQLiteJDBC jdbc = new SQLiteJDBC();
				try {
					
					int userid = jdbc.displayUser(MainWindow.username).getInt("userid");
					int maxLevel = jdbc.getMaxLevel(userid).getInt("level");
					
					ResultSet resultSet = jdbc.getScore(userid, maxLevel);
					lblLevel.setText("Level: " + (resultSet.getInt("level") + 1));
					lblSubLevel.setText("Mines: " + resultSet.getInt("minePer") + "%");
					lblHrsminsec_1.setText("Time: " + resultSet.getString("time"));
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnRefresh.setBounds(68, 50, 100, 23);
		btnRefresh.setFocusPainted(false);
		btnRefresh.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_2.add(btnRefresh);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setBounds(444, 11, 440, 20);
		lblScore.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 16f));
		contentPane.add(lblScore);
		confirmPasswordField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent event) {
				
				if (confirmPasswordField.getText().toLowerCase().equals("§§§§§§§§")) {
					
					confirmPasswordField.setText("");
					confirmPasswordField.setForeground(Color.BLACK);
					
				}
				
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if (confirmPasswordField.getText().equals("")) {
					
					confirmPasswordField.setText("§§§§§§§§");
					confirmPasswordField.setForeground(Color.LIGHT_GRAY);
					
				}
				
			}
			
		});
		passwordField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent event) {
				
				if (passwordField.getText().toLowerCase().equals("§§§§§§§§")) {
					
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
					
				}
				
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if (passwordField.getText().equals("")) {
					
					passwordField.setText("§§§§§§§§");
					passwordField.setForeground(Color.LIGHT_GRAY);
					
				}
				
			}
			
		});
		
		setLocationRelativeTo(null);
		
	}
}
