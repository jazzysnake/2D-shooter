package projectiles;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.*;
import gfx.Assets;
import world.Room;


/**
 * 
 * @author Debreczeni Mate
 *
 * Lovedek osztaly mely a jatekban az elolenyek altal lott lovedekek
 * adatait tarolja.
 */
public class Bullet {
	
	/**
	 * Szoba melyben a lovedek van
	 */
	protected Room room;
	/**
	 * A lovedek pozicioja
	 */
	protected Point2D.Double pos;
	/**
	 * A lovedek szelessege es magassaga
	 */
	protected int width,height;
	/**
	 * a lovedek sebessege
	 */
	protected double speed;
	/**
	 * a lovedek iranya
	 */
	protected Direction direction;
	/**
	 * a lovedek texturai
	 */
	protected BufferedImage[] texture;
	/**
	 * a lovedeket hatarolo teglalap
	 */
	protected Rectangle2D.Double bounds;
	/**
	 * a lovedek sebzese
	 */
	protected int damage;
	/**
	 * a lovedeket hatarolo teglalap negy sarkat tarolo tomb
	 */
	protected Point2D.Double[] boundCorners;
	/**
	 * igazsagvaltozo melyben eltarolodik hogy a lovedek beleutkozott e mar valamibe
	 */
	protected boolean active;
	/**
	 * a lovedeket letrehozo eloleny
	 */
	protected Creature shooter;

	/**
	 * A lovest vegzo eloleny adatai alapjan hoz letre lovedeke a kello ertekekkel.
	 * @param shooter a lovest vegzo eloleny
	 */
	public Bullet(Creature shooter) {
		this.shooter = shooter;
		this.active = true;
		this.room =shooter.getRoom();
		this.pos = new Point2D.Double(calcBulletSpawnPos(shooter).getX(),calcBulletSpawnPos(shooter).getY());
		this.width = (int)(BulletManager.DEFAULT_BULLET_WIDTH*shooter.getDamageMultiplier());
		this.height = (int)(BulletManager.DEFAULT_BULLET_HEIGHT*shooter.getDamageMultiplier());
		boundCorners = new Point2D.Double[4];
		this.bounds = new Rectangle2D.Double(pos.getX(),pos.getY(),width,height);
		this.speed = 2.5*shooter.getSpeedMultiplier();
		this.direction = shooter.getDirection();
		this.damage = (int)(1*shooter.getDamageMultiplier());
		updateBoundsPos();
		if(shooter==room.getEntityManager().getHero()) {
			this.texture=Assets.heroBullets;
		}
		else {
			this.texture=Assets.enemyBullets;
		}
	}
	
	/**
	 * A lovedeket hatarolo teglalap negy sarkat eltarolo tombben levo adatokat frissiti a 
	 * lovedek jelenlegi pozicioja szerint
	 */
	public void updateBoundsPos() {
		boundCorners[0] = new Point2D.Double(pos.x,pos.y);
		boundCorners[1] = new Point2D.Double(pos.x+bounds.width,pos.y);
		boundCorners[2] = new Point2D.Double(pos.x+bounds.width,pos.y+bounds.height);
		boundCorners[3] = new Point2D.Double(pos.x+bounds.width,pos.y+bounds.height);
	}


	/**
	 * A lovedek mozgasatasakor meghivando fuggveny, amely a pozicio adatait irja felul
	 */
	public void move(){
		if(direction == Direction.UP) {
			pos.y-=speed;
		}
		else if(direction == Direction.DOWN) {
			pos.y+=speed;
		}
		else if(direction == Direction.LEFT) {
			pos.x-=speed;
		}
		else if(direction == Direction.RIGHT) {
			pos.x+=speed;
		}
		else
			return;
	}
	
	/**
	 * Az adatok frissiteset vegzo fuggvenyeket meghivva frissiti a lovedek adatait, figyelve az utkozesekre
	 */
	public void update() {
		move();
		if(collisionWithTile()) {
			deactivate();
		}
		if(checkEntityCollision()!=null && 	checkEntityCollision().isShootable() && active) {
			if(!(shooter!=shooter.getRoom().getEntityManager().getHero()&&checkEntityCollision()!=shooter.getRoom().getEntityManager().getHero())) {
				checkEntityCollision().hurt(damage);
			}
			deactivate();
		}
		updateBoundsPos();
	}

	/**
	 * A lovedek kepernyore rajzolasat vegzo fuggveny
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	public void render(Graphics graphics) {
		if(direction==Direction.UP) {
		graphics.drawImage(texture[0],(int) pos.x, (int) pos.y, width,height,null);
		}
		else if(direction==Direction.DOWN) {
		graphics.drawImage(texture[1],(int) pos.x, (int) pos.y, width,height,null);
		}
		else if(direction==Direction.LEFT) {
		graphics.drawImage(texture[2],(int) pos.x, (int) pos.y, width,height,null);
		}
		else if(direction==Direction.RIGHT) {
		graphics.drawImage(texture[3],(int) pos.x, (int) pos.y, width,height,null);
		}
	}
	

	/**
	 * A lovedeket deaktivalo fuggveny
	 */
	public void deactivate() {
		active = false;
	}

