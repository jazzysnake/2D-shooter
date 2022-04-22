package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import world.Room;
/**
 * 
 * @author Debreceni Mate
 * 
 *Statikus entitas osztaly, amely az egyed leszarmazottja, funkcionalisan ugyanaz tudja mint ose,
 *de van szilardsagot szimbolizalo igazsagerteke, illetve texturaja 
 */
public class StaticEntity extends Entity{
	/**
	 * A statikus entitas szilardsagat meghatarozo igazsagertek
	 */
	protected boolean solid;
	/**
	 * az entitas texturaja
	 */
	protected BufferedImage texture;
	/**
	 * @param room A szoba amelyikben az entitasnak letre kell jonnie
	 * @param x A spawn pozicio x koordinataja
	 * @param y A spawn pozicio y koordinataja
	 * @param width Az entitas szelessege
	 * @param height Az entitas magassaga
	 * @param texture Az entitas texturaja
	 * @param solid az entitas szilardsaganak igazsagerteke
	 */
	public StaticEntity(Room room,double x, double y, int width, int height,BufferedImage texture,boolean solid) {
		super(room,x, y, width, height);
		this.solid =solid;
		this.texture= texture;

	}
	
	
	@Override
	public void update() {}

	/**
	 * A statikus entitas kirajzolasaert felelos fuggveny
	 * 
	 * @param graphics A kirajzolasert felelos grafikai osztaly
	 */
	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(texture,(int)x,(int)y,width,height,null);
	}

	/**
	 * A statikus entitas halalkor meghivano fuggveny
	 */
	@Override
	public void die() {
		alive = false;
	}
	
	public boolean isSolid() {
		return solid;
	}

}
