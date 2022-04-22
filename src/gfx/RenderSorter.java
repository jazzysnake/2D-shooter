package gfx;

import java.util.Comparator;

import entities.Entity;

/**
 * 
 * @author Debrczeni Mate
 *
 *Komparator osztaly, amely ket entitast hasonlit ossze legalacsonyabb pontjuk alapjan.
 */
public class RenderSorter {
	private Comparator<Entity> entityComparator;

	public RenderSorter(){
		entityComparator = new Comparator<Entity>() {

			/**
			 * 
			 * @param e1 az egyik osszehasonlitando entitas
			 * @param e2 a masik osszehasonlitando entitas
			 * @return -1 ha az elso entitas van magasabban,1 ha a masodik 
			 */
			@Override
			public int compare(Entity e1, Entity e2) {
				if(e1.getLowestPoint()<e2.getLowestPoint()) {
					return -1;
				}
				else {
					return 1;
				}
			
			}
		};
	}
	
	public Comparator<Entity> getEntityComparator(){
		return entityComparator;
	}
}
