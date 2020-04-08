import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Event: abstract class used to define Event object
 */
public abstract class Event {
	public String name;
	public Venue venue;
	public String type;
	public double cost;
	public LocalDate date;
	public LocalTime time;
	//2D boolean array store seats; if true at index, seat is taken
	public boolean[][] seats;
	public ArrayList<Review> reviews;
	
	/**
	 * Average rating generates an average out of 5 from all ratings
	 * 
	 * @return A double containing the average rating of an event
	 */
	public double AverageRating() {
		if(reviews.size() == 0) {
			return 5;
		}
		double sum = 0;
		for(int i = 0; i < reviews.size(); i++) {
			sum += reviews.get(i).rating;
		}
		return sum/reviews.size();
	}
	
	/**
	 * Generates a string containing relevant fields for output
	 */
	public String toString() {
		return "Name: " + this.name +
				"\nVenue: " + this.venue.getName() +
				"\nType: " + this.type +
				"\nDate: " + this.date +
				"\nTime: " + this.time +
				"\nCost: $" + this.cost +
				"\nAverage Review: " + AverageRating() + "/5.0";
	}
	

	public String getType() {
		return type;
	}
	
	/**
	 * Generates a printout of seats, showing row and column along with which seats are taken
	 */
	public void visualizeSeating() {
		for(int i = 0; i < venue.getRows(); i++) {
			for(int j = 0; j < venue.getColumns(); j++) {
				//Print the row and column in "row, column" format if not taken
				if (seats[i][j] == false) {
					System.out.print((i+1) + "," + (j+1) + " ");
				}
				//Otherwise print an 'x'
				else {
					System.out.print("X ");
				}
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Checks if a seat at a given index is taken
	 * @param i - a row index
	 * @param j - a column index
	 * @return boolean - true if seat is available, false otherwise
	 */
	public boolean checkSeatAvailability(int i, int j) {
		if (seats[i][j] == false)
			return true;
		else
			return false;
	}
	
	/**
	 * Checks number of seats remaining (in order to determine whether user can purchase X amount of tickets"
	 * @returns count of remaining seats
	 */
	public int seatsRemaining() {
		int count = 0;
		for(int i = 0; i < venue.getRows(); i++) {
			for(int j = 0; j < venue.getColumns(); j++) {
				if(seats[i][j] == false)
					count++;
			}
		}
		return count;
	}
}
