public class Review {
	public String eventName;
	public String title;
	public String description;
	public double rating;

	public Review(String eventName, String title, String description, double rating) {
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

	public double getRating() {
		return rating;
	}

	public void setRating(int rating) {
		if(rating >= 0 && rating <= 5)
			this.rating = rating;
	}

}
