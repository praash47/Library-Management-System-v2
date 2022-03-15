package generaluser;
import windows.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;

import utils.*;

public class GeneralUserMainMenu extends MainMenuPanel {
	ArrayList<String> options = new ArrayList<String>(
		      Arrays.asList("View Books Catalog", "My Issued Books", "Pay Bill"));
	GeneralUser generaluser;
	
	public GeneralUserMainMenu(GeneralUser generaluser) {
		super("General User");
		this.generaluser = generaluser;
		ArrayList<MenuOption> menuOptions =new ArrayList<MenuOption>();
		menuOptions.add(new MenuOption(options.get(0), new Color(154, 205, 50), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\books.png")));
		menuOptions.add(new MenuOption(options.get(1), new Color(30, 144, 255), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\issue.png")));
		menuOptions.add(new MenuOption(options.get(2), new Color(199, 10, 4), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\return.png")));
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
					case "View Books Catalog":
						generaluser.viewBooks();
						break;
					case "My Issued Books":
						generaluser.issuedBooks();
						break;
					case "Pay Bill":
						generaluser.payBill();
						break;
					}
				}
			});
		}
	}
}
