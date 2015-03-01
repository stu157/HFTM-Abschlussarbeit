package application.note;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.image.ImageController;
import application.image.ImageModel;
import application.interfaces.*;
import application.main.Main;
import application.main.MainController;
import application.url.UrlController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NoteController implements Initializable, DialogCallBack 
{
	SaveNoteCallBack callBack;
	private MainController parentController;
	private Note selectedNote;
	private boolean selectionChanged = false;
	private List<ImageModel> imageModelList = new ArrayList<ImageModel>();
	
	@FXML private Button newImage;
	@FXML private Button newUrl;
	@FXML private ListView<Hyperlink> noteUrlList;
	@FXML private TextArea noteContent;
	@FXML private TextField title;
	@FXML private ListView<ImageView> noteImagesList;
	@FXML private Label date;
	@FXML private Button deleteImage;
	@FXML private Button modifyImage;
	@FXML private Button modifyUrl;
	@FXML private Button deleteUrl;
	
	@FXML
	void newImageCommand(ActionEvent event) 
	{
		loadImageSelection(null);
	}
	
	@FXML
	void newUrlCommand(ActionEvent event) 
	{
		loadLinkSelection(null);
	}
	
	@FXML
	void linkClicked(MouseEvent event)
	{
		if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
            	Hyperlink hl = noteUrlList.getSelectionModel().getSelectedItem();
        		Main.windowsHostServices.showDocument(hl.getText());
            }
        }
	}
	
	void loadLinkSelection(Hyperlink hl)
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane ap = loader.load(getClass().getResource("/application/url/Url.fxml").openStream());
			
			UrlController controller = (UrlController)loader.getController(); 
			controller.initData(this, hl);
			
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
	@FXML
	void deleteImageCommand(Event event)
	{
		ImageView iv = noteImagesList.getSelectionModel().getSelectedItem();
		
		ObservableList<ImageView> imageList = noteImagesList.getItems();
		imageList.remove(iv);
		noteImagesList.setItems(imageList);
		
		selectedNote.removeImage(getImageModel(iv).getImageUrl());
		imageModelList.remove(iv);
		
		register(callBack);
	}
	@FXML
	void modifyImageCommand(Event event)
	{
		ImageView iv = noteImagesList.getSelectionModel().getSelectedItem();
		ImageModel im = getImageModel(iv);
		
		loadImageSelection(im);				
	}
	@FXML
	void modifyUrlCommand(Event event)
	{
		Hyperlink hl = noteUrlList.getSelectionModel().getSelectedItem();
		loadLinkSelection(hl);
	}
	@FXML
	void deleteUrlCommand(Event event)
	{
		Hyperlink hl = noteUrlList.getSelectionModel().getSelectedItem();
		
		ObservableList<Hyperlink> linkList = noteUrlList.getItems();
		linkList.remove(hl);
		noteUrlList.setItems(linkList);
		
		selectedNote.removeUrl(hl.getText());
		register(callBack);		
	}
	
	ImageModel getImageModel(ImageView iv)
	{
		for(ImageModel im : imageModelList)
		{
			if(im.getImageView().equals(iv))
			{
				return im;
			}
		}	
		
		return null;
	}


	void loadImageSelection(ImageModel im)
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane ap = loader.load(getClass().getResource("/application/image/Image.fxml").openStream());
			
			ImageController controller = (ImageController)loader.getController(); 
			
			controller.initData(this, im);
			
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

	public void setParentController(MainController mc)
	{
		if(parentController == null)
		{
			parentController = mc;
			callBack = parentController;
		}
	}
	
	public void setSelectedNote(Note newNote, boolean sc) {
		selectionChanged = sc;
		
		selectedNote = newNote;
		noteContent.setText(selectedNote.getContent());
		title.setText(selectedNote.getTitle());
		
		noteImagesList.getItems().clear();
		List<String> imageUrls = selectedNote.getImages();
		for(String s : imageUrls)
		{
			ImageModel im = new ImageModel(s);
			imageModelList.add(im);
			noteImagesList.itemsProperty().get().add(im.getImageView());		
		}
		
		noteUrlList.getItems().clear();
		List<String> urls = selectedNote.getUrls();
		
		for(String hlString : urls)
		{
			Hyperlink hl = new Hyperlink(hlString);
			
			hl.setOnAction(new EventHandler<ActionEvent>() 
			{
	            @Override
	            public void handle(ActionEvent t) {
	            	Main.windowsHostServices.showDocument(hl.getText());
	            }
	        });
			noteUrlList.itemsProperty().get().add(hl);
		}
		
		selectionChanged = false;
		date.setText(newNote.getDate().toString());
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
	public void dialogCallBackMessage(Hyperlink link, Hyperlink oldLink) 
	{
		ObservableList<Hyperlink> itemList = noteUrlList.getItems();
		
		if(oldLink == null)
		{
			itemList.add(link);
			noteUrlList.setItems(itemList);
			
			selectedNote.addUrl(link.getText());
			
			register(callBack);
		}
		else
		{
			itemList.remove(oldLink);
			itemList.add(link);
			noteUrlList.setItems(itemList);
			
			selectedNote.removeUrl(oldLink.getText());
			selectedNote.addUrl(link.getText());
			
			register(callBack);
		}
	}

	//Diese Methode stammt aus dem Interface DialogCallBack und wird aus dem ImageController aufgerufen, wenn das Image-Fenster bestätigt wird.
	@Override
	public void dialogCallBackMessage(ImageModel image, ImageModel oldImage) 
	{
		ObservableList<ImageView> imageList = noteImagesList.getItems();
		
		if(oldImage == null)
		{
			imageList.add(image.getImageView());
			noteImagesList.setItems(imageList);
			imageModelList.add(image);
			
			selectedNote.addImage(image.getImageUrl());
			
			register(callBack);
		}
		else
		{
			for(ImageView iv : imageList)
			{
				if(iv.equals(oldImage.getImageView()))
				{
					iv = image.getImageView();
					noteImagesList.setItems(imageList);
					imageModelList.remove(oldImage);
					imageModelList.add(image);
					
					selectedNote.removeImage(oldImage.getImageUrl());
					selectedNote.addImage(image.getImageUrl());
					
					register(callBack);
					return;
				}
			}
		}
	}
	
	//ChangeListener für TextArea noteContent
	private void setContentChangeListener()
	{
		noteContent.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{				
				if(selectionChanged || selectedNote == null)
					return;
					
				selectedNote.setContent(noteContent.getText());

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
				if(selectionChanged || selectedNote == null)
					return;
				
				selectedNote.setTitle(title.getText());
				
		        register(callBack);
			}
		});
	}
}
