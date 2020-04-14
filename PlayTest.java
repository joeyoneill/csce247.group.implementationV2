import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/*
 * JUnit tests for Play.java
 */
class PlayTest {

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
	 * Tests play constructor
	 */
	@Test
	public void testPlayConstructor() {
		Venue venue = new Venue("Venue", 5, 5);
		LocalDate date = LocalDate.parse("2019-12-12");
		LocalTime time = LocalTime.parse("10:15:45");
		Play play = new Play("play", venue, date, time, 1);
		
		assertEquals(play.name, "play");
		assertEquals(play.time, LocalTime.parse("10:15:45"));
		assertEquals(play.date, LocalDate.parse("2019-12-12"));
		assertEquals(play.cost, 1);
		assertEquals(play.venue.getName(), "Venue");
		assertEquals(play.venue.getRows(), 5);
		assertEquals(play.venue.getColumns(), 5);
	}
	
	/**
	 * Tests alternate play constructor
	 */
	@Test
	public void testAlternatePlayConstructor() {
		boolean[][] seats = new boolean[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				seats[i][j] = false;
			}
		}
		
		Venue venue = new Venue("Venue", 5, 5);
		LocalDate date = LocalDate.parse("2019-12-12");
		LocalTime time = LocalTime.parse("10:15:45");
		Play play = new Play("play", venue, date, time, seats, 1);
		
		assertEquals(play.name, "play");
		assertEquals(play.time, LocalTime.parse("10:15:45"));
		assertEquals(play.date, LocalDate.parse("2019-12-12"));
		assertEquals(play.cost, 1);
		assertEquals(play.venue.getName(), "Venue");
		assertEquals(play.venue.getRows(), 5);
		assertEquals(play.venue.getColumns(), 5);
		assertEquals(play.seats, seats);
	}

}