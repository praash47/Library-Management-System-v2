package librarian;

import utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.ImageIcon;

import windows.*;

public class LibrarianLoginWindow extends LoginWindow {

	public LibrarianLoginWindow() {
		super("Librarian");
		FileHandler librarianFile = new FileHandler("librarian");
		LoginWindow window = this;
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator librarianIter = librarianFile.al.iterator();
				boolean librarianFound = false;
				while (librarianIter.hasNext()) {
					Librarian librarian = (Librarian) librarianIter.next();
					if (librarian.checkCredentials(username.getText(), new String(password.getPassword()))) {
						window.dispose();
						librarianFound = true;
						LibrarianMainMenu librarianMainMenu = new LibrarianMainMenu(librarian);
						InfoDialog dialog = new InfoDialog("Login Successful", "Login is successful!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
						break;
					}
				}
				if (!librarianFound) {					
					InfoDialog dialog = new InfoDialog("Login Unsuccessful", "Username or Password incorrect!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
				}
			}
		});
	}
}
