package ch.hftm.notizverwaltung.main;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;

import ch.hftm.notizverwaltung.enumerations.SortDirection;
import ch.hftm.notizverwaltung.interfaces.SaveNoteCallBack;
import ch.hftm.notizverwaltung.note.AllNotes;
import ch.hftm.notizverwaltung.note.Note;
import ch.hftm.notizverwaltung.note.NoteController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable, SaveNoteCallBack {
	private AllNotes allNotes = new AllNotes();
	private SimpleListProperty<Note> noteListProperty = new SimpleListProperty(
			FXCollections.<String> observableArrayList());
	private NoteController noteController;
	// Um den Filter ein und aus zu schalten
	private SimpleBooleanProperty filterBooleanProperty = new SimpleBooleanProperty();
	private SortDirection sortDirection;
	
	// Selection-View-Properties
	@FXML private Button deleteNote;
	@FXML private Button newNote;
	@FXML private ListView<Note> notesList;
	@FXML private BorderPane contentPane;
	@FXML private CheckBox filter;
	@FXML private TextField filterText;
	@FXML private Label noteCounter;
	
	/**
	 * Regelt die Änderungen in der Selektion der Filter-Checkbox
	 * @param event Das Event das ausgelöst wurde
	 */
	@FXML
	void FilterSwitched(ActionEvent event)
	{
		if(filter.isSelected())
		{
			filterText.textProperty().set(filterText.textProperty().get() + "ProvokeEvent");
			filterText.textProperty().set(filterText.textProperty().get().replace("ProvokeEvent", ""));
		}
		else
		{
			filterText.textProperty().set("");
			notesList.itemsProperty().bind(noteListProperty);
		}	
	}

	
	/**
	 * Erstellt eine neue Notiz und fügt diese zur Gesamtliste hinzu.
	 * @param event Das Event das ausgelöst wurde
	 */
	@FXML
	void NewNoteCommand(ActionEvent event) {
		Note note = new Note();
		note.setTitle("Neue Notiz");
		note.setContent("");
		
		// Datum hinzufügen
		note.setDate(new Date());
		// Note in Note-Collection einfügen
		noteListProperty.add(note);
		allNotes.addNote(note);
		setNoteCounter();
	}

	/**
	 * Löst die Löschung der ausgewählten Notiz aus.
	 * @param event Das Event das ausgelöst wurde
	 */
	@FXML
	void DeleteNoteCommand(ActionEvent event) {
		allNotes.removeNote(noteController.getSelectedNote());
		noteListProperty.remove(noteController.getSelectedNote());
		setNoteCounter();
	}
	
	/**
	 * Löst die Absteigende Sortierung der Liste aus, wenn der entsprechende Button geklickt wird.
	 * @param event Das Event das ausgelöst wurde
	 */
	@FXML
	void SortDescendingCommand(ActionEvent event)
	{
		sortDescending();
	}
	
	/**
	 * Löst die Aufsteigende Sortierung der Liste aus, wenn der entsprechende Button geklickt wird.
	 * @param event Das Event das ausgelöst wurde
	 */
	@FXML
	void SortAscendingCommand(ActionEvent event)
	{
		sortAscending();
	}
	
	
	/**
	 * Initialisiert den MainController.
	 */
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{
		loadNoteList();
		setFilterListener();
		setSelectedItemChangeListener();
		loadNoteView();
		notesList.itemsProperty().bind(noteListProperty);
	}

	/**
	 * Lädt sämtliche vorhandene Notizen in die Applikation
	 */
	private void loadNoteList()
	{
		int selectedIndex = notesList.getSelectionModel().getSelectedIndex();
		noteListProperty = new SimpleListProperty(FXCollections.<String> observableArrayList());
		for (Note n : allNotes.loadNotes()) 
			noteListProperty.add(n);
		
		notesList.itemsProperty().bind(noteListProperty);
		notesList.getSelectionModel().select(selectedIndex);

		setNoteCounter();
	}
	
	/**
	 * Lädt die View in der die Notizen angezeigt werden.
	 */
	private void loadNoteView()
	{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();

			BorderPane bp;
			bp = fxmlLoader.load(getClass().getResource("/ch/hftm/notizverwaltung/note/Note.fxml").openStream());

			noteController = fxmlLoader.getController();
			noteController.setParentController(this);

			contentPane.centerProperty().set(bp);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Sortiert die Notizenliste in absteigender Reihenfolge.
	 */
	void sortDescending()
	{
		Note previouslySelectedItem = notesList.getSelectionModel().getSelectedItem();
		
		ObservableList<Note> itemList = notesList.getItems();
		Collections.sort(itemList, new Comparator<Note>()
		{
			@Override
			public int compare(Note n1, Note n2)
			{
				return n1.getTitle().compareTo(n2.getTitle());				
			}
		});
		
		MultipleSelectionModel msm = notesList.getSelectionModel();
		msm.select(previouslySelectedItem);
		msm.select(msm.getSelectedIndex());
		
		sortDirection = SortDirection.Descending;
	}
	
	/**
	 * Sortiert die Notizenliste in aufsteigender Reihenfolge.
	 */
	void sortAscending()
	{
		Note previouslySelectedItem = notesList.getSelectionModel().getSelectedItem();
		
		ObservableList<Note> itemList = notesList.getItems();		
		Collections.sort(itemList, new Comparator<Note>()
		{
			@Override
			public int compare(Note n1, Note n2)
			{
				return n2.getTitle().compareTo(n1.getTitle());				
			}
		});
		
		MultipleSelectionModel msm = notesList.getSelectionModel();
		msm.select(previouslySelectedItem);
		msm.select(msm.getSelectedIndex());
		
		sortDirection = SortDirection.Ascending;
	}	
	
	/**
	 * Setzt den Listener für die Filtertextbox.
	 */
	private void setFilterListener()
	{
		filterText.textProperty().addListener((observable, oldValue, newValue) -> 
		{
			FilteredList<Note> filteredData = new FilteredList<>(noteListProperty, auswahl -> true);
			if(filter.isSelected())
			{
				filteredData.setPredicate(auswahl -> 
				{
					if (newValue == null || newValue.isEmpty() || filterBooleanProperty.getValue().booleanValue()) 
						return true;

					String lowerCaseFilter = newValue.toLowerCase();
	
					if (auswahl.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 || auswahl.getContent().toLowerCase().indexOf(lowerCaseFilter) != -1)
						return true; 
					else
						return false; 
				});

				SimpleListProperty<Note> sortedData = new SimpleListProperty<>(filteredData);
				notesList.itemsProperty().bind(sortedData);
			}
			else
				notesList.itemsProperty().bind(noteListProperty);
		});	
	}
	
	/**
	 * Setzt die Anzeige der Anzahl erfasster Notizen
	 */
	private void setNoteCounter()
	{
		int size = noteListProperty.getSize();
		noteCounter.setText(Integer.toString(size));		
	}
	
	/**
	 * Setzt den Change-Listener für die Notizenliste
	 */
	private void setSelectedItemChangeListener() 
	{
		notesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() 
		{
			@Override
			public void changed(ObservableValue<? extends Note> observable, Note oldValue, Note newValue) 
			{
				if(noteListProperty.get().size() > 0)
				{
					Note selectedNote = notesList.getSelectionModel().getSelectedItem();
					
					// Damit nach dem sortieren keine nullPointerException
					// entsteht
					// Wenn selectedNote==null, dann wird die selection auf
					// die "nullte" note gesetzt.
					if (selectedNote == null) 
						noteController.setSelectedNote(new Note(), true);
					else
						noteController.setSelectedNote(selectedNote, true);
				}
			}
		});
	}

	/**
	 * Überschreibt die saveNoteCallback-Methode aus dem Interface SaveNoteCallBack und löst den Speichervorgang über alle Notizen aus.
	 */
	@Override
	public void saveNoteCallback() 
	{
		Note previouslySelectedItem = notesList.getSelectionModel().getSelectedItem();
		
		allNotes.saveNote(noteController.getSelectedNote());
		loadNoteList();		
		
		//Aufgrund eines Reloads der Notizen wird die Sortierung die vor dem speichern herrschte wiederhergestellt.
		if(sortDirection == sortDirection.Ascending)
			sortAscending();
		if(sortDirection == sortDirection.Descending)
			sortDescending();
		
		if(filter.isSelected())
		{
			filterText.textProperty().set(filterText.textProperty().get() + "ProvokeEvent");
			filterText.textProperty().set(filterText.textProperty().get().replace("ProvokeEvent", ""));
		}
		
		MultipleSelectionModel msm = notesList.getSelectionModel();
		msm.clearSelection();
		msm.select(previouslySelectedItem);
		//NotesList.getSelectionModel().select(previouslySelectedItem);
		
		setNoteCounter();
	}
}