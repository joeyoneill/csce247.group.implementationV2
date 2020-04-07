import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;

class Ticket {
  Event event;
  LocalTime purchaseTime;
  LocalDate purchaseDate;
  int seatRow;
  int seatCol;
  Boolean isRefundable;

  public Ticket(Event event, int seatRow, int seatCol, boolean isRefundable, Venue venue) {
    this.purchaseTime = LocalTime.now();
    this.purchaseDate = LocalDate.now();
    this.event = event;
    this.seatRow = seatRow; 
    this.seatCol = seatCol;
    this.isRefundable = isRefundable;
  }

  public Boolean isRefundable() {
    return isRefundable;
  }
  
  public String printTicket() {
	String tick =
			"********************\n" +
			"Event name: " + event.name +
			"\n********************" +
			"\n" + event.getType() +
			"\n" + event.venue.getName() +
			"\n" + "Row: " + seatRow +
			"Col: " + seatCol +
			"\n" + event.toString() +
			"\n********************";
	String file = event.getType() + "_ticket.txt";
	try {
		FileWriter writer = new FileWriter(file);
		writer.write(tick);
		writer.close();  
	}
	catch (Exception e) {  
		System.out.println(e);
	}
	System.out.println("Print Success");
	return tick;
  }

}
