package application.note;

import java.util.ResourceBundle;

import application.interfaces.CallBack;
import application.main.MainController;

import com.sun.glass.ui.Pixels.Format;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class NoteController implements Initializable
{
	private MainController parentController;
	private Note selectedNote;

	public void setParentController(MainController mc)
	{
		if(parentController == null)
			parentController = mc;
	}
	
	public void setSelectedNote(Note newNote)
	{
		selectedNote = newNote;
		noteContent.setText(selectedNote.getContent());
		title.setText(selectedNote.getTitle());
	}
	
	public Note getSelectedNote()
	{
		return selectedNote;
	}
	
	//ChangeListener für TextArea noteContent
	private void setContentChangeListener()
	{
		noteContent.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				selectedNote.setContent(noteContent.getText());
				//parentController.saveChanges();
				
		        CallBack callBack = parentController;
		        register(callBack);
			}
		});
	}
	
	public void register(CallBack callback) {
        callback.methodToCallBack();
    }
	
	@FXML
	private Button newImage;
	@FXML
	private Button newUrl;
	@FXML
    private ListView<Hyperlink> noteUrlList;
    @FXML
    private TextArea noteContent;
    @FXML
    private TextField title;
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
		setContentChangeListener();
	}
}
