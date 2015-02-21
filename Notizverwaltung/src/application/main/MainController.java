package application.main;

import java.io.IOException;
import java.util.ResourceBundle;

import application.note.*;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainController implements Initializable
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
    	
    }

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{		
		for(SerializableNote sn : allNotes.loadNotes())
		{
			noteListProperty.add(new Note(sn));
		}
		NotesList.setItems(noteListProperty);	
		
		NotesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() {
		    @Override
		    public void changed(ObservableValue<? extends Note> observable, Note oldValue, Note newValue) {
		    	noteController.setSelectedNote(NotesList.getSelectionModel().getSelectedItem());
		    }
		});
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			BorderPane bp;
			bp = fxmlLoader.load(getClass().getResource("/application/note/Note.fxml").openStream()); 
			noteController = fxmlLoader.getController();
			
			ContentPane.centerProperty().set(bp);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}