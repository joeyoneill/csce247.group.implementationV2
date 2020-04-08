import java.util.ArrayList;

/**
 * User object
 */
public class User {
	private String name;
	private String password;
	private UserType userType;
	private String ccNum;
	private ArrayList<Ticket> purchasedTickets;
	
	/**
	 * Defualt constructor - GUEST
	 */
	public User() {
		this.name = "Guest";
		this.password = null;
		this.userType = UserType.GUEST;
		this.ccNum = null;
		purchasedTickets = new ArrayList<Ticket>();
	}
	
	/**
	 * Alternate constructor - new user 
	 * @param name - String username
	 * @param password - String password
	 * @param userType - ENUM userType
	 */
	public User(String name, String password, UserType userType) {
		this.name = name;
		this.password = password;
		this.userType = userType;
		this.ccNum = null;
		purchasedTickets = new ArrayList<Ticket>();
	}
	
	/**
	 * Alternate constructor - existing user
	 * @param name - String username
	 * @param password - String password
	 * @param userType - ENUM userType
	 * @param ccNum - String credit card number
	 */
	public User(String name, String password, UserType userType, String ccNum) {
		this.name = name;
		this.password = password;
		this.userType = userType;
		this.ccNum = ccNum;
		purchasedTickets = new ArrayList<Ticket>();
	}
	
	// Getters
	public String getName() {
		return this.name;
	}
	public String getPassword() {
		return this.password;
	}
	public UserType getUsertype() {
		return this.userType;
	}
	public String getCCNum() {
		return this.ccNum;
	}
	public ArrayList<Ticket> getTickets() {
		return this.purchasedTickets;
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
	public void setCreditCardNumber(String ccNum) {
		this.ccNum = ccNum;
	}
	
	/**
	 * Add to purchased tickets
	 * @param ticket - ticket to an event
	 */
	public void purchaseTicket(Ticket ticket) {
		purchasedTickets.add(ticket);
	}
	
	/**
	 * Remove ticket from purchased tickets
	 * @param ticket
	 */
	public void removeTicket(Ticket ticket) {
		purchasedTickets.remove(ticket);
	}
	
	/**
	 * Add payment info to user - must be 16 digits
	 * @param ccNum - String credit card number
	 */
	public void addPaymentInfo(String ccNum) {
		if (ccNum.length() != 16) {
			ccNum = null;
		}
		this.ccNum = ccNum;
	}
	
}
