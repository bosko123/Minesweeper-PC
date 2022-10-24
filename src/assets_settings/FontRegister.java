package assets_settings;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontRegister {
	
	public static Font registerFont(String path, int type, float size) {
		
		Font font = null;
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, FontRegister.class.getResourceAsStream(path)).deriveFont(type, size);
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return font;
		
	}
	
}
