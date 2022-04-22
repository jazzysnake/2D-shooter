package world;

import java.awt.Graphics;
import java.awt.Point;

import entities.Hero;
import states.GameState;

/*
 * 
 * Szint osztaly, szobak 2D-s matrixat tarolja es egyutt kezeli oket
 */
public class Level {


	private GameState gameState;
	private Room[][] rooms;
	private Room currentRoom;
	private Hero hero;
	private RoomLayoutManager roomLayoutManager;
	private boolean cleared;
	private int clearedRooms;
	
	/**
	 * Publikus konstruktor, letrehoz egy peldanyt a szintbol.
	 * @param gameState A jatekallapot amiben letrehozni kivanjuk a szintet 
	 */
	public Level(GameState gameState) {
		roomLayoutManager = new RoomLayoutManager(3,3);
		this.gameState= gameState;
		rooms = roomLayoutManager.getLayout();
		cleared = false;
		clearedRooms=0;
		
		initRoomsArray();
		
		currentRoom = rooms[roomLayoutManager.getCenterRow()][roomLayoutManager.getCenterCol()];
		hero = new Hero(currentRoom,currentRoom.getSpawnX(),currentRoom.getSpawnY(),4,3);
		
		currentRoom.init();
	}
	
	/**
	 * A szobak matrixat inicializaljuk
	 */
	public void initRoomsArray() {
		for(int i =0;i<roomLayoutManager.getRows();++i) {
			for(int j=0;j<roomLayoutManager.getCols();++j) {
				rooms[i][j].setParentLevel(this);
				//rooms[i][j].setCleared(true);

			}
		}
	}
	
	/**
	 * A jelenlegi szobat allitja at i parameternek megfeleloen
	 * @param i Az ajto szama amelyen kilepett a hos 
	 */
	public void changeCurrentRoom(int i) {
		if(i==0) {
			initNorthNeighbor();
		}
		if(i==1) {
			initWestNeighbor();	
		}
		if(i==2) {
			initEastNeighbor();
		}
		if(i==3) {
			initSouthNeighbor();
		}
	}
	
	/**
	 * Inicializalja az eszaki szomszedot, es beallitja jelenlegi szobakent
	 */
	public void initNorthNeighbor() {
		Room r = getNeighbor(0);
		r.init(3);
		currentRoom = r;
	}
	/**
	 * Inicializalja az nyugati szomszedot, es beallitja jelenlegi szobakent
	 */
	public void initWestNeighbor() {
		Room r = getNeighbor(1);
		r.init(2);
		currentRoom = r;
	}
	/**
	 * Inicializalja az deli szomszedot, es beallitja jelenlegi szobakent
	 */
	public void initSouthNeighbor() {
		Room r = getNeighbor(3);
		r.init(0);
		currentRoom = r;
	}
	/**
	 * Inicializalja az keleti szomszedot, es beallitja jelenlegi szobakent
	 */
	public void initEastNeighbor() {
		Room r = getNeighbor(2);
		r.init(1);
		currentRoom = r;
	}
	
	/**
	 * Visszater a jelenlegi szoba parameter szerinti szomszedjaval.
	 * @param i az ajto szama amin a hos kilepett
	 * @return a megtalalt szomszed
	 */
	public Room getNeighbor(int i) {
		Room[] neighbors = roomLayoutManager.getNeighbors(getIndexOfCurrentRoom().y,getIndexOfCurrentRoom().x);
		if(i==0) {
			return neighbors[1];
		}
		else if(i==1) {
			return neighbors[7];
		}
		else if(i==2) {
			return neighbors[3];
		}
		else if(i==3) {
			return neighbors[5];
		}
		else return null;
	}
	
	/**
	 * @return a jelenlegi szoba indexe a 2d-s matrixban
	 */
	public Point getIndexOfCurrentRoom() {
		for(int y=0;y<roomLayoutManager.getRows();++y) {
			for(int x =0;x<roomLayoutManager.getCols();++x) {
				if(rooms[y][x]==currentRoom) {
					return new Point(x,y);
				}
			}
		}
		return null;
	}
	
	
	/**
	 * A jelenlegi szoba adatait frissiti es meghivja a 
	 * gyozelmi feltetel teljesuleset ellenorzo fuggvenyt
	 */
	public void update() {
		clearedRooms=countClearedRooms();
		currentRoom.update();
		
		if(currentRoom.heroOnOpenDoor()!=-1) {
			changeCurrentRoom(currentRoom.heroOnOpenDoor());
		}
		if(checkWincondition()) {
			cleared = true;
			clearedRooms++;
		}
	}
	
	/**
	 * 
	 * @return a szornyektol megtisztitott szobak szama
	 */
	public int countClearedRooms() {
		int i=0;
		for(Room[] r: rooms) {
			for(Room r1:r) {
				if(r1.isCleared()) {
					i++;
				}
			}
		}
		return i;
	}
	
	/**
	 * 
	 * @return igaz ha teljesul a gyozelmi feltetel
	 */
	public boolean checkWincondition() {
		for(Room[] r: rooms) {
			for(Room r1:r) {
				if(!r1.isCleared()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * A szint kirajzolasaert felelos fuggveny 
	 * @param graphics A rajzolasert felelos grafikai osztaly
	 */
	public void render(Graphics graphics) {
		currentRoom.render(graphics);
	}
	
	//getters and setters
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Room[][] getRooms() {
		return rooms;
	}

	public Hero getHero() {
		return hero;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}

	public int getClearedRooms() {
		return clearedRooms;
	}

	public void setClearedRooms(int clearedRooms) {
		this.clearedRooms = clearedRooms;
	}
	
	
}
