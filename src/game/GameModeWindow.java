package game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import assets_settings.FontRegister;
import main.MainWindow;
import java.awt.Toolkit;

public class GameModeWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameModeWindow frame = new GameModeWindow();
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
	public GameModeWindow() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameModeWindow.class.getResource("/assets/textures/gui/icon1.png")));
		setTitle("Minesweeper - Game Mode Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 73, 434, 72);
		panel.setBackground(new Color(211, 211, 211));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnClassic = new JButton("Classic Mode");
		btnClassic.setToolTipText("");
		btnClassic.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 16f));
		btnClassic.setFocusPainted(false);
		btnClassic.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent event) {
				
				GameWindow.crazyModeOn = false;
				new GameWindow().setVisible(true);
				GameWindow.lblDrill.setVisible(false);
				GameWindow.lblDrillsCount.setVisible(false);
				GameWindow.lblXRay.setVisible(false);
				GameWindow.lblXRaysCount.setVisible(false);
				dispose();
				
			}
			
		});
		btnClassic.setBounds(14, 11, 200, 50);
		panel.add(btnClassic);
		
		JButton btnCrazyMode = new JButton("Crazy Mode");
		btnCrazyMode.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 16f));
		btnCrazyMode.setFocusPainted(false);
		btnCrazyMode.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				GameWindow.crazyModeOn = true;
				new GameWindow().setVisible(true);
				GameWindow.lblMines_1.setVisible(false);
				GameWindow.lblRow.setVisible(false);
				GameWindow.lblColumns.setVisible(false);
				GameWindow.txtMines.setVisible(false);
				GameWindow.txtRows.setVisible(false);
				GameWindow.txtCols.setVisible(false);
				dispose();
				
			}
			
		});
		btnCrazyMode.setBounds(224, 11, 200, 50);
		panel.add(btnCrazyMode);
		
		JLabel lblSelectGameMode = new JLabel("Select game mode");
		lblSelectGameMode.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 20f));
		lblSelectGameMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectGameMode.setBounds(10, 11, 414, 39);
		contentPane.add(lblSelectGameMode);
	}
}
