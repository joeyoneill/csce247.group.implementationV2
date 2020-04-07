import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;

class Ticket {
  String username;
  Event event;
  LocalTime purchaseTime;
  LocalDate purchaseDate;
  int seatRow;
  int seatCol;
  Boolean isRefundable;

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
  
  public String toString() {
	  String tick =
				"Event name: " + event.name +
				"\n********************" +
				"\n" + event.getType() +
				"\n" + event.venue.getName() +
				"\n" + "Row: " + seatRow +
				" Col: " + seatCol +
				"\n" + event.toString() +
				"\n********************";
	  return tick;
  }

}
