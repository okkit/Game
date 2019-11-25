package game;

import java.awt.Point;

public class Schlüssel extends GamePiece{
	
	public Schlüssel(Point position) {
		super(Constants.KEY_IMAGE, position);
	}
	
	public Schlüssel(int x, int y) {
		super(Constants.KEY_IMAGE, new Point(x, y));
	}
	

}
