
public class Venue {
	
	private String name;
	public int rows;
	public int columns;
	
	public Venue(String name, int rows, int columns) {
		this.name = name;
		this.rows = rows;
		this.columns = columns;
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
}
