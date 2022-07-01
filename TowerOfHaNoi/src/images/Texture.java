package images;

import java.awt.image.BufferedImage;

public class Texture {
	public BufferedImage rodImg[] = new BufferedImage[2];
	public BufferedImage diskImg[] = new BufferedImage[3];
	public BufferedImage iconNormal[] = new BufferedImage[10];
	public BufferedImage iconHover[] = new BufferedImage[10];
	public BufferedImage background;
	
	LoadImage loader = new LoadImage();
	/*
	 * load hình ảnh thông qua class LoadImage và lưu vào những mảng riêng biệt
	 * normal là hình hình ảnh ở trạng thái bình thường. Khi ta di chuyển chuột hoặc click vào object thì sẽ chuyển sang hover icon hoặc clicked icon
	 */
	public Texture() {
		background = loader.loadImage("/background/background.png");
		
		getObjects();
		getIcons();
	}
	
	private void getObjects() {
		//disks
		diskImg[0] = loader.loadImage("/disks/disk_normal.png");
		diskImg[1] = loader.loadImage("/disks/disk_hover.png");
		diskImg[2] = loader.loadImage("/disks/disk_clicked.png");
		
		//rods
		rodImg[0] = loader.loadImage("/rods/rod_normal.png");
		rodImg[1] = loader.loadImage("/rods/rod_hover.png");
	}
	
	/*
	 * 1: add
	 * 2: back
	 * 3: exit
	 * 4: play
	 * 5: hint
	 * 6: home
	 * 7: minus
	 * 8: next
	 * 9: pause
	 */
	private void getIcons() {
		for(int i = 0; i < 9; i++) {
			//normal icon
			iconNormal[i] = loader.loadImage("/icons/normal/icon (" + (i+1) + ").png");
			
			//hover icon
			iconHover[i] = loader.loadImage("/icons/hover/icon (" + (i+1) + ").png");
		}
	}
}