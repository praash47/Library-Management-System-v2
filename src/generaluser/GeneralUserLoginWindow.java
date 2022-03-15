package generaluser;

import utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.ImageIcon;

import windows.*;

public class GeneralUserLoginWindow extends LoginWindow {

	public GeneralUserLoginWindow() {
		super("General User");
		FileHandler generalUserFile = new FileHandler("generaluser");
		LoginWindow window = this;
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator generalUserIter = generalUserFile.al.iterator();
				boolean generalUserFound = false;
				while (generalUserIter.hasNext()) {
					GeneralUser generalUser = (GeneralUser) generalUserIter.next();
					if (generalUser.checkCredentials(username.getText(), new String(password.getPassword()))) {
						window.dispose();
						generalUserFound = true;
						GeneralUserMainMenu menu = new GeneralUserMainMenu(generalUser);
						InfoDialog dialog = new InfoDialog("Login Successful", "Login is successful!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
						break;
					}
				}
				if (!generalUserFound) {					
					InfoDialog dialog = new InfoDialog("Login Unsuccessful", "Username or Password incorrect!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
				}
			}
		});
	}
}
