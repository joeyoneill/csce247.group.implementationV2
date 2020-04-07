package csce247groupImplementation;

import java.time.LocalDate;

public class PaymentInfo {
	
	private String ccNum;
	private String expMonth;
	private String expYear;
	private LocalDate expDate;
	private String ccv;
	
	//Constructor
	public PaymentInfo(String ccNum, String expMonth, String expYear, String ccv) {
		this.ccNum = ccNum;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.ccv = ccv;
	}
	
	// Getters
	public String getCcNum() {
		return ccNum;
	}
	public String getExpMonth() {
		return expMonth;
	}
	public String getExpYear() {
		return expYear;
	}
	public LocalDate getExpDate() {
		return expDate;
	}
	public String getCcv() {
		return ccv;
	}
	
	// Setters
	public void setCcNum(String ccNum) {
		if (ccNum.length() != 16) {
			ccNum = null;
		}
		this.ccNum = ccNum;
	}
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}
	public void setExpYear(String expYear) {
		this.expYear = "20" + expYear;
	}
	public void setExpDate(LocalDate expDate) {
		if (expMonth == null) {
			expDate = null;
		}
		else if (expYear == null) {
			expDate = null;
		}
		else {
			expDate = LocalDate.of(Integer.parseInt(expYear), Integer.parseInt(expMonth),1);
		}
		this.expDate = expDate;
	}
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
	
	// Methods
	/**
	 * Checks if the payment info is expied or not
	 * @return boolean
	 */
	public boolean isExpired() {
		LocalDate currentDate = LocalDate.now();
		if (this.expDate.isBefore(currentDate) || this.expDate.isEqual(currentDate)) {
			return true;
		}
		return false;
	}
}
