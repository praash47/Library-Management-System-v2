package librarian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import generaluser.GeneralUser;
import transactions.Issue;
import transactions.Issue.IssueStatus;
import transactions.Sale;
import transactions.Sale.SaleStatus;
import utils.FileHandler;
import windows.InfoDialog;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;

public class PrintBill extends JFrame {

	private JPanel contentPane;
	JPanel split = new JPanel();
	Librarian librarian;
	JPanel pendingBillPrintRequests;
	JPanel clientCheckoutPayment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintBill frame = new PrintBill(new Librarian("" , ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrintBill(Librarian librarian) {
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
		
		JPanel printBillPanel = new JPanel();
		contentPane.add(printBillPanel, BorderLayout.CENTER);
		printBillPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel printBillHeader = new JLabel("Print Bill");
		printBillHeader.setIcon(new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\print.png"));
		printBillHeader.setFont(new Font("Yu Gothic UI", Font.PLAIN, 27));
		printBillPanel.add(printBillHeader, BorderLayout.NORTH);
		
		printBillPanel.add(split, BorderLayout.CENTER);
		split.setLayout(new BorderLayout(0, 0));
		
		pendingBillPrintRequests = new JPanel();
		split.add(pendingBillPrintRequests, BorderLayout.WEST);
		
		JLabel pendingBillPrintRequestsLabel = new JLabel("Pending Bill Print Requests");
		pendingBillPrintRequestsLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		pendingBillPrintRequests.add(pendingBillPrintRequestsLabel);
		
		clientCheckoutPayment = new JPanel();
		split.add(clientCheckoutPayment, BorderLayout.CENTER);
		
		JLabel clientCheckoutPaymentLabel = new JLabel("Client Checkout and Payment");
		clientCheckoutPaymentLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		clientCheckoutPayment.add(clientCheckoutPaymentLabel);
		
		checkStatus();
		
		this.setVisible(true);
	}
	public void checkStatus() {
		FileHandler saleFile = new FileHandler("sales");
		FileHandler generalUserFile = new FileHandler("generaluser");
		ArrayList<Object> existingSales = saleFile.al;
		ArrayList<Object> generalUsers = generalUserFile.al;
		for (Object gu: generalUsers) {
			GeneralUser generalUser = (GeneralUser) gu;
			JPanel pendingBillRequestPerson = new JPanel();
			pendingBillRequestPerson.setBackground(new Color(240, 255, 255));
			pendingBillPrintRequests.add(pendingBillRequestPerson);
			JPanel clientCheckout = new JPanel();
			clientCheckout.setBackground(new Color(240, 255, 255));
			clientCheckoutPayment.add(clientCheckout);

			pendingBillPrintRequests.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			clientCheckoutPayment.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel personName = new JLabel(generalUser.username + ":");
			personName.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			pendingBillRequestPerson.add(personName);
			JLabel clientName = new JLabel (generalUser.username + ":");
			clientName.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			clientCheckout.add(clientName);
			
			for (Object s: existingSales) {
				Sale sale = (Sale) s;
				if (sale.status == SaleStatus.PENDINGBILLPRINT && sale.purchaser.username.equals(generalUser.username)) {
					JPanel pendingRequest = new JPanel();
					pendingRequest.setBackground(new Color(230, 230, 250));
					pendingBillPrintRequests.add(pendingRequest);
					
					JButton acceptButton = new JButton("Accept");
					acceptButton.setIcon(null);
					acceptButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
					acceptButton.setForeground(new Color(34, 139, 34));
					pendingRequest.add(acceptButton);
					
					acceptButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							printXPSBill(sale);
							sale.status = SaleStatus.BILLPRINTED;
							saleFile.updateFile();
							JButton sourceButton = (JButton) e.getSource();
							pendingBillPrintRequests.remove(sourceButton.getParent());
							pendingBillPrintRequests.revalidate();
							pendingBillPrintRequests.repaint();
						}
					});
				} else if ((sale.status == SaleStatus.ADDEDTOCART || sale.status == SaleStatus.PENDINGPAYMENT
						|| sale.status == SaleStatus.PAID) && sale.purchaser.username.equals(generalUser.username)) {
					updateClientCheckout(sale, clientCheckout, saleFile);
				}
			}	
		}
	}

	private void updateClientCheckout(Sale sale, JPanel clientCheckout, FileHandler saleFile) {
		JButton actionButton = null;
		switch(sale.status) {
		case ADDEDTOCART:
			actionButton = new JButton("Checkout");
			actionButton.setIcon(null);
			actionButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			actionButton.setForeground(new Color(34, 139, 34));
			clientCheckout.add(actionButton);
			actionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sale.status = SaleStatus.PENDINGPAYMENT;
					saleFile.updateFile();
					JButton sourceButton = (JButton) e.getSource();
					clientCheckout.remove(sourceButton);
					clientCheckout.revalidate();
					clientCheckout.repaint();
					updateClientCheckout(sale, clientCheckout, saleFile);
				}
			});
			break;
		case PENDINGPAYMENT:
			actionButton = new JButton("Pay");
			actionButton.setIcon(null);
			actionButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			actionButton.setForeground(new Color(34, 139, 34));
			clientCheckout.add(actionButton);
			actionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sale.status = SaleStatus.PAID;
					saleFile.updateFile();
					JButton sourceButton = (JButton) e.getSource();
					clientCheckout.remove(sourceButton);
					clientCheckout.revalidate();
					clientCheckout.repaint();
					updateClientCheckout(sale, clientCheckout, saleFile);
				}
			});
			break;
		case PAID:
			actionButton = new JButton("Print Bill");
			actionButton.setIcon(null);
			actionButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			actionButton.setForeground(new Color(34, 139, 34));
			clientCheckout.add(actionButton);
			actionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					printXPSBill(sale);
					sale.status = SaleStatus.BILLPRINTED;
					saleFile.updateFile();
					JButton sourceButton = (JButton) e.getSource();
					clientCheckout.remove(sourceButton);
					clientCheckout.revalidate();
					clientCheckout.repaint();
					updateClientCheckout(sale, clientCheckout, saleFile);
				}
			});
			break;
		}
	}

	protected void printXPSBill(Sale sale) {
		PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(sale.books, sale.totalAmount),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
	}
	
//	PRINT ESSENTIALS
	public PageFormat getPageFormat(PrinterJob pj)
	{
	    
	    PageFormat pf = pj.defaultPage();
	    Paper paper = pf.getPaper();    

	    double middleHeight =8.0;  
	    double headerHeight = 2.0;                  
	    double footerHeight = 2.0;                  
	    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
	    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
	    paper.setSize(width, height);
	    paper.setImageableArea(                    
	        0,
	        10,
	        width,            
	        height - convert_CM_To_PPI(1)
	    );   //define boarder size    after that print area width is about 180 points
	            
	    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
	    pf.setPaper(paper);    

	    return pf;
	}
	    
	protected static double convert_CM_To_PPI(double cm) {            
		        return toPPI(cm * 0.393600787);            
	}
	 
	protected static double toPPI(double inch) {            
		        return inch * 72d;            
	}
}