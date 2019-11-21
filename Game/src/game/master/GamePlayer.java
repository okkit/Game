package game.master;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GamePlayer extends GamePiece implements Mortable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GamePlayer instance;

	private GamePlayer(Point position) {
		super(Constants.PLAYER_IMAGE, position);
	}

	public static GamePlayer playerAtPosition(Point position) {
		if (GamePlayer.instance == null) {
			GamePlayer.instance = new GamePlayer(position);
		}
		else {
			Rectangle rect = GamePlayer.instance.getBounds();
			GamePlayer.instance.setBounds(new Rectangle(position.x, position.y, rect.width, rect.height));
		}
		return GamePlayer.instance;
	}
	

	public static GamePlayer getPlayer() {
		if (GamePlayer.instance == null) {
			GamePlayer.instance = new GamePlayer(new Point(0,0));
		}
		return GamePlayer.instance;
	}

//	public boolean isMortal() {
//
//		return false;
//	}

	public void die() {

		GamePlayer me = this;
		Timer timer = new Timer(100, new ActionListener() {
			private int counter = 1;

			@Override
			public void actionPerformed(ActionEvent evt) {

				if (counter == 5) {
					((Timer) evt.getSource()).stop();
					((GamePanel) me.getParent()).pause = false;
					((GamePanel) me.getParent()).resetGame();
				}

				else {
					me.resetIcon(me.getPengImage(counter));
					counter++;
					me.repaint();
				}
			}
		});
		((GamePanel) me.getParent()).pause = true;
		timer.start();
	}
	
	ImageIcon getPengImage(int i) {

//		String img = "/CatPeng" + i + ".png";
		String img = "/gif_tot.gif";
//		String img = "/CatSmall.png";
		return new ImageIcon(getClass().getResource(img));
	}
}
