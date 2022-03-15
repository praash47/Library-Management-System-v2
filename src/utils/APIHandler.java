package utils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import transactions.*;

public class APIHandler {
	private String allBooksURL = "https://gutendex.com/books/?page=";
	private ArrayList<Book> books = new ArrayList<Book>();
	public int page = 1;
	public int subpage = 0;
	private int PER_PAGE = 8;

	public APIHandler() {}
	
	public ArrayList<Book> fetchBooks() {
		if(subpage == 0) {
			String url = allBooksURL + page;
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
			HttpClient client = HttpClient.newBuilder().build();
			HttpResponse response;
			try {
				System.out.println("Sending request to " + url);
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
				String body = (String) response.body();
				books.addAll(parseBooks(body));
				page++;
				return sliceBooksBySubpage();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (subpage == 3) {
			ArrayList<Book>booksInSubpage = sliceBooksBySubpage();
			subpage = 0;
			return booksInSubpage;
		}
		return sliceBooksBySubpage();
	}
	public ArrayList<Book> sliceBooksBySubpage() {
		int startIndex = (subpage + (page - 2) * 4) * PER_PAGE;
		subpage++;
		return new ArrayList<Book>(books.subList(startIndex, startIndex + PER_PAGE));
	}
	public Object fetchCount() {
		String url = "https://gutendex.com/books/";
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		HttpClient client = HttpClient.newBuilder().build();
		HttpResponse response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String body = (String) response.body();
			JSONObject json = parseJSON(body);
			return json.get("count");
		} catch (Exception e) { return 0; }
	}

	private ArrayList<Book> parseBooks(String body) {
		JSONObject json = parseJSON(body);
		JSONArray apiBooks = (JSONArray) json.get("results");
		Iterator apiBooksIterator = apiBooks.iterator();
		ArrayList<Book> toReturnBooks = new ArrayList<Book>();
		
		while (apiBooksIterator.hasNext()) 
        {
            toReturnBooks.add(parseBook(((Map) apiBooksIterator.next()).entrySet().iterator()));
        }
		return toReturnBooks;
	}
	private Book parseBook(Iterator bookIterator) {
		Hashtable book = new Hashtable();
		while (bookIterator.hasNext()) {
			Map.Entry bookAttribute = (Entry) bookIterator.next();
			book.put(bookAttribute.getKey(), bookAttribute.getValue());
		}
		ArrayList<Author> authors = parseAuthors((JSONArray) book.get("authors"));
		ArrayList<Author> translators = parseAuthors((JSONArray) book.get("translators"));
		
		return new Book((long)book.get("id"), (String)book.get("title"), (ArrayList<String>)book.get("subjects"), authors, translators, (ArrayList<String>)book.get("bookshelves"), (ArrayList<String>)book.get("languages"));
	}

	private ArrayList<Author> parseAuthors(JSONArray jsonAuthors) {
	    // iterating phoneNumbers
        Iterator authorsIterator = jsonAuthors.iterator();
        ArrayList<Author> authors = new ArrayList<Author>();
        while (authorsIterator.hasNext()) 
        {
        	Author author = new Author();
            Iterator authorIterator = ((Map) authorsIterator.next()).entrySet().iterator();
            while (authorIterator.hasNext()) {
                Map.Entry authorAttribute = (Entry) authorIterator.next();
                author.setAttribute((String) authorAttribute.getKey(), authorAttribute.getValue());
            }
            authors.add(author);
        }
		return authors;
	}

	private JSONObject parseJSON(String body) {
		try {
			return (JSONObject) new JSONParser().parse(body);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void main (String arg[]) {
		APIHandler api = new APIHandler();
		api.fetchBooks();
	}

	public Book findBookByName(String bookName) {
		for(Book book: books) {
			if (book.title.equals(bookName)) { return book; }
		}
		return null;
	}
}
