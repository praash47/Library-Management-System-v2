package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import windows.*;

public class AdminLoginWindow extends LoginWindow {

	public AdminLoginWindow() {
		super("Admin");
		Admin admin = new Admin();
		LoginWindow window = this;
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (admin.checkCredentials(username.getText(), new String(password.getPassword()))) {
					window.dispose();
					AdminMainMenu adminMainMenu = new AdminMainMenu();
					InfoDialog dialog = new InfoDialog("Login Successful", "Login is successful!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
				} else {
					InfoDialog dialog = new InfoDialog("Login Unsuccessful", "Username or Password incorrect!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
				}
			}
		});
	}
}
