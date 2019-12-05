package game;

import java.awt.Point;

public class Schlüssel extends GamePiece{
	boolean sammeln;
	
	public Schlüssel(Point position, boolean sammeln) {
		super((sammeln) ? Constants.KEY_IMAGE : Constants.BLOCK_IMAGE, position);
	}
	
	public Schlüssel(int x, int y, boolean sammeln) {
		super((sammeln) ? Constants.KEY_IMAGE : Constants.KEY2_IMAGE, new Point(x, y));
	}

}
