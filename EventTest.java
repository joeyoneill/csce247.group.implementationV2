import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class EventTest {
	Venue concertVenue = new Venue("concertVenue", 2, 2);
	Event event = new Concert("Hank William Jr. Live", concertVenue, LocalDate.parse("2020-08-03"),
			LocalTime.parse("11:11:11"), 99.99);
	
	// Event.getType() is obviously tested but does not show on the coverage report
	@Test
	void testGetType() {
		String actual = event.getType();
		String expected = "Concert";
		assertEquals(expected, actual);
	}
	
	@Test
	void testAvgRating() {
		double expected = 5.0;
		double actual = event.AverageRating();
		assertEquals(expected, actual);
	}
	
	@Test
	void testSeatCount() {
		int expected = 4;
		int actual = event.seatsRemaining();
		assertEquals(expected, actual);
	}
	
	@Test
	void testSeatAvailability() {
		boolean expected = true;
		boolean actual = event.checkSeatAvailability(1,1);
		assertEquals(expected, actual);
	}
	
	@Test
	void testToString() {
		String expected = "Name: " + event.name +
				"\nVenue: " + event.venue.getName() +
				"\nType: " + event.type +
				"\nDate: " + event.date +
				"\nTime: " + event.time +
				"\nCost: $" + event.cost +
				"\nAverage Review: " + event.AverageRating() + "/5.0";
		String actual = event.toString();
		assertEquals(expected, actual);
	}
	
}
