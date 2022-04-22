package gfx;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Debreczeni Mate
 * 
 *A texturakat tartalmazo kep osztaly amibol kivagjuk az egyes 
 *osztalyok sajat texturait.
 */
public class SpriteSheet {

	private BufferedImage sheet;
	
	/**
	 * Publikus konstruktor, letrehoz az osztalybol egy peldanyt
	 * @param sheet a kep amin a spiretok vannak
	 */
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	/**
	 * A kivagast vegzo fuggveny
	 * 
	 * @param x a kivagando kepdarab balfelso pontjanak x koordinataja
	 * @param y a kivagando kepdarab balfelso pontjanak x koordinataja
	 * @param width a kivagando kep szelessege
	 * @param height a kivagando kep magassaga
	 * @return a kivagott kep
	 */
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
}
