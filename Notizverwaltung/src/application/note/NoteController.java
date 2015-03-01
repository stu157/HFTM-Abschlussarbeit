package application.note;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.image.ImageController;
import application.image.ImageModel;
import application.interfaces.*;
import application.main.MainController;
import application.url.UrlController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NoteController implements Initializable, DialogCallBack
{
	private MainController parentController;
	private Note selectedNote;
	
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
	private ListView<ImageView> noteImagesList;
	
	@FXML
	void newImageCommand(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane ap = loader.load(getClass().getResource("/application/image/Image.fxml").openStream());
			
			ImageController controller = (ImageController)loader.getController(); 
			
			controller.initData(this);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(ap));
			stage.setTitle("Neues Bild hinzufügen");
			
			// Setzt das ProgrammIcon
			stage.getIcons().add(new Image("application/images/Icon.png"));
			stage.setResizable(false);
			stage.show();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void newUrlCommand(ActionEvent event) 
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane ap = loader.load(getClass().getResource("/application/url/Url.fxml").openStream());
			
			UrlController controller = (UrlController)loader.getController(); 
			controller.initData(this);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(ap));
			stage.setTitle("Neuer URL hinzufügen");
			
			// Setzt das ProgrammIcon
			stage.getIcons().add(new Image("application/images/Icon.png"));
			stage.setResizable(false);
			stage.show();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void setParentController(MainController mc)
	{
		if(parentController == null)
			parentController = mc;
	}
	
	private boolean selectionChanged = false;
	public void setSelectedNote(Note newNote, boolean sc) {
		selectionChanged = sc;
		
		selectedNote = newNote;
		noteContent.setText(selectedNote.getContent());
		title.setText(selectedNote.getTitle());
		
		noteImagesList.getItems().clear();
		List<String> imageUrls = selectedNote.getImages();
		for(String s : imageUrls)
		{
			noteImagesList.itemsProperty().get().add(new ImageView(new Image(s, 100, 100, true, true)));		
		}
		
		noteUrlList.getItems().clear();
		List<String> urls = selectedNote.getUrls();
		for(String hl : urls)
		{
			noteUrlList.itemsProperty().get().add(new Hyperlink(hl));		
		}
		
		selectionChanged = false;
	}

	public Note getSelectedNote() {
		return selectedNote;
	}

	public String getContent() {
		return noteContent.getText();
	}

	public String getTitle() {
		return title.textProperty().get();
	}

	public void register(SaveNoteCallBack callback) {
        callback.saveNoteCallback();
    }		

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		setContentChangeListener();
		setTitleChangeListener();
	}

	//Diese Methode stammt aus dem Interface DialogCallBack und wird aus dem UrlController aufgerufen, wenn das Link-Fenster bestätigt wird.
	@Override
	public void dialogCallBackMessage(Hyperlink link) 
	{
		ObservableList<Hyperlink> itemList = noteUrlList.getItems();
		itemList.add(link);
		noteUrlList.setItems(itemList);
		
		selectedNote.addUrl(link.getText());
		
		SaveNoteCallBack callBack = parentController;
		register(callBack);
	}

	//Diese Methode stammt aus dem Interface DialogCallBack und wird aus dem ImageController aufgerufen, wenn das Image-Fenster bestätigt wird.
	@Override
	public void dialogCallBackMessage(ImageModel image) 
	{
		ObservableList<ImageView> imageList = noteImagesList.getItems();
		imageList.add(image.getImageView());
		noteImagesList.setItems(imageList);
		
		selectedNote.addImage(image.getImageUrl());
		
		SaveNoteCallBack callBack = parentController;
		register(callBack);
	}
	
	//ChangeListener für TextArea noteContent
	private void setContentChangeListener()
	{
		noteContent.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if(selectionChanged)
					return;
					
				selectedNote.setContent(noteContent.getText());
				//parentController.saveChanges();
				
				SaveNoteCallBack callBack = parentController;
		        register(callBack);
			}
		});
	}
	
	//ChangeListener für TextArea Title
	private void setTitleChangeListener()
	{
		title.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if(selectionChanged)
					return;
				
				selectedNote.setTitle(title.getText());
				//parentController.saveChanges();
				
				SaveNoteCallBack callBack = parentController;
		        register(callBack);
			}
		});
	}
}
