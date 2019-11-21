package OffeneHose;

import game.Block;
import game.Constants;
import game.GamePanel;
import game.Robot;

public class OffeneHosePanel extends GamePanel{

	public OffeneHosePanel(String dataSource) {
		super(dataSource);			
	}
	protected void initPieces(char[][] content) {

		char ch;

		for (int i = 1; i < content.length - 3; i++) {
			for (int j = 1; j < content[i].length - 3; j++) {
				ch = content[i][j];
				if (ch == OffeneHoseConstants.CH_ROBOT) {
					this.add(new Robot(j * OffeneHoseConstants.FILE_STEP_X, i * OffeneHoseConstants.FILE_STEP_Y));
				} 
				else if (ch == OffeneHoseConstants.CH_BLOCK) {
					this.add(new Block(j * OffeneHoseConstants.FILE_STEP_X, i * OffeneHoseConstants.FILE_STEP_Y));
				}
				else if (ch == OffeneHoseConstants.CH_BLOCKFEST) {
					this.add(new BlockFest(j * OffeneHoseConstants.FILE_STEP_X, i * OffeneHoseConstants.FILE_STEP_Y));
				}
				else if (ch == OffeneHoseConstants.CH_SPAWNER) {
					this.add(new Spawner(j * OffeneHoseConstants.FILE_STEP_X, i * OffeneHoseConstants.FILE_STEP_Y));
				}
				
			}
		}
	}

}
