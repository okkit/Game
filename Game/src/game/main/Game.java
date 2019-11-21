package game.main;

import game.master.GameFrame;

//https://allaboutmynonexistedworld.wordpress.com/2014/02/05/eclipse-git-merging-branch-to-master/
public class Game {

	public static void main(String[] args) {

		String nameOfMapFile = "Room1";
		if (args != null && args.length > 0) {
	
			nameOfMapFile = args[0];
		}
		
		GameFrame m = new GameFrame(nameOfMapFile);
		
		m.repaint();
	}
}