package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * 
 * @author Debreczeni Mate
 *
 * Alapveto egeres felhasznaloi bevitelt megvalosito absztrakt osztaly.
 */
public abstract class UIObject {
	
	protected double x, y;
	protected int width, height;
	protected Rectangle2D.Double bounds;
	protected boolean hovering;
	
	/**
	 * Publikus konstruktor, egy peldanyt hoz letre az osztalybol.
	 * 
	 * @param x A UIObject balfelso sarkanak x koordinataja
	 * @param y A UIObject balfelso sarkanak y koordinataja
	 * @param width A UIObject szelessege
	 * @param height A UIObject magassaga
	 */
	public UIObject(double x, double y, int width, int height){
		hovering = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle2D.Double( x, y, width, height);
	}
	
	/**
	 * A leszarmazott osztalyoktol elvart ertekeket frissito fuggveny
	 */
	public abstract void update();
	
	/**
	 * A leszarmazottaktol elvart kirajzolo fuggveny
	 * 
	 * @param graphics a kirajzolast vegzo grafikai osztaly
	 */
	public abstract void render(Graphics graphics);
	
	/**
	 * A kattintaskor elvart viselkedesrt felelos fuggveny
	 */
	public abstract void onClick();
	
	/**
	 * Ha az kurzor pozicioja a UIObject felett helyezkedik el
	 * akkor allitja igazra a hovering igazsagerteket.
	 * @param e A mozgas esemenye
	 */
	public void onMouseMove(MouseEvent e){
		if(bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
	}
	
	/**
	 * Az eger gombjanak felengedesekor meghivja a viselkedest megvalosito fuggvenyt
	 * @param e Az egergomb felengedesenek esemenye
	 */
	public void onMouseRelease(MouseEvent e){
		if(hovering)
			onClick();
	}
	
	// Getters and setters

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}

}
