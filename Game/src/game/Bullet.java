package game;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import game.okkit.GameRules_okkit;
import utils.BoundsUtils;

public class Bullet extends GamePiece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Timer timer;
	
	public Bullet(Point position) {
		super(Constants.BLOCK_IMAGE, position);
	}
	
	public Bullet(int x, int y, int direction, int step) {
		super(Constants.BLOCK_IMAGE, new Point(x, y));
	}
	
	public Timer getTimer() {
		return this.timer;
	}
	
	public void die() {
		Container parent = this.getParent();
		parent.remove(this);
		parent.repaint();
	}
	
	public void goKill(GamePiece bullet, int direction, int step) {
		
		Bullet me = this;
		timer = new Timer(5, new ActionListener() {
			
			int x = bullet.getX();
			int y = bullet.getY();
		
			@Override
			public void actionPerformed(ActionEvent evt) {
				switch (direction) {
				case Constants.OBEN:
					if(y>0) {
						y -= step;
						bullet.setBounds(x, y, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT);
						repaint();
					}
					else {
						timer.stop();
						me.die();
						return;
					}
					break;
				case Constants.LINKS:
					if(x>0) {
						x -= step;
						bullet.setBounds(x, y, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT);
						repaint();
					}
					else {
						timer.stop();
						me.die();
						return;
					}
					break;
				case Constants.UNTEN:
					if(y<Constants.PANEL_HEIGHT-15) {
						y += step;
						bullet.setBounds(x, y, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT);
						repaint();
					}
					else {
						timer.stop();
						me.die();
						return;
					}
					break;
				case Constants.RECHTS:
					if(x<Constants.PANEL_WIDTH-15) {
						x += step;
						bullet.setBounds(x, y, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT);
						repaint();
					}
					else {
						timer.stop();
						me.die();
						return;
					}
					break;
				}
			}
				
		});
		timer.start();
	}
}