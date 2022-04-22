package states;

/**
 * 
 * @author Debreczeni Mate
 * 
 * Singleton allapotmenedzser osztaly. az allapotvaltasokert, az allapotok viselkedeset 
 * meghatarozo fo fuggvenyek meghivasaert felel.
 */
public class StateManager {
	
	private static StateManager singleInstance=null;
	
	public State currentState;

	/**
	 * privat konstruktor, hogy csak a getInstance fugveny tudja meghivni,
	 * a singleton mintanak megfeleloen
	 */
	private StateManager() {}
	
	/**
	 * Az allapotmenedzser egyetlen peldanyanak lekereset vegzo fuggveny.
	 * Ha meg nem letezik a peldany, letrehozza.
	 * @return az allapotmenedzser egyetlen peldanya
	 */
	public static StateManager getInstance() {
		if(singleInstance==null) {
			singleInstance = new StateManager();
		}
		return singleInstance;
	}
	
	/**
	 * beallitja a jelenlegi allapotot, es inicializalja
	 * @param state Az allapot amire valtani szeretnenk
	 */
	public void setCurrentState(State state) {
		state.init();
		currentState=state;
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
}
