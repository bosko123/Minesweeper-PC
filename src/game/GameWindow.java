package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import assets_settings.FontRegister;
import assets_settings.ImageRegister;
import assets_settings.Responsive;
import main.MainWindow;
import main.UserSettingWindow;
import power_ups.PowerUpsEvents;

public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2025052403489910012L;
	private JPanel contentPane;
	private Runnable openTiles = new open();
	private Thread thread0 = new Thread(openTiles);
	private Runnable runningTimer = new timer();
	private Thread thread1 = new Thread(runningTimer);
	private Runnable openerCrate = new openCreate();
	private Thread thread2 = new Thread(openerCrate);
	private Runnable empty = new xRayCooldown();
	private Thread thread3 = new Thread(empty);
	
	public static int health = 10;
	
	public static int level = 0;
	public static int minePercentage = 10;
	public static int sizeX = 10;
	public static int sizeY = 10;
	
	public static int currentSizeX = 0;
	public static int currentSizeY = 0;
	public static int mines = 0;
	
	public static int timeInSec = -1;
	public static int secunds = -1;
	public static int minutes = 0;
	public static int hours = 0;
	
	public static int[] allPowerUps = {3, 3};
	public static boolean drillSelected = false;
	public static boolean xraySelected = false;
	public static boolean xrayActive = false;
	
	public static GameWindow frame = new GameWindow();
	
	public static int frames = 1;
	public static int uses = 0;
	public static int time = 0;
	
	public static JLabel[][] tiles = null;
	public static boolean gameOver = false;
	public static boolean openingCrate = false;
	public static double width = 0;
	public static double height = 0;
	private JPanel pnlGrid;
	public static JLabel lblTimer;
	public static JTextField txtRows;
	public static JTextField txtCols;
	public static JTextField txtMines;
	public static JLabel lblMines;
	public static boolean crazyModeOn = false;
	private JPanel panel_1;
	public static JLabel lblMines_1;
	public static JLabel lblRow;
	public static JLabel lblColumns;
	private JPanel panel_2;
	private JPanel panel_3;
	public static JLabel lblDrillsCount;
	public static JButton btnOpen;
	public static JLabel lblCrate;
	public static JLabel lblPowerup;
	public static JLabel lblXRaysCount;
	public static JLabel lblDrill;
	public static JLabel lblXRay;
	private JPanel panel_4;
	public static JButton btnReset;
	public static JButton btnStart;
	public static JLabel lblHealth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow();
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
	public GameWindow() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/assets/textures/gui/icon1.png")));
		setTitle("Minesweeper");
		
		Image image = null;
		try {
			image = ImageIO.read(GameWindow.class.getResource(ImageRegister.assets + "tile_closed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ImageRegister.imageLoader();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnlGrid = new JPanel();
		pnlGrid.addMouseListener(new MouseAdapter() {
			
			//Set cursors
			@Override
			public void mouseEntered(MouseEvent event) {
				
				try {
					
					//check if drill is selected
					if (drillSelected) {
						
						Toolkit toolkit = Toolkit.getDefaultToolkit();
						Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/cursors/drill_cursor.png"));
						Cursor cursor = toolkit.createCustomCursor(image, new Point(2, 2), "Drill Cursor");
						
						setCursor(cursor);
						
					}
					
				} catch (Exception e) {
					
				}
				
			}
			
			//if cursor is out of the tile grid set is to default;
			@Override
			public void mouseExited(MouseEvent event) {
				
				setCursor(Cursor.DEFAULT_CURSOR);
				
			}
			
		});
		
		btnOpen = new JButton("Click To Open");
		btnOpen.setBounds(396, 620, 192, 30);
		btnOpen.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 14));
		contentPane.add(btnOpen);
		btnOpen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				if (btnOpen.getText().equals("Click To Open")) {
					
					PowerUpsEvents.getPowerUp();
					try {
						
						thread2.start();
						
					} catch (Exception e) {
						
					}
					
				}
				else {
					
					openingCrate = false;
					frames = 1;
					
				}
				
			}
			
		});
		btnOpen.setVisible(false);
		btnOpen.setFocusPainted(false);
		
		lblPowerup = new JLabel("");
		lblPowerup.setBounds(400, 200, 150, 150);
		contentPane.add(lblPowerup);
		lblPowerup.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblCrate = new JLabel("");
		lblCrate.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCrate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrate.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/textures/gui/create_icons/crate_1.png")));
		lblCrate.setBounds(142, 0, 700, 661);
		contentPane.add(lblCrate);
		lblCrate.setVisible(false);
		pnlGrid.setBackground(SystemColor.scrollbar);
		pnlGrid.setBounds(0, 0, 640, 640);
		contentPane.add(pnlGrid);
		pnlGrid.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(683, 11, 89, 629);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(0, 150, 89, 67);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMinesLeft = new JLabel("Mines left:");
		lblMinesLeft.setBounds(0, 10, 89, 14);
		panel_1.add(lblMinesLeft);
		lblMinesLeft.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblMinesLeft.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMines = new JLabel("0");
		lblMines.setBounds(0, 25, 89, 14);
		panel_1.add(lblMines);
		lblMines.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblMines.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTimer = new JLabel("hrs:min:sec");
		lblTimer.setBounds(0, 40, 89, 14);
		panel_1.add(lblTimer);
		lblTimer.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(0, 250, 89, 157);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		lblMines_1 = new JLabel("Mines:");
		lblMines_1.setBounds(0, 10, 89, 14);
		panel_2.add(lblMines_1);
		lblMines_1.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblMines_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtMines = new JTextField();
		txtMines.setBounds(0, 30, 89, 20);
		panel_2.add(txtMines);
		txtMines.setHorizontalAlignment(SwingConstants.CENTER);
		txtMines.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		txtMines.setText("10");
		txtMines.setColumns(10);
		
		lblRow = new JLabel("Row:");
		lblRow.setBounds(0, 61, 89, 14);
		panel_2.add(lblRow);
		lblRow.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblRow.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtRows = new JTextField();
		/*txtRows.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				
				String string = txtRows.getText();
				
				if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9' || event.getKeyCode() == 8) {
					
					txtCols.setText(txtRows.getText());
					
				}
				else {
					
					string = string.substring(0, string.length()-1);
					txtRows.setText(string);
					
				}
				
			}
			
		});*/
		txtRows.setBounds(0, 86, 89, 20);
		panel_2.add(txtRows);
		txtRows.setHorizontalAlignment(SwingConstants.CENTER);
		txtRows.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		txtRows.setText("10");
		txtRows.setColumns(10);
		
		lblColumns = new JLabel("Columns:");
		lblColumns.setBounds(0, 117, 89, 14);
		panel_2.add(lblColumns);
		lblColumns.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		lblColumns.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtCols = new JTextField();
		/*txtCols.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent event) {
				
				String string = txtCols.getText();
				
				if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9' || event.getKeyCode() == 8) {
					
					txtRows.setText(txtCols.getText());
					
				}
				else {
					
					string = string.substring(0, string.length()-1);
					txtCols.setText(string);
					
				}
				
			}
			
		});*/
		txtCols.setBounds(0, 137, 89, 20);
		panel_2.add(txtCols);
		txtCols.setHorizontalAlignment(SwingConstants.CENTER);
		txtCols.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		txtCols.setText("10");
		txtCols.setColumns(10);
		
		panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.controlHighlight);
		panel_3.setBounds(0, 435, 89, 57);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(0, 34, 89, 23);
		btnReset.setFocusPainted(false);
		btnReset.setEnabled(false);
		btnReset.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_3.add(btnReset);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(0, 5, 89, 23);
		btnStart.setFocusPainted(false);
		btnStart.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel_3.add(btnStart);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				
				new UserSettingWindow().setVisible(true);
				dispose();
				
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				
				new MainWindow().setVisible(true);
				dispose();
				
			}
		});
		btnLogout.setBounds(0, 561, 89, 23);
		btnLogout.setFocusPainted(false);
		btnLogout.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(btnLogout);
		btnProfile.setBounds(0, 595, 89, 23);
		btnProfile.setFocusPainted(false);
		btnProfile.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 12f));
		panel.add(btnProfile);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.controlHighlight);
		panel_5.setBounds(782, 11, 192, 629);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(211, 211, 211));
		panel_4.setBounds(29, 249, 134, 134);
		panel_5.add(panel_4);
		panel_4.setLayout(null);
		
		lblDrillsCount = new JLabel(0 + "");
		lblDrillsCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrillsCount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDrillsCount.setBounds(42, 42, 24, 24);
		lblDrillsCount.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 16f));
		panel_4.add(lblDrillsCount);
		
		lblDrill = new JLabel("");
		lblDrill.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent event) {
				
				if (crazyModeOn && allPowerUps[0] > 0) {
					
					//set how much tiles can drill open
					int stTiles = tiles.length * tiles[0].length;
					double percentageUse = Math.round((stTiles * PowerUpsEvents.DRILL_USES) / 100);
					PowerUpsEvents.drill.setUses((int)percentageUse);
					
					drillSelected = true;
					uses = PowerUpsEvents.drill.getUses();
					allPowerUps[0]--;
					lblDrillsCount.setText(allPowerUps[0] + "");
					
				}
				
			}
			
		});
		
		lblXRaysCount = new JLabel(0 + "");
		lblXRaysCount.setFont(FontRegister.registerFont(MainWindow.fontPath, Font.PLAIN, 16f));
		lblXRaysCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblXRaysCount.setBounds(108, 42, 24, 24);
		panel_4.add(lblXRaysCount);
		lblDrill.setBounds(2, 2, 64, 64);
		panel_4.add(lblDrill);
		lblDrill.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrill.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/textures/powerups/drill_icon.png")));
		
		lblXRay = new JLabel("");
		lblXRay.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent event) {
				
				if (crazyModeOn && allPowerUps[1] > 0) {
					
					//set how time will drill last
					int stTiles = tiles.length * tiles[0].length;
					double percentageTime = Math.round((stTiles * PowerUpsEvents.XRAY_TIME) / 100);
					PowerUpsEvents.xRay.setTime((int)percentageTime);
					
					xraySelected = true;
					time = PowerUpsEvents.xRay.getTime();
					allPowerUps[1]--;
					lblXRaysCount.setText(allPowerUps[1] + "");
					
				}
				
			}
			
		});
		lblXRay.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/textures/powerups/xray_icon.png")));
		lblXRay.setBounds(68, 2, 64, 64);
		panel_4.add(lblXRay);
		
		lblHealth = new JLabel("Lives " + health + "/10");
		lblHealth.setBounds(0, 79, 134, 44);
		panel_4.add(lblHealth);
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHealth.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (!crazyModeOn) {
			
			GameWindow.lblDrill.setVisible(false);
			GameWindow.lblDrillsCount.setVisible(false);
			GameWindow.lblXRay.setVisible(false);
			GameWindow.lblXRaysCount.setVisible(false);
			GameWindow.lblHealth.setVisible(false);
			
		}
		else {
			
			GameWindow.lblMines_1.setVisible(false);
			GameWindow.lblRow.setVisible(false);
			GameWindow.lblColumns.setVisible(false);
			GameWindow.txtMines.setVisible(false);
			GameWindow.txtRows.setVisible(false);
			GameWindow.txtCols.setVisible(false);
			
		}
		
		btnStart.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent event) {
				
				if (btnStart.isEnabled()) {
					
					btnStart.setEnabled(false);
					btnReset.setEnabled(true);
					
					//set tile grid size
					if (!crazyModeOn) {
						
						currentSizeX = Integer.parseInt(txtRows.getText());
						currentSizeY = Integer.parseInt(txtCols.getText());
						mines = Integer.parseInt(txtMines.getText());
						tiles = new JLabel[currentSizeY][currentSizeX];
						EventHandler.stMines = Integer.parseInt(txtMines.getText());
						
					}
					else {
						
						int x = sizeX + level;
						int y = sizeY + level;
						tiles = new JLabel[x][y];
						EventHandler.stMines = Math.round(((x * y) * minePercentage) / 100);
						System.out.println(minePercentage);
						
					}
					
					//define grid tiles rows and columns
					double rows = tiles.length;
					double cols = tiles[tiles.length-1].length;
					
					//set tile size
					width = Math.round(pnlGrid.getWidth() / rows);
					height = Math.round(pnlGrid.getHeight() / cols);
					
					System.out.println(width);
					System.out.println(height);
					
					//set offset
					int offsetX = Responsive.getOffsetX((int)rows, pnlGrid.getWidth(), (int)width);
					int offsetY = Responsive.getOffsetY((int)cols, pnlGrid.getHeight(), (int)height);
					
					//define image as null
					Image image = null;
					
					//get image and set it's size
					image = Responsive.resizeImage(ImageRegister.flagImages[0], (int)width, (int)height);
					
					pnlGrid.setBounds(offsetX, offsetY, (int)(width * rows), (int)(height * cols));
					
					//prepare game (generate mines, flags, crates)
					EventHandler.generateMines();
					EventHandler.rightFlags();
					
					if (crazyModeOn)
						EventHandler.crazyMode();
					
					lblDrillsCount.setText(allPowerUps[0] + "");
					lblXRaysCount.setText(allPowerUps[1] + "");
					
					//start timers/threads
					thread0.start();
					thread1.start();
					thread3.start();
					
					lblMines.setText(EventHandler.stMines + "");
					
					//place nukes if got before
					int nukes = PowerUpsEvents.xNukes;
					
					/*while (nukes != 0) {
						
						RandomPowerUp.placeNuke();
						
						nukes--;
						
					}*/
					
					//place tiles
					for (int i = 0; i < tiles.length; i++) {
						
						for (int j = 0; j < tiles[i].length; j++) {
							
							tiles[i][j] = new JLabel("");
							JLabel tile = tiles[i][j];
							
							tile.addMouseListener(new MouseAdapter() {
								
								@Override
								public void mouseEntered(MouseEvent event) {
									
									try {
										
										if (drillSelected) {
											
											Toolkit toolkit = Toolkit.getDefaultToolkit();
											Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/cursors/drill_cursor.png"));
											Cursor cursor = toolkit.createCustomCursor(image, new Point(2, 2), "Drill Cursor");
											
											setCursor(cursor);
											
										}
										
										if (xraySelected)
											EventHandler.xray(tile);
										
									} catch (Exception e) {
										
									}
									
								}
								
								@Override
								public void mouseExited(MouseEvent event) {
									
									setCursor(Cursor.DEFAULT_CURSOR);
									
								}
								
								@Override
								public void mousePressed(MouseEvent event) {
									
									//left click
									if (event.getButton() == event.BUTTON1 && !gameOver && !openingCrate) {
										
										//drill selected?
										if (!drillSelected) {
											
											EventHandler.openTile(tile);
											
										}
										else {
											
											EventHandler.breakTile(tile);
											
										}
										
									}
									
									//right click
									if (event.getButton() == event.BUTTON3 && !gameOver) {
										
										EventHandler.placeFlags(tile);
										
									}
									
								}
								
							});
							int x = i * (int)width;
							int y = j * (int)height;
							tile.setBounds(x, y, (int)width, (int)height);
							pnlGrid.add(tile);
							tile.setHorizontalAlignment(SwingConstants.CENTER);
							tile.setIcon(new ImageIcon(image));
							tiles[i][j] = tile;
							
						}
						
					}
					
				}
				
			}
			
		});
		btnReset.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent event) {
				
				if (btnReset.isEnabled()) {
					
					thread0.stop();
					thread1.stop();
					thread2.stop();
					thread3.stop();
					EventHandler.reset();
					new GameWindow().setVisible(true);
					dispose();
					
				}
				
			}
			
		});
		
	}
	
	public class xRayCooldown implements Runnable {

		@Override
		public void run() {
			
			while (!gameOver) {
				
				EventHandler.xRayCooldown();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	public class openCreate implements Runnable {

		@Override
		public void run() {
			
			while (!gameOver) {
				
				try {
					
					//System.out.println(frames);
					Thread.sleep(40);
					//GameWindow.lblCrate.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/textures/gui/create_icons/crate (" + frames + ").png")));
					//openCrate = false;
					
					if (frames == 60) {
						
						thread2.stop();
						
					}
					else {
						
						frames++;
						
					}
					
					//thread2.stop();
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		}
		
	}
	
	public class open implements Runnable {

		@Override
		public void run() {
			
			while (!gameOver) {
				
				if (!openingCrate) {
					
					EventHandler.openTiles();
					
				}
				if (openingCrate) {
					
					if (frames == 1 || frames == 60)
						GameWindow.btnOpen.setVisible(true);
					else
						GameWindow.btnOpen.setVisible(false);
					if (frames < 60)
						GameWindow.btnOpen.setText("Click To Open");
					else if (frames == 60) {
						btnOpen.setText("OK");
						GameWindow.lblPowerup.setVisible(true);
					}
					GameWindow.lblCrate.setVisible(true);
					GameWindow.lblCrate.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/textures/gui/create_icons/crate_" + frames + ".png")));
					
				}else {
					
					GameWindow.btnOpen.setVisible(false);
					GameWindow.lblCrate.setVisible(false);
					GameWindow.lblPowerup.setVisible(false);
					
				}
				
			}
			
		}
		
	}
	
	public class timer implements Runnable {

		@Override
		public void run() {
			
			while (!gameOver) {
				
				EventHandler.timer();
				
				try {
					
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		}
		
	}
}
