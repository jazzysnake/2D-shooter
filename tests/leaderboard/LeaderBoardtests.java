package leaderboard;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class LeaderBoardtests {
	
	LeaderBoard leaderBoard;
	Player player1;
	Player player2;

	
	
	@Before
	public void init() {
		leaderBoard = new LeaderBoard();
		player1 = new Player("Pal", 5);
		player2 = new Player("Peter", 6);
	}

	@Test
	public void testAddPlayer() {
		leaderBoard.addPlayer(player1);
		leaderBoard.addPlayer(player2);
		assertEquals(2, leaderBoard.getList().size());
	}
	
	@Test
	public void testSort() {
		leaderBoard.getList().add(player1);
		leaderBoard.getList().add(player2);
		leaderBoard.sort();
		assertNotSame(player1, leaderBoard.getList().get(0));
		player1.setRoomsCleared(6);
		leaderBoard.sort();
		assertSame(player1, leaderBoard.getList().get(0));
	}

}
