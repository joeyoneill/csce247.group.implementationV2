import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;

/**
 * Ticket object
 */
class Ticket {
  String username;
  Event event;
  LocalTime purchaseTime;
  LocalDate purchaseDate;
  int seatRow;
  int seatCol;
  Boolean isRefundable;

  /**
   * Default constructor - generates new ticket (set purchase date and time to current time)
   * @param username - String of user's username
   * @param event -  event object
   * @param seatRow - integer of the seat row index within theater
   * @param seatCol - integer of the seat column index within theater
   * @param isRefundable - boolean of whether the ticket can be refunded or not
   */
  public Ticket(String username, Event event, int seatRow, int seatCol, boolean isRefundable) {
	this.username = username;
	this.event = event;
	this.purchaseTime = LocalTime.now();
    this.purchaseDate = LocalDate.now();
    this.event = event;
    this.seatRow = seatRow; 
    this.seatCol = seatCol;
    this.isRefundable = isRefundable;
  }
  
  /**
   * Default constructor - generates existing ticket (with saved purchaseTime and purchaseDate) - used for database
   * @param username - String of user's username
   * @param purchaseTime - LocalTime storing time purchased
   * @param purchaseDate - LocalTime storing date purchased
   * @param event -  event object
   * @param seatRow - integer of the seat row index within theater
   * @param seatCol - integer of the seat column index within theater
   * @param isRefundable - boolean of whether the ticket can be refunded or not
   */
  public Ticket(String username, Event event, LocalTime purchaseTime, LocalDate purchaseDate, int seatRow, int seatCol, boolean isRefundable) {
	this.username = username;
	this.event = event;
	this.purchaseTime = purchaseTime;
	this.purchaseDate = purchaseDate;
	this.event = event;
	this.seatRow = seatRow; 
	this.seatCol = seatCol;
	this.isRefundable = isRefundable;
  }

  public Boolean isRefundable() {
    return isRefundable;
  }
  
  /**
   * Prints a ticket to a text file
   */
  public void printTicket() {
	String tick =
			"********************\n" +
			"Event name: " + event.name +
			"\n********************" +
			"\n" + event.getType() +
			"\n" + event.venue.getName() +
			"\n" + "Row: " + seatRow +
			" Col: " + seatCol +
			"\n" + event.toString() +
			"\n********************";
	String file = event.name + "_" + seatRow + "_" + seatCol + "_ticket.txt";
	try {
		FileWriter writer = new FileWriter(file);
		writer.write(tick);
		writer.close();  
	}
	catch (Exception e) {  
		System.out.println(e);
	}
	System.out.println("Print Success");
  }
  
  /**
   * Prints a ticket in a neat format
   * @return tick - a string representation of the important fields of a ticket
   */
  public String toString() {
	  String tick =
				"Event name: " + event.name +
				"\n********************" +
				"\nType: " + event.getType() +
				"\nVenue: " + event.venue.getName() +
				"\n" + "Row: " + seatRow +
				" Col: " + seatCol +
				"\nDate: " + event.date +
				"\nTime: " + event.time +
				"\nCost: $" + event.cost +
				"\n********************";
	  return tick;
  }

}
