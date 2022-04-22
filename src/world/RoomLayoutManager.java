package world;

import java.awt.Point;

/**
 * 
 * @author Debreczeni Mate
 *
 * A szobak elrendezeset generalja le es tolt be hozza veletlenszeruen szobakat.
 */
public class RoomLayoutManager{


	int rows, cols;
	int centerRow, centerCol;
	private Room layout[][];
	
	/**
	 * Publikus konstruktor, letrehoz az osztalybol egy peldanyt a parametereknek megfeleloen.
	 * @param rows Hany soros palyat szeretnenk
	 * @param cols hany oszlopos palyat szeretnenk
	 */
	public RoomLayoutManager(int rows, int cols) {
		this.cols = cols;
		this.rows = rows;
		layout = new Room[rows][cols];

		for(int i=0;i<rows;++i) {
			for(int j = 0;j<cols;++j) {
				layout[i][j]= RoomLoader.loadRandomRoom("res/levels/level1");
			}
		}

		centerRow=rows/2;
		centerCol = cols/2;
		layout[centerRow][centerCol] = RoomLoader.loadRoom("res/levels/level1/room0.txt");
		initNeighborStatus();
	}
	
	public Room[][] getLayout() {
		return layout;
	}
	
	/**
	 * 
	 * @param room a keresett szoba
	 * @return a szoba indexe a szobak 2d-s tombjeben, ha nincs akkor null
	 */
	public Point getRoomIndex(Room room) {
		for(int i =0;i<rows;++i) {
			for(int j=0;j<cols;++j) {
				if(layout[i][j]==room) {
					return new Point(i,j);
				}
			}
		}
		return null;
	}
	
	/**
	 * Minden szobanak beallitja hogy leteznek-e a kulonbozo oldali szomszedjaik
	 */
	public void initNeighborStatus() {
		for(int i =0;i<rows;++i) {
			for(int j=0;j<cols;++j) {
				Room[] neighbors = getNeighbors(i,j);

					if(neighbors[1]!=null) {
						layout[i][j].setNorthNeighbor(true);
					}
					if(neighbors[3]!=null) {
						layout[i][j].setEastNeighbor(true);
					}
					if(neighbors[5]!=null) {
						layout[i][j].setSouthNeighbor(true);
					}
					if(neighbors[7]!=null) {
						layout[i][j].setWestNeighbor(true);
					}
			}
		}
					
	}
	
