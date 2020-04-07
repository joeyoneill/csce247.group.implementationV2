
public class Venue {
	
	private String name;
	public int rows;
	public int columns;
	public boolean[][] seats; //false = not taken
	
	public Venue(String name, int rows, int columns) {
		this.name = name;
		this.rows = rows;
		this.columns = columns;
		seats = new boolean[rows][columns];
	}
	
	// Getters
	public String getName() {
		return name;
	}
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}
	public boolean[][] getSeats() {
		return seats;
	}
}
