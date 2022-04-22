package leaderboard;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Debreczeni Mate
 *
 * Dicsoseglista osztaly, amely a jatekosok altal elert eredmenyeket tarolja el, es ezen adatok 
 * rendezeseert is felelos.
 */
public class LeaderBoard {

	/**
	 * A dicsoseglistaban tarolt jatekosokat tarolo lista
	 */
	ArrayList<Player> list;
	
	public LeaderBoard() {
		list = new ArrayList<>();
	}
	public LeaderBoard(ArrayList<Player> list) {
		this.list=list;
	}
	
	/**
	 * A rendezest vegzo fuggveny
	 */
	public void sort() {
		Collections.sort(list);
	}
	
	/**
	 * A dicsoseglistahoz hozzaad egy jatekost
	 * @param p A hozzaadni kivant jatekos
	 */
	public void addPlayer(Player p) {
		list.add(p);
		sort();
	}

	/**
	 * A dicsoseglosta elmenteset vegzo fuggveny
	 * @throws IOException ha nem sikerulne a kiiras
	 */
	public void save() throws IOException {
		FileOutputStream fo = new FileOutputStream("save.ser");
		ObjectOutputStream ou = new ObjectOutputStream(fo);
		ou.writeObject(list);
		ou.close();
	}

	/**
	 * A kimentett dicsoseglista fajlrendszerbol valo betolteset vegzo fuggveny
	 * @return a betoltott dicsoseglista
	 * @throws ClassNotFoundException 
	 * @throws IOException ha nem talalhato a filerendszeben a kimentett dicsoseglista
	 */
	public static ArrayList<Player> load() throws ClassNotFoundException, IOException {
		FileInputStream fi = new FileInputStream("save.ser");
		ObjectInputStream oi = new ObjectInputStream(fi);
		@SuppressWarnings("unchecked")
		ArrayList<Player> lb = (ArrayList<Player>)oi.readObject();
		oi.close();
		return lb;
	}
	public ArrayList<Player> getList() {
		return list;
	}
	public void setList(ArrayList<Player> list) {
		this.list = list;
	}
	
}
