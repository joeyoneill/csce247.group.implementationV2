import java.util.ArrayList;

public class User {
	private String name;
	private UserType userType;
	private PaymentInfo paymentInfo = null;
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
	
	// Methods
	public void addPaymentInfo(String ccNum, String expMonth, String expYear, String ccv) {
		paymentInfo.setCcNum(ccNum);
		paymentInfo.setExpMonth(expMonth);
		paymentInfo.setExpYear(expYear);
		paymentInfo.setExpDate();
		paymentInfo.setCcv(ccv);
	}
	
}
