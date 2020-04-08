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
	 * @param cost - double of ticket cost in dollars
	 */
	public Concert(String name, Venue venue, LocalDate date, LocalTime time, double cost) {
		this.name = name;
		this.venue = venue;
		this.type = "Concert";		
		this.date = date;
		this.time = time;
		this.seats = new boolean[venue.getRows()][venue.getColumns()];
		this.reviews = new ArrayList<Review>();
		this.cost = cost;
	}
	
	/**
	 * Alternate Constructor: generates existing concert (using seat array) - mainly for database
	 * @param name - name of movie
	 * @param venue - venue object
	 * @param date - event date in LocalDate format
	 * @param time - event time in LocalTime format
	 * @param seats - boolean array of available seats
	 * @param cost - double of ticket cost in dollars
	 */
	public Concert(String name, Venue venue, LocalDate date, LocalTime time, boolean[][] seats, double cost) {
		this.name = name;
		this.venue = venue;
		this.type = "Concert";		
		this.date = date;
		this.time = time;
		this.seats = seats;
		this.reviews = new ArrayList<Review>();
		this.cost = cost;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
}
