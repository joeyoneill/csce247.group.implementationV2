import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DriverTest {

	// This class did not contain much testable material as it requires extensive user input
	
	@Test
	void testGetUserInput() {
		Driver d = new Driver();
		boolean actual = d.displayMenuOptions();
		boolean expected = true;
		assertEquals(actual, expected);
	}
	
	@Test
	void testDisplayEmployeeOptions() {
		Driver d = new Driver();
		boolean actual = d.displayEmployeeOptions();
		boolean expected = true;
		assertEquals(actual, expected);
	}
	
	@Test
	void testUpdateDBOnClose() {
		Driver d = new Driver();
		boolean actual = d.updateDatabaseOnClose();
		boolean expected = true;
		assertEquals(actual, expected);
	}
}
