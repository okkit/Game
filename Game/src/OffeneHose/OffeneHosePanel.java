package OffeneHose;

import game.Block;
import game.BlockFest;
import game.Constants;
import game.GamePanel;
import game.Robot;
import game.Spawner;

public class OffeneHosePanel extends GamePanel{

	public OffeneHosePanel(String dataSource) {
		super(dataSource);			
	}
	protected void initPieces(char[][] content) {

		char ch;

		for (int i = 1; i < content.length - 3; i++) {
			for (int j = 1; j < content[i].length - 3; j++) {
				ch = content[i][j];
				if (ch == Constants.CH_ROBOT) {
					this.add(new Robot(j * Constants.FILE_STEP_X, i * Constants.FILE_STEP_Y));
				} 
				else if (ch == Constants.CH_BLOCK) {
					this.add(new Block(j * Constants.FILE_STEP_X, i * Constants.FILE_STEP_Y));
				}
				else if (ch == Constants.CH_BLOCKFEST) {
					this.add(new BlockFest(j * Constants.FILE_STEP_X, i * Constants.FILE_STEP_Y));
				}
				else if (ch == Constants.CH_SPAWNER) {
					this.add(new Spawner(j * Constants.FILE_STEP_X, i * Constants.FILE_STEP_Y));
				}
				
			}
		}
	}

}
