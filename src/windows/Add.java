package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Add extends JFrame {

	private JPanel addPanel;
	protected JTextField usernameField;
	protected JPasswordField passwordField;
	public JButton addButton = new JButton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add frame = new Add("test");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Add(String role) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		setTitle("Add " + role + " - Library Management System v2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 488);
		addPanel = new JPanel();
		addPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(addPanel);
		addPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		addPanel.add(logoPanel, BorderLayout.NORTH);
		logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\main-icon-small.png"));
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		logoPanel.add(logo);
		
		JLabel logoText = new JLabel("Library Management System\r\n");
		logoText.setHorizontalAlignment(SwingConstants.CENTER);
		logoText.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 27));
		logoPanel.add(logoText);
		
		JPanel additionPanel = new JPanel();
		addPanel.add(additionPanel, BorderLayout.CENTER);
		additionPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		additionPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new CardLayout(0, 20));
		
		JLabel add = new JLabel("Add " + role);
		add.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\add-user.png"));
		add.setHorizontalAlignment(SwingConstants.LEFT);
		add.setFont(new Font("Yu Gothic UI", Font.PLAIN, 27));
		headerPanel.add(add, "name_46113929945400");
		
		JPanel dataEntryPanel = new JPanel();
		additionPanel.add(dataEntryPanel, BorderLayout.EAST);
		GridBagLayout gbl_dataEntryPanel = new GridBagLayout();
		gbl_dataEntryPanel.columnWidths = new int[] {400, 100};
		gbl_dataEntryPanel.rowHeights = new int[] {20, 20, 0, 0, 0, 0};
		gbl_dataEntryPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_dataEntryPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dataEntryPanel.setLayout(gbl_dataEntryPanel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\username.png"));
		lblUsername.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		dataEntryPanel.add(lblUsername, gbc_lblUsername);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.gridx = 0;
		gbc_usernameField.gridy = 1;
		dataEntryPanel.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblNewLabel.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\password.png"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		dataEntryPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 3;
		dataEntryPanel.add(passwordField, gbc_passwordField);
		
		addButton = new JButton("Add " + role);
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.setBackground(new Color(34, 139, 34));
		addButton.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\add-user.png"));
		addButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.ipadx = 10;
		gbc_addButton.anchor = GridBagConstraints.WEST;
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 4;
		dataEntryPanel.add(addButton, gbc_addButton);
		this.setVisible(true);
	}
	public String getUsername() throws Exception {
		if (this.usernameField.getText().length() > 0) {
			return this.usernameField.getText();
		} else {
			throw new Exception("Username should not be empty!");
		}
	}
	public char[] getPassword() throws Exception {
		if (this.passwordField.getPassword().length > 0) {
			return this.passwordField.getPassword();
		} else {
			throw new Exception("Password should not be empty!");
		}
	}
}
