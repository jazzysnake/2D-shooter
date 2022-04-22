package shooter;

/**
 * 
 * @author Debreczeni Mate
 * 
 * fo osztaly, a jatek elinditasahoz szukseges eroforrasokat hozza letre es elinditja a jatekot
 */
public class Main {
	
	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame(1040,550);
		Game game = new Game(gameFrame);
		game.start();
		
	}

}
