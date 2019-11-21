package game.master;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class GameFrame extends JFrame {

	/**
	 * Diese Klasse ist der Einstieg ins Programm.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 */
	public GameFrame(String map) {

		setLayout(null);
		setTitle(Constants.GAME_NAME);
		setBounds(Constants.FRAME_X, Constants.FRAME_Y, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setVisible(true);
		getContentPane()
				.setBackground(new Color(Constants.FRAME_C_RED, Constants.FRAME_C_GREEN, Constants.FRAME_C_BLUE));

		/**
		 * Fenster schließen
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}
		});
		
		add(new GamePanel(map)).requestFocusInWindow();
	}

	/**
	 * Das alte Panel löschen, ein neues hinzufügen (zu JFrame = Hauptfenster)
	 */

	public void nextRoom(GamePanel previousRoom, GameDoor door) {
		
//		String nextRoomClass = door.getRoomClassName();
//		Class<?> room = null;
//		try {
//			room = Class.forName(nextRoomClass);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return;
//		}
//		
//		 // Get the Constructor object of the public constructor
//        // that matches the specified parameterTypes
//		Constructor<?> constructor = null;
//        try {
//			constructor = room.getConstructor();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        if (constructor == null)
//        	return;
//	
//        GamePanel nextRoomPanel = null;
//        try {
//        	nextRoomPanel = (GamePanel) constructor.newInstance();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return;
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        if (nextRoomPanel == null)
//        	return;
//        
        this.remove(previousRoom);
        GamePanel nextRoomPanel = new GamePanel(door.getRoomDataFile(), door.getPosition());
        this.add(nextRoomPanel).requestFocusInWindow();
        
//        this.add(((Component) constructor.newInstance(new Point(100, 500))).requestFocusInWindow();
//		((GamePanel)this.add(constructor.newInstance(new Point(100, 500))).requestFocusInWindow();
		this.repaint();
		
	}
}