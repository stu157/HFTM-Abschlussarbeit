package application.main;

import java.util.ResourceBundle;

import application.note.Note;
import application.note.NoteController;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MainController implements Initializable
{ 
	private SimpleListProperty<NoteController> noteListProperty = new SimpleListProperty(FXCollections.<String>observableArrayList());
	private SimpleListProperty<String> noteUrlListProperty = new SimpleListProperty(FXCollections.<String>observableArrayList());
	private SimpleListProperty<String> noteImagesListProperty = new SimpleListProperty(FXCollections.<String>observableArrayList());
	private StringProperty titleProperty;
	
	//Selection-View-Properties
    @FXML
    private Button DeleteNote;
    @FXML
    private Button NewNote;
    @FXML
    private ListView<NoteController> NotesList;
    
    //Note-Properties
    @FXML
    private ListView<Hyperlink> NoteUrlList;
    @FXML
    private TextArea NoteContent;
    @FXML
    private Label NoteTitle;
    @FXML
    private ListView<Image> NoteImagesList;
    
    @FXML
    void OpenNote(ActionEvent event) {
    	
    }
    
    @FXML
    void NewNoteCommand(ActionEvent event) {

    }

    @FXML
    void DeleteNoteCommand(ActionEvent event) {

    }

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		noteListProperty.add(new NoteController());
		NotesList.setItems(noteListProperty);		
	}
}