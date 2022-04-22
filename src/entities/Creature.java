package entities;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import world.Room;

/**
 * Az entitasra epulo absztrakt eloleny osztaly. Az entitast mozgassal es tamadassal bovito osztaly
 * 
 * @author Debreczeni Mate
 *
 */

public abstract class Creature extends Entity{
	/**
	 * Az eloleny sebessege
	 */
	protected double speed;
	/**
	 * Az eloleny elmozdulasa x es y iranyban.
	 */
	protected double xMove, yMove;
	/**
	 * Az eloleny sebesseg, es sebzes szorzoja poitionokkel novelheto 1 fole.(jelenleg csak a sebzes)
	 */
	protected double speedMultiplier, damageMultiplier;
	/**
	 * Az eloleny melyik iranyba nez
	 */
	protected Direction direction;
	/**
	 * eppen lo-e az eloleny
	 */
	protected boolean shooting;

	/**
	 * alapertelmezett ertekek
	 */
	public static final int DEFAULT_CREATUREWIDTH = 64;
	public static final int DEFAULT_CREATUREHEIGHT = 64;
	public static final double DEFAULT_CREATURESPEED = 3;

	/**
	 * Az elolenyt a parameterek szerint letrehozo konstruktor
	 * 
	 * @param room A szoba amelyben az elolenynek letre kell jonnie
	 * @param x A kezdeti poziciojanak x koordinataja
	 * @param y A kezdeti poziciojanak x koordinataja
	 * @param width Az elolenyt hatarolo teglalap szelessege
	 * @param height Az elolenyt hatarolo teglalap magassaga
	 * @param lives Az eloleny eleteinek szama
	 * @param speed Az eloleny sebessege (mozgas eseten ennyivel tolhato el a poziciojanak koordinataja)
	 */
	public Creature(Room room,double x, double y,int width, int height, int lives,double speed) {
		super(room,x,y,width,height);
		this.shootable = true;
		this.lives = lives;
		this.speed = speed;
		
		speedMultiplier = damageMultiplier = 1;
		xMove = yMove = 0;
	}
	
	/**
	 * Az elolenyt alapertelmezett magassaggal,szelesseggel letrehozo konstruktor
	 * 
	 * @param room A szoba amelyben az elolenynek letre kell jonnie
	 * @param x A kezdeti poziciojanak x koordinataja
	 * @param y A kezdeti poziciojanak x koordinataja
	 * @param lives Az eloleny eleteinek szama
	 * @param speed Az eloleny sebessege (mozgas eseten ennyivel tolhato el a poziciojanak koordinataja)
	 */

	public Creature(Room room,double x, double y,int lives,double speed) {
		super(room,x,y,DEFAULT_CREATUREWIDTH,DEFAULT_CREATUREHEIGHT);
		speedMultiplier = damageMultiplier = 1;
		this.lives = lives;
		this.speed = speed;
	}
	
	/**
	 * Az eloleny mozgatasahoz meghivando fuggveny,
	 */
	
	public void move() {
		moveX();
		moveY();
		xMove=yMove=0;
	}
	
	/**
	 * Az eloleny x iranyba valo mozgasat megvalosito fuggveny
	 */
	
	public void moveX() {
		if(xMove>0) { //Moving RIGHT
			if(!checkCollisions(xMove,0)) {
				x+=xMove;
			}
		}
		else if(xMove<0) { //Moving LEFT
			if(!checkCollisions(xMove,0)) {
				x+=xMove;
			}
		}
	}
	
	/**
	 * Az eloleny y iranyba valo mozgasat megvalosito fuggveny
	 */
	
	public void moveY() {
		if(yMove>0) { //Moving DOWN
			if(!checkCollisions(0,yMove)) {
				y+=yMove;
			}
		}
		else if(yMove<0) { //Moving UP
			if(!checkCollisions(0,yMove)) {
				y+=yMove;
			}
		}
	}
	
	/**
	 * Az eloleny szobabeli pozicioja alapjan ter vissza igazsagertekkel, hogy utkozne-e egy szilard mezovel
	 * ha el lenne tolva a paraméterekben megkapott mennyiseggel a pozicioja
	 * 
	 * @param Xoffset A hatarolo teglalap X iranyban eltolasa (altalaban a sebesseg)
	 * @param Yoffset A hatarolo teglalap Y iranyban eltolasa (altalaban a sebesseg)
	 * @return igazsagertek, hogy utkozne-e az eloleny az eltolassal egyutt
	 */
	
	public boolean collisionWithTile(double Xoffset,double Yoffset) {
		for(int i=0;i<4;++i) {
			if(room.getTileByPixels((boundCorners[i].getX()+Xoffset),boundCorners[i].getY()+Yoffset).isSolid()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Az eloleny szobabeli pozicioja alapjan ter vissza igazsagertekkel, hogy utkozne-e egy masik entitassal
	 * ha el lenne tolva a paraméterekben megkapott mennyiseggel a pozicioja
	 * 
	 * @param Xoffset A hatarolo teglalap X iranyban eltolasa (altalaban a sebesseg)
	 * @param Yoffset A hatarolo teglalap Y iranyban eltolasa (altalaban a sebesseg)
	 * @return igazsagertek, hogy utkozne-e az eloleny az eltolassal egyutt
	 */
	
	public boolean checkEntityCollision(double xOffset,double yOffset) {
		for(Entity e: room.getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
				}
			if(e.getCollisionBounds(0,0).intersects(this.getCollisionBounds(xOffset, yOffset))) {
				return true;
				}
			}
		return false;
	}

	/**
	 * Kiszamolja, hogy adott eltolas eseten utkozne-e barmivel az eloleny
	 * 
	 * 
	 * @param xOffset A hatarolo teglalap X iranyban eltolasa (altalaban a sebesseg)
	 * @param yOffset A hatarolo teglalap Y iranyban eltolasa (altalaban a sebesseg)
	 * @return igazsagertek, hogy utkozne-e az eloleny az eltolassal egyutt
	 */
	public boolean checkCollisions(double xOffset,double yOffset) {
		if(checkEntityCollision( xOffset, yOffset)||
			collisionWithTile( xOffset, yOffset)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Az eloleny tamadasaert felelos absztakt fuggveny, az eloleny leszarmazottainak meg kell valositaniuk
	 */
	public abstract void attack();

	/**
	 * At eloleny adatainak frissiteset megvalosito fuggveny
	 */
	@Override
	public void update() {
		updateBoundsPos();
		calcDirection();
	}
	
	/**
	 * Az eloleny kepernyore rajzolasaert felelos fuggveny
	 * 
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	@Override
	public void render(Graphics graphics) {}
	
	/**
	 * A szobaban levo hos pozicioja alapjan kiszamolja es frissiti az eloleny iranyat,
	 * ugy hogy az eloleny mindig a hos fele nezzen
	 */
	public void calcDirection() {
		Point2D.Double dest = new Point2D.Double(room.getEntityManager().getHero().getX(),room.getEntityManager().getHero().getY());
		double xDelta = dest.x-x;
		double yDelta = dest.y-y;
		
		if(Math.abs(xDelta)<Math.abs(yDelta)) {
			if(yDelta<0) {
				direction = Direction.UP;
			}
			else {
				direction = Direction.DOWN;
			}
		}
		else {
			if(xDelta<0) {
				direction = Direction.LEFT;
			}
			else {
				direction = Direction.RIGHT;
			}
		}
	}
	

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	public double getDamageMultiplier() {
		return damageMultiplier;
	}

	public void setDamageMultiplier(double damageMultiplier) {
		this.damageMultiplier = damageMultiplier;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}



}
