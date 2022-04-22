package states;

import java.awt.Graphics;

import projectiles.BulletManager;
import shooter.Game;
import world.Level;

/**
 * 
 * @author Debreczeni Mate
 * 
 * Jatekallapot osztaly, amely a jatek gameplay ideje alatt aktiv.
 */
public class GameState extends State{
	

	private Level currentLevel;
	
	/**
	 * Publikus konstruktor, letrehoz egy peldanyt a parameterben megadott jatekvaltozo szerint.
	 * @param game a jatek melynek az allapotot kivanjuk adni
	 */
	public GameState(Game game) {
		super(game);
		//currentLevel = new Level(this);

	}
	
	/**
	 * A jatekallapot frissiteert felelos fuggveny. A jelenlegi szint frissito fuggvenyet hivja meg, ha 
	 * a jatek meg nem ert veget.
	 */
	@Override
	public void update() {
		currentLevel.update();
		if(currentLevel.isCleared()) {
			game.getGameOverstate().setWin(true);
			StateManager.getInstance().setCurrentState(game.getGameOverstate());
		}
	}

	/**
	 * A kirajzolast vegzo fuggveny
	 * @param graphics a kirajzolast vegzo grafikai osztaly
	 */
	@Override
	public void render(Graphics graphics) {
		currentLevel.render(graphics);
	}
	
	/**
	 * A jatekallapot inicializalasat vegzo fuggveny
	 */
	@Override
	public void init() {
		game.getKeyManager().reset();
		currentLevel = new Level(this);
		currentLevel.getCurrentRoom().setBulletManager(BulletManager.getInstance());
	}

	
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
}
