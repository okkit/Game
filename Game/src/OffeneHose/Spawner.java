package OffeneHose;

import java.awt.Point;

import javax.swing.Timer;

import game.GamePiece;

public class Spawner extends GamePiece {

	Timer timer;
	int sekunden;

	
	private static final long serialVersionUID = 1L;

	public Spawner(Point position) {
		super(OffeneHoseConstants.SPAWNER_IMAGE, position);
	}
	
	public Spawner(int x, int y) {
		super(OffeneHoseConstants.SPAWNER_IMAGE, new Point(x, y));
	}
}