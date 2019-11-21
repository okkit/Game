package game;

import java.awt.Point;

import javax.swing.Timer;

public class Spawner extends GamePiece {

	Timer timer;
	int sekunden;

	
	private static final long serialVersionUID = 1L;

	public Spawner(Point position) {
		super(Constants.SPAWNER_IMAGE, position);
	}
	
	public Spawner(int x, int y) {
		super(Constants.SPAWNER_IMAGE, new Point(x, y));
	}
}