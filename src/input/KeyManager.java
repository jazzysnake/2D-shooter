package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Debreczeni Mate
 *
 *A gombnyomasok eszleleset vegzo osztaly.
 */
public class KeyManager implements KeyListener{

	/**
	 * igazsagertek tomb, amelyben a billenytuk lenyomasat tarolhatjuk
	 */
	private boolean[] keys;
	/**
	 * igazsagertekek, melyeket az inputokkal allitunk
	 */
	private boolean up,down,right,left, attacking;
	
	/**
	 * Default konstruktor
	 */
	public KeyManager(){
		keys = new boolean[256];
	}
	
	/**
	 * A lenyomott gombok alapjan a billentyuzetet szimbolizalo igazsagertektombben allitja az ertekeket.
	 */
	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_A];	
		attacking = keys[KeyEvent.VK_SPACE];
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * Gomb lenyomasakor igazra allitja az igazsagertektombben a megfelelo erteket.
	 * 
	 * @param e a lenyomott gomb esemenye
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()<0||e.getKeyCode()>keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	/**
	 * A gomb felengedesekor hamisra allitja a megfelelo erteket az
	 * igazsagertek tombben.
	 * 
	 * @param e a felengedett gomb esemenye
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()<0||e.getKeyCode()>keys.length)
			return;
		keys[e.getKeyCode()] = false;
		
	}

	/**
	 * Ket jatek lefutasa kozott meghivando reset fuggveny, amely
	 * az igazsagertek tombben minden erteket hamisra ir.
	 */
	public void reset() {
		for(int i =0;i<keys.length;++i) {
			keys[i]=false;
		}
	}

	public boolean isUp() {
		return up;
	}


	public boolean isDown() {
		return down;
	}


	public boolean isRight() {
		return right;
	}


	public boolean isLeft() {
		return left;
	}

	public boolean isAttacking() {
		return attacking;
		}

}
