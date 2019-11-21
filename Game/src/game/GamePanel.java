package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import game.okkit.GameRules_okkit;
import utils.BoundsUtils;
import utils.FileUtils;

public class GamePanel extends JPanel implements KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;

//	GamePiece player;
	boolean pause;
	
	/**
	 * 
	 * @param dataSource Name der Mappingdatei
	 */
	public GamePanel(String dataSource) {

		this(dataSource, -1);
	}

	/**
	 * 
	 * @param dataSource
	 * @param playerPosition bezieht sich auf UNTEN, OBEN, LINKS oder RECHTS
	 */
	public GamePanel(String dataSource, int playerPosition) {

		super();

		pause = false;

		setFocusable(true);

		setBackground(new Color(Constants.PANEL_C_RED, Constants.PANEL_C_GREEN, Constants.PANEL_C_BLUE));
		setBounds(Constants.PANEL_X, Constants.PANEL_Y, Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);

		setMinimumSize(new Dimension(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT));

		this.addKeyListener(this);
		char[][] content = this.readContent(dataSource);

		if (content == null) {

			return;
		}

		initDoors(content);
		initPlayer(content, playerPosition);
		initPieces(content);
		startRobots();
	}

	protected void initPlayer(char[][] content, int playerPosition) {
		
		char ch;
		
		if (playerPosition < 0) {
			for (int i = 1; i < content.length - 3; i++) {
				for (int j = 1; j < content[i].length - 3; j++) {
					ch = content[i][j];
					if (ch == Constants.CH_PLAYER) {
						this.add(GamePlayer.playerAtPosition(new Point(j * Constants.FILE_STEP_X, i * Constants.FILE_STEP_Y))).repaint();
						return;
					} 
					
				}
			}
		}
		
		int x = 0;
		int y = 0;
		
		GameDoor door = this.getDoor(playerPosition);
		Rectangle rect = door.getBounds();
		switch (door.getPosition()) {
		case Constants.OBEN:
			y = 20;
			x = rect.x + rect.width/2;
			break;
		case Constants.UNTEN:
			y = this.getBounds().height - 100;
			x = rect.x + rect.width/2;
			break;
		case Constants.LINKS:
			x = 20;
			y = rect.y + rect.height/2;
			break;
		case Constants.RECHTS:
			x = this.getBounds().width - 100;
			y = rect.y + rect.height/2;
			break;
		}

		this.add(GamePlayer.playerAtPosition(new Point(x, y))).repaint();
	}

	protected char[][] readContent(String source) {

		char[][] content = null;
		try {
			content = FileUtils.readText(source+".txt");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
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
			}
		}
	}

	protected void initDoors(char[][] content) {

		int begin = 0;
		int end = 0;
		char ch;

		boolean door = false;
		String doorClassName = "";

		System.out.println("LETZTER BUCHSTABE IST>" + content[0][content[0].length - 1] + "<");

		// Türen am oberen Rand (die erste Zeile) Die Anzahl der Buchstaben =
		// content[0].length
		for (int i = 0; i < content[0].length - 1; i++) {
			ch = content[0][i];
			if (ch != Constants.CH_NOTHING) {
				if (!door) {
//					System.out.println(door + "------------ oben ---------- beginnt ");
					begin = i;
					door = true;
				}
				doorClassName += ch;
			} else if (door) {
				end = i - 1;
				this.add(new GameDoor(Constants.OBEN, begin * Constants.FILE_STEP_X, 0, (end - begin) * Constants.FILE_STEP_X,
						Constants.DOOR_THICKNESS, doorClassName));
				door = false;

//				System.out.println(door + "---------------------- oben endet " + doorClassName);
				doorClassName = "";
			}
		}

		// Türen am unteren Rand (die letzte Zeile im File). Die Anzahl der Zeilen =
		// content.length;
		for (int i = 0; i < content[content.length - 1].length - 1; i++) {
			ch = content[content.length - 1][i];
			if (ch != Constants.CH_NOTHING) {
				if (!door) {
//					System.out.println(door + "------------ unten ---------- beginnt ");
					begin = i;
					door = true;
				}
				doorClassName += ch;
			} else if (door) {
				end = i - 1;
				this.add(new GameDoor(Constants.UNTEN, begin * Constants.FILE_STEP_X, this.getBounds().height - Constants.DOOR_THICKNESS,
						(end - begin) * Constants.FILE_STEP_X, Constants.DOOR_THICKNESS, doorClassName));
				door = false;
//				System.out.println(door + "---------------------- unten endet " + doorClassName);
				doorClassName = "";
			}
		}

		// Türen am linken Rand. Schleife über Zeilen
		for (int i = 0; i < content.length; i++) {
			ch = content[i][0];
			if (ch != Constants.CH_NOTHING) {
				if (!door) {
					begin = i;
//					System.out.println(door + "------------ links ---------- beginnt ");
					door = true;
				}
				doorClassName += ch;
			} else if (door) {
				end = i - 1;
				this.add(new GameDoor(Constants.LINKS, 0, begin * Constants.FILE_STEP_Y, Constants.DOOR_THICKNESS,
						(end - begin) * Constants.FILE_STEP_Y, doorClassName));
//				System.out.println(door + "---------------------- links endet " + doorClassName);
				door = false;
				doorClassName = "";
			}
		}

		// Die Türen am rechten Rand
		for (int i = 0; i < content.length; i++) {
			ch = content[i][content[i].length - 2];
			if (ch != Constants.CH_NOTHING) {
				if (!door) {
//					System.out.println(door + "------------ rechts ---------- beginnt ");
					begin = i;
					door = true;
				}
				doorClassName += ch;
			} else if (door) {
				end = i - 1;
				this.add(new GameDoor(Constants.RECHTS, this.getBounds().width - Constants.DOOR_THICKNESS, begin * Constants.FILE_STEP_Y,
						Constants.DOOR_THICKNESS, (end - begin) * Constants.FILE_STEP_Y, doorClassName));
				door = false;
//				System.out.println(door + "---------------------- rechts endet " + doorClassName);
				doorClassName = "";
			}
		}
	}

	public void resetGame() {

		ArrayList<GamePiece> arr = this.getAllGamePiece();

		for (GamePiece gamePiece : arr) {
			gamePiece.reset();
		}
	}

	private void startRobots() {

		Component[] piecies = this.getComponents();

		for (Component piece : piecies) {

			if (piece instanceof Robot) {
				((Robot) piece).action();
				;
			}
		}
	}

	public void pause(boolean pause) {
		this.pause = pause;
	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (this.pause)
			return;

		char ch = e.getKeyChar();
		GamePlayer player = null;
		player = GamePlayer.getPlayer();

		if (ch == 'w') {
			this.willDoStep(player, Constants.OBEN, Constants.STEP_DEFAULT);
		} else if (ch == 'd') {
			this.willDoStep(player, Constants.RECHTS, Constants.STEP_DEFAULT);
		} else if (ch == 's') {
			this.willDoStep(player, Constants.UNTEN, Constants.STEP_DEFAULT);
		} else if (ch == 'a') {
			this.willDoStep(player, Constants.LINKS, Constants.STEP_DEFAULT);
		}
	}

	public int willDoStep(GamePiece moving, int richtung, int step) {

		if (moving instanceof GamePlayer) {
			GameDoor door = this.checkPlayerAtDoor(moving);
			if (door != null) {

				System.out.println("Player at the Door " + door.getRoomDataFile());
				this.stop(door);
				return -1;
			}
		}

		boolean overlap = false;
		int moveStep = step;

		ArrayList<GamePiece> piecies = this.getAllGamePiece();
		for (GamePiece next : piecies) {

			if (next != null && !moving.equals(next)) {
				overlap = BoundsUtils.overlapByMoving(moving.getBounds(), next.getBounds(), richtung, step);

				if (overlap) {

					moveStep = BoundsUtils.distance(moving.getBounds(), next.getBounds(), richtung);

					if (moveStep == 0) {
						if (this.interaction(moving, next)) {
							break;
						} else if (this.movable(moving, next)) {
						
							moveStep = step;
							moveStep = this.willDoStep(next, richtung, moveStep);
						}
					}
				}
			}
		}

		if (BoundsUtils.smallOutsideOfGreat(moving.getBounds(), this.getBounds(), richtung, moveStep,
				moving.getClass().toString())) {
			moveStep = (int) BoundsUtils.distanceToBound(moving.getBounds(), this.getBounds(), richtung);
		}

		if (moveStep > 0) {
			moving.move(moveStep, richtung);
//			this.bewege(moving, richtung, moveStep);
		}
		return moveStep;
	}

	protected GameDoor checkPlayerAtDoor(GamePiece moving) {

		ArrayList<GameDoor> doors = this.getAllDoors();
		for (GameDoor door : doors) {
			if (door.pieceAtMe(moving.getBounds())) {
				return door;
			}
		}
		return null;
	}

	protected boolean interaction(Object attacker, Object attacked) {
		return GameRules_okkit.interaction(attacker, attacked);
	}
	
	protected boolean movable(Object moving, Object standing) {
		return GameRules_okkit.movable(moving, standing);
	}


	/**
	 * Aufgabe: Kommentieren Sie JEDE Zeile. D. h. erklären Sie, was in dieser jeder
	 * Zeile passiert!
	 */
	protected void stop(GameDoor door) {
		System.out.println("STOOOOOOOOOOOOOOOOOPPPPPPPPPPP in " + this.getMyFrame().getClass());

		this.pause = true;

		GamePanel me = this;
		int alpha = 0;
		int alphastep = 10;
		int timestep = 100;

		JPanel fog = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, this.getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		fog.setOpaque(false);
		fog.setBackground(new Color(0, 0, 0, alpha));
		fog.setBounds(new Rectangle(0, 0, this.getBounds().width, this.getBounds().height));
		this.getParent().add(fog);
		this.getParent().setComponentZOrder(fog, 0);

		Timer timer = new Timer(timestep, new ActionListener() {
			private int counter = 0;

			@Override
			public void actionPerformed(ActionEvent evt) {

				if (alpha + counter * alphastep > 255) {
					me.getParent().remove(fog);
					((Timer) evt.getSource()).stop();
					me.getMyFrame().nextRoom(me, door);

//					System.out.println("############################ " + me.getParent().getClass());
				}

				else {
					fog.setBackground(new Color(0, 0, 0, alpha + counter * alphastep));
					counter++;
					me.repaint();
				}
			}
		});
		timer.start();
	}

	// new ArrayList<>(Arrays.asList(array))
	ArrayList<GamePiece> getAllGamePiece() {

		Component[] piecies = this.getComponents();
		ArrayList<GamePiece> list = new ArrayList<GamePiece>();

		for (Component piece : piecies) {

			if (piece instanceof GamePiece) {
				list.add((GamePiece) piece);
			}
		}
		return list;
	}

	ArrayList<GameDoor> getAllDoors() {

		Component[] piecies = this.getComponents();
		ArrayList<GameDoor> list = new ArrayList<GameDoor>();

		for (Component piece : piecies) {

			if (piece instanceof GameDoor) {
				list.add((GameDoor) piece);
			}
		}
		return list;
	}
	
	GameDoor getDoor(int position) {
		
		ArrayList<GameDoor> list = getAllDoors();
		
		for (GameDoor gameDoor : list) {
			if (gameDoor.getPositionInTheNextRoom() == position)
				return gameDoor;
		}
		return null;
	}

	GameFrame getMyFrame() {

		return (GameFrame) SwingUtilities.getAncestorOfClass(GameFrame.class, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
