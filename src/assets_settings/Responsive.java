package assets_settings;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Responsive {

	public static Image resizeImage(Image image, int width, int height) {
		
		BufferedImage bufferedImage = null;
		
		bufferedImage = (BufferedImage)image;
		
		image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return image;
		
	}

	public static int getOffsetX(int rows, int gridWidth, int width) {
		
		int offsetX = width * rows;
		
		offsetX = offsetX - gridWidth;
		
		if (offsetX < 0) offsetX *= -1;
		
		offsetX /= 2;
		
		return offsetX;
		
	}

	public static int getOffsetY(int cols, int gridHeight, int height) {
		
		int offsetY = height * cols;
		
		offsetY = offsetY - gridHeight;
		
		if (offsetY < 0) offsetY *= -1;
		
		offsetY /= 2;
		
		return offsetY;
		
	}
	
}
