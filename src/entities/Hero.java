package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import gfx.Animation;
import gfx.Assets;
import items.Inventory;
import states.StateManager;
import utils.Timer;
import world.Room;


/**
 * 
 * @author Debreczeni Mate
 *
 *Hos osztaly, az eloleny leszarmazottja, mely boviti a kepessegeit egy inventoryval,
 *illetve a jatekos altali bemeneteknek megfeleloen viselkedik
 */
public class Hero extends Creature{
	
	/**
	 * A megjeleneset meghatarozo animaciok
	 */
	private Animation downAnim,upAnim,leftAnim,rightAnim;
	/**
	 * a hos felszerelese
	 */
	private Inventory inventory;
	/**
	 * A hos eletereje legutobbi feluliraskor.(eleterocsokkenes detektalasra hasznalhato)
	 */
	private int lastHP;
	/**
	 * A hos serules utani serthetetlensegi idejet szabalyozo idozito
	 */
	Timer invincibilityTimer;
	/**
	 * A hos tamadasi frekvenciajat szabalyozo idozito
	 */
	Timer attackTimer = new Timer();

	/**
	 * A host letrehozo konstruktor
	 * 
	 * @param room A szoba amelyben a hosnek letre kell jonnie
	 * @param x A hos szobabeli poziciojanak x koordinataja
	 * @param y A hos szobabeli poziciojanak y koordinataja
	 * @param lives A hos eletinek szama
	 * @param speed A hos sebessege
	 */
	public Hero(Room room, double x, double y,int lives, double speed) {
		super(room,x, y, lives, speed);
		downAnim = new Animation(400,Assets.heroRunDown);
		upAnim = new Animation(400, Assets.heroRunUp);
		leftAnim = new Animation(400,Assets.heroRunLeft);
		rightAnim = new Animation(400, Assets.heroRunRight);
		direction = Direction.DOWN;
		bounds.x = 12;
		bounds.y = 18;
		bounds.height = 32;
		bounds.width = 28;
		inventory = new Inventory(this);
		shootable=true;
		lastHP=lives;
		invincibilityTimer = new Timer();
	}
	
	/**
	 * At hos adatainak frissiteset megvalosito fuggveny, meghivja a frissitendo ertekeket 
	 * kiszamolo fuggvenyeket
	 */
	@Override
	public void update() {
		downAnim.update();
		upAnim.update();
		leftAnim.update();
		rightAnim.update();

		inventory.update();
		
		getInput();
		move();
		updateBoundsPos();
		invincibilityFrame();
	}
	
	/**
	 * A hos halalakor meghivando fuggveny amely a jatekallapot allitasaert felelos fuggvenyt is meghivja
	 */
	@Override
	public void die() {
		alive = false;
		StateManager.getInstance().setCurrentState(room.getParentLevel().getGameState().getGame().getGameOverstate());		
	}
	
	/**
	 * A hos tamadasi viselkedesenek megfeleloen alitja  a shooting igazsagerteket.
	 */
	@Override
	public void attack() {
			if(attackTimer.timerWithoutWait(4)){
			shooting = true;
			}
		}

	
	/**
	 * Az hos kepernyore rajzolasaert felelos fuggveny
	 * 
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	@Override
	public void render(Graphics graphics) {

		if(!isMoving()) {
			if(direction==Direction.UP) {
				graphics.drawImage(Assets.heroUp, (int) x, (int) y,width,height, null);
			}
			else if(direction==Direction.DOWN) {
				graphics.drawImage(Assets.heroDown, (int) x, (int) y,width,height, null);
			}
			else if(direction==Direction.LEFT) {
				graphics.drawImage(Assets.heroLeft, (int) x, (int) y,width,height, null);
			}
			else if(direction==Direction.RIGHT) {
				graphics.drawImage(Assets.heroRight, (int) x, (int) y,width,height, null);
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
		else {
			graphics.drawImage(Assets.heroDown, (int) x, (int) y,width,height, null);
		}
		inventory.render(graphics);
		renderLives(graphics);
		//graphics.setColor(Color.yellow);
		//graphics.fillRect((int)(x+bounds.x), (int)(y+bounds.y), bounds.width, bounds.height);
	}


	/**
	 * A jatekos altal adott inputok szerint allitja a hos mozgasat es iranyat
	 */
	public void getInput() {

		if(room.getParentLevel().getGameState().getGame().getKeyManager().isUp()) {
			yMove+=(-speed);
			direction = Direction.UP;
		}
		if(room.getParentLevel().getGameState().getGame().getKeyManager().isDown()) {
			yMove+=(speed);
			direction = Direction.DOWN;
		}
		if(room.getParentLevel().getGameState().getGame().getKeyManager().isLeft()) {
			xMove+=(-speed);
			direction = Direction.LEFT;
		}
		if(room.getParentLevel().getGameState().getGame().getKeyManager().isRight()) {
			xMove+=(speed);
			direction = Direction.RIGHT;
		}
		if(room.getParentLevel().getGameState().getGame().getKeyManager().isAttacking()) {
			attack();
		}
	}
	
	/**
	 * A hos eletszamanak kepernyore rajzolasaert felelos fuggveny
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	public void renderLives(Graphics graphics) {
		graphics.drawImage(Assets.heart, 20, 20,64,64, null);
		graphics.setFont(new Font("Times New Roman",Font.BOLD,20));
		graphics.setColor(Color.white);
		graphics.drawString("x" + lives, 90, 60);
	}
	
	/**
	 * Az inputok alapjan ter vissza azzal hogy a hos mozgasban van-e vagy sem.
	 * @return mozgasallapotot jellemzo igazsagertek
	 */
	public boolean isMoving() {
		if(room.getParentLevel().getGameState().getGame().getKeyManager().isUp()||room.getParentLevel().getGameState().getGame().getKeyManager().isDown()
		||room.getParentLevel().getGameState().getGame().getKeyManager().isLeft()||room.getParentLevel().getGameState().getGame().getKeyManager().isRight()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Ha a hos serulest szenvedett akkor nem tamadhato 2 masodpercig.
	 * A fuggveny ennek megfeleloen allitja a shootable igazsagerteket
	 */
	public void invincibilityFrame() {
		 if(isHit()) {
			 invincibilityTimer.setLastTime(System.nanoTime());
			 shootable = false;
		 }
		 if(invincibilityTimer.timerWithoutWait(0.5)) {
			 shootable = true;
		 }
	}
	
	/**
	 * @return igazat ad ha a hos eletereje csokkent az elozo meghivas ota
	 */
	public boolean isHit() {
		if(lastHP>lives) {
			lastHP= lives;
			return true;
		}
		return false;
	}
	public Inventory getInventory() {
		return inventory;
	}


	


}
