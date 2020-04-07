import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Play extends Event {
	public ArrayList<String> acts;
	
	public Play(String name, String genre, double cost, Venue venue,
			LocalDate date, LocalTime time, int runTime,
			int seatRows, int seatColumns) {
		this.name = name;
		this.venue = venue;
		this.type = "Play";		
		this.date = date;
		this.time = time;
		this.rows = seatRows;
		this.columns = seatColumns;
		
		//Check for valid seat dimensions
		if(seatRows > 0 && seatColumns > 0) {
			seats = new boolean[seatRows][seatColumns];
			
			//Set all seats to empty (false)
			//Also set all handicap seats to non-handicapped (false) until otherwise noted
			for(int i = 0; i < seatRows; i++) {
				for(int j = 0; j < seatColumns; j++) {
					seats[i][j] = false;
				}
			}
		}
		else {
			System.out.println("Error, invalid seat dimensions");
		}
	}
	
	@Override
	public String getType() {
		return type;
	}
	
}
