package world;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import entities.Creature;
import entities.Enemy;
import entities.EntityManager;
import entities.Hero;
import entities.StaticEntity;
import gfx.Assets;
import items.ItemManager;
import projectiles.BulletManager;
import tiles.Tile;
import tiles.TileManager;
import utils.Utils;

/**
 * 
 * @author Debreczeni Mate
 *
 * A jatekban a szintek szobakbol epulnek fel. A szoba osztaly a benne levo entitasoknak biztosit
 * teret, es mezokbol epul fel. 
 */

public class Room{

	private int width, height;
	private int spawnX, spawnY;
	private Tile[][] roomTiles;
	private ArrayList<Point> doorTiles;
	private Level parentLevel;
	private boolean northNeighbor,southNeighbor,eastNeighbor,westNeighbor;
	private boolean cleared;	
	
	private EntityManager entityManager;
	private ItemManager itemManager;
	private TileManager tileManager;
	private BulletManager bulletManager;
	
	private Hero hero;


	/**
	 * Publikus konstruktor, letrehoz az osztalybol egy peldanyt a parametereknek megfeleloen.
	 * @param width A szoba szelessege
	 * @param height A szoba magassaga
	 * @param spawnX A hos kezdoozicioja mezoindex elso tagjaval
	 * @param spawnY A hos kezdoozicioja mezoindex masodik tagjaval
	 * @param roomtiles A mezokbol felepulo matrix
	 */
	public Room(int width, int height, int spawnX,int spawnY, Tile[][] roomtiles) {
		northNeighbor=southNeighbor=eastNeighbor=westNeighbor=false;
		this.width = width;
		this.height = height;
		this.spawnX = spawnX*TileManager.getInstance().getTileWidth();
		this.spawnY = spawnY*TileManager.getInstance().getTileHeight();
		this.roomTiles = roomtiles;
		cleared = false;
		
		doorTiles = new ArrayList<>();
		tileManager = TileManager.getInstance();

		findDoorTiles();
	}
	
	/**
	 * A szoba kezdeti ertekeit inicializalja
	 */
	public void init() {
		hero = parentLevel.getHero();
		hero.setX(this.spawnX);
		hero.setY(this.spawnY);
		hero.setRoom(this);
		
		entityManager = new EntityManager(hero);
		bulletManager = BulletManager.getInstance();
		bulletManager.setCurrentRoom(this);
		itemManager = new ItemManager(this);
		
		//temporary
		entityManager.addEntity(new StaticEntity(this,100,100,64,64,Assets.barrel,true));

		if(!cleared) {
			initEnemies();
		}
	
	}
	/**
	 * Ez a fuggveny a szoba kezdeti ertekeit inicializalja, a kivant belepesi pont alapjan
	 * @param i a belepesi ajto sorszama
	 */
	public void init(int i) {
		hero = parentLevel.getHero();
		setSpawnRelativeToDoor(i);
		hero.setX(this.spawnX);
		hero.setY(this.spawnY);
		hero.setRoom(this);
		
		entityManager = new EntityManager(hero);
		bulletManager = BulletManager.getInstance();
		bulletManager.setCurrentRoom(this);
		itemManager = new ItemManager(this);
		
		//temporary
		entityManager.addEntity(new StaticEntity(this,100,100,64,64,Assets.barrel,true));

		if(!cleared) {
			initEnemies();
		}
	
	}
	
