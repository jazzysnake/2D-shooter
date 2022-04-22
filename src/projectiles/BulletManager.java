package projectiles;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import entities.Creature;
import world.Room;

/**
 * 
 * @author Debreczeni Mate
 *
 * A jatekban levo lovedekek egyuttes kezeleset vegzo osztaly 
 */

public class BulletManager{

	/**
	 * a lovedekek alapertelmezett magassaga es szelessege
	 */
	public static final int DEFAULT_BULLET_WIDTH=16,DEFAULT_BULLET_HEIGHT=16;
	/**
	 * Az egyetlen peldanyt tarolo statikus valtozo
	 */
	public static BulletManager singleInstance=null;
	/**
	 * A szobaban levo lovedekeket tarolo lista
	 */
	protected ArrayList<Bullet> bulletsOnMap;
	/**
	 * a szoba melyben a hos jelenleg tartozkodik
	 */
	protected Room currentRoom;
	
	private BulletManager() {
		bulletsOnMap = new ArrayList<>();
		
	}
	
	/**
	 * Az osztalyban eltarolt lovedekekre meghivja azok adatainak frissitiseert felelos fuggvenyeit
	 */
	public void update() {
		ArrayList<Creature> shooters = currentRoom.getEntityManager().currentlyShooting();
		Iterator<Creature> it = shooters.iterator();
		while(it.hasNext()){
			Creature c = it.next();
			addBullet(new Bullet(c));
		}
		
		Iterator<Bullet> it1 = bulletsOnMap.iterator();
		while(it1.hasNext()){
			Bullet b = it1.next();
			b.update();
			if(!b.isActive()) {
				it1.remove();
			}
		}
	}
	
	/**
	 * Az osztalyban eltarolt lovedekek kirajzolasaert felos fuggvenyeket meghivo fuggveny.
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	public void render(Graphics graphics) {
		for(Bullet b : bulletsOnMap) {
			b.render(graphics);
		}
	}

	public ArrayList<Bullet> getBulletsOnMap() {
		return bulletsOnMap;
	}
	
	public static BulletManager getInstance() {
		if(singleInstance == null) {
			singleInstance = new BulletManager();
			return singleInstance;
		}
		return singleInstance;
	}
	
	/**
	 * Ez a fuggveny hozzaad egy lovedeket a jelenleg kezelt lovedekek listajahoz.
	 * @param b a hozzaadni kivant lovedek
	 */
	public void addBullet(Bullet b) {
		bulletsOnMap.add(b);
	}
	/**
	 * A loveskezelonek uj kezelendo szobat allit be, es az elozo szoba lovedekeit deaktivalja.
	 * @param currentroom a szoba melynek lovedekeit kezelni kivanjuk
	 */
	public void setCurrentRoom(Room currentroom) {
		Iterator<Bullet> it = bulletsOnMap.iterator();
		while(it.hasNext()) {
			Bullet b=it.next();
			b.setActive(false);
		}
		this.currentRoom = currentroom;
	}
	public Room getCurrentRoom() {
		return currentRoom;
	}



	

	



}
