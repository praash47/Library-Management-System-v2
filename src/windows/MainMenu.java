package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	public JButton adminLogin = new JButton("Admin Login");
	public JButton librarianLogin = new JButton("Librarian Login");
	public JButton generalUserLogin = new JButton("General User Login");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setResizable(false);
		setTitle("Library Management System v2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel logo = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, logo, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, logo, 151, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, this, -348, SpringLayout.WEST, logo);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\main-icon-small.png"));
		contentPane.add(logo);
		JLabel logoText = new JLabel("Library Management System\r\n");
		sl_contentPane.putConstraint(SpringLayout.NORTH, logoText, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, logoText, -122, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, logo, -6, SpringLayout.WEST, logoText);
		sl_contentPane.putConstraint(SpringLayout.NORTH, this, 0, SpringLayout.NORTH, logoText);
		contentPane.add(logoText);
		logoText.setHorizontalAlignment(SwingConstants.CENTER);
		logoText.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 27));
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, adminLogin, 59, SpringLayout.SOUTH, logoText);
		sl_contentPane.putConstraint(SpringLayout.EAST, adminLogin, 467, SpringLayout.WEST, contentPane);
		adminLogin.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		contentPane.add(adminLogin);
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, librarianLogin, 184, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, librarianLogin, -180, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, adminLogin, 0, SpringLayout.WEST, librarianLogin);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, adminLogin, -31, SpringLayout.NORTH, librarianLogin);
		sl_contentPane.putConstraint(SpringLayout.WEST, librarianLogin, 235, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, librarianLogin, -235, SpringLayout.EAST, contentPane);
		
		librarianLogin.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		contentPane.add(librarianLogin);
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, generalUserLogin, 32, SpringLayout.SOUTH, librarianLogin);
		sl_contentPane.putConstraint(SpringLayout.WEST, generalUserLogin, 0, SpringLayout.WEST, adminLogin);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, generalUserLogin, -102, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, generalUserLogin, 465, SpringLayout.WEST, contentPane);
		generalUserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		generalUserLogin.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		contentPane.add(generalUserLogin);
		this.setVisible(true);
	}
}
