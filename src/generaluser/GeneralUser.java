package generaluser;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;

import transactions.Book;
import user.*;
import utils.APIHandler;

public class GeneralUser extends User implements Serializable {
	public GeneralUser(String username, String password) {
		super(username, password);
	}

	public void viewBooks() {
		ViewBooksCatalog viewBooksCatalog = new ViewBooksCatalog(this);
		viewBooksCatalog.renderPage();
		viewBooksCatalog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void issuedBooks() {
		IssuedBooks issuedBooks = new IssuedBooks(this);
		issuedBooks.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void payBill() {
		PayBill payBill = new PayBill(this);
		payBill.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
