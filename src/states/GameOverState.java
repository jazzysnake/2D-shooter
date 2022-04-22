package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import gfx.Assets;
import input.MouseManager;
import input.TextInputWindow;
import leaderboard.Player;
import shooter.Game;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

/**
 * 
 * @author Debreczeni Mate
 *
 * Jatek vege allapot osztaly, amely a jatek vegetertekor hatarozza meg a program viselkedeset.
 */
public class GameOverState extends State{
	
	private UIManager uiManager;
	private StateManager stateManager;
	
	private boolean stateChange;
	private Player player;
	boolean playerAdded,initialized;
	private MenuState menuState;
	private String winTitle, loseTitle;
	boolean win;
	
	/**
	 * Publikus konstruktor, letrehoz egy peldanyt az osztalybol a megadott parameterekkel.
	 * @param game A jatek melynek az allapotot kivanjuk adni
	 * @param menuState A menuallapot amire vissza kivanunk valtani
	 */
	public GameOverState(Game game,MenuState menuState){
		super(game);
		stateManager = StateManager.getInstance();
		stateChange = false;
		uiManager = new UIManager();
		player = new Player("Default", 0);
		playerAdded = false;
		initialized=false;
		this.menuState=menuState;
		winTitle = "You Won!";
		loseTitle = "Game Over";
		win = false;
		
	}
	
	/**
	 * Az allapot inicializalasat vegzo fuggveny
	 */
	@Override
	public void init() {
		if(!initialized) {
			MouseManager.getInstance().setUIManager(uiManager);
			initialized=true;
			TextInputWindow txt = new TextInputWindow();
	
			uiManager.addObject(new UIImageButton(400, 300,256, 96, Assets.backButton, new ClickListener() {
	
				/**
				 * A gomb megnyomasakor torteno viselkedest vegrehajto fuggveny.
				 * Kattintaskor kimenti a megjelenitett ablak szovegdobozabol a nevet, es beallitja a 
				 * jatekosnak, majd hozzaadja a jatekost s dicsoseglistahoz, elmenti azt, es visszalep a 
				 * menu allapotba.
				 */
				@Override
				public void onClick() {
					if(txt.isNameSet()) {
						String nonDefaultName = txt.getPlayerName();
						if(nonDefaultName!=null) {
							player.setName(nonDefaultName);
						}
						stateChange =true;
						if(!playerAdded) {
							game.getLeaderBoard().addPlayer(player);
							playerAdded = true;
						}
						try {
							game.getLeaderBoard().save();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					initialized=false;
					MouseManager.getInstance().setUIManager(menuState.getUiManager());
				}
				
				
			}));
			}

	}

	/**
	 * Az allapot frissiteset vegzo fuggveny
	 */
	@Override
	public void update() {
		updatePlayerStats();
			if(!stateChange) {
				uiManager.update();
				}
			else{
				stateChange = false;
				stateManager.setCurrentState(menuState);
				}

	}

	/**
	 * Az allapot kirajzolasat vegzo fuggveny
	 * @param graphics a kirajzolast vegzo grafikai osztaly
	 */
	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.background, 0, 0,game.getWidth(),game.getHeight(),null);
		graphics.setColor(Color.white);
		graphics.setFont(new Font("Times New Roman",Font.BOLD,60));
		if(win) {
			graphics.drawString(winTitle, 375, 200);
		}
		else {
			graphics.drawString(loseTitle, 375, 200);

		}
		uiManager.render(graphics);

	}
	
	/**
	 * A jatekos adatainak frissiteset vegzo fuggveny
	 */
	public void updatePlayerStats() {
		if(!playerAdded) {
			int points = game.getGameState().getCurrentLevel().getClearedRooms();
			player.setRoomsCleared(points);
		}
		else {
			player = new Player("deafult",0);
			playerAdded = false;
		}
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}
	

}
