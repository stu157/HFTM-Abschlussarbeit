package application.main;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import application.interfaces.*;

import application.note.*;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class MainController implements Initializable, CallBack
{ 
	private AllNotes allNotes = new AllNotes();
	private SimpleListProperty<Note> noteListProperty = new SimpleListProperty(FXCollections.<String>observableArrayList());	
	private NoteController noteController;
	
	//Selection-View-Properties 
    @FXML
    private Button DeleteNote;
    @FXML
    private Button NewNote;
    @FXML
    private ListView<Note> NotesList;
    @FXML
    private BorderPane ContentPane;
    
    @FXML
    void NewNoteCommand(ActionEvent event) {
    	Note note = new Note();
    	note.setTitle("Neue Notiz");
    	note.setContent("");
    	// Note in Note-Collection einfügen
    	noteListProperty.add(note);
    	allNotes.addNote(note);
    }

    @FXML
    void DeleteNoteCommand(ActionEvent event) {
    	allNotes.removeNote(noteController.getSelectedNote());
    }

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{		
		for(Note n : allNotes.loadNotes())
		{
			noteListProperty.add(new Note(n));
		}
		NotesList.itemsProperty().bind(noteListProperty);
		//NotesList.setItems(noteListProperty);
		
		
		setSelectedItemChangeListener();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			BorderPane bp;
			bp = fxmlLoader.load(getClass().getResource("/application/note/Note.fxml").openStream()); 
			
			noteController = fxmlLoader.getController();
			noteController.setParentController(this);
			
			ContentPane.centerProperty().set(bp);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setSelectedItemChangeListener()
	{
		NotesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() 
		{
		    @Override
		    public void changed(ObservableValue<? extends Note> observable, Note oldValue, Note newValue) {
		    	Note selectedNote = NotesList.getSelectionModel().getSelectedItem();
		    	noteController.setSelectedNote(selectedNote);
		    }
		});
	}

	@Override
	public void methodToCallBack() 
	{
		allNotes.saveNote(noteController.getSelectedNote());
	}
}