	/**
	 * A parameter szerinti szobanak adja vissza a szomszedai tombjet
	 * @param y A szoba sorindexe
	 * @param x A szoba oszlopindexe
	 * @return A szoba szomszedjainak tombje
	 */
	public Room[] getNeighbors(int y,int x) {
		Room[] neighbours = new Room[8];

		
		if(x>0&&y>0 && x<cols-1 && y <rows-1) { //HA MINDEN SZOMSZEDJA MATRIXON BELULI
			
			neighbours[0] = layout[y-1][x-1];//bal felso szomszed
			neighbours[1] = layout[y-1][x];//felso szomszed
			neighbours[2] = layout[y-1][x+1];//jobb felso szomszed
			
			neighbours[3] = layout[y][x+1];//jobb szomszed
			
			neighbours[4] = layout[y+1][x+1];//jobb also szomszed
			neighbours[5] = layout[y+1][x];//also szomszed
			neighbours[6] = layout[y+1][x-1];//bal also szomszed

			neighbours[7] = layout[y][x-1];//bal szomszed			
		}
		else if(x==0&&y==0) {//BAL FELSO SAROKBAN VAN
			neighbours[0] = null;//bal felso szomszed
			neighbours[1] = null;//felso szomszed
			neighbours[2] = null;//jobb felso szomszed
			
			neighbours[3] = layout[y][x+1];//jobb szomszed
			
			neighbours[4] = layout[y+1][x+1];//jobb also szomszed
			neighbours[5] = layout[y+1][x];//also szomszed
			neighbours[6] = null;//bal also szomszed

			neighbours[7] = null;//bal szomszed
		}
		else if(y==0 && x==cols-1) {//JOBB FELSO SAROKBAN VAN
			neighbours[0] = null;//bal felso szomszed
			neighbours[1] = null;//felso szomszed
			neighbours[2] = null;//jobb felso szomszed
			
			neighbours[3] = null;//jobb szomszed
			
			neighbours[4] = null;//jobb also szomszed
			neighbours[5] = layout[y+1][x];//also szomszed
			neighbours[6] = layout[y+1][x-1];//bal also szomszed

			neighbours[7] = layout[y][x-1];//bal szomszed
		}
		else if(x==cols-1 && y==rows-1) {//JOBB ALSO SAROKBAN VAN
			neighbours[0] = layout[y-1][x-1];//bal felso szomszed
			neighbours[1] = layout[y-1][x];//felso szomszed
			neighbours[2] = null;//jobb felso szomszed
			
			neighbours[3] = null;//jobb szomszed
			
			neighbours[4] = null;//jobb also szomszed
			neighbours[5] = null;//also szomszed
			neighbours[6] = null;//bal also szomszed

			neighbours[7] = layout[y][x-1];//bal szomszed
		}
		else if(x==0&&y==rows-1) {//BAL ALSO SAROKBAN VAN
			neighbours[0] = null;//bal felso szomszed
			neighbours[1] = layout[y-1][x];//felso szomszed
			neighbours[2] = layout[y-1][x+1];//jobb felso szomszed
			
			neighbours[3] = layout[y][x+1];//jobb szomszed
			
			neighbours[4] = null;//jobb also szomszed
			neighbours[5] = null;//also szomszed
			neighbours[6] = null;//bal also szomszed

			neighbours[7] = null;//bal szomszed
		}
		else if(x>0&&y==0 && x<cols-1) { //HA FELSO SOR NEM SAROK
			
			neighbours[0] = null;//bal felso szomszed
			neighbours[1] = null;//felso szomszed
			neighbours[2] = null;//jobb felso szomszed
			
			neighbours[3] = layout[y][x+1];//jobb szomszed
			
			neighbours[4] = layout[y+1][x+1];//jobb also szomszed
			neighbours[5] = layout[y+1][x];//also szomszed
			neighbours[6] = layout[y+1][x-1];//bal also szomszed

			neighbours[7] = layout[y][x-1];//bal szomszed			
		}
		else if(y>0 && x==cols-1 && y <rows-1) { //HA JOBB OLDALSO OSZLOP, NEM SAROK
			neighbours[0] = layout[y-1][x-1];//bal felso szomszed
			neighbours[1] = layout[y-1][x];//felso szomszed
			neighbours[2] = null;//jobb felso szomszed
			
			neighbours[3] = null;//jobb szomszed
			
			neighbours[4] = null;//jobb also szomszed
			neighbours[5] = layout[y+1][x];//also szomszed
			neighbours[6] = layout[y+1][x-1];//bal also szomszed

			neighbours[7] = layout[y][x-1];//bal szomszed			
		}
		else if(x>0&&x<cols-1 && y ==rows-1) { //HA ALSO SOR NEM SAROK
			
			neighbours[0] = layout[y-1][x-1];//bal felso szomszed
			neighbours[1] = layout[y-1][x];//felso szomszed
			neighbours[2] = layout[y-1][x+1];//jobb felso szomszed
			
			neighbours[3] = layout[y][x+1];//jobb szomszed
			
			neighbours[4] = null;//jobb also szomszed
			neighbours[5] = null;//also szomszed
			neighbours[6] = null;//bal also szomszed

			neighbours[7] = layout[y][x-1];//bal szomszed			
		}
		else if(x==0&&y>0 && y <rows-1) { //HA BAL OLDALSO OSZLOP NEM SAROK
			
			neighbours[0] = null;//bal felso szomszed
			neighbours[1] = layout[y-1][x];//felso szomszed
			neighbours[2] = layout[y-1][x+1];//jobb felso szomszed
			
			neighbours[3] = layout[y][x+1];//jobb szomszed
			
			neighbours[4] = layout[y+1][x+1];//jobb also szomszed
			neighbours[5] = layout[y+1][x];//also szomszed
			neighbours[6] = null;//bal also szomszed

			neighbours[7] = null;//bal szomszed			
		}
		
		return neighbours;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getCenterRow() {
		return centerRow;
	}

	public int getCenterCol() {
		return centerCol;
	}
	
}
