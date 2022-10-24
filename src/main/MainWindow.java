package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import assets_settings.FontRegister;
import game.GameModeWindow;
import game.LevelSaveAndLoad;
import java.awt.Toolkit;

public class MainWindow extends JFrame {
	
	public static String fontPath = "/assets/pixle_font.ttf";
	public static String explosionSoundPath = "src/resources/sound/explosion.wav";
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private static JButton btnLogin;
	private JTextField textField;
	private JPasswordField pwdPassword;
	
	public static String username;
	public static String password;
	private JLabel lblPassword;
	private JLabel lblUsername;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/assets/textures/gui/icon1.png")));
		
		setTitle("Minesweeper - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 335);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				contentPane.requestFocus();
				
			}
		});
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 0, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(125, 105, 200, 180);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(10, 32, 180, 20);
		panel.add(txtUsername);
		txtUsername.setFont(FontRegister.registerFont(fontPath, Font.PLAIN, 12f));
		txtUsername.setForeground(Color.LIGHT_GRAY);
		txtUsername.setText("Username");
		txtUsername.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent event) {
				
				if (txtUsername.getText().toLowerCase().equals("username")) {
					
					txtUsername.setText("");
					txtUsername.setForeground(Color.BLACK);
					
				}
				
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if (txtUsername.getText().equals("")) {
					
					txtUsername.setText("Username");
					txtUsername.setForeground(Color.LIGHT_GRAY);
					
				}
				
			}
			
		});
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField("§§§§§§§§");
		pwdPassword.setBounds(10, 78, 180, 20);
		panel.add(pwdPassword);
		pwdPassword.setForeground(Color.LIGHT_GRAY);
		pwdPassword.setFont(FontRegister.registerFont(fontPath, Font.PLAIN, 24.5f));
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(10, 109, 180, 23);
		btnLogin.setFont(FontRegister.registerFont(fontPath, Font.PLAIN, 12f));
		panel.add(btnLogin);
		btnLogin.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				username = txtUsername.getText();
				password = pwdPassword.getText();
				
				if (RegisterUser.loginUser(username, password)) {
					
					LevelSaveAndLoad.loadLevel(username);
					LevelSaveAndLoad.loadPowerUps(username);
					new GameModeWindow().setVisible(true);
					dispose();
					
				}
				
			}
			
		});
		btnLogin.setFocusPainted(true);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(10, 143, 180, 23);
		btnRegister.setFont(FontRegister.registerFont(fontPath, Font.PLAIN, 12f));
		panel.add(btnRegister);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 57, 180, 20);
		lblPassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(lblPassword);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 11, 180, 20);
		lblUsername.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(lblUsername);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(10, 11, 414, 83);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMinesweeper = new JLabel("");
		lblMinesweeper.setIcon(new ImageIcon(MainWindow.class.getResource("/assets/textures/gui/title.png")));
		lblMinesweeper.setBounds(0, 0, 414, 80);
		panel_1.add(lblMinesweeper);
		lblMinesweeper.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinesweeper.setFont(FontRegister.registerFont(fontPath, Font.PLAIN, 24f));
		btnRegister.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new RegisterWindow().setVisible(true);
				dispose();
				
			}
			
		});
		btnLogin.requestFocus();
		pwdPassword.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent event) {
				
				if (pwdPassword.getText().toLowerCase().equals("§§§§§§§§")) {
					
					pwdPassword.setText("");
					pwdPassword.setForeground(Color.BLACK);
					
				}
				
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if (pwdPassword.getText().equals("")) {
					
					pwdPassword.setText("§§§§§§§§");
					pwdPassword.setForeground(Color.LIGHT_GRAY);
					
				}
				
			}
			
		});
		
	}
}
