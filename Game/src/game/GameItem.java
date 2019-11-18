package game;

import java.awt.Point;

public class GameItem extends GamePiece {

	private static final long serialVersionUID = 1L;
	String name;
	int itemType;
	
	public GameItem(String name, int itemType, String image, Point p) {
		super(image, p);
		this.name = name;
		this.itemType = itemType;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
}