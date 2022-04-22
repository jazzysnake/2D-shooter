package world;

import java.util.Random;

import tiles.Tile;
import tiles.TileManager;
import utils.Utils;

/**
 * 
 * @author Debreczeni Mate
 *
 *Szobak fajlrendszerbol betolteseert felelo osztaly
 */
public class RoomLoader {
	
	/**
	 * A megadott eleresi utvonal alapjan betolt egy veletlenszeru szobat a mappabol
	 * @param pathToFolder A mappa eleresi utvonala
	 * @return a betoltott szoba
	 */
	public static Room loadRandomRoom(String pathToFolder) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(pathToFolder+"/room");
		sb.append(r.nextInt(8)+1);
		sb.append(".txt");
		return loadRoom(sb.toString());
	}

	
	/**
	 * A megadott eleresi utvonal alapjan betolti a szobat a filerendszerbol
	 * @param path A szoba falj eleresi utvonala
	 * @return a betoltott szoba
	 */
	public static Room loadRoom(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		int width = Utils.parseInt(tokens[0]);
		int height = Utils.parseInt(tokens[1]);
		int spawnX = Utils.parseInt(tokens[2]);
		int spawnY = Utils.parseInt(tokens[3]);

		
		int[][] roomTileIDs = new int[width][height];
		for(int y=0;y<height;++y) {
			for(int x=0;x<width;++x) {
				roomTileIDs[x][y]= Utils.parseInt(tokens[(x+y*width)+4]);
			}
		}
		
		Tile[][] roomTiles = new Tile[width][height];
		for(int y=0;y<height;++y) {
			for(int x=0;x<width;++x) {
				roomTiles[x][y]= TileManager.getInstance().getNewTile(roomTileIDs[x][y]);
			}
		}
		
		return new Room( width,  height,  spawnX, spawnY, roomTiles);
	}
	
}
