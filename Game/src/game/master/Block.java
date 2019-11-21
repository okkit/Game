package game.master;

import java.awt.Point;

public class Block extends GamePiece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Block(Point position) {
		super(Constants.BLOCK_IMAGE, position);
	}
	
	public Block(int x, int y) {
		super(Constants.BLOCK_IMAGE, new Point(x, y));
	}
}
