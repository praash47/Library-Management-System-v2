package librarian;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;

import transactions.Book;

public class BillPrintable implements Printable {
	private ArrayList<Book> books = new ArrayList<Book>();
	private double totalAmount;
	
	BillPrintable(ArrayList<Book> books, double totalAmount) {
		this.books = books;
		this.totalAmount = totalAmount;
	}
	
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		int result = NO_SUCH_PAGE;    
    	if (pageIndex == 0) {                       
		    Graphics2D g2d = (Graphics2D) graphics;                    
		
		    double width = pageFormat.getImageableWidth();                    
		   
		    g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 
		
		        
		    FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
			int idLength=metrics.stringWidth("000");
			int amtLength=metrics.stringWidth("000000");
			int qtyLength=metrics.stringWidth("00000");
			int priceLength=metrics.stringWidth("000000");
			int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;
			
			int productPosition = 0;
			int discountPosition= prodLength+5;
			int pricePosition = discountPosition +idLength+10;
			int qtyPosition=pricePosition + priceLength + 4;
			int amtPosition=qtyPosition + qtyLength;
			      
			try{
			    /*Draw Header*/
			    int y=20;
			    int yShift = 10;
			    int headerRectHeight=15;
			    int headerRectHeighta=40;
			        
			    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
			    g2d.drawString("-------------------------------------",12,y);y+=yShift;
			    g2d.drawString("           LMSv2 Bill Receipt        ",12,y);y+=yShift;
			    g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
			  
			    g2d.drawString("-------------------------------------",10,y);y+=yShift;
			    g2d.drawString(" Book Name                   Price   ",10,y);y+=yShift;
			    g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
			    for (Book book: this.books) {
			    	String cost = String.format("%.2f", book.cost);
			    	String title = book.title;
			    	if (title.length() > (37-7-3)) { title = title.substring(0, 37-7-3); }
			    	String toDrawString = " ";
			    	toDrawString += title;
			    	for(int i=0;i < (34 - title.length() - cost.length()); i++) {
			    		toDrawString += " ";
			    	}
			    	toDrawString += cost;
			    	toDrawString += "  ";
			    	g2d.drawString(toDrawString,10,y);y+=yShift;
			    }
			    g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
			    String totalAmount = String.format("%.2f", this.totalAmount);
			    String toDrawString = " Total Amount: ";
			    toDrawString += totalAmount;
			    for(int i=0; i<(22 - totalAmount.length()); i++) {
			    	toDrawString += " ";
			    }
			    g2d.drawString(toDrawString,10,y);y+=yShift;
			    g2d.drawString("*************************************",10,y);y+=yShift;
			    g2d.drawString("   THANKS FOR PURCHASING THE BOOKS!  ",10,y);y+=yShift;
			    g2d.drawString("*************************************",10,y);y+=yShift;
			    g2d.drawString("              READ WELL!             ",10,y);y+=yShift;
			    g2d.drawString("*************************************",10,y);y+=yShift;
			} catch(Exception r){
				r.printStackTrace();
			}
			result = PAGE_EXISTS;    
    	}    
	  return result;
  }
}