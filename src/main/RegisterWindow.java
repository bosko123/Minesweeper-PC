package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import assets_settings.FontRegister;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class RegisterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField textField;
	private JPasswordField pwdPassword;
	
	public static String username;
	public static String password;
	private JPasswordField pwdPasswordConfirm;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow frame = new RegisterWindow();
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
	public RegisterWindow() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterWindow.class.getResource("/assets/textures/gui/icon1.png")));
		setTitle("Minesweeper - Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		panel.setBounds(125, 19, 200, 225);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("Enter username");
		txtUsername.setBounds(10, 32, 180, 20);
		txtUsername.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(txtUsername);
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
		pwdPassword.setToolTipText("Enter password");
		pwdPassword.setBounds(10, 78, 180, 20);
		panel.add(pwdPassword);
		pwdPassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 24.5f));
		pwdPassword.setForeground(Color.LIGHT_GRAY);
		
		pwdPasswordConfirm = new JPasswordField("§§§§§§§§");
		pwdPasswordConfirm.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 24.5f));
		pwdPasswordConfirm.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent event) {
				
				if (pwdPasswordConfirm.getText().toLowerCase().equals("§§§§§§§§")) {
					
					pwdPasswordConfirm.setText("");
					pwdPasswordConfirm.setForeground(Color.BLACK);
					
				}
				
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if (pwdPasswordConfirm.getText().equals("")) {
					
					pwdPasswordConfirm.setText("§§§§§§§§");
					pwdPasswordConfirm.setForeground(Color.LIGHT_GRAY);
					
				}
				
			}
			
		});
		pwdPasswordConfirm.setToolTipText("Confirm Password");
		pwdPasswordConfirm.setForeground(Color.LIGHT_GRAY);
		pwdPasswordConfirm.setBounds(10, 124, 180, 20);
		panel.add(pwdPasswordConfirm);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (RegisterUser.registerUser(txtUsername.getText(), pwdPassword.getText(), pwdPasswordConfirm.getText())) {
					
					new MainWindow().setVisible(true);
					dispose();
					
				}
				
			}
			
		});
		btnRegister.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		btnRegister.setBounds(10, 155, 180, 23);
		panel.add(btnRegister);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		btnCancel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new MainWindow().setVisible(true);
				dispose();
				
			}
			
		});
		btnCancel.setBounds(10, 189, 180, 23);
		panel.add(btnCancel);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 11, 180, 20);
		lblUsername.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 57, 180, 20);
		lblPassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(lblPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(10, 103, 180, 20);
		lblConfirmPassword.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(lblConfirmPassword);
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
