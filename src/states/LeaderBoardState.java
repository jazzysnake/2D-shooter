package states;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import leaderboard.LeaderBoard;
import shooter.Game;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

/**
 * 
 * @author Debreczeni Mate
 *
 */
public class LeaderBoardState extends State{
	
	private UIManager uiManager;
	StateManager stateManager = StateManager.getInstance();
	
	private boolean stateChange;
	private MenuState menuState;
	private LeaderBoard leaderBoard;

	/**
	 * publikus konstruktor, letrehoz egy peldanyt az osztalybol az adott parametereknek megfeleloen
	 * 
	 * @param game A jatek melynek az allapotot kivanjuk adni.
	 * @param menuState A menuallapot amibe visszalepunk.
	 */
	public LeaderBoardState(Game game, MenuState menuState) {
		super(game);
		stateChange = false;
		uiManager = new UIManager();
		game.getMouseManager().setUIManager(uiManager);
		this.menuState = menuState;
	}

	/**
	 * A dicsoseglista allapot inicializalasa
	 */
	@Override
	public void init() {
		leaderBoard = game.getLeaderBoard();
		uiManager.addObject(new UIImageButton(400, 400,256, 96, Assets.backButton, new ClickListener() {
			
			/**
			 * A gombra kattintaskor megvalositando viselkedes implementalasa:
			 * allapotovaltas igazdasertket tesszuk igazza.
			 */
			@Override
			public void onClick() {
					stateChange =true;
					game.getMouseManager().setUIManager(menuState.getUiManager());
				}
	
			
			
		}));
		}		

	/**
	 * Az allapot frissitiset vegzo fuggveny, allapotvaltas valtozo igaz erteke eseten allapototo valt, vissza
	 * a menube.
	 * 
	 */
	@Override
	public void update() {
		if(!stateChange) {
			uiManager.update();
			}
		else{
			stateChange = false;
			stateManager.setCurrentState(menuState);
			}		
	}

	/**
	 * Az allapot kirajzolasat vegzo fuggveny.
	 * @param graphics A kirajzolast vegzo grafikai osztaly
	 */
	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.background, 0, 0,game.getWidth(),game.getHeight(),null);
		graphics.setColor(Color.gray);
		graphics.fillRect((game.getWidth()/2)-200, (game.getHeight()/2)-200, 400, 300);
		graphics.setColor(Color.white);
		for(int i=0;i<10;++i) {
			String placeOnBoard = "Helyezes: " + (i+1) + ". Nev: " + leaderBoard.getList().get(i).getName() + " Rooms Cleared: " + leaderBoard.getList().get(i).getRoomsCleared();
			graphics.drawString(placeOnBoard, (game.getWidth()/2)-150, ((game.getHeight()/2)-150)+i*20);
		}
		uiManager.render(graphics);
	}

	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
}
