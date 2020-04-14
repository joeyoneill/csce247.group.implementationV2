import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class DriverTest {

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
