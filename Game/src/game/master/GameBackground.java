package game.master;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameBackground extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image background;

	public GameBackground(String img) {

		Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource(img));

		System.out.println("image" + image.getWidth(null));
		this.background = image;
	}

	public void paint(Graphics g) {

		if (this.background == null)
			super.paint(g);
		else
			g.drawImage(background, 0, 0, null);
		return;
	}

}
