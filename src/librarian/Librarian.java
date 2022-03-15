package librarian;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import admin.AddLibrarian;
import user.*;
import utils.FileHandler;
import windows.InfoDialog;

import generaluser.*;

public class Librarian extends User implements Serializable {
	public Librarian(String username, String password) {
		super(username, password);
	}
	public void addGeneralUser() {
		AddGeneralUser addGeneralUser = new AddGeneralUser();
		Librarian l = this;
		addGeneralUser.addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralUser generaluser = new GeneralUser("", "");
				l.addUserToFile(generaluser, "generaluser", addGeneralUser);
			}
		});
		addGeneralUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void pendingRequestIssue() {
		PendingIssueRequests pendingIssueRequests = new PendingIssueRequests(this);
		pendingIssueRequests.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void pendingReturns() {
		PendingReturns pendingReturns = new PendingReturns(this);
		pendingReturns.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void printBill() {
		PrintBill printBill = new PrintBill(this);
		printBill.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
