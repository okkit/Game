package game;

import java.awt.Point;

public class BlockFest extends GamePiece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlockFest(Point position) {
		super(Constants.BLOCKFEST_IMAGE, position);
	}
	
	public BlockFest(int x, int y) {
		super(Constants.BLOCKFEST_IMAGE, new Point(x, y));
	}
}
