import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class DBDriver {
	public static void main(String[] args) { 
		//Test Write
		ArrayList<Event> events = new ArrayList<Event>();
		Venue regalCinema = new Venue("Regal Cinema", 5, 5);
		LocalDate frozenDate = LocalDate.parse("2019-12-12");
		LocalTime frozenTime = LocalTime.parse("10:15:45");
		Movie frozenTwo = new Movie("Frozen 2", regalCinema, frozenDate, frozenTime);
		events.add(frozenTwo);
		Database DB = new Database();
		DB.writeEventDBFile("test.txt", events);
		
		//Test Write
		Venue littleTheater = new Venue("Little Theater", 6, 5);
		LocalDate parasiteTime = LocalDate.parse("2020-12-12");
		LocalTime parasiteDate = LocalTime.parse("12:15:45");
		Movie parasite = new Movie("Parasite", littleTheater, parasiteTime, parasiteDate);
		events.add(parasite);
		DB.writeEventDBFile("input.txt", events);
		
		ArrayList<Event> eventsRead = new ArrayList<Event>();
		DB.readEventDBFile("input.txt", eventsRead);
		
		//output
		DB.writeEventDBFile("output.txt", eventsRead);
	}
}
