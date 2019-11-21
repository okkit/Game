package game.master;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Rectangle;

import utils.BoundsUtils;

public class GameDoor extends Canvas {

	private static final long serialVersionUID = 1L;
	private String roomDataFile;
	private int position;

	public String getRoomDataFile() {
		return roomDataFile;
	}

	public int getPosition() {
		return position;
	}
	
	public int getPositionInTheNextRoom() {
		
		switch (this.position) {
		case Constants.OBEN:
			return Constants.UNTEN;
			
		case Constants.UNTEN:
			return Constants.OBEN;
			
		case Constants.LINKS:
			return Constants.RECHTS;
			
		case Constants.RECHTS:
			return Constants.LINKS;

		default:
			return this.position;
		}
	}

	public GameDoor(int position, int x, int y, int width, int height, String roomClassName) {
		super();
		setBounds(x, y, width, height);
		setBackground(new Color(33, 66, 99));
		this.roomDataFile = roomClassName;
		this.position = position;

//		System.out.println("AM door to " + roomClassName);
	}

	public boolean pieceAtMe(Rectangle piece) {

		boolean yes = false;

//		// Bin eine Waagerechte Tür am oberen oder am unteren Rand des Raums
		if (this.position == Constants.OBEN || this.position == Constants.UNTEN) {// this.getBounds().width >
																					// this.getBounds().getHeight()) {

			if (this.position == Constants.OBEN) // (this.getBounds().y < 4) {
//				yes = piece.getBounds().y <= this.getBounds().height; // die Tür ist oben
				return BoundsUtils.overlapByMoving(piece, this.getBounds(), Constants.OBEN, 5);//(piece, this.getBounds(), Constants.OBEN);
			else
				return BoundsUtils.overlapByMoving(piece, this.getBounds(), Constants.UNTEN, 5);
//				yes = piece.getBounds().y + piece.getBounds().height + 10 > this.getBounds().y; // die Tür ist unten
		}

		// Bin eine Senkrechte Tür am linken oder am rechten Rand des Raums
		else {
			if (this.position == Constants.LINKS) // (this.getBounds().x < 4)
				yes = piece.getBounds().x - 10 < this.getBounds().width; // die Tür ist Links
			else
				yes = piece.getBounds().x + piece.getBounds().width + 10 > this.getBounds().x; // die Tür ist rechts
		}

		return yes;//? this.roomClassName : null;
	}
}
