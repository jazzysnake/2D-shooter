package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ui.UIManager;

/**
 * 
 * @author Debreczeni Mate
 * 
 * Az egermozgasokat, es kattintasokat kezelo singleton osztaly.
 */
public class MouseManager implements MouseListener, MouseMotionListener {

	/**
	 * igazsagertekek,melyek taroljak hogy le van-e nyomva az eger jobb vagy bal gombja
	 */
	private boolean leftPressed, rightPressed;
	/**
	 * a kurzor pozicioja
	 */
	private int mouseX, mouseY;
	/**
	 * A felhasznaloi felulethez tartozo kezelo
	 */
	private UIManager uiManager;
	
	private static MouseManager singleInstance=null;
	
	/**
	 * privat default konstruktor
	 */
	private MouseManager(){}
	
	/**
	 * 
	 * @return az osztaly egyetlen peldanya
	 */
	public static MouseManager getInstance() {
		if(singleInstance==null) {
			singleInstance = new MouseManager();
		}
		return singleInstance;
	}
	/**
	 * Az epp megjelenitett, azaz az egermozgasok es kattintasok szempontjabol figyelendo
	 * felhasznaloi feluletet beallito fuggveny.
	 * @param uiManager A felhasznaloi felulet kezeleset vegzo osztaly peldanya.
	 */
	public void setUIManager(UIManager uiManager){
		this.uiManager = uiManager;
	}
	
	// Getters
	
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	/**
	 * A bal egergomb lenyomasakor allitja igazza az ennek meglelo igazsagerteket.
	 * @param e a lenyomas esemyenye
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	/**
	 * A bal egergomb felengedesekor allitja igazza az ennek meglelo igazsagerteket.
	 * @param e a felengedes esemenye
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		
		if(uiManager != null)
			uiManager.onMouseRelease(e);
	}

	/**
	 * Az eger mozditasakor a kurzor poziciojat allitja be.
	 * 
	 * @param e az egermozgas esemenye
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
	}
	
	//Orokles miatt felulirando fuggvenyek
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}