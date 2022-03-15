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

public class PendingIssueRequests extends JFrame {

	private JPanel contentPane;
	JPanel pendingRequests = new JPanel();
	Librarian librarian;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PendingIssueRequests frame = new PendingIssueRequests(new Librarian("" , ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PendingIssueRequests(Librarian librarian) {
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
		
		JPanel pendingRequestsPanel = new JPanel();
		contentPane.add(pendingRequestsPanel, BorderLayout.CENTER);
		pendingRequestsPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel pendingRequestsHeader = new JLabel("Pending Request Issues");
		pendingRequestsHeader.setFont(new Font("Yu Gothic UI", Font.PLAIN, 27));
		pendingRequestsPanel.add(pendingRequestsHeader, BorderLayout.NORTH);
		
		pendingRequestsPanel.add(pendingRequests, BorderLayout.CENTER);
		
		checkpendingRequests();
		
		this.setVisible(true);
	}
	public void checkpendingRequests() {
		FileHandler issueFile = new FileHandler("issues");
		FileHandler generalUserFile = new FileHandler("generaluser");
		ArrayList<Object> existingIssues = issueFile.al;
		ArrayList<Object> generalUsers = generalUserFile.al;
		for (Object gu: generalUsers) {
			GeneralUser generalUser = (GeneralUser) gu;
			JPanel pendingRequestPerson = new JPanel();
			pendingRequestPerson.setBackground(new Color(240, 255, 255));
			pendingRequests.add(pendingRequestPerson);
			pendingRequestPerson.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel personName = new JLabel(generalUser.username + ":");
			personName.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			pendingRequestPerson.add(personName);
			for (Object i: existingIssues) {
				Issue issue = (Issue) i;
				if (issue.issuedTo.username.equals(generalUser.username) && issue.status == IssueStatus.PENDINGACCEPT) {
					JPanel pendingRequest = new JPanel();
					pendingRequest.setBackground(new Color(230, 230, 250));
					pendingRequestPerson.add(pendingRequest);
					
					JLabel bookName = new JLabel(issue.book.title);
					bookName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
					pendingRequest.add(bookName);
					
					JButton acceptButton = new JButton("Accept");
					acceptButton.setIcon(null);
					acceptButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
					acceptButton.setForeground(new Color(34, 139, 34));
					acceptButton.setName(issue.book.title);
					pendingRequest.add(acceptButton);
					
					acceptButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							issue.status = IssueStatus.ACCEPTED;
							issue.issuedBy = librarian;
							issueFile.updateFile();
							JButton sourceButton = (JButton) e.getSource();
							pendingRequestPerson.remove(sourceButton.getParent());
							pendingRequestPerson.revalidate();
							pendingRequestPerson.repaint();
							InfoDialog dialog = new InfoDialog("Issue Accepted", "Successfully accepted the issue!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
						}
					});
					
					JButton rejectButton = new JButton("Reject");
					rejectButton.setForeground(new Color(165, 42, 42));
					rejectButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
					rejectButton.setName(issue.book.title);
					pendingRequest.add(rejectButton);
					
					rejectButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							issue.status = IssueStatus.REJECTED;
							issueFile.updateFile();
							JButton sourceButton = (JButton) e.getSource();
							pendingRequestPerson.remove(sourceButton.getParent());
							pendingRequestPerson.revalidate();
							pendingRequestPerson.repaint();
							InfoDialog dialog = new InfoDialog("Issue Rejected", "Successfully rejected the issue!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
						}
					});
				}
			}	
		}
	}
}
