import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Database {
	public static final String DELIM = "\t"; //Delimiting character for file
	public static final int FIELD_AMOUNT = 8; //There should be 7 fields in each line of the file
	
	public Database() {
	}
	
	public void writeEventDBFile(String aFileName, ArrayList<Event> events) {
		try {
			PrintWriter fileWriter = new PrintWriter(
					new FileOutputStream(aFileName));
			
			 
			for(Event event : events) {
				 if(event == null)
					 break;
				 String boolString = boolArrayToString(event.venue.getRows(), event.venue.getColumns(), event.seats);
				 fileWriter.println(event.name+DELIM+
						event.venue.getName()+DELIM+
						event.venue.getRows()+DELIM+
						event.venue.getColumns()+DELIM+
						event.getType()+DELIM+
						event.date.toString()+DELIM+
						event.time.toString()+DELIM+
				 		boolString);
				 		
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
				boolean array[][] = stringToBoolArray(rows, columns, splitStr[7]);
				
				Venue venue = new Venue(venueName, rows, columns);
						
				if(type.equalsIgnoreCase("Movie")) {
					Event event = new Movie(name, venue, date, time, array);
					events.add(event);
				}
				else if(type.equalsIgnoreCase("Play")) {
					Event event = new Play(name, venue, date, time, array);
					events.add(event);
				}
				else if(type.equalsIgnoreCase("Concert")) {
					Event event = new Concert(name, venue, date, time, array);
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
	
	//write user database file
	public void writeUserDBFile(String aFileName, ArrayList<User> users) {
		//TO DO...
	}
	
	//read user database file
	public void readUserDBFile(String aFileName, ArrayList<User> users) {
		//TO DO...
	}
	
	public String boolArrayToString(int rows, int columns, boolean array[][]) {
		String ret = "";
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(array[i][j] == true) {
					ret = ret + '1';
				}
				else {
					ret = ret + '0';
				}
			}
		}
		return ret;
	}
	
	public boolean[][] stringToBoolArray(int rows, int columns, String arrayString) {
		boolean[][] array = new boolean[rows][columns];
		int count = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(arrayString.charAt(count) == '1') {
					array[i][j] = true;
				}
				else {
					array[i][j] = false;
				}
				count++;
			}
		}
		return array;
	}
	
}
