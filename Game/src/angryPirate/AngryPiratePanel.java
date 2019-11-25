package angryPirate;

import java.awt.event.KeyEvent;

import game.Bullet;
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
	public void keyTyped(KeyEvent e) {
		// Steuerung aus GamePanel.java soll nicht mehr funktionieren
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();
		GamePlayer player = null;
		player = GamePlayer.getPlayer();
		int richtung = Constants.RECHTS;
		
		switch(keyCode) {
		// Steuerung mit Pfeiltasten
		case KeyEvent.VK_UP:
			willDoStep(player, Constants.OBEN, Constants.STEP_DEFAULT);
			richtung = Constants.OBEN;
			break;
		case KeyEvent.VK_LEFT:
			willDoStep(player, Constants.LINKS, Constants.STEP_DEFAULT);
			richtung = Constants.LINKS;
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
			richtung = Constants.OBEN;
			break;
		case KeyEvent.VK_A:
			willDoStep(player, Constants.LINKS, Constants.STEP_DEFAULT);
			richtung = Constants.LINKS;
			break;
		case KeyEvent.VK_S:
			willDoStep(player, Constants.UNTEN, Constants.STEP_DEFAULT);
			break;
		case KeyEvent.VK_D:
			willDoStep(player, Constants.RECHTS, Constants.STEP_DEFAULT);
			break;		
		}
		
		if(keyCode == KeyEvent.VK_SPACE) {
			this.shoot(player, richtung, Constants.STEP_DEFAULT);
		}
	}
	
	public void shoot(GamePiece player, int richtung, int step) {
		Bullet canon = new Bullet(player.getX(), player.getY(), richtung, step);
		add(canon);
		canon.goKill(canon.getX(), canon.getY(), richtung, step);
		
	}

}
