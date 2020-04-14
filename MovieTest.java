import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/*
 * JUnit tests for Movie.java
 */
class MovieTest {

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
	 * Tests movie constructor
	 */
	@Test
	public void testMovieConstructor() {
		Venue venue = new Venue("Venue", 5, 5);
		LocalDate date = LocalDate.parse("2019-12-12");
		LocalTime time = LocalTime.parse("10:15:45");
		Movie movie = new Movie("movie", venue, date, time, 1);
		
		assertEquals(movie.name, "movie");
		assertEquals(movie.time, LocalTime.parse("10:15:45"));
		assertEquals(movie.date, LocalDate.parse("2019-12-12"));
		assertEquals(movie.cost, 1);
		assertEquals(movie.venue.getName(), "Venue");
		assertEquals(movie.venue.getRows(), 5);
		assertEquals(movie.venue.getColumns(), 5);
	}
	
	/**
	 * Tests alternate movie constructor
	 */
	@Test
	public void testAlternateMovieConstructor() {
		boolean[][] seats = new boolean[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				seats[i][j] = false;
			}
		}
		Venue venue = new Venue("Venue", 5, 5);
		LocalDate date = LocalDate.parse("2019-12-12");
		LocalTime time = LocalTime.parse("10:15:45");
		Movie movie = new Movie("movie", venue, date, time, seats, 1);
		
		assertEquals(movie.name, "movie");
		assertEquals(movie.time, LocalTime.parse("10:15:45"));
		assertEquals(movie.date, LocalDate.parse("2019-12-12"));
		assertEquals(movie.cost, 1);
		assertEquals(movie.venue.getName(), "Venue");
		assertEquals(movie.venue.getRows(), 5);
		assertEquals(movie.venue.getColumns(), 5);
		assertEquals(movie.seats, seats);
	}

}
