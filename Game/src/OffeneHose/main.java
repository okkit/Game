package OffeneHose;

import game.GameFrame;
import game.main.Game;

public class main extends Game{

	public static void main(String[] args) {

		String nameOfMapFile = "Room1";
		if (args != null && args.length > 0) {
	
			nameOfMapFile = args[0];
		}
		
		GameFrame m = new GameFrame(nameOfMapFile);
		
		m.repaint();
	}
}