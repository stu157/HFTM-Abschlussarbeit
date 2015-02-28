package application.main;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import application.interfaces.SaveNoteCallBack;
import application.note.AllNotes;
import application.note.Note;
import application.note.NoteController;

public class MainController implements Initializable, SaveNoteCallBack {
	private AllNotes allNotes = new AllNotes();
	private SimpleListProperty<Note> noteListProperty = new SimpleListProperty(
			FXCollections.<String> observableArrayList());
	private NoteController noteController;
	// Um den Filter ein und aus zu schalten
	private SimpleBooleanProperty filterBooleanProperty = new SimpleBooleanProperty();

	// Selection-View-Properties
	@FXML private Button DeleteNote;
	@FXML private Button NewNote;
	@FXML private ListView<Note> NotesList;
	@FXML private BorderPane ContentPane;
	@FXML private CheckBox Filter;
	@FXML private TextField FilterText;
	
	@FXML
	void FilterSwitched(ActionEvent event)
	{
		if(Filter.isSelected())
		{
			FilterText.textProperty().set(FilterText.textProperty().get() + "ProvokeEvent");
			FilterText.textProperty().set(FilterText.textProperty().get().replace("ProvokeEvent", ""));
		}
		else
		{
			FilterText.textProperty().set("");
			NotesList.itemsProperty().bind(noteListProperty);
		}	
	}

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
		
	}

	@FXML
	void DeleteNoteCommand(ActionEvent event) {
		allNotes.removeNote(noteController.getSelectedNote());
		noteListProperty.remove(noteController.getSelectedNote());
	}
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{
		for (Note n : allNotes.loadNotes()) {
			noteListProperty.add(new Note(n));
		}

		setFilterListener();
		setSelectedItemChangeListener();
		loadNoteView();
		NotesList.itemsProperty().bind(noteListProperty);
	}

	private void loadNoteView()
	{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();

			BorderPane bp;
			bp = fxmlLoader.load(getClass().getResource("/application/note/Note.fxml").openStream());

			noteController = fxmlLoader.getController();
			noteController.setParentController(this);

			ContentPane.centerProperty().set(bp);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void setFilterListener()
	{
		FilteredList<Note> filteredData = new FilteredList<>(noteListProperty, auswahl -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		FilterText.textProperty().addListener((observable, oldValue, newValue) -> 
		{
			if(Filter.isSelected())
			{
				filteredData.setPredicate(auswahl -> 
				{
	
				// zeigt alles an
					if (newValue == null || newValue.isEmpty() || filterBooleanProperty.getValue().booleanValue()) 
						return true;
					// compare with filter text
					String lowerCaseFilter = newValue.toLowerCase();
	
					if (auswahl.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1)
						return true; // Filter matches
					else
						return false; // Does not match
				});

				SimpleListProperty<Note> sortedData = new SimpleListProperty<>(filteredData);
				NotesList.itemsProperty().bind(sortedData);
			}
			else
				NotesList.itemsProperty().bind(noteListProperty);
		});	
	}
	
	
	private void setSelectedItemChangeListener() {
		NotesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() 
		{
			@Override
			public void changed(ObservableValue<? extends Note> observable, Note oldValue, Note newValue) 
			{
				Note selectedNote = NotesList.getSelectionModel().getSelectedItem();
	
				// Damit nach dem sortieren keine nullPointerException
				// entsteht
				// Wenn selectedNote==null, dann wird die selection auf
				// die "nullte" note gesetzt.
				if (selectedNote == null) 
					noteController.setSelectedNote(new Note());
				else
					noteController.setSelectedNote(selectedNote);
			}
		});
	}

	@Override
	public void saveNoteCallback() {
		allNotes.saveNote(noteController.getSelectedNote());
	}
}