package power_ups;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import assets_settings.Responsive;
import game.GameWindow;
import power_up_objects.MinusTime;
import power_up_objects.PlusTime;

public class PowerUpsEvents {
	
	public static PlusTime plusTime = new PlusTime(30);
	public static MinusTime minusTime = new MinusTime(30);
	
	public static final int DRILL_USES = 7;
	public static final int XRAY_TIME = 3;
	
	public static Drill drill = new Drill(DRILL_USES);
	public static XRay xRay = new XRay(XRAY_TIME);
	
	public static int xNukes = 0;
	
	public static void getPowerUp() {
		
		//generate number to select random powerup
		int x = RandomPowerUp.randomPowerUp();
		//int x = 5;
		
		switch (x) {
		case 0:
			RandomPowerUp.minusTime();
			
			try {
				Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/minusTime.png"));
				GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		case 1:
			GameWindow.timeInSec += plusTime.getTimeInSec();
			GameWindow.secunds += plusTime.getTimeInSec();
			
			try {
				Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/plusTime.png"));
				GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		case 3:
			RandomPowerUp.placeNuke();
			
			try {
				Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/tiles/tile_mine_nuke.png"));
				GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
				PowerUpsEvents.xNukes++;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		case 5:
			RandomPowerUp.setNoXray();
			
			try {
				Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/no_xray_icon.png"));
				GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		default:
			int randomPowerUp = (int)(Math.random()*2+0);
			GameWindow.allPowerUps[randomPowerUp]++;
			
			switch (randomPowerUp) {
			case 0:
				GameWindow.lblDrillsCount.setText(GameWindow.allPowerUps[0] + "");
				
				try {
					Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/drill_icon.png"));
					GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
			case 1:
				GameWindow.lblXRaysCount.setText(GameWindow.allPowerUps[1] + "");
				
				try {
					Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/xray_icon.png"));
					GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
			default:
				break;
			}
			
			break;
		
		}
		
		System.out.println(x);
		
	}
	
}
