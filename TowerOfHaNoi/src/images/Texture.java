package images;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet iconSheet, objectSheet;
	
	private BufferedImage iconImage = null;
	private BufferedImage objectImage = null;
	
	public BufferedImage rodImg[] = new BufferedImage[2];
	public BufferedImage diskImg[] = new BufferedImage[3];
	public BufferedImage iconImg[] = new BufferedImage[20];
	public BufferedImage background;
	
	public Texture() {
		LoadImage loader = new LoadImage();
		objectImage = loader.loadImage("/object.png");
		background = loader.loadImage("/background.png");
		iconImage = loader.loadImage("/icons.png");
		
		iconSheet = new SpriteSheet(iconImage);
		objectSheet = new SpriteSheet(objectImage);
		
		getTextures();
	}
	
	private void getTextures() {
		
		//game objects
		rodImg[0] = objectSheet.grabImage(1, 1, 32, 32*8);
		rodImg[1] = objectSheet.grabImage(2, 1, 32, 32*8);
		
		diskImg[0] = objectSheet.grabImage(1, 6, 29*8, 29*2);
		diskImg[1] = objectSheet.grabImage(2, 6, 29*8, 29*2);
		diskImg[2] = objectSheet.grabImage(3, 6, 29*8, 29*2);
		
		//icon
		for(int i = 0; i < 16; i++) {
			if(i >= 8)
				iconImg[i] = iconSheet.grabImage(i+1-8, 2, 50, 50);
			else
				iconImg[i] = iconSheet.grabImage(i+1, 1, 50, 50);
		}
	}
}