package entities;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import gfx.Assets;
import tiles.Tile;
import tiles.TileManager;
import world.Room;

public class EntityTests {
	Enemy enemy;
	Hero hero;
	EntityManager entityManager;
	Room room;
	Tile roomTiles[][];
	
	@Before
	public void init() {
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
		enemy = new Enemy(room,200, 200,64, 64, 3,3);
		hero = new Hero(room,300,300,5,3);
		entityManager = new EntityManager(hero);
		entityManager.addCreature(enemy);
		entityManager.addEntity(enemy);
		room.setEntityManager(entityManager);

	}

	@Test
	public void testHurt() {
		int livesBeforeHurt = enemy.getLives();
		enemy.hurt(2);
		int livesAfterHurt = enemy.getLives();

		assertNotEquals(livesBeforeHurt,livesAfterHurt);
	}
	
	@Test
	public void testRemoveDeadCreatures() {
		int creatureCountBefore = entityManager.getCreatures().size();
		entityManager.getEntities().remove(entityManager.getEntities().indexOf(enemy));
		entityManager.removeDeadCreautres();
		int creatureCountAfter = entityManager.getCreatures().size();
		assertNotEquals(creatureCountBefore, creatureCountAfter);
	}
	
	@Test
	public void testUpdateBoundsPos() {
		Point2D.Double[] cornersBefore = new Point2D.Double[4];
		for(int i=0;i<4;++i) {
			cornersBefore[i] = new Point2D.Double(enemy.getBoundCorners()[i].getX(),enemy.getBoundCorners()[i].getY());
		}
		enemy.setX(210);
		enemy.setY(210);
		enemy.updateBoundsPos();
		
		Point2D.Double[] cornersAfter = new Point2D.Double[4];
		for(int i=0;i<4;++i) {
			cornersAfter[i] = new Point2D.Double(enemy.getBoundCorners()[i].getX(),enemy.getBoundCorners()[i].getY());
		}

		for(int i=0;i<4;++i) {
			assertNotEquals(cornersBefore[0].getX(),cornersAfter[0].getX());
			assertNotEquals(cornersBefore[0].getY(),cornersAfter[0].getY());
		}
	}
	
	@Test
	public void testCollisionWithTile() {
		assertFalse(enemy.collisionWithTile(0, 0));
		assertFalse(hero.collisionWithTile(0, 0));
		
		enemy.setX(10);
		hero.setX(10);
		enemy.updateBoundsPos();
		hero.updateBoundsPos();
		
		assertTrue(enemy.collisionWithTile(0, 0));
		assertTrue(hero.collisionWithTile(0, 0));
	}
	
	@Test
	public void testGetCollisionBounds() {
		Rectangle bounds = enemy.getCollisionBounds(0, 0);
		assertNotNull(bounds);
		assertEquals( enemy.getBoundCorners()[0].getX(),bounds.x,0.1);
		
		bounds = enemy.getCollisionBounds(-3, 0);
		assertNotEquals(enemy.getBoundCorners()[0].getX(), bounds.x);
	}
	
	@Test
	public void testCheckEntityCollisions() {
		assertFalse(enemy.checkEntityCollision(0, 0));
		assertFalse(hero.checkEntityCollision(0, 0));
		
		enemy.setX(100);
		hero.setX(100);
		enemy.setY(100);
		hero.setY(100);
		
		assertTrue(enemy.checkEntityCollision(1, 1));
		assertTrue(hero.checkEntityCollision(1, 1));
	}
	
	@Test
	public void testCalcDirection() {
		enemy.setDirection(Direction.UP);
		//setting hero directly below enemy
		enemy.setX(300);
		hero.setX(300);
		enemy.setY(100);
		hero.setY(200);
		
		enemy.calcDirection();
		
		assertNotEquals(Direction.UP, enemy.getDirection());
		assertEquals(Direction.DOWN, enemy.getDirection());
	}
	
	

}
