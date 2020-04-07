import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Concert extends Event {
	
	public Concert(String name, Venue venue, LocalDate date, LocalTime time) {
		this.name = name;
		this.venue = venue;
		this.type = "Concert";		
		this.date = date;
		this.time = time;
		this.seats = new boolean[venue.getRows()][venue.getColumns()];
		this.reviews = new ArrayList<Review>();
	}
	
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
