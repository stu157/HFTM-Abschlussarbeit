package application.main;

import java.io.IOException;
import java.util.ResourceBundle;

import application.note.Note;
import application.note.NoteController;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MainController implements Initializable
{ 
	private SimpleListProperty<Note> noteListProperty = new SimpleListProperty(FXCollections.<String>observableArrayList());	
	
	private NoteController noteController;
	
	//Selection-View-Properties 
    @FXML
    private Button DeleteNote;
    @FXML
    private Button NewNote;
    @FXML
    private ListView<String> NotesList;
    //Note-Properties
    private ListView<Note> NotesList;
    @FXML
    private BorderPane ContentPane;
    
    @FXML
    void NewNoteCommand(ActionEvent event) {
    	titleProperty.set("asflH");
    	noteController.getSelectedNote().setTitle("dsglsndg");
    }

    @FXML
    void DeleteNoteCommand(ActionEvent event) {

    }

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		noteListProperty.add("asf");
		NotesList.setItems(noteListProperty);	
		
		 titleProperty = NoteTitle.textProperty();
		 titleProperty.set("asdfasdf");
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
	}
}