	/**
	 * Ez a fuggveny a parameterkent
	 * kapott ajtonak megfeleloen allitja be a spawnpoziciot a kovetkezo szobaban.
	 * @param i Az ajto sorszama amin be kivanunk lepni
	 */
	public void setSpawnRelativeToDoor(int i) {
		if(i==0) {
			this.spawnX = (int)(this.doorTiles.get(i).getX()*TileManager.getInstance().getTileWidth()+TileManager.getInstance().getTileWidth()/2);
			this.spawnY = (int)(this.doorTiles.get(i).getY()*TileManager.getInstance().getTileHeight()+TileManager.getInstance().getTileHeight());
		}
		else if(i==3) {
			this.spawnX = (int)(this.doorTiles.get(i).getX()*TileManager.getInstance().getTileWidth()+TileManager.getInstance().getTileWidth()/2);
			this.spawnY = (int)(this.doorTiles.get(i).getY()*TileManager.getInstance().getTileHeight()-TileManager.getInstance().getTileHeight());
		}
		else if(i==1) {
			this.spawnX = (int)(this.doorTiles.get(i).getX()*TileManager.getInstance().getTileWidth()+TileManager.getInstance().getTileWidth());
			this.spawnY = (int)(this.doorTiles.get(i).getY()*TileManager.getInstance().getTileHeight()+TileManager.getInstance().getTileHeight()/2);
		}
		else if(i==2) {
			this.spawnX = (int)(this.doorTiles.get(i).getX()*TileManager.getInstance().getTileWidth()-TileManager.getInstance().getTileWidth());
			this.spawnY = (int)(this.doorTiles.get(i).getY()*TileManager.getInstance().getTileHeight()+TileManager.getInstance().getTileHeight()/2);
		}
	}
	
	/**
	 * A szobaban levo ajtomezok indexet adja hozza az doorTiles tombhoz
	 */
	public void findDoorTiles() {
		for(int y =0; y<height;++y) {
			for(int x =0; x<width;++x) {
				if(roomTiles[x][y].getID()==9||roomTiles[x][y].getID()==10||
					roomTiles[x][y].getID()==11||roomTiles[x][y].getID()==12) {
					doorTiles.add(new Point(x,y));
				}
			}
		}
	}


	/**
	 * A szobaban levo entitasok targyak lovedekek es mezok ertekeinek frissiteert
	 * felelos fuggvenyeket meghivo fuggveny
	 */
	public void update() {
		entityManager.update();
		bulletManager.update();
		itemManager.update();
		
		updateTiles();
		updateDoors();
	}
	
	/**
	 * A kirajzolasert felelos fuggveny
	 * @param graphics A kirajzolasert felelos grafikai osztaly
	 */
	public void render(Graphics graphics) {
		for(int y =0; y<height;++y) {
			for(int x =0; x<width;++x) {
				roomTiles[x][y].render(graphics, x*TileManager.getInstance().getTileWidth(), y*TileManager.getInstance().getTileHeight());
			}
		}
		entityManager.render(graphics);
		itemManager.Render(graphics);
		bulletManager.render(graphics);
	}
	
	/**
	 * A mezok ertekeit frissito fuggveny
	 */
	public void updateTiles() {
		for(int y =0; y<height;++y) {
			for(int x =0; x<width;++x) {
				roomTiles[x][y].update();
			}
		}
	}
	
	/**
	 * Amennyiben meghivasakor mar csak a hos van eletben a szobaban, 
	 * a szoba elhelyezkedesenek megfelelo ajtokat kinyitja
	 */
	public void updateDoors() {
		if(entityManager.roomClearedByHero()) {
			cleared = true;
			if(this.northNeighbor) {
				roomTiles[doorTiles.get(0).x][doorTiles.get(0).y]= tileManager.getNewTile(13);
				}

			if(this.westNeighbor) {
				roomTiles[doorTiles.get(1).x][doorTiles.get(1).y]= tileManager.getNewTile(15);
			}

			if(this.eastNeighbor) {
				roomTiles[doorTiles.get(2).x][doorTiles.get(2).y]= tileManager.getNewTile(16);
			}

			if(this.southNeighbor) {
				roomTiles[doorTiles.get(3).x][doorTiles.get(3).y]= tileManager.getNewTile(14);
			}

		}
	}
	
	/**
	 * ha a hos egy nyitott ajton all a fuggveny visszater az ajto szamaval
	 * (0-ha az eszaki, 1 ha a nyugati ,2 ha a keleti es 3 ha a deli)
	 * @return az ajto szama amelyen a hos all
	 */
	public int heroOnOpenDoor() {
		for(Point door:doorTiles) {
			if(roomTiles[door.x][door.y].getID()==13||roomTiles[door.x][door.y].getID()==14
					||roomTiles[door.x][door.y].getID()==15||roomTiles[door.x][door.y].getID()==16) {
				for(int i=0;i<4;++i) {
					if(roomTiles[door.x][door.y]==getTileByPixels((hero.getBoundCorners()[i].getX()),hero.getBoundCorners()[i].getY()))
					{
						return doorTiles.indexOf(door);
					}
				}
			}
		}
		return -1;
	}
	
