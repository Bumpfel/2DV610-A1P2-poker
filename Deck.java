public class Deck {

	private int size;
		
	public Deck() {
		for(int i = 0; i < 52;  i ++) {
			size ++;
		}
	}
	
	public int size() {
		return size;
	}
	
}
