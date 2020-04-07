
public class PaymentInfo {
	
	private String ccNum = null;
	
	//Constructor
	public PaymentInfo(String ccNum) {
		this.setCcNum(ccNum);
	}
	
	// Getters
	public String getCcNum() {
		return ccNum;
	}
	
	// Setters
	public void setCcNum(String ccNum) {
		if (ccNum.length() != 16) {
			ccNum = null;
		}
		this.ccNum = ccNum;
	}

}
