
/**
 * Review object
 */
public class Review {
	public String eventName;
	public String title;
	public String description;
	public int rating;

	/**
	 * Default constructor: generates review
	 * 
	 * @param eventName   - name of event
	 * @param title       - title of review
	 * @param description - description of review
	 * @param rating      - integer rating 0-5 of event
	 */
	public Review(String eventName, String title, String description, int rating) {
		this.eventName = eventName;
		this.title = title;
		this.description = description;
		this.rating = rating;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		if (rating >= 0 && rating <= 5)
			this.rating = rating;
	}

	public String toString() {
		return "Review title:\t" + title + "\nDescription:\t" + description + "\nRating:\t\t" + rating;
	}

}
