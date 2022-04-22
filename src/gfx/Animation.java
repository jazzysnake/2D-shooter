package gfx;

import java.awt.image.BufferedImage;
import utils.Timer;

/**
 * 
 * @author Debreczeni Mate
 *
 * Animacio osztaly, amely megjelenitheto kepek tombjebol valtogat a kepek kozott adott intervallumonkent.
 */
public class Animation {

	private int index;
	private BufferedImage[] frames;
	Timer timer = new Timer();

	/**
	 * 
	 * @param milisPerFrame a kepek kivetitesenek ideje miliszekundumban
	 * @param frames a kepek tombje melyeket felvaltva ki fogunk rajzolni
	 */
	public Animation(int milisPerFrame,BufferedImage[] frames) {
		this.frames = frames;
		index=0;
	}
	
	/**
	 * Az animacio kepeinek lepteteseert felelo fuggveny
	 */
	public void update() {
		if(timer.timerWithoutWait(2)) {
			index++;
		}
		if(index==frames.length) {
			index=0;
		}
	}

	/**
	 * 
	 * @return az epp megjelenitendo kep a keptombbol
	 */
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}


	

	
}
