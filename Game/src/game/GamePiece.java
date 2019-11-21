package game;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class GamePiece extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String default_image;
	protected Point initial_Position;

	public GamePiece() {
		super();
	}

	public GamePiece(Point position) {
		super();
		setLocation(position);
		initial_Position = position;
	}

	public GamePiece(String img, Point position) {

		this(position);

		default_image = img;
		setFocusable(false);
		Dimension dim = defineImage();
		setBounds(position.x, position.y, dim.width, dim.height);

//		ObjectSizer sizer = new ObjectSizer();
//		long size = sizer.getObjectSize(getClass());
//		System.out.println("Piece size " + size);
	}
	
	public boolean amPlayer() {
		return this instanceof GamePlayer;
	}

	public void reset() {
		Dimension dim = defineImage();
		setBounds(this.initial_Position.x, this.initial_Position.y, dim.width, dim.height);
	}

	Dimension defineImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(this.default_image));
		setIcon(icon);
		setPressedIcon(icon);
		setRolloverIcon(icon);
		setDisabledIcon(icon);
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		
		return new Dimension(icon.getIconWidth(), icon.getIconHeight());
	}

	protected void resetIcon(ImageIcon icon) {

		if (icon == null)
			return;
		setIcon(icon);
		setBounds(this.getBounds().x, this.getBounds().y, icon.getIconWidth(), icon.getIconHeight());
	}

	public void action() {

	}

	public void move(int step, int direction) {

		switch (direction) {
		case Constants.OBEN:
			this.setBounds(this.getBounds().x, this.getBounds().y - step, this.getBounds().width,
					this.getBounds().height);
			break;
		case Constants.UNTEN:
			this.setBounds(this.getBounds().x, this.getBounds().y + step, this.getBounds().width,
					this.getBounds().height);
			break;
		case Constants.LINKS:
			this.setBounds(this.getBounds().x - step, this.getBounds().y, this.getBounds().width,
					this.getBounds().height);
			break;
		case Constants.RECHTS:
			this.setBounds(this.getBounds().x + step, this.getBounds().y, this.getBounds().width,
					this.getBounds().height);
			break;

		default:
			break;
		}
	}

}
