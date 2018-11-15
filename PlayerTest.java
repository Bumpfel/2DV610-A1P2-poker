import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void showHandShouldReturnIterable() {
		Player sut = new Player();
		
		assertTrue(sut.showHand() instanceof Iterable);
	}
}
