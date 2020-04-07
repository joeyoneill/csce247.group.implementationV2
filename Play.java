import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Play extends Event {
	
	public Play(String name, Venue venue, LocalDate date, LocalTime time) {
		this.name = name;
		this.venue = venue;
		this.type = "Play";		
		this.date = date;
		this.time = time;
		this.seats = new boolean[venue.getRows()][venue.getColumns()];
		this.reviews = new ArrayList<Review>();
	}
	
	public Play(String name, Venue venue, LocalDate date, LocalTime time, boolean[][] seats) {
		this.name = name;
		this.venue = venue;
		this.type = "Play";		
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
