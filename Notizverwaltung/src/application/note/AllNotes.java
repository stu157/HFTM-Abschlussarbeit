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
	 * Fügt die im Parameter übergebene Notiz zu der kompletten Notiz-Liste hinzu
	 * @param n neue Notiz
	 */
	public void addNote(Note n) 
	{
		allAvailableNotes.add(n);
		saveNotes();
	}

	/**
	 * Entfernt die im Parameter übergebene Notiz aus der Notiz-Liste 
	 * @param noteToRemove zu löschende Notiz
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
	 * übernimmt gemachte Änderungen einer Notiz anhand der Id und speichert diese Änderungen dann auf der Harddisk ab.
	 * @param newNote geänderte Notiz
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
	 * Lädt sämtliche Notizen aus dem Applikationsorder, fügt diese der Notiz-Liste hinzu und gibt sie als Liste zurück
	 * @return Vollständige Liste der erfassten Notizen.
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
