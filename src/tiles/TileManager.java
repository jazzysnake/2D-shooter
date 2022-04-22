package tiles;

import gfx.Assets;

/**
 * 
 * @author Debreczeni Mate
 * 
 * A mezok egyuttes kezeleset megkonnyito singleton
 *  osztaly. Eltarol minden tipusu mezobol egy peldanyt.
 */
public class TileManager {


	private static TileManager singleInstance = null;

	private int tileWidth, tileHeight;
	private Tile[] tileSet = new Tile[256];
	
	/**
	 * Privat konstruktor.
	 */
	private TileManager() {
		tileWidth=64;
		tileHeight=64;
		tileSet[0] = new Tile(this,Assets.cobbleStoneTile, 0,false);
		
		tileSet[1] = new Tile(this,Assets.topWallTile,1,true);
		tileSet[2] = new Tile(this,Assets.bottomWallTile, 2,true);
		tileSet[3] = new Tile(this,Assets.leftWallTile,3,true);
		tileSet[4] = new Tile(this,Assets.rightWallTile, 4,true);
		
		tileSet[5] = new Tile(this,Assets.topLeftWallCorner,5,true);
		tileSet[6] = new Tile(this,Assets.topRightWallCorner,6,true);
		tileSet[7] = new Tile(this,Assets.bottomRightWallCorner,7,true);
		tileSet[8] = new Tile(this,Assets.bottomLeftWallCorner,8,true);
		
		tileSet[9]= new Tile(this,Assets.closedDoorTileTop,9,true);
		tileSet[10]= new Tile(this,Assets.closedDoorTileBottom,10,true);
		tileSet[11]= new Tile(this,Assets.closedDoorTileLeft,11,true);
		tileSet[12]= new Tile(this,Assets.closedDoorTileRight,12,true);

		tileSet[13]= new Tile(this,Assets.openDoorTileTop,13,false);
		tileSet[14]= new Tile(this,Assets.openDoorTileBottom,14,false);
		tileSet[15]= new Tile(this,Assets.openDoorTileLeft,15,false);
		tileSet[16]= new Tile(this,Assets.openDoorTileRight,16,false);

	}
	
	/**
	 * Az osztaly egyetlen peldanyat visszaado fuggveny,
	 * ha meg nem letezik a peldany letrehozza.
	 * @return az osztaly egyetlen pelanya
	 */
	public static TileManager getInstance() {
		if(singleInstance == null) {
			singleInstance = new TileManager();
			return singleInstance;
		}
		return singleInstance;
	}
	
	/**
	 * azonosito alapjan hoz letre egy mezopeldanyt
	 * @param id a mezo azonositoja
	 * @return az uj mezo
	 */
	public Tile getNewTile(int id) {
		return new Tile(this,tileSet[id].getTexture(),id,tileSet[id].isSolid());
	}
	
	/**
	 * A parameterben megadott mezot helyezi el a mezoszettben.
	 * @param tile az eltarolando mezotipus.
	 */
	public void placeTileInTileSet(Tile tile) {
		tileSet[tile.getID()]=tile;
	}
	
	public Tile[] getTileSet() {
		return tileSet;
	}
	
	public Tile getStoneTile() {
		return tileSet[0];
	}
	
	public Tile getWallTile() {
		return tileSet[1];
	}
	
	public Tile getDoorTile() {
		return tileSet[2];
	}
	
	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

}
