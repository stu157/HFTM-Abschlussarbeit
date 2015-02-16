package application.note;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AllNotes implements Serializable {
	
	List<Note> allNotes = new ArrayList<>();
	
	public void addNote(Note n){
		allNotes.add(n);
	}
	
	public void removeNote(Note n){
		// Wird nur geloescht wenn vorhanden ist
		if(allNotes.contains(n)==true)
			allNotes.remove(n);
	}
	
	public void saveNote(Note n){
		
		try {
			OutputStream output = new FileOutputStream("notes.dat");
			ObjectOutputStream oos = new ObjectOutputStream(output);
			oos.writeObject(allNotes);
			oos.close();
			output.close();
		} catch (Exception e) {
			e.getMessage();
		} 
	}
	
	public void loadNote(){
		
		try {
			InputStream input = new FileInputStream("notes.dat");
			ObjectInputStream ois = new ObjectInputStream(input);
			allNotes.addAll((Collection<Note>)ois.readObject());
			ois.close();
			input.close();
		} catch (Exception e) {
			e.getMessage();
		} 
	}
}
