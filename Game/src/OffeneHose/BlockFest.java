package OffeneHose;

import java.awt.Point;

import game.GamePiece;

public class BlockFest extends GamePiece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlockFest(Point position) {
		super(OffeneHoseConstants.BLOCKFEST_IMAGE, position);
	}
	
	public BlockFest(int x, int y) {
		super(OffeneHoseConstants.BLOCKFEST_IMAGE, new Point(x, y));
	}
}
