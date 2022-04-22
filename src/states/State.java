package states;

import java.awt.Graphics;

import shooter.Game;

/**
 * 
 * @author Debreczeni Mate
 *
 * absztrakt allapot osztaly, mely definial egy alap viselkedest a raepulo allapotoknak.
 */
public abstract class State{
	

	protected Game game;
	
	/**
	 * @param game jatek melyhez az allapotot kivanjuk adni
	 */
	public State(Game game) {
		this.game = game;
	}
	
	/**
	 * absztrakt inicializalo fuggveny, melyet minden leszarmazottnak implementalnia kell.
	 */
	public abstract void init();

	/**
	 * absztrakt frissito fuggveny, mely az allapotbeli ertekek frissiteseert felel, minden leszarmazottnak
	 * implementalnia kell.
	 */
	public abstract void update();
	
	/**
	 * absztrakt kirajzolasert felelo fuggveny, minden leszarmazottnak implementalnia kell.
	 * @param graphics a kirajzolast vegzo osztaly.
	 */
	public abstract void render(Graphics graphics);

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
