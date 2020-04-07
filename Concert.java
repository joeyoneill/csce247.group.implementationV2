package csce247groupImplementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Concert extends Event {
	public ArrayList<String> acts;
	
	public Concert(String name, String genre, double cost, Venue venue,
			LocalDate date, LocalTime time, int runTime,
			int seatRows, int seatColumns) {
		this.name = name;
		this.venue = venue;
		this.genre = genre;
		this.type = "Concert";
		
		//Check for valid cost
		if(cost >= 0) {
			this.cost = cost;
		}
		else {
			System.out.println("Error, not a valid cost");
		}
		
		this.date = date;
		this.time = time;
		
		//Check for valid runTime
		if(runTime > 0) {
			this.runTime = runTime;
		}
		else {
			System.out.println("Error, not a valid runtime");
		}
		
		this.rows = seatRows;
		this.columns = seatColumns;
		
		//Check for valid seat dimensions
		if(seatRows > 0 && seatColumns > 0) {
			seats = new boolean[seatRows][seatColumns];
			handicapSeats = new boolean[seatRows][seatColumns];
			
			//Set all seats to empty (false)
			//Also set all handicap seats to non-handicapped (false) until otherwise noted
			for(int i = 0; i < seatRows; i++) {
				for(int j = 0; j < seatColumns; j++) {
					seats[i][j] = false;
					handicapSeats[i][j] = false;
				}
			}
		}
		else {
			System.out.println("Error, invalid seat dimensions");
		}
		
		//Initialize arrayList of acts/bands
		acts = new ArrayList<String>();
	}
	
	
	@Override
	public String getType() {
		return type;
	}

	public void addAct(String name) {
		acts.add(name);
	}	
	
}
