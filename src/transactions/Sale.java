package transactions;

import librarian.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import generaluser.*;

public class Sale implements Serializable {
	public enum SaleStatus {
		CARTEMPTY,
		ADDEDTOCART,
		PENDINGPAYMENT,
		PAID,
		PENDINGBILLPRINT,
		BILLPRINTED
	}
	
	public UUID id = UUID.randomUUID();
	public GeneralUser purchaser;
	public ArrayList<Book> books = new ArrayList<Book>();
	public SaleStatus status = SaleStatus.CARTEMPTY;
	public double totalAmount = 0;
	
	public Sale() {}
	
	public String getStatusName() {
		switch(status) {
		case CARTEMPTY:
			return "Your Cart is Empty!";
		case ADDEDTOCART:
			return "Waiting for Checkout";
		case PENDINGPAYMENT:
			return "Checked out, Your payment is pending!";
		case PAID:
			return "Paid";
		case PENDINGBILLPRINT:
			return "Bill Print Requested!";
		case BILLPRINTED:
			return "Bill Printed";
		default:
			return "Unknown";
		}
	}
}
