package items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import gfx.Assets;
import world.Room;

/**
 * 
 * @author Debreczeni Mate
 * 
 * A targyak menedzserosztalya, a targyak egyuttes kezeleset konnyiti meg, illetve minden targytipusbol 
 * eltarol egyet statikusan, hogy ujabb targy letrehozasahoz eleg legyen a targy createNew fuggvenyet meghivni.
 */
public class ItemManager {

	public static final int ITEMWIDTH = 32,ITEMHEIGHT = 32;
	
	/**
	 * a szoba melynek a tarygait kezeljuk
	 */
	private Room room;
	/**
	 * a lista melyben a szobaban levo tarygak vannak
	 */
	private ArrayList<Item> itemsOnMap;
	/**
	 * egy tipust tarolo statikus valtozo
	 */
	public static Item heartItem;
	public static Item damagePotionItem;
	
	/**
	 * Publikus konstruktor.
	 * @param room A szoba melyben levo targyakat menedzselni kell.
	 */
	public ItemManager(Room room){
		this.room = room;
		itemsOnMap=new ArrayList<Item>();
		heartItem = new Item(this,Assets.healthPotion,"heart",0);
		damagePotionItem = new Item(this,Assets.strengthPotion,"damage",1);

	}
	
	/**
	 * A szobaban levo targyak update fuggvenyet hivja meg sorra.
	 */
	public void update() {
		Iterator<Item> it = itemsOnMap.iterator();
		while(it.hasNext()) {
			Item i = it.next();
			i.update();
			if(i.isPickedUp()) {
				it.remove();
			}
		}
	}
	
	/**
	 * A targyak kirajzolasat vegzo fuggvenyt meghivo fuggveny.
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	public void Render(Graphics graphics) {
		for(Item i: itemsOnMap) {
			i.render(graphics);
		}
	}
	
	/**
	 * Hozzaad egy targyat a menedzserhez, es a szobahoz
	 * @param i a hozzaadando targy
	 */
	public void addItem(Item i) {
		i.setRoom(room);
		itemsOnMap.add(i);
	}
	
}
