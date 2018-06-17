/**
 *
 */
package connect_4.engine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Paul J Jensen 1073380
 *
 */
public class GameTest {

	protected Game game;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		game = new Game();
	}
	/**
	 * Test method for initialization
	 */
	@Test
	public void testInitial() {
		assertFalse(game.winnerAllDirection());
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				assertEquals(game.getBoard()[r][c], '.');
			}
		}
	}

	/**
	 * Test method for {@link connect_4.engine.Game#Game()}.
	 */
	@Test
	public void testGame() {
		assertEquals(game.getBoard()[0][0], '.');
		assertEquals(game.getBoard()[0][6], '.');
		assertEquals(game.getBoard()[5][0], '.');
		assertEquals(game.getBoard()[5][6], '.');
	}

	/**
	 * Test method for {@link connect_4.engine.Game#canPlaceCoin(int, int)}.
	 */
	@Test
	public void testPlaceCoin() {
		assertTrue(game.canPlaceCoin(1, 0));
		for (int r = 0; r < 6; r++) {
			for (int c = 1; c <= 7; c++) {
				game.canPlaceCoin(c, 0);
			}
		}
		assertFalse(game.canPlaceCoin(2, 0));
	}

	/**
	 * Test method for {@link connect_4.engine.Game#winnerAllDirection()}.
	 */
	@Test
	public void testWinnerAllDirection() {
		assertFalse(game.winnerAllDirection());
		game.canPlaceCoin(1, 0);
		game.canPlaceCoin(2, 0);
		game.canPlaceCoin(3, 0);
		game.canPlaceCoin(4, 0);
		assertTrue(game.winnerAllDirection());

	}

	/**
	 * Test method for {@link connect_4.engine.Game#getWinner()}.
	 */
	@Test
	public void testGetWinner() {
		assertFalse(game.getWinner());
		game.canPlaceCoin(1, 0);
		game.canPlaceCoin(2, 0);
		game.canPlaceCoin(3, 0);
		game.canPlaceCoin(4, 0);
		assertTrue(game.getWinner());
	}

	/**
	 * Test method for {@link connect_4.engine.Game#getDraw()}.
	 */
	@Test
	public void testGetDraw() {
		assertFalse(game.getDraw());
		for (int r = 0; r < 6; r++) {
			for (int c = 1; c <= 7; c++) {
				game.canPlaceCoin(c, 0);
			}
		}
		assertTrue(game.getDraw());

	}

}
