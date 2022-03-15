package user;

import windows.*;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import librarian.Librarian;
import utils.FileHandler;
import windows.InfoDialog;

public abstract class User implements Serializable {
	public String username;
	protected String password;
	
	public User(String username, String masterpassword) {
		this.username = username;
		this.password = masterpassword;
	}

	public boolean checkCredentials(String cusername, String cpassword) {
		return username.equals(cpassword) && password.equals(cpassword);
	}
	
	public void setUsernamePassword(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void addUserToFile(User u, String fileName, Add window) {
		try {
			u.setUsernamePassword(window.getUsername(), new String(window.getPassword()));
			FileHandler file = new FileHandler(fileName);
			file.write(u);
			
			window.dispose();
			InfoDialog dialog = new InfoDialog("Add Successful", window.getUsername() + " is added!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
		} catch (Exception e1) {
			InfoDialog dialog = new InfoDialog("Add Not Successful", e1.getMessage(), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
		}
	}
	public String toString() {
		return this.username + " " + this.password;
	}
}
