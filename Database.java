import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Database {
	public static final String DELIM = "\t"; //Delimiting character for file
	public static final int FIELD_AMOUNT = 7; //There should be 7 fields in each line of the file
	
	public Database() {
	}
	
	public void writeEventDBFile(String aFileName, ArrayList<Event> events) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			 
			for(Event event : events) {
				 if(event == null)
					 break;
				 fileWriter.println(event.name +DELIM+
						 event.venue.getName()+DELIM+
						 event.venue.getRows()+DELIM+
						 event.venue.getColumns()+DELIM+
						 event.getType()+DELIM+
						 event.date.toString()+DELIM+
						 event.time.toString()+DELIM);
			 }
			 fileWriter.close();//DON'T FORGET TO CLOSE STREAMS
		  }
		  	catch(Exception e) {
			  
		  	}
	}
	
	public void readEventDBFile(String aFileName, ArrayList<Event> events) {
		try {
			Scanner fileScanner = new Scanner (new File(aFileName));
			while(fileScanner.hasNextLine()) {
				String input = fileScanner.nextLine();
				String[] splitStr = input.split(DELIM);
				if(splitStr.length != FIELD_AMOUNT) {
					//incorrect number of fields on line
					//return to top of while loop (don't bother with this line)
					continue;
				}
				String name = splitStr[0];
				String venueName = splitStr[1];
				int rows = Integer.parseInt(splitStr[2]);
				int columns = Integer.parseInt(splitStr[3]);
				String type = splitStr[4];
				LocalDate date = LocalDate.parse(splitStr[5]);
				LocalTime time = LocalTime.parse(splitStr[6]);
				
				Venue venue = new Venue(venueName, rows, columns);
						
				if(type.equalsIgnoreCase("Movie")) {
					Event event = new Movie(name, venue, date, time);
					events.add(event);
				}
				else if(type.equalsIgnoreCase("Play")) {
					Event event = new Play(name, venue, date, time);
					events.add(event);
				}
				else if(type.equalsIgnoreCase("Concert")) {
					Event event = new Concert(name, venue, date, time);
					events.add(event);
				}
			}
			fileScanner.close();//DON'T FORGET IT
		}
		catch(Exception e)
		{
		  System.out.println(e);
		}
	} 
	
}
