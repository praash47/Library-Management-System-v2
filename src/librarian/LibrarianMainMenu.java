package librarian;
import windows.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;

import utils.*;

public class LibrarianMainMenu extends MainMenuPanel {
	ArrayList<String> options = new ArrayList<String>(
		      Arrays.asList("Add General User", "Pending Request Issues", "Pending Returns", "Print Bill"));
	Librarian librarian;
	
	public LibrarianMainMenu(Librarian librarian) {
		super("Librarian");
		this.librarian = librarian;
		ArrayList<MenuOption> menuOptions =new ArrayList<MenuOption>();
		menuOptions.add(new MenuOption(options.get(0), new Color(154, 205, 50), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\add-user.png")));
		menuOptions.add(new MenuOption(options.get(1), new Color(30, 144, 255), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\issue.png")));
		menuOptions.add(new MenuOption(options.get(2), new Color(199, 10, 4), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\return.png")));
		menuOptions.add(new MenuOption(options.get(3), new Color(163, 136, 0), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\bill.png")));
		this.addOptions(menuOptions);
		
		this.addActionListeners();
	}
	public void addActionListeners() {
		int nMenuItems =  this.itemPanel.getComponentCount();
		for (int i=0; i < nMenuItems; i++) {
			JPanel panel = (JPanel) this.itemPanel.getComponent(i);
			String name = panel.getName();
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switch (name) {
					case "Add General User":
						librarian.addGeneralUser();
						break;
					case "Pending Request Issues":
						librarian.pendingRequestIssue();
						break;
					case "Pending Returns":
						librarian.pendingReturns();
						break;
					case "Print Bill":
						librarian.printBill();
						break;
					}
				}
			});
		}
	}
}
