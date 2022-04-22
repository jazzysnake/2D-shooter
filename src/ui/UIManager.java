package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * 
 * @author Debreczeni Mate
 * 
 * A felhasznaloi felulet elemeinek egyuttes kezeleset megvalosito osztaly.
 */
public class UIManager {
	

	private ArrayList<UIObject> objects;
	
	public UIManager(){
		objects = new ArrayList<UIObject>();
	}
	
	/**
	 * Az eltarolt UIObjecteket sorra frissito fuggveny
	 */
	public void update(){

		for(UIObject o : objects)
			o.update();
	}
	
	/**
	 * Az eltarolt UIObjecteket sorra kirajzolo fuggveny
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	public void render(Graphics graphics){
		for(UIObject o : objects)
			o.render(graphics);
	}
	
	/**
	 * Az eger mozgasi esemenyet a uiobjecteknek tovabbito fuggveny.
	 * @param e az esemeny
	 */
	public void onMouseMove(MouseEvent e){
		for(UIObject o : objects)
			o.onMouseMove(e);
	}
	
	/**
	 * Az eger gombjanak felengedesi esemenet tovabbitja az eltarolt UIObjecteknek.
	 * @param e Az esemeny
	 */
	public void onMouseRelease(MouseEvent e){
		for(UIObject o : objects)
			o.onMouseRelease(e);
	}
	
	/**
	 * A menedzserben eltarolja a parameterben megadott UIObjectet
	 * @param o A hozzaadado UIObject
	 */
	public void addObject(UIObject o){
		objects.add(o);
	}
	
	/**
	 * A menedzserben eltarolt UIObjectek kozul torli a parameterben kapott
	 * UIObjectet.
	 * @param o A torlendo UIObject
	 */
	public void removeObject(UIObject o){
		objects.remove(o);
	}
	

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
