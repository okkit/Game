package game;

import java.awt.Point;

public class Schl�ssel extends GamePiece{
	boolean sammeln;
	
	public Schl�ssel(Point position, boolean sammeln) {
		super((sammeln) ? Constants.KEY_IMAGE : Constants.BLOCK_IMAGE, position);
	}
	
	public Schl�ssel(int x, int y, boolean sammeln) {
		super((sammeln) ? Constants.KEY_IMAGE : Constants.KEY2_IMAGE, new Point(x, y));
	}

}
