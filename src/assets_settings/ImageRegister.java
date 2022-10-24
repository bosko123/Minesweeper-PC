package assets_settings;

import java.awt.Image;

import javax.imageio.ImageIO;

import game.GameWindow;

public class ImageRegister {
	
	public static Image[] images = new Image[12];
	public static Image[] imagesXRay = new Image[2];
	public static Image imageNuke = null;
	public static Image[] imagesCrates = new Image[9];
	public static Image[] imagesBroken = new Image[10];
	public static Image[] flagImages = new Image[3];
	public static String assets = "/assets/textures/tiles/";
	public static Image tile_no_xray = null;
	
	public static void imageLoader() {
		
		try {
			
			//normal icons
			Image tile_closed = ImageIO.read(GameWindow.class.getResource(assets + "tile_closed.png"));
			Image flag_0 = ImageIO.read(GameWindow.class.getResource(assets + "flag_0.png"));
			Image flag_1 = ImageIO.read(GameWindow.class.getResource(assets + "flag_1.png"));
			
			Image tile_open = ImageIO.read(GameWindow.class.getResource(assets + "tile_open.png"));
			Image tile_1 = ImageIO.read(GameWindow.class.getResource(assets + "tile_1.png"));
			Image tile_2 = ImageIO.read(GameWindow.class.getResource(assets + "tile_2.png"));
			Image tile_3 = ImageIO.read(GameWindow.class.getResource(assets + "tile_3.png"));
			Image tile_4 = ImageIO.read(GameWindow.class.getResource(assets + "tile_4.png"));
			Image tile_5 = ImageIO.read(GameWindow.class.getResource(assets + "tile_5.png"));
			Image tile_6 = ImageIO.read(GameWindow.class.getResource(assets + "tile_6.png"));
			Image tile_7 = ImageIO.read(GameWindow.class.getResource(assets + "tile_7.png"));
			Image tile_8 = ImageIO.read(GameWindow.class.getResource(assets + "tile_8.png"));
			Image tile_mine_on = ImageIO.read(GameWindow.class.getResource(assets + "tile_mine_on.png"));
			Image tile_mine_off = ImageIO.read(GameWindow.class.getResource(assets + "tile_mine_off.png"));
			Image tile_mine_flag = ImageIO.read(GameWindow.class.getResource(assets + "tile_mine_flag.png"));
			
			images[0] = tile_open;
			images[1] = tile_1;
			images[2] = tile_2;
			images[3] = tile_3;
			images[4] = tile_4;
			images[5] = tile_5;
			images[6] = tile_6;
			images[7] = tile_7;
			images[8] = tile_8;
			images[9] = tile_mine_on;
			images[10] = tile_mine_off;
			images[11] = tile_mine_flag;
			
			flagImages[0] = tile_closed;
			flagImages[1] = flag_0;
			flagImages[2] = flag_1;
			
		} catch (Exception e) {
			
		}
		
	}
	
	public static void imageCrateLoader() {
		
		try {
			
			//crazy mode icons
			imageNuke = ImageIO.read(GameWindow.class.getResource(assets + "tile_mine_nuke.png"));
			
			Image tile_0_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_0_crate.png"));
			Image tile_1_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_1_crate.png"));
			Image tile_2_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_2_crate.png"));
			Image tile_3_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_3_crate.png"));
			Image tile_4_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_4_crate.png"));
			Image tile_5_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_5_crate.png"));
			Image tile_6_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_6_crate.png"));
			Image tile_7_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_7_crate.png"));
			Image tile_8_crate = ImageIO.read(GameWindow.class.getResource(assets + "crates/tile_8_crate.png"));
			
			imagesCrates[0] = tile_0_crate;
			imagesCrates[1] = tile_1_crate;
			imagesCrates[2] = tile_2_crate;
			imagesCrates[3] = tile_3_crate;
			imagesCrates[4] = tile_4_crate;
			imagesCrates[5] = tile_5_crate;
			imagesCrates[6] = tile_6_crate;
			imagesCrates[7] = tile_7_crate;
			imagesCrates[8] = tile_8_crate;
			
			Image tile_0_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_0_cracked.png"));
			Image tile_1_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_1_cracked.png"));
			Image tile_2_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_2_cracked.png"));
			Image tile_3_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_3_cracked.png"));
			Image tile_4_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_4_cracked.png"));
			Image tile_5_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_5_cracked.png"));
			Image tile_6_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_6_cracked.png"));
			Image tile_7_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_7_cracked.png"));
			Image tile_8_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_8_cracked.png"));
			Image tile_mine_cracked = ImageIO.read(GameWindow.class.getResource(assets + "broken/tile_mine_cracked.png"));
			
			imagesBroken[0] = tile_0_cracked;
			imagesBroken[1] = tile_1_cracked;
			imagesBroken[2] = tile_2_cracked;
			imagesBroken[3] = tile_3_cracked;
			imagesBroken[4] = tile_4_cracked;
			imagesBroken[5] = tile_5_cracked;
			imagesBroken[6] = tile_6_cracked;
			imagesBroken[7] = tile_7_cracked;
			imagesBroken[8] = tile_8_cracked;
			imagesBroken[9] = tile_mine_cracked;
			
			Image tile_0_xray = ImageIO.read(GameWindow.class.getResource(assets + "xray/tile_0_xray.png"));
			Image tile_9_xray = ImageIO.read(GameWindow.class.getResource(assets + "xray/tile_9_xray.png"));
			tile_no_xray = ImageIO.read(GameWindow.class.getResource("/assets/textures/tiles/xray/tile_no_xray.png"));
			
			imagesXRay[0] = tile_0_xray;
			imagesXRay[1] = tile_9_xray;
			imagesXRay[2] = tile_no_xray;
			
		} catch (Exception e) {
			
		}
		
	}
	
}
