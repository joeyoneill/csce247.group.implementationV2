import java.util.ArrayList;

public class User {
	private String name;
	private UserType userType;
	private ArrayList<Ticket> purchasedTickets;
	
	// Guest Constructor
	public User() {
		this.name = "Guest";
		this.userType = UserType.GUEST;
		purchasedTickets = new ArrayList<Ticket>();
	}
	
	public User(String name, UserType userType) {
		this.name = name;
		this.userType = userType;
		purchasedTickets = new ArrayList<Ticket>();
	}
	
	// Getters
	public String getName() {
		return this.name;
	}
	public UserType getUsertype() {
		return this.userType;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setUsertype(UserType usertype) {
		this.userType = usertype;
	}
	
	public void purchaseTicket(Ticket ticket) {
		purchasedTickets.add(ticket);
	}
	
}
