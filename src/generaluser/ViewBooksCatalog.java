package generaluser;

import transactions.*;
import utils.APIHandler;
import windows.InfoDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import admin.AdminMainMenu;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollBar;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;
import java.awt.Cursor;
import java.awt.CardLayout;

public class ViewBooksCatalog extends JFrame {

	private JPanel contentPane;
	private JTextField searchField;
	private JPanel booksPanel = new JPanel();
	APIHandler api = new APIHandler();
	public GeneralUser user;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooksCatalog frame = new ViewBooksCatalog(new GeneralUser("", ""));
					frame.renderPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewBooksCatalog(GeneralUser user) {
		this.user = user;
		setResizable(false);
		setTitle("View Books Catalog - Library Management System v2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 389);
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
		
		JPanel searchPanel = new JPanel();
		contentPane.add(searchPanel, BorderLayout.WEST);
		searchPanel.setLayout(new MigLayout("", "[153px]", "[28px][][]"));
		
		JLabel searchLabel = new JLabel("Search book by name:");
		searchLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		searchPanel.add(searchLabel, "cell 0 0,alignx left,aligny center");
		
		searchField = new JTextField();
		searchField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		searchPanel.add(searchField, "cell 0 1,alignx center,aligny center");
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		searchButton.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\search.png"));
		searchPanel.add(searchButton, "cell 0 2");
		
		
	}
	public void renderPage() {
		ArrayList<Book> books = (ArrayList<Book>) api.fetchBooks();
		ViewBooksCatalog v = this;
		
		for (int i = 0; i < books.size(); i++) {
			JLabel num = new JLabel(i+1 + ".");
			booksPanel.add(num);
			JLabel bookName = new JLabel(books.get(i).title);
			bookName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			bookName.setMaximumSize(new Dimension(450, 14));
			bookName.setMinimumSize(new Dimension(450, 14));
			bookName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			bookName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel sourceLabel = (JLabel) e.getSource();
					Book bookDetails = api.findBookByName(sourceLabel.getText());
					ViewBookDetails viewBookDetails = new ViewBookDetails(bookDetails, user);
					viewBookDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			});
			booksPanel.add(bookName);
		}
		
		JButton nextButton = new JButton("Next");
		nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nextButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		booksPanel.add(nextButton);
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				booksPanel.removeAll();
				v.renderPage();
			}
		});
//		Decreasing all because we have already increased it all in the api.
		JLabel pageLabel;
		if (api.subpage != 0 && api.page >= 2) {
			pageLabel = new JLabel("Page " + ((api.page - 1) + ((api.page - 2) * 3) + (api.subpage-1)));
		} else {
			pageLabel = new JLabel("Page " + ((api.page - 1) + ((api.page - 2) * 3) + 3));
		}
		booksPanel.add(pageLabel);
		booksPanel.revalidate();
		booksPanel.repaint();
		
		contentPane.add(booksPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
