package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import generaluser.GeneralUser;
import net.miginfocom.swing.MigLayout;
import transactions.Issue;
import transactions.Issue.IssueStatus;
import transactions.Sale;
import transactions.Sale.SaleStatus;
import utils.APIHandler;
import utils.FileHandler;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class ReportsWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportsWindow frame = new ReportsWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReportsWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		setTitle("View Reports - Library Management System v2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);
		
		JPanel headers = new JPanel();
		contentPane.add(headers, BorderLayout.NORTH);
		headers.setLayout(new BorderLayout(0, 0));
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		headers.add(logoPanel, BorderLayout.NORTH);
		logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\main-icon-small.png"));
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		logoPanel.add(logo);
		
		JLabel logoText = new JLabel("Library Management System\r\n");
		logoText.setHorizontalAlignment(SwingConstants.CENTER);
		logoText.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 27));
		logoPanel.add(logoText);
		
		JPanel dashboardsPanel = new JPanel();
		contentPane.add(dashboardsPanel, BorderLayout.CENTER);
		dashboardsPanel.setLayout(new BorderLayout(0, 20));
		
		JLabel dashboardHeader = new JLabel("Dashboard");
		dashboardHeader.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\report.png"));
		dashboardHeader.setFont(new Font("Yu Gothic UI", Font.PLAIN, 27));
		dashboardsPanel.add(dashboardHeader, BorderLayout.NORTH);
		
		JPanel statsPanel = new JPanel();
		dashboardsPanel.add(statsPanel, BorderLayout.CENTER);
		statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel salesPanel = new JPanel();
		salesPanel.setForeground(new Color(255, 255, 255));
		salesPanel.setBackground(new Color(0, 100, 0));
		statsPanel.add(salesPanel);
		
		FileHandler saleFile = new FileHandler("sales");
		ArrayList<Object> existingSales = saleFile.al;
				
		double totalSales = 0;
		for (Object s: existingSales) {
			Sale fileSale = (Sale) s;
			if (fileSale.status == SaleStatus.PAID || fileSale.status == SaleStatus.BILLPRINTED
					|| fileSale.status == SaleStatus.PENDINGBILLPRINT) {
				totalSales += fileSale.totalAmount;
			}
		}
		JLabel salesAmount = new JLabel("Total Sales Amount: Rs. " + String.format("%.2f", totalSales));
		salesAmount.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\sales.png"));
		salesAmount.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		salesAmount.setForeground(new Color(255, 255, 255));
		salesPanel.add(salesAmount);
		
		JPanel totalBooksPanel = new JPanel();
		totalBooksPanel.setForeground(Color.WHITE);
		totalBooksPanel.setBackground(new Color(160, 82, 45));
		statsPanel.add(totalBooksPanel);
		
		APIHandler api = new APIHandler();
		JLabel totalBooks = new JLabel("Total Number of Books: " + api.fetchCount());
		totalBooks.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\books.png"));
		totalBooks.setForeground(Color.WHITE);
		totalBooks.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		totalBooksPanel.add(totalBooks);
		
		JPanel generalUsersPanel = new JPanel();
		generalUsersPanel.setForeground(Color.WHITE);
		generalUsersPanel.setBackground(new Color(100, 149, 237));
		statsPanel.add(generalUsersPanel);
		

		FileHandler generalUserFile = new FileHandler("generaluser");
		ArrayList<Object> existingGeneralUsers = generalUserFile.al;
				
		int numGeneralUsers = 0;
		for (Object u: existingGeneralUsers) {
			GeneralUser generaluser = (GeneralUser) u;
			numGeneralUsers++;
		}
		JLabel generalUsers = new JLabel("Total General Users: " + numGeneralUsers);
		generalUsers.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\users.png"));
		generalUsers.setForeground(Color.WHITE);
		generalUsers.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		generalUsersPanel.add(generalUsers);
		
		
		JPanel issuedBooksPanel = new JPanel();
		issuedBooksPanel.setForeground(Color.WHITE);
		issuedBooksPanel.setBackground(new Color(222, 184, 135));
		statsPanel.add(issuedBooksPanel);
		
		
		int issuesCount = 0;
		FileHandler issueFile = new FileHandler("issues");
		ArrayList<Object> existingIssues = issueFile.al;
		int indexNum = 0;
		for (Object i: existingIssues) {
			Issue issue = (Issue) i;
			if (issue.status == IssueStatus.ACCEPTED || issue.status == IssueStatus.RETURNED 
					|| issue.status == IssueStatus.REJECTEDRETURN || issue.status == IssueStatus.PENDINGRETURN) {
				issuesCount++;
			}
		}
		
		
		JLabel issuedBooks = new JLabel("Total Issued Books: " + issuesCount);
		issuedBooks.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\issue.png"));
		issuedBooks.setForeground(new Color(0, 0, 0));
		issuedBooks.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		issuedBooksPanel.add(issuedBooks);
		this.setVisible(true);
	}

}
