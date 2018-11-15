import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	Player sut;
	
	@Test
	public void showHandShouldReturnIterable() {
		sut = new Player();
		
		assertTrue(sut.showHand() instanceof Iterable);
	}
	
	@Test
	public void shouldBeAbleToGetSizeOfHand() {
		sut = new Player();
		
		int actual = sut.getSize();
		int expected = 0;
		assertEquals(expected, actual);
	}
}
