package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import gfx.RenderSorter;
/**
 * 
 * @author Debreczeni Mate
 *
 *Az entitasok egyuttes kezeleset, es interakciukat megkonnyito osztaly.
 */
public class EntityManager{

	/**
	 * A hos entitas
	 */
	private Hero hero;
	/**
	 * a szobaban levo entitasokat tarolo arraylist
	 */
	private ArrayList<Entity> entities;
	/**
	 * a szobaban levo elolenyeket tarolo arraylist
	 */
	private ArrayList<Creature> creatures;
	/**
	 * Az entitasokat y koordinatajuk szerint osszehasonlito komparator
	 */
	private RenderSorter renderSorter;
	/**
	 * Publikus konstruktor, letrehoz egypeldanyt az osztalybol.
	 * @param hero hos amit el kell tarolnunk
	 */
	public EntityManager(Hero hero) {
		this.hero = hero;
		entities = new ArrayList<>();
		creatures = new ArrayList<>();
		entities.add(hero);
		creatures.add(hero);
		renderSorter = new RenderSorter();

		}
	/**
	 * Az eltarolt entitasok ertekeit frissito duggvenyeket sorra meghivo fuggveny.
	 * Emellett meg felelos az entities lista sorbarendezeseert es a halott entitasok listabol valo eltavolitasaert
	 */
	public void update() {
		Iterator<Entity> it = entities.iterator();

		while(it.hasNext()){
			Entity e = it.next();
			e.update();
			if(!e.isAlive()) {
				it.remove();
			}
		}
		entities.sort(renderSorter.getEntityComparator());
		removeDeadCreautres();
		
	}
	/**
	 * Halott elolenyeket az elolenylistabol torlo fuggveny
	 */
	public void removeDeadCreautres() {
		Iterator<Creature> it = creatures.iterator();
		
		while(it.hasNext()){
			Creature c = it.next();
			if(!entities.contains(c)) {
				it.remove();
			}
		}
	}
	
	
	/**
	 * Az elolenylistan vegigiteralva kideriti melyik lo eppen.
	 * @return jelenleg lovo elolenyeket adja vissza arraylist formaban
	 */
	public ArrayList<Creature> currentlyShooting() {
		Iterator<Creature> it = creatures.iterator();
		ArrayList<Creature> shooters = new ArrayList<>();
		
		while(it.hasNext()){
			Creature c = it.next();
			if(c.isShooting()) {
				shooters.add(c);
				c.setShooting(false);
				}
		}
		return shooters;
	}
	
	/**
	 * az entities listaban szereplo entitasok kirajzolasaert felelos
	 * fuggvenyeket meghivo fuggveny
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	public void render(Graphics graphics) {
		for(Entity e : entities) {
			e.render(graphics);
		}
	}
	
	/**
	 * Entitast ad hozza az entities listahoz.
	 * @param entity a hozzaadni kivant entitas
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	/**
	 * elolenyt ad hozza az creatures listahoz
	 * @param c a hozzaadni kivant eloleny
	 */
	public void addCreature(Creature c) {
		creatures.add(c);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public boolean roomClearedByHero() {
		if(creatures.size()==1 && hero.alive) {
			return true;
		}
		return false;
	}

	public ArrayList<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(ArrayList<Creature> creatures) {
		this.creatures = creatures;
	}
	
}
