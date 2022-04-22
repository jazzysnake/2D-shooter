package shooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import gfx.Assets;
import input.KeyManager;
import input.MouseManager;
import leaderboard.LeaderBoard;
import states.GameOverState;
import states.GameState;
import states.LeaderBoardState;
import states.MenuState;
import states.StateManager;
import utils.Timer;

/**
 * 
 * @author Debreczeni Mate
 *
 * A fo jatekosztaly, mely a jatek futtatasahoz szukseges osztalyokat tarolja es menedzseli.
 */
public class Game implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	
	private  int width,height;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	private GameFrame gameFrame;
	
	private LeaderBoard leaderBoard;
	
	private MenuState menuState;
	private GameState gameState;
	private GameOverState gameOverstate;
	private LeaderBoardState leaderBoardstate;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private StateManager stateManager;
	
	public static final int FPS=60;
	
	/**
	 * Publikus konstruktor, letrehoz egy jatek peldanyt mely a parameterben megadott
	 * ablakban vegez majd muveleteket.
	 * @param gf Az ablak amire a kirajzolas tortenik
	 */
	public Game(GameFrame gf) {
		gameFrame = gf;
		width = gf.getframeWidth();
		height = gf.getframeHeight();
		keyManager = new KeyManager();
		
		mouseManager =  MouseManager.getInstance();
		stateManager = StateManager.getInstance();
		Assets.init();
		
		gameState = new GameState(this);
		menuState = new MenuState(this,2);
		gameOverstate = new GameOverState(this,menuState);
		leaderBoardstate = new LeaderBoardState(this,menuState);

	}

	/**
	 * A jatek adatainak inicializalasat vegzo fuggveny
	 */
	public void init() {
		gameFrame.addCanvas();
		gameFrame.getFrame().addKeyListener(keyManager);
		gameFrame.getFrame().addMouseListener(mouseManager);
		gameFrame.getFrame().addMouseMotionListener(mouseManager);
		gameFrame.getCanvas().addMouseListener(mouseManager);
		gameFrame.getCanvas().addMouseMotionListener(mouseManager);


		stateManager.setCurrentState(menuState);
		mouseManager.setUIManager(menuState.getUiManager());

		File file = new File("save.ser");

        if(file.isFile()) {
	        try {
				leaderBoard = new LeaderBoard(LeaderBoard.load());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else {
        	leaderBoard = new LeaderBoard();
        }
	}

	/**
	 * A jatek szaljanak futatasat vegzo fuggveny
	 */
	@Override
	public void run() {
		init();
		Timer timer = new Timer();
		while(running){
			if(timer.timerWithWait(FPS)){
				update();
				render();
			}
		}
		gameFrame.close();
		
		stop();
		
	}
	
	/**
	 * Az eltarolt menedzser-osztalyok frissiteset vegzo fuggveny
	 */
	private void update() {
		keyManager.update();
		if(stateManager.getCurrentState()!=null) {
			stateManager.getCurrentState().update();
			}
	}
	
	/**
	 * A kirajzolast vegzo fuggveny
	 */
	private void render() {
		bufferStrategy =gameFrame.getCanvas().getBufferStrategy();
		if(bufferStrategy == null) {
			gameFrame.getCanvas().createBufferStrategy(3);
			return;
		}
		graphics = bufferStrategy.getDrawGraphics();
		graphics.clearRect(0, 0, 1600, 800);
		graphics.setColor(Color.blue);
		//Drawing Starts

		if(stateManager.getCurrentState()!=null)
			stateManager.getCurrentState().render(graphics);
		
		//Drawing Ends
		bufferStrategy.show();
		graphics.dispose();
	}
	/**
	 * A jatek szaljanak futatasat vegzo fuggveny
	 */
	public synchronized void start() {
		if(!running) {
			thread = new Thread();
			thread.start();
			running = true;
			this.run();
		}
	}
	/**
	 * A jatek szaljanak futtatasat leallito fuggveny
	 */
	public synchronized void stop() {
		if(running) {
			running=false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public KeyManager getKeyManager() {
		return this.keyManager;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame frame) {
		this.gameFrame = frame;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public void setMouseManager(MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}
	
	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public GameOverState getGameOverstate() {
		return gameOverstate;
	}

	public void setGameOverstate(GameOverState gameOverstate) {
		this.gameOverstate = gameOverstate;
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}

	public LeaderBoard getLeaderBoard() {
		return leaderBoard;
	}

	public void setLeaderBoard(LeaderBoard leaderBoard) {
		this.leaderBoard = leaderBoard;
	}

	public LeaderBoardState getLeaderBoardstate() {
		return leaderBoardstate;
	}

	public void setLeaderBoardstate(LeaderBoardState leaderBoardstate) {
		this.leaderBoardstate = leaderBoardstate;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	
}
