package items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import entities.Hero;

/**
 * 
 * @author Debreczeni Mate
 * 
 * A hos felszereleset alkoto itemek felveteleert tarolasaert es hatasik teljesuleseert felelos osztaly.
 */
public class Inventory {

	/**
	 * A hos akie a felszereles
	 */
	private Hero hero;
	/**
	 * a felszeresben levo targyakat eltarolo lista
	 */
	private ArrayList<Item> inventoryItems;
	
	/**
	 * @param hero A hos melynek felszerelest kivanunk adni.
	 */
	public Inventory(Hero hero){
		this.hero = hero;
		inventoryItems = new ArrayList<>();
	}
	
	/**
	 * A felszerelesben tarolt kirajzolando targyak kepernyore rajzolasaert felelo osztaly.
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	public void render(Graphics graphics) {
	}
	
	/**
	 * A felszerelesben tarolt targyak kezeleset vegzo fuggveny
	 */
	public void update() {
		Iterator<Item> it = inventoryItems.iterator();
		while(it.hasNext()) {
			Item i = it.next();
			if(i.getName().equals("heart")) {
				hero.setLives(hero.getLives()+1);
				it.remove();
			}
			if(i.getName().equals("damage")) {
				hero.setDamageMultiplier(hero.getDamageMultiplier()*1.1);
				it.remove();
			}
		}
	}
	
	/**
	 * Hozzaad egy targyat a felszereleshez
	 * @param item a hozzaadni kivant targy
	 */
	public void additem(Item item) {
		for(Item i:inventoryItems) {
			if(i.getId()==item.getId()) {
				i.setCount(i.getCount()+item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
}
