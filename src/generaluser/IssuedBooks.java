package generaluser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import transactions.Book;
import transactions.Issue;
import transactions.Issue.IssueStatus;
import utils.FileHandler;

import javax.swing.JScrollPane;

public class IssuedBooks extends JFrame {

	private JPanel contentPane;
	GeneralUser generaluser;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssuedBooks frame = new IssuedBooks(new GeneralUser("", ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IssuedBooks(GeneralUser generaluser) {
		setTitle("My Issued Books - Library Management System v2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		this.generaluser = generaluser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel logoPanel = new JPanel();
		contentPane.add(logoPanel, BorderLayout.NORTH);
		logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\main-icon-small.png"));
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		logoPanel.add(logo);
		
		JLabel logoText = new JLabel("Library Management System\r\n");
		logoText.setHorizontalAlignment(SwingConstants.CENTER);
		logoText.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 27));
		logoPanel.add(logoText);
		
		JPanel myIssuesPane = new JPanel();
		contentPane.add(myIssuesPane, BorderLayout.CENTER);
		myIssuesPane.setLayout(new BorderLayout(0, 0));
		
		JLabel myIssuedBooksHeader = new JLabel("My Issued Books");
		myIssuedBooksHeader.setFont(new Font("Yu Gothic UI", Font.PLAIN, 27));
		myIssuesPane.add(myIssuedBooksHeader, BorderLayout.NORTH);
		
		JPanel issuesTablePanel = new JPanel();
		myIssuesPane.add(issuesTablePanel, BorderLayout.CENTER);
		issuesTablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		String[] columns = {
		        "Book Name", "Status", "Actions"
	    };
	    // Create the table model
	    DefaultTableModel model = new DefaultTableModel(columns, 0);
	    // Create the JTable
	    table = new JTable(model);
	    table.getColumnModel().getColumn(0).setPreferredWidth(275);
	    table.getColumnModel().getColumn(1).setPreferredWidth(125);
	    table.getColumnModel().getColumn(2).setPreferredWidth(125);
		table.setFillsViewportHeight(true);
		
		FileHandler issueFile = new FileHandler("issues");
		updateTableModel(model, issueFile);
		
		IssuedBooks ib = this;
		table.addMouseListener(new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      int column = target.getSelectedColumn();
		      if (row >= 1 && column == 2) {
		    	  performOperation((String)table.getModel().getValueAt(row, column), (String)table.getModel().getValueAt(row, 0), model, issueFile, ib);
		      }
		  }
		});
		
		issuesTablePanel.add(table);
		
		JLabel note = new JLabel("Note: Single Click at the Actions to perform selected action.");
		note.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		myIssuesPane.add(note, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	void updateTableModel(DefaultTableModel model, FileHandler issueFile) {
		String[] columns = {
		        "Book Name", "Status", "Actions"
	    };
		model.setRowCount(0);
	    model.addRow(columns);
		ArrayList<Object> existingIssues = issueFile.al;
		int indexNum = 0;
		for (Object i: existingIssues) {
			Issue issue = (Issue) i;
			if (issue.issuedTo.username.equals(this.generaluser.username)){
				model.addRow(new Object[] {
					issue.book.title,
					issue.getStatusName(),
					getActionsAndCollectListeners(issue, indexNum)
				});
			}
		}
	}

	private String getActionsAndCollectListeners(Issue issue, int index) {
		String returnBook = "Return";
		String addToCart = "Add to Cart";
		String cancel = "Cancel";
		switch(issue.status) {
		case ACCEPTED:
			return returnBook + ", " + addToCart;
		case REJECTED:
			return addToCart;
		case PENDINGACCEPT:
			return cancel + ", " + addToCart;
		case PENDINGRETURN:
			return cancel + ", " + addToCart;
		case REJECTEDRETURN:
			return addToCart;
		case RETURNED:
			return addToCart;
		case CANCELED:
			return addToCart;
		default:
			return "";
		}
	}

	private void performOperation(String operation, String bookName, DefaultTableModel model, FileHandler issueFile, IssuedBooks window) {
		switch(operation) {
		case "Return, Add to Cart":
			MultipleActions ma = new MultipleActions("Return", "Add to Cart", bookName, issueFile, model, window, generaluser);
			break;
		case "Cancel, Add to Cart":
			MultipleActions ma1 = new MultipleActions("Cancel", "Add to Cart", bookName, issueFile, model, window, generaluser);
			break;
		case "Add to Cart":
			Book bookToAdd = null;
			ArrayList<Object> existingIssues = issueFile.al;
			for (Object i: existingIssues) {
				Issue issue = (Issue) i;
				if (issue.issuedTo.username.equals(this.generaluser.username) && issue.book.title.equals(bookName)){
					bookToAdd = issue.book;
				}
			}
			ViewBookDetails.addToCart(bookToAdd, generaluser);
			model.fireTableDataChanged();
			break;
		}
	}

}
