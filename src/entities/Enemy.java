package entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

import gfx.Animation;
import gfx.Assets;
import items.ItemManager;
import utils.Timer;
import world.Room;

/**
 * 
 * @author Debreczeni Mate
 *
 *Az eloleny leszarmazott osztalya mely a mozgas tamadas es halalozas funkciojat valositja meg. A jatekban a hos/jatekos 
 *ellenfeleinek viselkedeset hatarozza meg.
 */
public class Enemy extends Creature{
	/**
	 * Az ellenfel tamadasi frekvenciajat meghatarozo idozito
	 */
	Timer attackTimer = new Timer();
	/**
	 * Az ellenfelt a kepernyon abrazolo animaciok
	 */
	private Animation downAnim,upAnim,leftAnim,rightAnim;
	/**
	 * az ellenfel pozicioja a legutobbi feluliraskor (a mozgasallapot igazsagertekre forditasahoz kell)
	 */
	private Point lastPos;
	
	/**
	 * 
	 * @param room A szoba amelyben az ellenfelnek letre kell jonnie
	 * @param x A kezdeti poziciojanak x koordinataja
	 * @param y A kezdeti poziciojanak x koordinataja
	 * @param width Az ellenfelt hatarolo teglalap szelessege
	 * @param height Az ellenfelt hatarolo teglalap magassaga
	 * @param lives Az ellenfel eleteinek szama
	 * @param speed Az ellenfel sebessege (mozgas eseten ennyivel tolhato el a poziciojanak koordinataja)
	 */
	public Enemy(Room room,double x, double y,int width, int height, int lives,double speed) {
		super(room, x, y, width, height, lives, speed);
		alive = true;
		shootable = true;
		downAnim = new Animation(400,Assets.enemyRunDown);
		upAnim = new Animation(400, Assets.enemyRunUp);
		leftAnim = new Animation(400,Assets.enemyRunLeft);
		rightAnim = new Animation(400, Assets.enemyRunRight);
		lastPos=new Point((int)x,(int)y);
	}

	/**
	 * Az ellenfel tamadasi viselkedesenek megfeleloen allitja a
	 * shooting igazsagerteket.
	 */
	@Override
	public void attack() {
		shooting = false;
		if(attackTimer.timerWithoutWait(1)) {
			shooting= true;
		}
	}

	/**
	 * Az ellenfel halalkor meghivando fuggveny, amely veletelnszeruen letrehoz a szobaban
	 * eleterot biztosito vagy sebzest novelo targyat.
	 */
	@Override
	public void die() {
		alive = false;
		Random r = new Random();
		int i = r.nextInt(100);
		if(i<40) {
			room.getItemManager().addItem(ItemManager.heartItem.createNew((int)x,(int)y));
		}
		if(i>80) {
			room.getItemManager().addItem(ItemManager.damagePotionItem.createNew((int)x,(int)y));
		}
	}
	
	/**
	 * At eloleny adatainak frissiteset megvalosito fuggveny, meghivja a frissitendo ertekeket 
	 * kiszamolo fuggvenyeket
	 */
	@Override
	public void update() {
		downAnim.update();
		upAnim.update();
		leftAnim.update();
		rightAnim.update();
		calcMovement();
		calcDirection();
		updateBoundsPos();
		move();
		attack();
	}
	
	/**
	 * Az ellenfel kepernyore rajzolasaert felelos fuggveny
	 * 
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	@Override
	public void render(Graphics graphics) {

		if(!isMoving()) {
			if(direction==Direction.UP) {
				graphics.drawImage(Assets.enemyUp, (int) x, (int) y,width,height, null);
			}
			else if(direction==Direction.DOWN) {
				graphics.drawImage(Assets.enemyDown, (int) x, (int) y,width,height, null);
			}
			else if(direction==Direction.LEFT) {
				graphics.drawImage(Assets.enemyLeft, (int) x, (int) y,width,height, null);
			}
			else if(direction==Direction.RIGHT) {
				graphics.drawImage(Assets.enemyRight, (int) x, (int) y,width,height, null);
			}

		}
		else if(direction == Direction.DOWN) {
			graphics.drawImage(downAnim.getCurrentFrame(), (int) x, (int) y,width,height, null);
		}
		else if(direction == Direction.UP) {
			graphics.drawImage(upAnim.getCurrentFrame(), (int) x, (int) y,width,height, null);
		}
		else if(direction == Direction.LEFT) {
			graphics.drawImage(leftAnim.getCurrentFrame(), (int) x, (int) y,width,height, null);
		}
		else if(direction == Direction.RIGHT) {
			graphics.drawImage(rightAnim.getCurrentFrame(), (int) x, (int) y,width,height, null);
		}
		//graphics.setColor(Color.yellow);
		//graphics.fillRect((int)(x+bounds.x), (int)(y+bounds.y), bounds.width, bounds.height);
	}
	
	/**
	 * A mozgashoz szukseges erteket allitja annak erdekeben hogy az ellenfel hostol vett tavolsgat csokkentse
	 */
	public void calcMovement() {
		Point2D.Double destination = new Point2D.Double(room.getEntityManager().getHero().getX(),room.getEntityManager().getHero().getY());
		if(destination.getX()>x) {
			xMove =+ speed;
		}
		else if(destination.getX()<x) {
			xMove =+ -speed;
		}
		if(destination.getY()>y) {
			yMove =+ speed;
		}
		else if(destination.getY()<y) {
			yMove = (-speed);
		}
	}
	/**
	 * 
	 * @return igaz ha az ellenfel mozgasban van.
	 */
	public boolean isMoving() {
		Point currentPos = new Point((int)x,(int)y);
		if(currentPos!=lastPos) {
			lastPos = currentPos;
			return true;
		}
		return false;
	}
	

}
