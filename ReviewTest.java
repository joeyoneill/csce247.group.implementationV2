import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ReviewTest {

	Review r = new Review("Concert", "Hank Williams Jr.", "A country boy can survive.", 5);

	@Test
	void testToString() {
		String expected = "Review title:\t" + r.getTitle() + "\nDescription:\t" + r.getDescription() + 
				"\nRating:\t\t" + r.getRating();
		String actual = r.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	void testRatingOutOfBounds() {
		r.setRating(-1);
		assertEquals(5, r.getRating());
	}
	
	@Test
	void testRatingInBounds() {
		r.setRating(4);
		int actual = r.getRating();
		assertEquals(4, actual);
	}
	
	@Test
	void testDescription() {
		String rowdyFriends = "All my rowdy friends have settled down.";
		r.setDescription(rowdyFriends);
		assertEquals(rowdyFriends, r.getDescription());
	}
	
	@Test
	void testTitle() {
		String title = "Ol' Hank";
		r.setTitle(title);
		assertEquals(title, r.getTitle());
	}
	
	@Test
	void testName() {
		String name = "A Concert";
		r.setEventName(name);
		String actual = r.getEventName();
		assertEquals(name, actual);
	}
}
