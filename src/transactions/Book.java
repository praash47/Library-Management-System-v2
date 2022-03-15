package transactions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Book implements Serializable {
	public long id;
	public String title;
	public ArrayList<String> subjects;
	public ArrayList<Author> authors;
	public ArrayList<Author> translators;
	public ArrayList<String> bookshelves;
	public ArrayList<String> languages;
    private int minCost = 100;
    private int maxCost = 3000;
    public double cost = Math.random() * ( maxCost - minCost );
	
	public Book(long id, String title, ArrayList<String> subjects, ArrayList<Author> authors, ArrayList<Author> translators, ArrayList<String> bookshelves, ArrayList<String> languages) {
		this.id = id;
		this.title = title;
		this.subjects = subjects;
		this.authors = authors;
		this.translators = translators;
		this.bookshelves = bookshelves;
		this.languages = languages;
	}
}