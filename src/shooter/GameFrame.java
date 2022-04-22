package shooter;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.*;

/**
 * 
 * @author Debreczeni Mate
 *
 *A jatek megjeleniteset vegzo keretosztaly
 */
public class GameFrame {
	
	private JFrame frame;
	private int frameWidth;
	private int frameHeight;
	private Canvas canvas;
	
	/**
	 * Publikus konstruktor, letrehoz egy peldanyt a megadott parameterk szerint
	 * @param width a kivant szelesseg
	 * @param height a kivant magassag
	 */
	public GameFrame(int width, int height) {
		
		frameWidth = width;
		frameHeight = height;

		frame = new JFrame();
		frame.setTitle("2D shooter");
		frame.setSize(width, height);
		frame.setLayout(null);
		frame.setPreferredSize(new Dimension(frameWidth,frameHeight));
		frame.setMaximumSize(new Dimension(frameWidth,frameHeight));
		frame.setMinimumSize(new Dimension(frameWidth,frameHeight));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	/**
	 * A kerethez vaszont hozzaado fuggveny
	 */
	public void addCanvas() {
		frame.invalidate();
		frame.setLayout(new BorderLayout());
		canvas = new Canvas();		
		canvas.setPreferredSize(new Dimension(frameWidth,frameHeight));
		canvas.setMaximumSize(new Dimension(frameWidth,frameHeight));
		canvas.setMinimumSize(new Dimension(frameWidth,frameHeight));
		canvas.setBackground(Color.BLACK);
		canvas.setFocusable(false);
		canvas.setVisible(true);

		frame.add(canvas);
		frame.pack();

	}
	
	/**
	 * A keret vasznat eltavolito fuggveny
	 */
	public void removeCanvas() {
		frame.remove(canvas);
	}
	
	/**
	 * A keret bezarasat vegzo fuggveny
	 */
	public void close() {
		removeCanvas();
		frame.dispose();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public int getframeWidth() {
		return frameWidth;
	}
	
	public int getframeHeight() {
		return frameHeight;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}
