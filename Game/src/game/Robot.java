package game;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import game.okkit.GameRules_okkit;
import utils.BoundsUtils;

public class Robot extends GamePiece implements Mortable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer timer;
	int millisec;
	int step;
	int dieAfter;

	public Robot(int x, int y) {
		this(new Point(x, y));
	}

	public Robot(Point position) {
		this(position, 10, 500);
	}

	public Robot(Point position, int step, int millisec) {
		super(Constants.ROBOT_IMAGE1, position);
		this.millisec = millisec;
		this.step = step;
		this.dieAfter = 5;
	}

	public Timer getTimer() {
		return this.timer;
	}

//	public boolean isDeadly() {
//
//		return true;
//	}
//
//	public boolean isMovable() {
//
//		return false;
//	}

	public void die() {

		Container parent = this.getParent();
		parent.remove(this);
		parent.repaint();
	}

	public void action() {

		Robot me = this;
		timer = new Timer(this.millisec, new ActionListener() {

			private int counter = 0;

			@Override
			public void actionPerformed(ActionEvent evt) {

				if ("DIE".equals(evt.getActionCommand())) {
					timer.stop();
					me.die();
					return;
				}
				
			
				Rectangle player = GamePlayer.getPlayer().getBounds();
				Rectangle my = me.getBounds();
				int step = me.step;

				int new_x = my.x;
				int new_y = my.y;

				int richtung_LR;
				int richtung_UO;

				if (my.y + my.height < player.y) {
					richtung_UO = Constants.UNTEN;
				} else {// if (my.y > player.y + player.height)
					richtung_UO = Constants.OBEN;
				}

				if (my.x + my.width < player.x) {
					richtung_LR = Constants.RECHTS;
				} else { // if (my.x > player.x + player.width)
					richtung_LR = Constants.LINKS;
				}
				
				GamePanel parent = (GamePanel) me.getParent();
				parent.willDoStep(me, richtung_UO, 30);
//
//				GamePanel parent = (GamePanel) me.getParent();
//
//				boolean stop = false;
//				for (GamePiece comp : parent.getAllGamePiece()) {
//
//					if (me.equals(comp))
//						continue;
//
//					stop = BoundsUtils.overlapByMoving(my, comp.getBounds(), richtung_LR, step)
//							|| BoundsUtils.overlapByMoving(my, comp.getBounds(), richtung_UO, step)
//							|| BoundsUtils.smallOutsideOfGreat(my, me.getParent().getBounds(), richtung_LR, step)
//							|| BoundsUtils.smallOutsideOfGreat(my, me.getParent().getBounds(), richtung_UO, step);
//
//					if (stop) {
//						if (!GameRules_okkit.interaction(me, comp))
//							counter++;
//						break;
//					}
//				}
//
//				if (richtung_UO == Constants.UNTEN) {
//					if (stop)
//						new_y = my.y - step;
//					else
//						new_y = my.y + step;
//				} else {
//					if (stop)
//						new_y = my.y + step;
//					else
//						new_y = my.y - step;
//				}
//
//				if (richtung_LR == Constants.LINKS) {
//					if (stop)
//						new_x = my.x + step;
//					else
//						new_x = my.x - step;
//				} else {
//					if (stop)
//						new_x = my.x - step;
//					else
//						new_x = my.x + step;
//				}

				if (counter == me.dieAfter) {
					((Timer) evt.getSource()).stop();
					me.die();
				} 
//				else
//					me.setBounds(new_x, new_y, my.width, my.height);
			}
		});
		timer.start();
	}
}
