import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Event {
	public String name;
	public Venue venue;
	public String type;
	public LocalDate date;
	public LocalTime time;
	public boolean[][] seats;
	public ArrayList<Review> reviews = new ArrayList<Review>();
	
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
	
	public String toString() {
		return "Name: " + this.name + 
				"\nType: " + this.type +
				"\nDate: " + this.date +
				"\nTime: " + this.time +
				"\nAverage Review: " + AverageRating() + "/5.0";
	}

	public String getType() {
		return type;
	}
}
