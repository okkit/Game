package game.okkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Actions;
import game.Block;
import game.GamePlayer;
import game.Robot;
import game.rules.GameRules;

public class GameRules_okkit extends GameRules {

	public static boolean interaction(Object attacker, Object attacked) {

		if (attacker instanceof Robot) {
			if (attacked instanceof GamePlayer) {
				((GamePlayer) attacked).die();
//				System.out.println("Robot attakiert Player");
				return true;
			} 
			else if (attacked instanceof Robot) {
//				System.out.println("Robot attakiert Robot");
				
				long when = System.currentTimeMillis();

				for (ActionListener listener : ((Robot) attacker).getActionListeners()) {
				    listener.actionPerformed(new ActionEvent((Robot) attacker, ActionEvent.ACTION_PERFORMED, "DIE", when, 0));
				}
				for (ActionListener listener : (((Robot) attacked)).getTimer().getActionListeners()) {
				    listener.actionPerformed(new ActionEvent(((Robot) attacked).getTimer(), ActionEvent.ACTION_PERFORMED, "DIE", when, 0));
				}
				return true;
			}
		}
		else if (attacker instanceof GamePlayer) {
			if (attacked instanceof Robot) {
				((GamePlayer) attacker).die();
				System.out.println("Player attakiert Robot");
				return true;
			}
		}
		
		return false;
	}
	
	public static Actions interaction() {
		return Actions.DIE;
	}
	
	public static boolean movable(Object moving, Object standing) {

		if (moving instanceof Robot) {
			if (standing instanceof GamePlayer) {
				return false;
			} 
			else if (standing instanceof Block) {
				return true;
			}
		}
		else if (moving instanceof GamePlayer) {
			if (standing instanceof Block) {
				return true;
			}
			if (standing instanceof Block) {
				return true;
			}
		}
		else if (moving instanceof Block) {
			if (standing instanceof Robot) {
				return true;
			}
			if (standing instanceof Block) {
				return true;
			}
		}
		
		return false;
	}

}
