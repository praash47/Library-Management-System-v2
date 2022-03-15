package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler extends File {
	public ArrayList<Object> al = new ArrayList<Object>();
	
	public FileHandler(String name) {
		super(name + ".txt");
		al = (ArrayList<Object>) this.readArrayList();
		
//		Initial case when there is no any object in the array.
		if (al == null) { al = new ArrayList<Object>(); }
	}
	public void write(Object o) {
		al.add(o);
		updateFile();
	}
	public Object readArrayList() {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(this));
			ArrayList<Object> readObject = (ArrayList<Object>) inputStream.readObject();
			inputStream.close();
			return readObject;
			
		} catch (FileNotFoundException f) {
			System.out.println("No file found, so creating!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updateFile() {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(this));
			outputStream.writeObject(al);
			outputStream.close();
		} catch (FileNotFoundException f) {
			System.out.println("No file found, so creating!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
