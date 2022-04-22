package utils;

/**
 * 
 * @author Debreczeni Mate
 *
 *Idozito osztaly, amely adott idokozonkent igazzal ter vissza
 */

public class Timer {

	private double delta;
	private long currentTime;
	private long lastTime;
	
	
	public Timer(){
		delta = 0;
		lastTime = System.nanoTime();
	}
	
	/**
	 * Olyan idozitot mely nem ter vissza adott ido eltelteig
	 * @param truePerSec masodpercenkent hanyszor akarjuk hogy igazzal terjen vissza
	 * @return igaz ha eltelt meglelo mennyisegu ido.
	 */
	public boolean timerWithWait(double truePerSec) {
		double timePerTick = 1000000000 / truePerSec;
		while(true) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / timePerTick;
			lastTime = currentTime;
		
			if(delta >= 1){
				delta=0;
				return true;

			}
		}
	}
	
	/**
	 * olyan idozito mely minden meghivaskor visszater egy igazsagertekkel, hogy eltelt-e mar
	 * az adott ido az elozo meghivas ota.
	 * @param truePerSec masodpercenkent hanyszor akarjuk hogy igazzal terjen vissza
	 * @return igaz ha eltelt meglelo mennyisegu ido.
	 */
	public  boolean timerWithoutWait(double truePerSec) {
		double timePerTick = 1000000000 / truePerSec;
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / timePerTick;
			lastTime = currentTime;
		
			if(delta >= 1){
				delta=0;
				return true;
			}
			return false;
		}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	}
