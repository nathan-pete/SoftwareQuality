package domain;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class BitmapItem extends SlideItem {
	private BufferedImage bufferedImage;
	private String imageName;

	protected static final String FILE = "File ";
	protected static final String NOTFOUND = " not found on classpath";

	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		try (InputStream imageStream = getClass().getResourceAsStream("/resources/" + imageName)) {
			if (imageStream == null) {
				System.err.println(FILE + imageName + NOTFOUND);
			} else {
				bufferedImage = ImageIO.read(imageStream);
			}
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND) ;
		}
	}

	public BitmapItem() {
		this(0, null);
	}

	public String getName() {
		return imageName;
	}

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {

		if (bufferedImage == null) {
			return new Rectangle((int) (myStyle.indent * scale), 0, 0,
				(int) (myStyle.leading * scale));
		}
		return new Rectangle((int) (myStyle.indent * scale), 0,
			(int) (bufferedImage.getWidth(observer) * scale),
			((int) (myStyle.leading * scale)) +
				(int) (bufferedImage.getHeight(observer) * scale));
	}

	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		if (bufferedImage == null) {
			return;
		}
		int width = x + (int) (myStyle.indent * scale);
		int height = y + (int) (myStyle.leading * scale);
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale),
			(int) (bufferedImage.getHeight(observer)*scale), observer);
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}