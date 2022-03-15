package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import user.*;
import librarian.*;
import utils.*;
import windows.InfoDialog;

public class Admin extends User {
	private final static String masterUsername = "lms";
	private final static String masterPassword = "lms";
	
	Admin() {
		super(masterUsername, masterPassword);
	}
	public void addLibrarian() {
		AddLibrarian addLibrarian = new AddLibrarian();
		Admin a = this;
		addLibrarian.addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Librarian librarian = new Librarian("", "");
				a.addUserToFile(librarian, "librarian", addLibrarian);
			}
		});
		addLibrarian.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void viewReports() {
		ReportsWindow reportswindow = new ReportsWindow();
		reportswindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
