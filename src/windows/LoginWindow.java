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

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	protected JTextField username;
	protected JPasswordField password;
	protected JButton loginButton = new JButton("Login");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow("Test");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow(String person) {
		setResizable(false);
		setTitle(person + " Login - Library Management System v2");
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
		
		JLabel headerText = new JLabel(person + " Login");
		sl_contentPane.putConstraint(SpringLayout.NORTH, headerText, 74, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, headerText, 10, SpringLayout.WEST, contentPane);
		headerText.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 27));
		contentPane.add(headerText);
		JLabel logoText = new JLabel("Library Management System\r\n");
		sl_contentPane.putConstraint(SpringLayout.NORTH, logoText, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, logoText, -122, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, logo, -6, SpringLayout.WEST, logoText);
		sl_contentPane.putConstraint(SpringLayout.NORTH, this, 0, SpringLayout.NORTH, logoText);
		contentPane.add(logoText);
		logoText.setHorizontalAlignment(SwingConstants.CENTER);
		logoText.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 27));
		
		username = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, username, 50, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, username, -204, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, username, -31, SpringLayout.EAST, contentPane);
		username.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		username.setToolTipText("Enter username");
		username.setBackground(new Color(245, 245, 245));
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, usernameLabel, -242, SpringLayout.SOUTH, contentPane);
		usernameLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		sl_contentPane.putConstraint(SpringLayout.WEST, usernameLabel, 0, SpringLayout.WEST, headerText);
		contentPane.add(usernameLabel);
		
		JLabel usernameIcon = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, username, -2, SpringLayout.NORTH, usernameIcon);
		sl_contentPane.putConstraint(SpringLayout.NORTH, usernameIcon, 4, SpringLayout.SOUTH, usernameLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, usernameIcon, 11, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, usernameIcon, -1, SpringLayout.WEST, username);
		usernameIcon.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\username-diff-sizes\\favicon-32x32.png"));
		contentPane.add(usernameIcon);
		
		JLabel passwordLabel = new JLabel("Password");
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordLabel, 19, SpringLayout.SOUTH, username);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, headerText);
		passwordLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		contentPane.add(passwordLabel);
		
		JLabel passwordIcon = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordIcon, 257, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordIcon, 0, SpringLayout.WEST, headerText);
		passwordIcon.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\password-diff-sizes\\favicon-32x32.png"));
		contentPane.add(passwordIcon);
		
		password = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, password, 6, SpringLayout.SOUTH, passwordLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, username);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, password, 7, SpringLayout.SOUTH, passwordIcon);
		sl_contentPane.putConstraint(SpringLayout.EAST, password, 0, SpringLayout.EAST, username);
		password.setToolTipText("Enter password");
		contentPane.add(password);
		
		loginButton.setForeground(new Color(0, 0, 0));
		loginButton.setBackground(new Color(154, 205, 50));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, loginButton, 69, SpringLayout.SOUTH, password);
		loginButton.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\login.png"));
		loginButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		sl_contentPane.putConstraint(SpringLayout.NORTH, loginButton, 30, SpringLayout.SOUTH, password);
		sl_contentPane.putConstraint(SpringLayout.WEST, loginButton, 0, SpringLayout.WEST, headerText);
		sl_contentPane.putConstraint(SpringLayout.EAST, loginButton, 121, SpringLayout.WEST, contentPane);
		contentPane.add(loginButton);
		this.setVisible(true);
	}
}
