package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Debreczeni Mate
 *
 * Egy kepes gombot megvalosito osztaly, mely egy gomb alapveto funkcioival rendelkezik,
 * de texturat lehet megadni a megjeleniteshez.
 */
public class UIImageButton extends UIObject {

	private BufferedImage[] images;
	private ClickListener clicker;
	
	/**
	 * Publikus konstruktor letrehoz egy peldanyt az osztalybol
	 * @param x A gomb balfelso sarkanak x parametere
	 * @param y A gomb balfelso sarkanak y parametere
	 * @param width A gomb szelessege
	 * @param height A gomb magassaga
	 * @param images A gomb texturai
	 * @param clicker a kattintast figyelo interface
	 */
	public UIImageButton(double x, double y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}

	/**
	 * A gomb adatainak frissiteset vegzo fuggveny. Ha lennenek leszarmazott osztalyok amelyek
	 * valtozo pozicioju vagy texturahu stb gombot valositana meg, ezt a fuggvenyt kene felulirnia.
	 */
	@Override
	public void update() {}

	/**
	  * A kirajzolast vegzo fuggveny
	  * @param graphics a kirajzolast vegzo grafikai osztaly
	  */
	@Override
	public void render(Graphics graphics) {
		if(hovering) {
			graphics.drawImage(images[1], (int) x, (int) y, width, height, null);
		}
		else
			graphics.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	/**
	 * A kattintaskor a clickListener interface fuggvenye hivodik meg. 
	 */
	@Override
	public void onClick() {
		clicker.onClick();
	}

}
