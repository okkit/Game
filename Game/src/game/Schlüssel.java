package game;

import java.awt.Point;

public class Schl�ssel extends GamePiece{
	
	public Schl�ssel(Point position) {
		super(Constants.KEY_IMAGE, position);
	}
	
	public Schl�ssel(int x, int y) {
		super(Constants.KEY_IMAGE, new Point(x, y));
	}
	

}
