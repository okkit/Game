package angryPirate;

import java.awt.event.KeyEvent;

import javax.swing.Timer;

import game.Block;
import game.Constants;
import game.GamePanel;
import game.GamePiece;
import game.GamePlayer;

public class AngryPiratePanel extends GamePanel {

	public AngryPiratePanel(String dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	public AngryPiratePanel(String dataSource, int playerPosition) {
		super(dataSource, playerPosition);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		if (this.pause)
			return;

		int keyCode = e.getKeyCode();
		GamePlayer player = null;
		player = GamePlayer.getPlayer();
		int richtung;
		
		switch(keyCode) {
		// Steuerung mit Maustasten
		case KeyEvent.VK_UP:
			willDoStep(player, Constants.OBEN, Constants.STEP_DEFAULT);
			break;
		case KeyEvent.VK_LEFT:
			willDoStep(player, Constants.LINKS, Constants.STEP_DEFAULT);
			break;
		case KeyEvent.VK_DOWN:
			willDoStep(player, Constants.UNTEN, Constants.STEP_DEFAULT);
			break;
		case KeyEvent.VK_RIGHT:
			willDoStep(player, Constants.RECHTS, Constants.STEP_DEFAULT);
			break;
		// Steuerung mit w, a, s, d
		case KeyEvent.VK_W:
			willDoStep(player, Constants.OBEN, Constants.STEP_DEFAULT);
			break;
		case KeyEvent.VK_A:
			willDoStep(player, Constants.LINKS, Constants.STEP_DEFAULT);
			break;
		case KeyEvent.VK_S:
			willDoStep(player, Constants.UNTEN, Constants.STEP_DEFAULT);
			break;
		case KeyEvent.VK_D:
			willDoStep(player, Constants.RECHTS, Constants.STEP_DEFAULT);
			break;		
		}
		
		if(keyCode == KeyEvent.VK_SPACE) {
			this.shoot(player, Constants.UNTEN, Constants.STEP_DEFAULT);
		}
	}
	
	public void shoot(GamePiece player, int richtung, int step) {
		Block canon = new Block(player.getX(), player.getY());
		add(canon);
		
	}

}
