package csce247groupImplementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.io.*;

class Ticket {
  Event event;
  Venue venue;
  LocalTime purchaseTime;
  LocalDate purchaseDate;
  int seatRow;
  int seatCol;
  Boolean checkedInStatus;
  Boolean isRefundable;
  User owner;

  public Ticket(Event event, int seatRow, int seatCol, boolean isRefundable, Venue venue, User owner) {
    this.purchaseTime = LocalTime.now();
    this.purchaseDate = LocalDate.now();
    this.event = event;
    this.seatRow = seatRow; 
    this.seatCol = seatCol;
    this.isRefundable = isRefundable;
    this.checkedInStatus = false;
    this.venue = venue;
    this.owner = owner;
  }

  public Boolean isRefundable() {
    return isRefundable;
  }
  
  public String printTicket() {
	String tick =
			"Event type: " + event.getType() +
			"\n" + venue.name +
			"\n" + venue.address +
			"\n" + "Row: " + seatRow +
			"Col: " + seatCol +
			"\n" + venue.address +
			"\n" + event.toString();
	String file = event.getType() + "_ticket.txt";
	try {
		FileWriter writer = new FileWriter(file);
		writer.write(tick);
		writer.close();  
	}
	catch (Exception e) {  
		
	}
	System.out.println("Print Success");
	return tick;
  }

}
