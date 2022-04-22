package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


import gfx.Assets;
import input.MouseManager;
import shooter.Game;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

/**
 * 
 * @author Debreczeni Mate
 * 
 * Menuallapot osztaly, a menu kinezetet, es a benne levo felhasznaloi felulet
 * mukodteteseert felelos
 */

public class MenuState extends State{
	StateManager stateManager;
	UIManager uiManager;
	
	private int stateChangeButtonNum;
	private boolean stateChange[];
	private State[] stateButton;


	/**
	 * Publikus konstruktor, letrehoz egy peldanyt az osztalybol
	 * @param game A jatek aminek az allapotot kivanjuk adni
	 * @param stateChangeButtonNum Az allapotvaltast vegzo gombok szama
	 */
	
	public MenuState(Game game,int stateChangeButtonNum) {
		super(game);
		stateManager = StateManager.getInstance();
		this.stateChangeButtonNum = stateChangeButtonNum;
		stateChange = new boolean[stateChangeButtonNum];
		stateButton = new State[stateChangeButtonNum];
		for(int i = 0;i<stateChangeButtonNum;++i)
			stateChange[i]=false;
		uiManager = new UIManager();
		MouseManager.getInstance().setUIManager(uiManager);
	}
	
	/**
	 * Az allapot inicializalasat vegzo fuggveny
	 */
	@Override
	public void init() {
		uiManager.addObject(new UIImageButton(500, 100,256, 96, Assets.playButton, new ClickListener() {

			/**
			 * A gombra kattintaskor megvalositando viselkedes implementalasa:
			 * allapotovaltas igazsagertket tesszuk igazza, es a jatek uimenedzseret nullra allitjuk
			 */
			@Override
			public void onClick() {
				MouseManager.getInstance().setUIManager(null);
				stateButton[0] = game.getGameState();
				stateChange[0] = true;

			}
			
			
		}));
		uiManager.addObject(new UIImageButton(500, 200,256, 96, Assets.leaderBoardButton, new ClickListener() {

			/**
			 * A gombra kattintaskor megvalositando viselkedes implementalasa:
			 * allapotovaltas igazsagertket tesszuk igazza, es a jatek uimenedzseret
			 *  a kovetkezo allapotra allitjuk
			 */
			@Override
			public void onClick() {
				MouseManager.getInstance().setUIManager(game.getLeaderBoardstate().getUiManager());
				stateButton[1] = game.getLeaderBoardstate();
				stateChange[1] = true;


			}
			
			
		}));
		uiManager.addObject(new UIImageButton(500, 300,256, 96, Assets.quitButton, new ClickListener() {

			/**
			 * A gombra kattintaskor megvalositando viselkedes implementalasa:
			 * allapotovaltas igazsagertket tesszuk igazza, es a jatek uimenedzseret nullra allitjuk
			 */
			@Override
			public void onClick() {
				MouseManager.getInstance().setUIManager(null);
				game.setRunning(false);
			}
			
			
		}));
	}

	/**
	 * Az allapot frissitiset vegzo fuggveny, allapotvaltas valtozo igaz erteke eseten allapototo valt,
	 * a gombnak megfelelo allapotba.
	 * 
	 */
	@Override
	public void update() {
		for(int i = 0;i<stateChangeButtonNum;++i) {
			if(!stateChange[i]) {
				uiManager.update();
				}
			else{
				stateChange[i] = false;
				stateManager.setCurrentState(stateButton[i]);
	
			}
		}
	}

	/**
	 * Az allapot kirajzolasat vegzo fuggveny.
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.white);
		graphics.drawImage(Assets.background, 0, 0,game.getWidth(),game.getHeight(),null);
		graphics.setFont(new Font("Times New Roman",Font.BOLD,60));
		graphics.drawString("2D SHOOTER", 100, 250);
		uiManager.render(graphics);
	}
	

	public UIManager getUiManager() {
		return uiManager;
	}

}
