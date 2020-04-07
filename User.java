import java.util.ArrayList;

public class User {
	private String name;
	private String password;
	private UserType userType;
	private String ccNum;
	private ArrayList<Ticket> purchasedTickets;
	
	// Guest Constructor
	public User() {
		this.name = "Guest";
		this.password = null;
		this.userType = UserType.GUEST;
		this.ccNum = null;
		purchasedTickets = new ArrayList<Ticket>();
	}
	
	public User(String name, String password, UserType userType) {
		this.name = name;
		this.password = password;
		this.userType = userType;
		this.ccNum = null;
		purchasedTickets = new ArrayList<Ticket>();
	}
	
	// Getters
	public String getUsername() {
		return this.name;
	}
	public String getPassword() {
		return this.password;
	}
	public UserType getUsertype() {
		return this.userType;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsertype(UserType usertype) {
		this.userType = usertype;
	}
	
	public void purchaseTicket(Ticket ticket) {
		purchasedTickets.add(ticket);
	}
	
	// Methods
	public void addPaymentInfo(String ccNum) {
		if (ccNum.length() != 16) {
			ccNum = null;
		}
		this.ccNum = ccNum;
	}
	
}
