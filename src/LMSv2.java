import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.*;
import generaluser.GeneralUserLoginWindow;
import librarian.LibrarianLoginWindow;
import windows.*;

public class LMSv2 extends MainMenu {

	public static void main(String args[]) {
		MainMenu mainmenu = new MainMenu();
		mainmenu.adminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainmenu.dispose();
				LoginWindow window = new AdminLoginWindow();
			}
		});
		mainmenu.librarianLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainmenu.dispose();
				LoginWindow window = new LibrarianLoginWindow();
			}
		});
		mainmenu.generalUserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainmenu.dispose();
				LoginWindow window = new GeneralUserLoginWindow();
			}
		});
	}

}
