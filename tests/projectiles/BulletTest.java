package projectiles;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;


import entities.Direction;
import entities.Enemy;
import entities.EntityManager;
import entities.Hero;
import gfx.Assets;
import tiles.Tile;
import tiles.TileManager;
import world.Room;

public class BulletTest {
	Enemy shooter;
	Hero hero;
	Bullet bullet;
	Tile[][] roomTiles;
	Room room;
	EntityManager entityManager;
	
	@Before
	public void init(){
		Assets.init();
		
		roomTiles = new Tile[15][7];
		//setting up room
		for(int y = 0;y<7;++y) {
			for(int x=0;x<15;++x) {
				roomTiles[x][y] = TileManager.getInstance().getNewTile(0);
			}
		}
		//setting the first column to SOLID TILES (wall tiles)
		for(int i=0;i<roomTiles[0].length;++i) {
			roomTiles[0][i]= TileManager.getInstance().getNewTile(1);
		}
		room = new Room(15, 7, 5, 5, roomTiles);
		shooter = new Enemy(room, 200, 200, 64, 64, 5, 3);
		hero = new Hero(room, 280, 200, 5, 3);
		shooter.setDirection(Direction.LEFT);
		bullet = new Bullet(shooter);
		entityManager = new EntityManager(hero);
		entityManager.addCreature(shooter);
		entityManager.addEntity(shooter);
		room.setEntityManager(entityManager);
	}

	@Test
	public void testMove() {
		Point2D.Double bulletPosBefore = new Point2D.Double(bullet.getPos().getX(),bullet.getPos().getY());
		bullet.move();
		Point2D.Double bulletPosAfter = new Point2D.Double(bullet.getPos().getX(),bullet.getPos().getY());
		
		assertNotEquals(bulletPosBefore, bulletPosAfter);
	}
	
	@Test
	public void testCollisionsWithTile() {
		assertFalse(bullet.collisionWithTile());
		//setting shooter position right next to wall 
		shooter.setX(1);
		shooter.setY(1);
		
		Bullet newBullet = new Bullet(shooter);
		assertTrue(newBullet.collisionWithTile());
	}
	
	@Test
	public void testCheckEntityCollision() {
		shooter.setDirection(Direction.RIGHT);
		assertNull(bullet.checkEntityCollision());
	}

}
