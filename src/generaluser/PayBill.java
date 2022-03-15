package generaluser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import transactions.Book;
import transactions.Issue;
import transactions.Issue.IssueStatus;
import transactions.Sale;
import transactions.Sale.SaleStatus;
import utils.FileHandler;

import javax.swing.JScrollPane;

public class PayBill extends JFrame {

	private JPanel contentPane;
	GeneralUser generaluser;
	private JTable table;
	JPanel payBillPane = new JPanel();
	JPanel saleHeader;
	JPanel salePanel;
	SaleStatus currentStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayBill frame = new PayBill(new GeneralUser("", ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayBill(GeneralUser generaluser) {
		this.generaluser = generaluser;
		setTitle("My Carts - Library Management System v2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
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
		
		contentPane.add(payBillPane, BorderLayout.CENTER);
		payBillPane.setLayout(new BorderLayout(0, 0));
		
		JLabel payBillHeader = new JLabel("My Carts");
		payBillHeader.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\cart.png"));
		payBillHeader.setFont(new Font("Yu Gothic UI", Font.PLAIN, 27));
		payBillPane.add(payBillHeader, BorderLayout.NORTH);
		

		FileHandler saleFile = new FileHandler("sales");
		ArrayList<Object> existingSales = saleFile.al;
		
		for (Object s: existingSales) {
			Sale sale = (Sale) s;
			if (sale.purchaser.username.equals(generaluser.username)) {
				salePanel = new JPanel();
				salePanel.setLayout(new BorderLayout(0, 0));
				
				saleHeader = new JPanel();
				
				updateCartStatus(sale);
				
				String[] columns = {
				        "Book Name", "Cost", "Delete"
			    };
			    // Create the table model
			    DefaultTableModel model = new DefaultTableModel(columns, 0);
				
				table = new JTable(model);
				table.getColumnModel().getColumn(0).setPreferredWidth(275);
			    table.getColumnModel().getColumn(1).setPreferredWidth(75);
			    table.getColumnModel().getColumn(2).setPreferredWidth(75);
			    table.setName(sale.id.toString());

				table.addMouseListener(new MouseAdapter() {
				  public void mouseClicked(MouseEvent e) {
				      JTable target = (JTable)e.getSource();
				      int row = target.getSelectedRow();
				      int column = target.getSelectedColumn();
				      if (row >= 1 && column == 2) {
				    	  deleteBook(target.getName(), (String)target.getModel().getValueAt(row, 0), saleFile, model);
				      }
				  }
				});
				salePanel.add(table, BorderLayout.CENTER);
				
				updateTableModel(model, saleFile, sale);
				payBillPane.add(salePanel, BorderLayout.CENTER);
			}
		}
		
		
		JLabel note = new JLabel("Note: Single Click at the Actions to perform selected action.");
		note.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		payBillPane.add(note, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	private void updateCartStatus(Sale sale) {
		saleHeader.removeAll();
		saleHeader.revalidate();
		JLabel cartStatusLabel = new JLabel("Cart Status: " + sale.getStatusName());
		cartStatusLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		saleHeader.add(cartStatusLabel);
		JFrame window = this;
		
		if (sale.status == SaleStatus.ADDEDTOCART) {
			JButton checkoutSale = new JButton("Checkout");
			checkoutSale.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\checkout.png"));
			checkoutSale.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			
			checkoutSale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(window,"Are you sure to checkout?", "Confirm Checkout?",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
		            if(result == JOptionPane.YES_OPTION){
		            	proceedSale(sale, "checkout");
		            }
				}
			});
			
			saleHeader.add(checkoutSale);
		} else if (sale.status == SaleStatus.PENDINGPAYMENT) {
			JButton payButton = new JButton("Pay");
			payButton.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\bill.png"));
			payButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			saleHeader.add(payButton);
			
			payButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(window,"Are you sure to pay?", "Confirm payming?",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
		            if(result == JOptionPane.YES_OPTION){
		            	proceedSale(sale, "payment");
		            }
				}
			});
		} else if (sale.status == SaleStatus.PAID) {
			JButton requestBillPrint = new JButton("Request Bill Print");
			requestBillPrint.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\bill.png"));
			requestBillPrint.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			saleHeader.add(requestBillPrint);
			
			requestBillPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					proceedSale(sale, "requestbillprint");
				}
			});
		}
		saleHeader.revalidate();
		saleHeader.repaint();
		salePanel.add(saleHeader, BorderLayout.NORTH);
	}

	void updateTableModel(DefaultTableModel model, FileHandler saleFile, Sale sale) {
		String[] columns = {
		        "Book Name", "Cost", "Delete"
	    };
		model.setRowCount(0);
	    model.addRow(columns);
		for (Book book: sale.books) {
			model.addRow(new Object[] {
				book.title,
				String.format("%.2f", book.cost),
				"Delete"
			});
		}
		model.addRow(new Object[] {
			"Total Amount:",
			String.format("%.2f", sale.totalAmount),
			""
		});
	}

	private void deleteBook(String saleID, String bookName, FileHandler saleFile, DefaultTableModel model) {
		if ((!(currentStatus == SaleStatus.PENDINGPAYMENT) 
			&& !(currentStatus == SaleStatus.PAID)
			&& !(currentStatus == SaleStatus.PENDINGBILLPRINT)
			&& !(currentStatus == SaleStatus.BILLPRINTED)
			)) {
			ArrayList<Object> existingSales = saleFile.al;
			for (Object s: existingSales) {
				Sale sale = (Sale) s;
				Book bookToDelete = null;
				String id = sale.id.toString();
				if (id.equals(saleID) && sale.status == SaleStatus.ADDEDTOCART) {
					for (Book book: sale.books) {
						if (book.title.equals(bookName)) {
							bookToDelete = book;
							sale.totalAmount -= book.cost;
						}
					}
					sale.books.remove(bookToDelete);
				}
				if (sale.books.size() == 0 ) {
					sale.status = SaleStatus.CARTEMPTY;
					updateCartStatus(sale);
				}
				updateTableModel(model, saleFile, sale);
			}
			saleFile.updateFile();
			model.fireTableDataChanged();
		}
	}
	private void proceedSale(Sale sale, String operation) {
	FileHandler saleFile = new FileHandler("sales");
	ArrayList<Object> existingSales = saleFile.al;
			
	for (Object s: existingSales) {
		Sale fileSale = (Sale) s;
		String id = fileSale.id.toString();
		if (id.equals(sale.id.toString())) {
			switch(operation) {
			case "checkout":				
				fileSale.status = SaleStatus.PENDINGPAYMENT;
				currentStatus = SaleStatus.PENDINGPAYMENT;
				break;
			case "payment":
				fileSale.status = SaleStatus.PAID;
				currentStatus = SaleStatus.PAID;
				break;
			case "requestbillprint":
				fileSale.status = SaleStatus.PENDINGBILLPRINT;
				currentStatus = SaleStatus.PENDINGBILLPRINT;
				break;	
			}
			updateCartStatus(fileSale);
		}
	}
	saleFile.updateFile();
	}

}
