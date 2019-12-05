package game;

import java.awt.Point;

public class Coin extends GamePiece {

	private static final long serialVersionUID = 1L;

	public Coin(Point position) {
		super(Constants.COIN_IMAGE, position);
	}
	
	public Coin(int x, int y) {
		super(Constants.COIN_IMAGE, new Point(x, y));
	}
	
}
