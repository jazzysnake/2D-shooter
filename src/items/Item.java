package items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import world.Room;

/**
 * 
 * @author Debreczeni Mate
 * 
 * A jatekban levo felveheto targyak osztalya.
 */
public class Item {

	/**
	 * szoba melyben a targy van
	 */
	protected Room room;
	/**
	 * a taryg texturaja
	 */
	protected BufferedImage texture;
	/**
	 * a targy neve
	 */
	protected String name;
	/**
	 * a taryg azonositoja
	 */
	protected final int id;
	/**
	 * a tarygat hatarolo teglalap
	 */
	protected Rectangle bounds;
	/**
	 * a targy poziciojat tarolo valtozok
	 */
	protected double x,y;
	/**
	 * a targy darabszama
	 */
	int count;
	/**
	 * igazsagertek hogy felvettek e a targyat
	 */
	protected boolean pickedUp;
	/**
	 * az targyakat kezelo osztaly
	 */
	protected ItemManager itemManager;
	
	/**
	 * A letrehozando item publilkus konstruktora
	 * 
	 * @param itemManager A targyak menedzseleset vegzo osztaly
	 * @param texture A targy texturaja
	 * @param name A targy neve
	 * @param id A targy azonositoja
	 */
	public Item(ItemManager itemManager,BufferedImage texture, String name,int id) {
		this.id = id;
		this.texture = texture;
		this.name = name;
		this.count = 1;
		this.itemManager = itemManager;
		
		pickedUp = false;
		bounds = new Rectangle((int)x,(int)y,ItemManager.ITEMWIDTH,ItemManager.ITEMHEIGHT);
		
	}
	
	/**
	 * A targy kirajzolasat vegzo fuggveny
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	public void render(Graphics graphics) {
		graphics.drawImage(texture, (int)x, (int)y, ItemManager.ITEMWIDTH, ItemManager.ITEMHEIGHT,null);
	}
	
	/**
	 * A hos poziciojat figyelve frissiti a picked up igazsagerteket, ha a hos
	 * hitboxa metszi a targyet.
	 */
	public void update() {
		if(room.getEntityManager().getHero().getCollisionBounds(0,0).intersects(bounds)) {
			pickedUp=true;
			room.getEntityManager().getHero().getInventory().additem(this);
		}
	}
	
	/**
	 * Uj targyat hoz letre az adott pozicioban.
	 * @param x A pozicio x koordinataja
	 * @param y A pozicio y koordinataja
	 * @return A letrehozott targy
	 */
	public Item createNew(int x, int y) {
		Item i = new Item(itemManager,texture,name,id);
		i.setPos(x, y);
		bounds.x = x;
		bounds.y = y;
		return i;
	}
	
	//getters and setters

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setPos(int x,int y) {
		this.x = x;
		this.y = y;
		bounds.x=x;
		bounds.y=y;
	}

	public double getY() {
		return y;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public boolean isPickedUp() {
		return pickedUp;
	}
}