    /**
     * A pixel szerinti poziciot leforditja mezore.
     * @param x a pixelpozicio x koordinataja
     * @param y a pixelpozicio y koordinataja
     * @return a pixelpozicio szerinti mezo
     */
	public Tile getTileByPixels(double x, double y) {
		return roomTiles[(int)(x/tileManager.getTileWidth())][(int)(y/tileManager.getTileHeight())];
	}
	
	/**
	 * Veletlen szamu ellenfelet general veletlen szeru helyre a szobaban. oket inicializalja is.
	 */
	public void initEnemies() {
		int enemyCount = Utils.randomBetween(2,6);

		Point2D.Double[] spawnPos = new Point2D.Double[enemyCount];
		for(int i=0;i<enemyCount;++i) {
			spawnPos[i]=new Point2D.Double(Utils.randomBetween(tileManager.getTileWidth()+10,800),Utils.randomBetween(tileManager.getTileHeight()+10,350));
		}
		for(int i=0;i<enemyCount;++i) {
			while(pointNearHero(250, spawnPos[i])||pointNearEnemies(150, spawnPos, spawnPos[i])) {
				spawnPos[i]=new Point2D.Double(Utils.randomBetween(tileManager.getTileWidth()+10,800),Utils.randomBetween(tileManager.getTileHeight()+10,350));
			}
		}
		for(int i=0;i<enemyCount;++i) {
			Enemy enemy = new Enemy(this,spawnPos[i].getX(),spawnPos[i].getY(),Creature.DEFAULT_CREATUREWIDTH,Creature.DEFAULT_CREATUREHEIGHT,5,1);
			entityManager.addEntity(enemy);
			entityManager.addCreature(enemy);
		}
	}
	
	/**
	 * @param distance a hostol vett tavolsag amin belul kozelinek minositjuk a pontot
	 * @param point a pont amit vizsgalunk
	 * @return az igazsagertek miszerint hoshoz közeli-e a pont
	 */
	public boolean pointNearHero(int distance,Point2D.Double point) {
		Point2D.Double heroPos = new Point2D.Double(hero.getX(), hero.getY());
		if(heroPos.distance(point)<distance) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param distance a tobbi ellensegtol vett tavolsag amin belul kozelinek minositjuk a pontot
	 * @param point a pont amit vizsgalunk
	 * @return az igazsagertek miszerint mas ellenfelhez közeli-e a pont
	 */
	public boolean pointNearEnemies(int distance,Point2D.Double[] enemies,Point2D.Double point ) {
		for(int i = 0;i<enemies.length;++i) {
			if(enemies[i].distance(point)<distance&&enemies[i]!=point) {
				return true;
			}
		}	
		return false;
	}
	
	
	
	
//getters and setters

	public BulletManager getBulletManager() {
		return bulletManager;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager e) {
		this.entityManager=e;
	}


	public Level getParentLevel() {
		return parentLevel;
	}


	public void setParentLevel(Level parentLevel) {
		this.parentLevel = parentLevel;
	}

	public boolean hasNorthNeighbor() {
		return northNeighbor;
	}

	public void setNorthNeighbor(boolean northNeighbor) {
		this.northNeighbor = northNeighbor;
	}

	public boolean hasSouthNeighbor() {
		return southNeighbor;
	}

	public void setSouthNeighbor(boolean southNeighbor) {
		this.southNeighbor = southNeighbor;
	}

	public boolean hasEastNeighbor() {
		return eastNeighbor;
	}

	public void setEastNeighbor(boolean eastNeighbor) {
		this.eastNeighbor = eastNeighbor;
	}

	public boolean hasWestNeighbor() {
		return westNeighbor;
	}

	public void setWestNeighbor(boolean westNeighbor) {
		this.westNeighbor = westNeighbor;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}

	public void setBulletManager(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}
	

}
