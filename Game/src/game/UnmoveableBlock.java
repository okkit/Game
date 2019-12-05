package game;

import java.awt.Point;

public class UnmoveableBlock extends GamePiece {

	private static final long serialVersionUID = 1L;

	public UnmoveableBlock(Point position) {
		super(Constants.UNMOVEABLEBLOCK_IMAGE, position);
	}
	
	public UnmoveableBlock(int x, int y) {
		super(Constants.UNMOVEABLEBLOCK_IMAGE, new Point(x, y));
	}
}
