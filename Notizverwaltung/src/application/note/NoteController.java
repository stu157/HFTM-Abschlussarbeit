package application.note;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;

public class NoteController implements Initializable 
{
	private Note selectedNote;
	
	public void setSelectedNote(Note newNote)
	{
		selectedNote = newNote;
		
		title.textProperty().bind(selectedNote.getTitle());
		noteContent.textProperty().bind(selectedNote.getContent());
	}
	
	public Note getSelectedNote()
	{
		return selectedNote;
	}
	
	@FXML
    private ListView<Hyperlink> noteUrlList;
    @FXML
    private TextArea noteContent;
    @FXML
    private Label title;
    @FXML
    private ListView<Image> noteImagesList;
    
    public String getContent()
    {
    	return noteContent.getText();
    }
    
    public String getTitle()
    {
    	return title.textProperty().get();
    }
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{
		
	}
}
