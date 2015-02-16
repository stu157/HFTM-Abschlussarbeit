package application.note;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class NoteController implements Initializable 
{
	//Bla
	private Note selectedNote;
	
	public void setSelectedNote(Note newNote)
	{
		selectedNote = newNote;
	}
	
	public Note getSelectedNote()
	{
		return selectedNote;
	}
	
	@FXML
    private ListView<Hyperlink> NoteUrlList;
    @FXML
    private TextArea NoteContent;
    @FXML
    private Label Title;
    @FXML
    private ListView<Image> NoteImagesList;
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{
		if(selectedNote == null)
			selectedNote = new Note();
		
		Title.textProperty().bind(selectedNote.getTitle());
		
		BorderPane bp = new BorderPane();
	}
}
