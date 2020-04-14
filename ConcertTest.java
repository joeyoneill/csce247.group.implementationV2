import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/*
 * JUnit tests for Concert.java
 */
class ConcertTest {

	@BeforeClass
	public static void oneTimeSetup() {
		
	}
	
	@AfterClass
	public static void oneTimeTearDown() {
		
	}
	
	@Before
	public static void setup() {
		//runs before each test
	}
	
	@After
	public static void tearDown() {
		//runs after each test
	}
	
	/**
	 * Tests concert constructor
	 */
	@Test
	public void testConcertConstructor() {
		Venue venue = new Venue("Venue", 5, 5);
		LocalDate date = LocalDate.parse("2019-12-12");
		LocalTime time = LocalTime.parse("10:15:45");
		Concert concert = new Concert("concert", venue, date, time, 1);
		
		assertEquals(concert.name, "concert");
		assertEquals(concert.time, LocalTime.parse("10:15:45"));
		assertEquals(concert.date, LocalDate.parse("2019-12-12"));
		assertEquals(concert.cost, 1);
		assertEquals(concert.venue.getName(), "Venue");
		assertEquals(concert.venue.getRows(), 5);
		assertEquals(concert.venue.getColumns(), 5);
	}
	
	/**
	 * Tests alternate concert constructor
	 */
	@Test
	public void testAlternateConcertConstructor() {
		boolean[][] seats = new boolean[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				seats[i][j] = false;
			}
		}
		Venue venue = new Venue("Venue", 5, 5);
		LocalDate date = LocalDate.parse("2019-12-12");
		LocalTime time = LocalTime.parse("10:15:45");
		Concert concert = new Concert("concert", venue, date, time, seats, 1);
		
		assertEquals(concert.name, "concert");
		assertEquals(concert.time, LocalTime.parse("10:15:45"));
		assertEquals(concert.date, LocalDate.parse("2019-12-12"));
		assertEquals(concert.cost, 1);
		assertEquals(concert.venue.getName(), "Venue");
		assertEquals(concert.venue.getRows(), 5);
		assertEquals(concert.venue.getColumns(), 5);
		assertEquals(concert.seats, seats);
	}

}
