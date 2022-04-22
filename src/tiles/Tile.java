package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Debreczeni Mate
 *
 * Mezo osztaly, a jatekban a szobak mezokbol allnak, es ezeknek az alapveto tulajdonsagait 
 * tarolja el az osztaly.
 */
public class Tile {

	protected BufferedImage texture;
	protected final int ID;
	protected boolean solid;
	protected TileManager tileManager;

	/**
	 * publikus konstruktor,letrehoz egy peldanyt a mezobol a parameterek szerint.
	 * @param tileManager mezomenedzser amihez hozzaadjuk a mezot
	 * @param texture a mezo texturaja
	 * @param ID a mezo azonositoja
	 * @param isSolid a mezo szilardsaganak igazsagerteke
	 */
	public Tile(TileManager tileManager,BufferedImage texture, int ID, boolean isSolid) {
		this.texture=texture;
		this.ID=ID;
		this.solid=isSolid;
		this.tileManager = tileManager;
		}

	/*
	 * A mezo ertekeinek frissiteseert felelo fuggveny, ha kesobb lenne nem statikus mezo ami
	 * a mezo leszarmazottja, ezt a fuggvenyt kene felulirnia.
	 */
	public void update() {
		
	}
	
	public int getID() {
		return ID;
	}

	public void render(Graphics graphics, int x, int y) {
		graphics.drawImage(texture, x, y,TileManager.getInstance().getTileWidth(),TileManager.getInstance().getTileHeight(), null);
	}
	
	public boolean isSolid() {
		return solid;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
}
