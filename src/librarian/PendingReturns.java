package librarian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import generaluser.GeneralUser;
import transactions.Issue;
import transactions.Issue.IssueStatus;
import utils.FileHandler;
import windows.InfoDialog;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;

public class PendingReturns extends JFrame {

	private JPanel contentPane;
	JPanel pendingReturns = new JPanel();
	Librarian librarian;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PendingReturns frame = new PendingReturns(new Librarian("" , ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PendingReturns(Librarian librarian) {
		this.librarian = librarian;
		setTitle("Pending Request Issues - Library Management System v2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 416);
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
		
		JPanel pendingReturnsPanel = new JPanel();
		contentPane.add(pendingReturnsPanel, BorderLayout.CENTER);
		pendingReturnsPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel pendingReturnsHeader = new JLabel("Pending Returns");
		pendingReturnsHeader.setFont(new Font("Yu Gothic UI", Font.PLAIN, 27));
		pendingReturnsPanel.add(pendingReturnsHeader, BorderLayout.NORTH);
		
		pendingReturnsPanel.add(pendingReturns, BorderLayout.CENTER);
		
		checkpendingReturns();
		
		this.setVisible(true);
	}
	public void checkpendingReturns() {
		FileHandler issueFile = new FileHandler("issues");
		FileHandler generalUserFile = new FileHandler("generaluser");
		ArrayList<Object> existingIssues = issueFile.al;
		ArrayList<Object> generalUsers = generalUserFile.al;
		for (Object gu: generalUsers) {
			GeneralUser generalUser = (GeneralUser) gu;
			JPanel pendingReturnPerson = new JPanel();
			pendingReturnPerson.setBackground(new Color(240, 255, 255));
			pendingReturns.add(pendingReturnPerson);
			pendingReturnPerson.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel personName = new JLabel(generalUser.username + ":");
			personName.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			pendingReturnPerson.add(personName);
			for (Object i: existingIssues) {
				Issue issue = (Issue) i;
				if (issue.issuedTo.username.equals(generalUser.username) && issue.status == IssueStatus.PENDINGRETURN) {
					JPanel pendingReturn = new JPanel();
					pendingReturn.setBackground(new Color(230, 230, 250));
					pendingReturnPerson.add(pendingReturn);
					
					JLabel bookName = new JLabel(issue.book.title);
					bookName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
					pendingReturn.add(bookName);
					
					JButton acceptButton = new JButton("Accept");
					acceptButton.setIcon(null);
					acceptButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
					acceptButton.setForeground(new Color(34, 139, 34));
					acceptButton.setName(issue.book.title);
					pendingReturn.add(acceptButton);
					
					acceptButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							issue.status = IssueStatus.RETURNED;
							issue.issuedBy = librarian;
							issueFile.updateFile();
							JButton sourceButton = (JButton) e.getSource();
							pendingReturnPerson.remove(sourceButton.getParent());
							pendingReturnPerson.revalidate();
							pendingReturnPerson.repaint();
							InfoDialog dialog = new InfoDialog("Return Accepted", "Successfully accepted the return!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
						}
					});
					
					JButton rejectButton = new JButton("Reject");
					rejectButton.setForeground(new Color(165, 42, 42));
					rejectButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
					rejectButton.setName(issue.book.title);
					pendingReturn.add(rejectButton);
					
					rejectButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							issue.status = IssueStatus.REJECTEDRETURN;
							issueFile.updateFile();
							JButton sourceButton = (JButton) e.getSource();
							pendingReturnPerson.remove(sourceButton.getParent());
							pendingReturnPerson.revalidate();
							pendingReturnPerson.repaint();
							InfoDialog dialog = new InfoDialog("Return Rejected", "Successfully rejected the return!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
						}
					});
				}
			}	
		}
	}
}