	//Collision stuff
	
	/**
	 * A szobaban levo mezokkel valo utkozest figyelo fuggveny
	 * @return igaz ha szilard mezovel utkozik, vagy ha nyitott ajtoval
	 */
	public boolean collisionWithTile() {

		for(int i=0;i<4;++i) {
			if(room.getTileByPixels((boundCorners[i].getX()+getOffset().getX()),(boundCorners[i].getY()+getOffset().getY())).isSolid()) {
				return true;
			}
			if(room.getTileByPixels((boundCorners[i].getX()+getOffset().getX()),(boundCorners[i].getY()+getOffset().getY())).getID()==13) {
				return true;
			}
			if(room.getTileByPixels((boundCorners[i].getX()+getOffset().getX()),(boundCorners[i].getY()+getOffset().getY())).getID()==14) {
				return true;
			}
			if(room.getTileByPixels((boundCorners[i].getX()+getOffset().getX()),(boundCorners[i].getY()+getOffset().getY())).getID()==15) {
				return true;
			}
			if(room.getTileByPixels((boundCorners[i].getX()+getOffset().getX()),(boundCorners[i].getY()+getOffset().getY())).getID()==16) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Az utkozesekkor figyelembe vevendo utkozesi hatarokat adja vissza, melyek a lovedek hatarai,
	 * de adott eltolassal.
	 * @param xOffset eltolas x iranyban
	 * @param yOffseteltolas y iranyban
	 * @return az eltolast is tartalmazo teglalap
	 */
	public Rectangle getCollisionBounds(double xOffset,double yOffset){
		return new Rectangle((int)(pos.x+xOffset),(int)(pos.y+yOffset),(int)bounds.width,(int)bounds.height);
	}
	
	/**
	 * Egyedekkel valo utkozeseket figyelo fuggveny
	 * @return az entitast adja vissza amelyikkel utkozik, vagy pedig null pointer ha nem tortent utkozes
	 */
	public Entity checkEntityCollision() {
		for(Entity e: room.getEntityManager().getEntities()) {
			for(Bullet b: BulletManager.getInstance().getBulletsOnMap()) {
				if(e.equals(b.shooter))
					continue;
				if(e.getCollisionBounds(0,0).intersects(b.getCollisionBounds(getOffset().getX(), getOffset().getY()))) {
					return e;
					}
				}
			}
		return null;
	}

	/**
	 * Kiszamolja az utkozes kiszamolasahoz szukseges eltolas merteket
	 * @return Az eltolas merteke
	 */
	public Point getOffset() {
		int Xoffset=0, Yoffset=0;
		if(direction == Direction.LEFT) {
			Xoffset = ((int)-speed);
			Yoffset = 0;
		}
		else if(direction == Direction.RIGHT) {
			Xoffset = ((int)speed);
			Yoffset = 0;
		}
		else if(direction == Direction.UP) {
			Xoffset = 0;
			Yoffset = ((int)-speed);
		}
		else if(direction == Direction.DOWN) {
			Xoffset = 0;
			Yoffset = ((int)speed);
		}
		return new Point(Xoffset,Yoffset);
	}
	
	/**
	 * A lovest vegzo eloleny pozicioja es iranya alapjan szamolja ki a lovedek
	 * kezdopoziciojat 
	 * @param shooter A lovest vegzo eloleny
	 * @return A kiszamitott pozicio
	 */
	public static Point2D.Double calcBulletSpawnPos(Creature shooter) {
		double x = shooter.getX();
		double y = shooter.getY();
		double xOffset = shooter.getWidth()/4;
		double yOffset = shooter.getHeight()/2;
		Direction dir = shooter.getDirection();
		if(dir == Direction.LEFT) {
			x-=xOffset;
			y+=yOffset;
		}
		else if(dir == Direction.RIGHT) {
			x +=xOffset+shooter.getWidth();
			y +=yOffset;
		}
		else if(dir == Direction.UP) {
			xOffset = shooter.getWidth()/2;
			yOffset = shooter.getHeight()/4;
			x+=xOffset;
			y -= yOffset; 
		}
		else if(dir == Direction.DOWN) {
			xOffset = shooter.getWidth()/2;
			yOffset = shooter.getHeight()/4;
			x +=xOffset;
			y +=yOffset+shooter.getHeight();
		}
		return new Point2D.Double(x,y);
		
	}
	

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Point2D.Double getPos() {
		return pos;
	}

	public void setPos(Point2D.Double pos) {
		this.pos = pos;
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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public BufferedImage[] getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage[] texture) {
		this.texture = texture;
	}

	public Rectangle2D.Double getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle2D.Double bounds) {
		this.bounds = bounds;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Point2D.Double[] getBoundCorners() {
		return boundCorners;
	}

	public void setBoundCorners(Point2D.Double[] boundCorners) {
		this.boundCorners = boundCorners;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
