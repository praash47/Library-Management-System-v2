package generaluser;

import utils.*;
import windows.InfoDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import transactions.Author;
import transactions.Book;
import transactions.Issue;
import transactions.Issue.IssueStatus;
import transactions.Sale;
import transactions.Sale.SaleStatus;

import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Cursor;

public class ViewBookDetails extends JFrame {
	public static GeneralUser user;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBookDetails frame = new ViewBookDetails(new Book(1, "", null, null, null, null, null), new GeneralUser("", ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewBookDetails(Book book, GeneralUser user) {
		this.user = user;
		setTitle(book.title + " - Library Management System v2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 399);
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
		
		JPanel infoPane = new JPanel();
		contentPane.add(infoPane, BorderLayout.CENTER);
		infoPane.setLayout(new MigLayout("", "[142px][46px]", "[27px][][][][][][][][][]"));
		
		JLabel bookName = new JLabel(book.title);
		bookName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		infoPane.add(bookName, "cell 0 0,alignx left,aligny center");
		
		String authors = "Authors: ";
		for (Author author: book.authors) {
			authors += author.name;
			authors += " (";
			authors += Long.toString(author.birthYear);
			authors += " - ";
			authors += Long.toString(author.deathYear);
			authors += ") |";
		}
		
		JLabel authorsLabel = new JLabel(authors);
		authorsLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		infoPane.add(authorsLabel, "cell 0 2,alignx left,aligny center");
		
		String translators = "Translators: ";
		for (Author translator: book.translators) {
			translators += translator.name;
			translators += " (";
			translators += Long.toString(translator.birthYear);
			translators += " - ";
			translators += Long.toString(translator.deathYear);
			translators+= ") |";
		}
		
		JLabel translatorsLabel = new JLabel(translators);
		translatorsLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		infoPane.add(translatorsLabel, "cell 0 3");
		
		String subjects = "Subjects: ";
		for (String subject: book.subjects) {
			subjects += subject;
			subjects += ", ";
		}
		JLabel subjectsLabel = new JLabel(subjects);
		subjectsLabel.setPreferredSize(new Dimension(300, 14));
		subjectsLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		infoPane.add(subjectsLabel, "cell 0 4");
		
		String bookshelves = "Bookshelves: ";
		for (String bookshelf: book.bookshelves) {
			bookshelves += bookshelf;
			bookshelves += ", ";
		}
		
		
		JLabel bookshelvesLabel = new JLabel(bookshelves);
		bookshelvesLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		infoPane.add(bookshelvesLabel, "cell 0 5");
		
		String languages = "Languages: ";
		for (String language: book.languages) {
			languages += language;
			languages += ", ";
		}
		
		JLabel languagesLabel = new JLabel(languages);
		languagesLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		infoPane.add(languagesLabel, "cell 0 6");
		
		JLabel costLabel = new JLabel("Cost: " + book.cost);
		costLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		infoPane.add(costLabel, "cell 0 7");
		
		JButton requestIssue = new JButton("Request for Issue");
		requestIssue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		requestIssue.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\issue.png"));
		requestIssue.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		infoPane.add(requestIssue, "flowx,cell 0 9");
		
		requestIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestBookForIssue(book);
			}
		});
		
		JButton addToCart = new JButton("Add to Cart");
		addToCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addToCart.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cart.png"));
		addToCart.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));

		addToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCart(book, null);
			}
		});
		
		infoPane.add(addToCart, "cell 0 9");
		this.setVisible(true);
	}
	private void requestBookForIssue(Book book) {
		FileHandler saleFile = new FileHandler("sales");
		FileHandler issueFile = new FileHandler("issues");
		ArrayList<Object> existingIssues = issueFile.al;
		ArrayList<Object> existingSales = saleFile.al;
		for (Object s: existingSales) {
			Sale sale = (Sale) s;
			for (Book soldBook: sale.books) {
				if(soldBook.title.equals(book.title)) {
					InfoDialog dialog = new InfoDialog("Issue Unsuccessful", "Sorry, " + book.title + " is already sold to someone else!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
					return;
				}
			}
		}
		for (Object o: existingIssues) {
			Issue issue = (Issue) o;
			if (issue.book.title.equals(book.title) && issue.issuedTo.username.equals(user.username)
					&& (issue.status == IssueStatus.ACCEPTED || issue.status == IssueStatus.PENDINGACCEPT
					|| issue.status == IssueStatus.PENDINGRETURN || issue.status == IssueStatus.REJECTEDRETURN)) {
				InfoDialog dialog = new InfoDialog("Book already Issued!", book.title + " is already issued to you/requested to be issued!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
				return;
			} else if (issue.book.title.equals(book.title) && (issue.status == IssueStatus.PENDINGACCEPT 
					|| issue.status == IssueStatus.ACCEPTED || issue.status == IssueStatus.PENDINGRETURN
					|| issue.status == IssueStatus.REJECTEDRETURN)) {
				InfoDialog dialog = new InfoDialog("Issue Unsuccessful", "Sorry, " + book.title + " is already issued to someone else!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
				return;
			}
		}
		Issue issue = new Issue();
		issue.requestIssue(user, book);
		issueFile.write(issue);
		InfoDialog dialog = new InfoDialog("Request Issue Successful", "Successfully requested " + book.title + " for Issue! Wait until the issue gets accepted by any librarian.", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
	}
	static void addToCart(Book book, GeneralUser generaluser) {
		if (generaluser != null) { user = generaluser; }
		FileHandler saleFile = new FileHandler("sales");
		FileHandler issueFile = new FileHandler("issues");
		ArrayList<Object> existingIssues = issueFile.al;
		for (Object o: existingIssues) {
			Issue issue = (Issue) o;
			if (issue.book.title.equals(book.title) && (issue.status == IssueStatus.PENDINGACCEPT 
					|| issue.status == IssueStatus.ACCEPTED || issue.status == IssueStatus.PENDINGRETURN
					|| issue.status == IssueStatus.REJECTEDRETURN) && !(issue.issuedTo.username.equals(generaluser.username))) {
				InfoDialog dialog = new InfoDialog("Add to Cart Unsuccessful", "Sorry, " + book.title + " is already issued to someone else!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
				return;
			}
		}
		ArrayList<Object> existingSales = saleFile.al;
		for (Object s: existingSales) {
			Sale sale = (Sale) s;
			for (Book soldBook: sale.books) {
				if (soldBook.title.equals(book.title) && sale.purchaser.username.equals(user.username)) {
					InfoDialog dialog = new InfoDialog("Already added to cart", book.title + " is already added to your cart!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
					return;
				} else if(soldBook.title.equals(book.title)) {
						InfoDialog dialog = new InfoDialog("Add to Cart Unsuccessful", "Sorry, " + book.title + " is already sold to someone else!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
						return;
					}
			}
		}
		
		for (Object s: existingSales) {
			Sale sale = (Sale) s;
			if (sale.purchaser.username.equals(user.username) && (sale.status == SaleStatus.CARTEMPTY || sale.status == SaleStatus.ADDEDTOCART)) {
				sale.books.add(book);
				sale.status = SaleStatus.ADDEDTOCART;
				sale.totalAmount += book.cost;
				saleFile.updateFile();
				InfoDialog dialog = new InfoDialog("Added to Cart!", "Successfully added " + book.title + " into the cart. Checkout from the Pay Bill!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
				return;
			} else if (sale.purchaser.username.equals(user.username) && (sale.status == SaleStatus.PENDINGPAYMENT)) {
				InfoDialog dialog = new InfoDialog("Clear dues", "Clear your pending dues first in order to add new item to cart!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cross.png"));
				return;
			}
		}

		Sale newSale = new Sale();
		newSale.books.add(book);
		newSale.purchaser = user;
		newSale.status = SaleStatus.ADDEDTOCART;
		newSale.totalAmount += book.cost;
		saleFile.write(newSale);
		InfoDialog dialog = new InfoDialog("Added to Cart!", "Successfully added " + book.title + " into the cart. Checkout from the Pay Bill!", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
	}
}
