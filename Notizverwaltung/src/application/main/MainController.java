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
    	noteController.getSelectedNote().setTitle("dsglsndg");
    }

    @FXML
    void DeleteNoteCommand(ActionEvent event) {

    }

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		Note n = new Note();
		n.setTitle("sdlgjh");
		noteListProperty.add(n);
		n = new Note();
		n.setTitle("sdfhsdfh");
		noteListProperty.add(n);
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
		
		Note no = new Note();
		no.setTitle("First Node");
		no.setContent("CONTENT TEXT BLABLABLA");
		allNotes.addNote(no);
		allNotes.saveNotes();
		
		allNotes.loadNote();
	}
}