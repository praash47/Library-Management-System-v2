package windows;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.CardLayout;
import utils.*;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPanel extends JFrame {

	private JPanel contentPane;
	protected JPanel itemPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuPanel frame = new MainMenuPanel("test");
					ArrayList<MenuOption> menuOptions =new ArrayList<MenuOption>();
					menuOptions.add(new MenuOption("Add Librarian", new Color(154, 205, 50), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\add-user.png")));
					menuOptions.add(new MenuOption("View Reports", new Color(30, 144, 255), new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\report.png")));
					frame.addOptions(menuOptions);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenuPanel(String titleInit) {
		setTitle(titleInit + " Main Menu - Library Management System v2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel logoPanel = new JPanel();
		contentPane.add(logoPanel, BorderLayout.NORTH);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\main-icon-small.png"));
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel logoText = new JLabel("Library Management System\r\n");
		logoText.setHorizontalAlignment(SwingConstants.CENTER);
		logoText.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 27));
		logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		logoPanel.add(logo);
		logoPanel.add(logoText);
		
		JPanel mainMenuPanel = new JPanel();
		contentPane.add(mainMenuPanel, BorderLayout.CENTER);
		mainMenuPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel headerText = new JLabel("Main Menu");
		headerText.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuPanel.add(headerText, BorderLayout.NORTH);
		headerText.setVerticalAlignment(SwingConstants.TOP);
		headerText.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 27));
		
		mainMenuPanel.add(itemPanel, BorderLayout.CENTER);
		itemPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	public void addOptions(ArrayList<MenuOption> menuOptions) {
		Iterator menuOptionsIter = menuOptions.iterator();
		
		while(menuOptionsIter.hasNext()) {
			JPanel menuOptionPanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) menuOptionPanel.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setHgap(10);
			flowLayout.setAlignOnBaseline(true);
			MenuOption menuOption = (MenuOption) menuOptionsIter.next();
			menuOptionPanel.setBackground((Color) menuOption.options.get("color"));
			menuOptionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			menuOptionPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			menuOptionPanel.setName((String) menuOption.options.get("name"));
			itemPanel.add(menuOptionPanel);
			
			JLabel menuOptionText = new JLabel((String) menuOption.options.get("name"));
			menuOptionText.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 19));
			menuOptionText.setIcon((ImageIcon) menuOption.options.get("icon"));
			menuOptionPanel.add(menuOptionText);
		}
		
		this.setVisible(true);
		
	}
}
