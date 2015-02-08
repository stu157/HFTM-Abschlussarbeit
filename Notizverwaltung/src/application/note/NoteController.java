package application.note;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class NoteController extends BorderPane implements Initializable {
	private SimpleListProperty<String> noteUrlListProperty = new SimpleListProperty(FXCollections.<String>observableArrayList());
	private SimpleListProperty<String> noteImagesListProperty = new SimpleListProperty(FXCollections.<String>observableArrayList());
	private StringProperty titleProperty;
	
    @FXML
    private ListView<Hyperlink> NoteUrlList;

    @FXML
    private TextArea NoteContent;
    @FXML
    private Label Title;
    @FXML
    private ListView<Image> NoteImagesList;

    public NoteController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Note.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) 
	{
		Title.textProperty().bind(titleProperty);
		titleProperty.set("asf");
	}
	
	@Override
	public String toString()
	{
		return titleProperty == null ? "asdf" : titleProperty.get();
	}
}
