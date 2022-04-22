package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author Debreczeni Mate
 *
 * Egyeb a program folyaman tobbszor hasznalhato statikus fuggvenyt tarolo osztaly
 */
public class Utils {

	/**
	 * A fajlrendszerbol tolt be egy fajlt string formatumba
	 * @param path a fajl eleresi helye
	 * @return a betoltott string
	 */
	public static String loadFileAsString(String path) {
		StringBuilder sb = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine())!=null) {
				sb.append(line + "\n");
			}
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * Egy stringben keresi meg egesz szamot.
	 * @param num A sztring amiben a szamot keressuk
	 * @return az egesz szam ha megtaltuk, egyebkent 0
	 */
	public static int parseInt(String num) {
		try {
			return Integer.parseInt(num);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Veletlenszeru integerrel ter vissza a ket ertek között
	 * 
	 * @param lower felso hatar
	 * @param upper also hatar
	 * @return veletlen szam a hataron belul
	 */
	public static int randomBetween(int lower,int upper) {
		Random r = new Random();
		int i = r.nextInt(upper);
		while(i<lower) {
			i= r.nextInt(upper);
		}
		return i;
	}
}
