package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * 
 * @author Debreczeni Mate
 * 
 *Textura betolto osztaly, amely a texturak fajlrendszerbol valo
 *betolteseert felel
 */
public class TextureLoader {

	/**
	 * A kep betolteset vegzo fuggveny
	 * @param path a kep eleresi utja a filerendszerben
	 * @return null ha nem talalja a file-t, vagy pedig a betoltott kep
	 */
	public static BufferedImage LoadTexture(String path) {
		try {
			return ImageIO.read(TextureLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	} 
}
