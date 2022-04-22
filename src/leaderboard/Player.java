package leaderboard;

import java.io.Serializable;

/**
 * 
 * @author Debreczeni Mate
 *
 * Jatekos osztaly mely eltarolja a jatekos adatait.
 */
public class Player implements Comparable<Player>, Serializable{

	private static final long serialVersionUID = 450250202605858944L;
	/**
	 * A jatekos altal legyozott szobak szamat tarolo valtozo
	 */
	private int roomsCleared;
	/**
	 * a jatekos neve
	 */
	private String name;
	
	/**
	 * Publikus konstruktor, letrehoz egy peldanyt a jatekosbol
	 * @param name A jatekos neve
	 * @param roomsCleared A jatekos altal kitisztitott szobak szama
	 */
	public Player(String name, int roomsCleared) {
		this.name = name;
		this.roomsCleared = roomsCleared;
	}
	
	//Setters and getters
	public int getRoomsCleared() {
		return roomsCleared;
	}

	public void setRoomsCleared(int roomsCleared) {
		this.roomsCleared = roomsCleared;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Player o) {
		if(o.getRoomsCleared()==roomsCleared) {
			return name.compareTo(o.getName());
		}
		else {
			return o.roomsCleared-roomsCleared;
		}
	}
	
	
}
