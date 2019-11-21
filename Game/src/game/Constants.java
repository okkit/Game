package game;

public class Constants {

	// Name des Spiels
	public static final String GAME_NAME = "Game";
	
	// Buchstaben für Objekte beim Einlesen aus dem File
	public static char CH_DOOR = 	'D';
	public static char CH_ROBOT = 	'R';
	public static char CH_BLOCK = 	'B';
	public static char CH_PLAYER = 	'P';
	public static char CH_NOTHING = '-';
	public static char CH_BLANK = ' ';
	
	// Schritt beim einlesen aus dem File
	public static int FILE_STEP_X = 10;
	public static int FILE_STEP_Y = 10;
	// Schritt beim einlesen aus dem File
	public static int DOOR_THICKNESS = 10;
	
	
	// Images
	public static final String PLAYER_IMAGE = "/cat.gif";
	public static final String ROBOT_IMAGE1 = "/spinningtaz.gif";
	public static final String ROBOT_IMAGE2 = "/666.gif";
	public static final String ROBOT_IMAGE3 = "/666.gif";
	public static final String BLOCK_IMAGE = "/Block_10x10.png";
	public static final String WATER_IMAGE = "/Wasser_ROM_50x50.png";
	
	// GameFrame-Positionierung und Größe auf dem Bildschirm
	public static final int FRAME_X = 900;
	public static final int FRAME_Y = 200;
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 800;
	
	public static final int DOOR_HEIGHT = 20;
	
	// Farbe des Frames
	public static final int FRAME_C_RED = 0;
	public static final int FRAME_C_GREEN = 0;
	public static final int FRAME_C_BLUE = 0;

	// Positionierung und Größe des GamePanels im Frame
	public static final int PANEL_X = 5;
	public static final int PANEL_Y = 5;
	public static final int PANEL_WIDTH = 975;
	public static final int PANEL_HEIGHT = 750;
	// Farbe des Panels
	public static final int PANEL_C_RED = 255;
	public static final int PANEL_C_GREEN = 255;
	public static final int PANEL_C_BLUE = 255;

	// Schritt, mit dem sich der Player bewegt
	public static final int STEP_DEFAULT = 10;

	// Richtungskonstanten
	public static final int OBEN = 15;
	public static final int LINKS = 12;
	public static final int UNTEN = 21;
	public static final int RECHTS = 73;

	// Player-Button-Dimensionierungen
	public static final int PLAYER_WIDTH = 50;
	public static final int PLAYER_HEIGHT = 100;

	// Block-Canva-Dimensionierungen
	public static final int BLOCK_WIDTH = 60;
	public static final int BLOCK_HEIGHT = 60;
	// BLOCK-FARBE
	public static final int BLOCK_C_RED = 30;
	public static final int BLOCK_C_GREEN = 30;
	public static final int BLOCK_C_BLUE = 30;
	
}
