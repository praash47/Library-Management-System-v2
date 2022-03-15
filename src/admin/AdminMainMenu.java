package admin;
import windows.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;

import utils.*;

public class AdminMainMenu extends MainMenuPanel {
	ArrayList<String> options = new ArrayList<String>(
		      Arrays.asList("Add Librarian", "View Reports"));
	Admin admin = new Admin();
	
	public AdminMainMenu() {
		super("Admin");
		ArrayList<MenuOption> menuOptions =new ArrayList<MenuOption>();
		menuOptions.add(new MenuOption(options.get(0), new Color(154, 205, 50), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\add-user.png")));
		menuOptions.add(new MenuOption(options.get(1), new Color(30, 144, 255), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\report.png")));
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
					case "Add Librarian":
						admin.addLibrarian();
						break;
					case "View Reports":
						admin.viewReports();
						break;
					}
				}
			});
		}
	}
}
