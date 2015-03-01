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

	public List<Note> getAllAvailableNotes() {
		return allAvailableNotes;
	}

	public void addNote(Note n) {
		allAvailableNotes.add(n);
		saveNotes();
	}

	public void removeNote(Note noteToRemove) {
		for(Note note : allAvailableNotes)
		{
			if(note.getId().equals(noteToRemove.getId()))
				noteToRemove = note;
		}
		allAvailableNotes.remove(noteToRemove);
		saveNotes();
	}

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

	public void saveNote(Note newNote) {
		for (Note n : allAvailableNotes) {
			if (n.getId().equals(newNote.getId())) {
				n.setContent(newNote.getContent());
				n.setTitle(newNote.getTitle());
				n.setImages(newNote.getImages());
				n.setUrls(newNote.getUrls());
			}
		}
		saveNotes();
	}

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
