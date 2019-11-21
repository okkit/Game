package utils;

import java.awt.Rectangle;

import game.master.Constants;

public class BoundsUtils {

	public static boolean overlapByMoving(Rectangle moving, Rectangle standing, int richtung, int step) {
		
		if (!BoundsUtils.isInTheWay(moving, standing, richtung))
			return false;
		int delta = 0;

		int moving_x_links = moving.x;
		int moving_x_rechts = moving.x + moving.width;
		int moving_y_oben = moving.y;
		int moving_y_unten = moving.y + moving.height;

		int standing_x_links = standing.x;
		int standing_x_rechts = standing.x + standing.width;
		int standing_y_oben = standing.y;
		int standing_y_unten = standing.y + standing.height;
	
		if (richtung == Constants.OBEN || richtung == Constants.UNTEN) {

			if (richtung == Constants.OBEN)
				delta = moving_y_oben - step - standing_y_unten;
			else if (richtung == Constants.UNTEN)
				delta = standing_y_oben - (moving_y_unten + step);

			if (delta > 0) {
				return false;
			}

			return 	   (moving_x_links > standing_x_links && moving_x_links < standing_x_rechts)
					|| (moving_x_rechts > standing_x_links && moving_x_rechts < standing_x_rechts)
					|| (standing_x_links > moving_x_links && standing_x_links < moving_x_rechts)
					|| (standing_x_rechts > moving_x_links && standing_x_rechts < moving_x_rechts)
					|| (moving_x_links == standing_x_links && moving_x_rechts == standing_x_rechts);
			
		} else if (richtung == Constants.LINKS || richtung == Constants.RECHTS) {

			if (richtung == Constants.LINKS)
				delta = moving_x_links - step - standing_x_rechts;
			else if (richtung == Constants.RECHTS)
				delta = standing_x_links - moving_x_rechts - step;

			if (delta > 0) {
				return false;
			}
			
			
			return 		(moving_y_oben > standing_y_oben && moving_y_oben < standing_y_unten)
					|| (moving_y_unten > standing_y_oben && moving_y_unten < standing_y_unten)
					|| (standing_y_oben > moving_y_oben && standing_y_oben < moving_y_unten)
					|| (standing_y_unten > moving_y_oben && standing_y_unten < moving_y_unten)
					|| (moving_y_oben == standing_y_oben && moving_y_unten == standing_y_unten);
		}
		return false;
	}

	public static boolean overlapByMoving(Rectangle moving, Rectangle standing, int step) {

		return BoundsUtils.overlapByMoving(moving, standing, Constants.OBEN, step)
				|| BoundsUtils.overlapByMoving(moving, standing, Constants.UNTEN, step)
				|| BoundsUtils.overlapByMoving(moving, standing, Constants.LINKS, step)
				|| BoundsUtils.overlapByMoving(moving, standing, Constants.RECHTS, step);
	}

	/**
	 * 
	 * @param small
	 * @param great
	 * @param richtung
	 * @param step
	 * @return
	 */
	public static boolean smallOutsideOfGreat(Rectangle small, Rectangle great, int richtung, int step) {

		if (richtung == Constants.OBEN) {
			return small.getY() - step < 0;
		} 
		else if (richtung == Constants.LINKS) {
			return small.getX() - step <= 0;
		}
		else if (richtung == Constants.UNTEN) {
			return small.getY() + small.getHeight() + step > great.height;
		} 
		else if (richtung == Constants.RECHTS) {
			return small.getX() + small.getWidth() + step >= great.width;
		}

		return false;
	}
	
	public static boolean smallOutsideOfGreat(Rectangle small, Rectangle great, int richtung, int step, String who) {

		if (richtung == Constants.OBEN) {
			return small.getY() - step < 0;
		} 
		else if (richtung == Constants.LINKS) {
			return small.getX() - step <= 0;
		}
		else if (richtung == Constants.UNTEN) {
			return small.y + small.height + step > great.height - great.y;
		} 
		else if (richtung == Constants.RECHTS) {
			return small.getX() + small.getWidth() + step >= great.width;
		}

		return false;
	}

	public static int distance(Rectangle moving, Rectangle standing, int richtung) {

		int moving_x_links = moving.x;
		int moving_x_rechts = moving.x + moving.width;
		int moving_y_oben = moving.y;
		int moving_y_unten = moving.y + moving.height;

		int standing_x_links = standing.x;
		int standing_x_rechts = standing.x + standing.width;
		int standing_y_oben = standing.y;
		int standing_y_unten = standing.y + standing.height;

		if (richtung == Constants.OBEN)
			return moving_y_oben - standing_y_unten;
		else if (richtung == Constants.UNTEN)
			return standing_y_oben - moving_y_unten;
		else if (richtung == Constants.LINKS)
			return moving_x_links - standing_x_rechts;
		else if (richtung == Constants.RECHTS)
			return standing_x_links - moving_x_rechts;
		else
			return -1;
	}
	
	public static boolean isInTheWay(Rectangle moving, Rectangle standing, int richtung) {
		
		switch (richtung) {
		case Constants.OBEN:
			return moving.y >= standing.y + standing.height;
		case Constants.UNTEN:
			return moving.y + moving.height <= standing.y;
		case Constants.RECHTS:
			return moving.x + moving.width <= standing.x;
		case Constants.LINKS:
			return moving.x >= standing.x + standing.height;
		default:
			return false;
		}
	}

	public static double distanceToBound(Rectangle small, Rectangle great, int richtung) {

		if (richtung == Constants.OBEN)
			return small.getY();
		else if (richtung == Constants.UNTEN)
			return great.getHeight() - (small.getY() + small.getHeight());
		else if (richtung == Constants.LINKS)
			return small.getX() - great.getX();
		else if (richtung == Constants.RECHTS)
			return great.getWidth() - (small.getX() + small.getWidth());
		else
			return 0;
	}

}
