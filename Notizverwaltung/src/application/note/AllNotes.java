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
	private final String USERPATH = System.getProperty("user.home")
			+ "\\AppData\\Local\\Notizverwaltung\\";
	List<Note> allAvailableNotes = new ArrayList<>();

	/**
	 * Gibt alle erfassten Notizen wider
	 * @return Liste aller gespeicherten Notizen
	 */
	public List<Note> getAllAvailableNotes() 
	{
		return allAvailableNotes;
	}

	/**
	 * F�gt die im Parameter �bergebene Notiz zu der kompletten Notiz-Liste hinzu
	 * @param n neue Notiz
	 */
	public void addNote(Note n) 
	{
		allAvailableNotes.add(n);
		saveNotes();
	}

	/**
	 * Entfernt die im Parameter �bergebene Notiz aus der Notiz-Liste 
	 * @param noteToRemove zu l�schende Notiz
	 */
	public void removeNote(Note noteToRemove) 
	{
		for(Note note : allAvailableNotes)
		{
			if(note.getId().equals(noteToRemove.getId()))
				noteToRemove = note;
		}
		allAvailableNotes.remove(noteToRemove);
		saveNotes();
	}

	/**
	 * Speichert den aktuellen Stand der Notiz-Liste.
	 */
	public void saveNotes() {
		try {
			OutputStream output = new FileOutputStream(USERPATH + "notes.dat");
			ObjectOutputStream oos = new ObjectOutputStream(output);
			oos.writeObject(allAvailableNotes);
			oos.close();
			output.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * �bernimmt gemachte �nderungen einer Notiz anhand der Id und speichert diese �nderungen dann auf der Harddisk ab.
	 * @param newNote ge�nderte Notiz
	 */
	public void saveNote(Note newNote) {
		for (Note n : allAvailableNotes) {
			if (n.getId().equals(newNote.getId())) {
				n.setContent(newNote.getContent());
				n.setTitle(newNote.getTitle());
				n.setDate(newNote.getDate());
				n.setImages(newNote.getImages());
				n.setUrls(newNote.getUrls());
			}
		}
		saveNotes();
	}

	/**
	 * L�dt s�mtliche Notizen aus dem Applikationsorder, f�gt diese der Notiz-Liste hinzu und gibt sie als Liste zur�ck
	 * @return Vollst�ndige Liste der erfassten Notizen.
	 */
	public List<Note> loadNotes() {

		try {
			allAvailableNotes.clear();
			InputStream input = new FileInputStream(USERPATH + "notes.dat");
			ObjectInputStream ois = new ObjectInputStream(input);
			Object o = ois.readObject();
			allAvailableNotes.addAll((List<Note>) o);
			ois.close();
			input.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return allAvailableNotes;
	}
}
