import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Stores a Concert object, a subclass of Event
 */
public class Concert extends Event {
	/**
	 * Default Constructor: generates new concert (full of empty seats)
	 * @param name - name of movie
	 * @param venue - venue object
	 * @param date - event date in LocalDate format
	 * @param time - event time in LocalTime format
	 */
	public Concert(String name, Venue venue, LocalDate date, LocalTime time) {
		this.name = name;
		this.venue = venue;
		this.type = "Concert";		
		this.date = date;
		this.time = time;
		this.seats = new boolean[venue.getRows()][venue.getColumns()];
		this.reviews = new ArrayList<Review>();
	}
	
	/**
	 * Alternate Constructor: generates existing concert (using seat array) - mainly for database
	 * @param name - name of movie
	 * @param venue - venue object
	 * @param date - event date in LocalDate format
	 * @param time - event time in LocalTime format
	 * @param seats - boolean array of available seats
	 */
	public Concert(String name, Venue venue, LocalDate date, LocalTime time, boolean[][] seats) {
		this.name = name;
		this.venue = venue;
		this.type = "Concert";		
		this.date = date;
		this.time = time;
		this.seats = seats;
		this.reviews = new ArrayList<Review>();
	}
	
	@Override
	public String getType() {
		return type;
	}
	
}
