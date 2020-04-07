import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Play extends Event {
	
	public Play(String name, String genre, double cost, Venue venue,
			LocalDate date, LocalTime time) {
		this.name = name;
		this.venue = venue;
		this.type = "Play";		
		this.date = date;
		this.time = time;
		this.seats = new boolean[venue.getRows()][venue.getColumns()];
	}
	
	@Override
	public String getType() {
		return type;
	}
	
}
