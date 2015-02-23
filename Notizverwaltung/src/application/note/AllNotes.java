package application.note;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllNotes implements Serializable {
	
	private final String USERPATH = System.getProperty("user.home") + "\\Desktop\\";
	List<SerializableNote> allAvailableNotes = new ArrayList<>();
	
	public List<SerializableNote> getAllAvailableNotes()
	{
		return allAvailableNotes;		
	}
	
	public void addNote(SerializableNote n){
		allAvailableNotes.add(n);
	}
	
	public void addNote(Note n)
	{
		allAvailableNotes.add(new SerializableNote(n));		
	}
	
	
	
	public void removeNote(SerializableNote n){
		// Wird nur geloescht wenn vorhanden ist
		if(allAvailableNotes.contains(n) == true)
			allAvailableNotes.remove(n);
	}
	
	public void saveNotes(){
		
		try {
			OutputStream output = new FileOutputStream(USERPATH + "notes.dat");
			ObjectOutputStream oos = new ObjectOutputStream(output);
			oos.writeObject(allAvailableNotes);
			oos.close();
			output.close();
		} catch (Exception e) {
			e.getMessage();
		} 
	}
	
	public void saveNote(Note n)
	{
		
	}
	
	public List<SerializableNote> loadNotes(){
		
		try {
			InputStream input = new FileInputStream(USERPATH + "notes.dat");
			ObjectInputStream ois = new ObjectInputStream(input);
			Object o = ois.readObject();
			allAvailableNotes.addAll((List<SerializableNote>)o);
			ois.close();
			input.close();
		} catch (Exception e) {
			e.getMessage();
		} 
		
		return allAvailableNotes;
	}
}
