package generaluser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import transactions.Issue;
import transactions.Issue.IssueStatus;
import utils.FileHandler;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MultipleActions extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JRadioButton firstOption, secondOption;
	String issueBookName;
	FileHandler issueFile;
	DefaultTableModel model;
	IssuedBooks mainWindow;
	private GeneralUser generaluser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MultipleActions dialog = new MultipleActions("", "", "", new FileHandler(""), new DefaultTableModel(), new IssuedBooks(new GeneralUser("", "")), new GeneralUser("", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MultipleActions(String Option1, String Option2, String issueBookName, FileHandler issueFile, DefaultTableModel model, IssuedBooks mainWindow, GeneralUser user) {
		this.generaluser = user;
		this.issueFile = issueFile;
		this.issueBookName = issueBookName;
		this.model = model;
		this.mainWindow = mainWindow;
		setTitle("Which action to perform? - Library Mangement System v2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		setBounds(100, 100, 192, 85);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		firstOption = new JRadioButton(Option1);
		firstOption.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				performAction(firstOption.getText());
			}
		});
		contentPanel.add(firstOption);
		JRadioButton secondOption = new JRadioButton(Option2);
		secondOption.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				performAction(secondOption.getText());
			}
		});
		contentPanel.add(secondOption);
		ButtonGroup bg=new ButtonGroup();    
		bg.add(firstOption);
		bg.add(secondOption);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void performAction(String action) {
		ArrayList<Object> existingIssues = issueFile.al;
		switch(action) {
		case "Return":
			for (Object i: existingIssues) {
				Issue issue = (Issue) i;
				if(issue.book.title == issueBookName) {
					issue.status = IssueStatus.PENDINGRETURN;
				}
			}
			issueFile.updateFile();
			break;
		case "Add to Cart":
			for (Object i: existingIssues) {
				Issue issue = (Issue) i;
				if(issue.book.title == issueBookName) {
					ViewBookDetails.addToCart(issue.book, generaluser);
					model.fireTableDataChanged();
				}
			}
			break;
		case "Cancel":
			for (Object i: existingIssues) {
				Issue issue = (Issue) i;
				if(issue.book.title == issueBookName) {
					issue.status = IssueStatus.CANCELED;
				}
			}
			issueFile.updateFile();
			break;
		}
		this.mainWindow.updateTableModel(model, issueFile);
		this.model.fireTableDataChanged();
		this.dispose();
	} 
}
