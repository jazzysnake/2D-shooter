package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import world.Room;

/**
 * Absztrakt egyed osztaly, mely pár alap funkciot, illetve adattagot tartalmaz, amelyekre
 * tobb a jatekban peldanyositott osztaly epul.
 * 
 * @author Debreczeni Mate
 *
 */
public abstract class Entity{

	/**
	 * Az entitas pozivioja
	 */
	protected double x,y;
	/**
	 * Az entitas szelessege es magassaga
	 */
	protected int width, height;
	/**
	 * Az entitast hatarolo teglalap
	 */
	protected Rectangle bounds;
	/**
	 * az entitast hatarolo teglalap sarkai
	 */
	protected Point2D.Double[] boundCorners;
	/**
	 * a szoba melyben az entitas letezik
	 */
	protected Room room;
	/**
	 * Az entitas eleteinek szama
	 */
	protected int lives;
	/**
	 * igazsagertekek, hogy el-e az entitas es hogy megloheto-e.
	 */
	protected boolean alive,shootable;

	/**
	 * @param room A szoba amelyikben az entitasnak letre kell jönnie
	 * @param x A spawn pozicio x koordinataja
	 * @param y A spawn pozicio y koordinataja
	 * @param width Az entitas szelessege
	 * @param height Az entitas magassaga
	 */

	public Entity(Room room,double x,double y,int width,int height) {
		this.x = x;
		this.y= y;
		this.width = width;
		this.height= height;
		this.room=room;
		alive = true;
		shootable = false;
		this.lives=5;
		
		bounds = new Rectangle(0,0,width,height);
		boundCorners = new Point2D.Double[4];
		updateBoundsPos();

	}

	/**
	 * Az entitas attributumai ertekenek frissitesert felelo fuggveny,
	 *  minden entitas leszarmazottnak kotelezo implementalnia
	 */
	public abstract void update();
	
	/**
	 * Az entitas kirajzolasaert felelos fuggveny, minden entitas leszarmazottnak kotelezo implementalnia
	 * 
	 * @param graphics A kirajzolasert felelos grafikai osztaly
	 */
	public abstract void render(Graphics graphics);
	
	/**
	 * Az entitast hatarolo teglalap 4 sarkanak poziciojat eltarolo tombot
	 * frisstiti, az entitas jelenlegi pozicioja alapjan
	 */
	public void updateBoundsPos() {
		boundCorners[0] = new Point2D.Double(x+bounds.x,y+bounds.y);
		boundCorners[1] = new Point2D.Double(x+bounds.x+bounds.width,y+bounds.y);
		boundCorners[2] = new Point2D.Double(x+bounds.x,y+bounds.y+bounds.height);
		boundCorners[3] = new Point2D.Double(x+bounds.x+bounds.width,y+bounds.y+bounds.height);
	}
	
	/**
	 * az entitas eletszamat csokkento fuggveny, mely meghivja az entitas meghalasaert fuggvenyt,
	 * ha az eletereje 0 vagy annal kevesebb lenne a serules hatasara
	 * 
	 * @param damage A mennyiseg amennyivel az entitas eleterejet kell csokkenteni
	 */
	public void hurt(int damage){
		lives=lives-damage;
		if(lives<=0) {
			alive = false;
			die();
		}
	}
	
	/**
	 * Az entitas halalakor meghivando fuggveny, minden entitas leszarmazottjanak implementalni kell
	 */
	public abstract void  die();
	
	/**
	 *Az entitas utkozesekor figyelembe vevendo hatarait kiszamolo fuggveny
	 * 
	 * @param xOffset Az eltolas merteke x iranyban, altalaban az entitas sebessege
	 * @param yOffset Az eltolas merteke y iranyban, altalaban az entitas sebessege
	 * @return Teglalap amely az entitast hatarolja az offszettel egyutt
	 */
	public Rectangle getCollisionBounds(double xOffset,double yOffset){
		return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset),bounds.width,bounds.height);
	}
	
	
	public Point2D.Double[] getBoundCorners(){
		return boundCorners;
	}
	
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public double getLowestPoint() {
		return boundCorners[2].getY();
	}


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
	
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isShootable() {
		return shootable;
	}

	public void setShootable(boolean shootable) {
		this.shootable = shootable;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
