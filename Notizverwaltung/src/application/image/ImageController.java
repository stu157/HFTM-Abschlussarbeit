package application.image;

import java.io.File;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import application.interfaces.DialogCallBack;
import application.note.NoteController;

public class ImageController 
{
	private DialogCallBack callBack;
	FileChooser fc = new FileChooser();
	ImageModel oldImage;
	
    @FXML
    private Button OkButton;

    @FXML
    private Button AbbrechenButton;
    
    @FXML
    private Button FileChooserButton;

    @FXML
    private TextField ImagePath;

    /**
	 * Wird ausgel�st, wenn der Benutzer auf den OK-Button klickt.
	 * Schliesst die Ansicht und gibt das ausgew�hlte Bild als ImageModel via Callback an den MainController zur�ck.
	 * @param event ausgel�stes Event
	 */
    @FXML
    void OkCommand(ActionEvent event) 
    {
    	sendCallBack();
    	Stage stage = (Stage) AbbrechenButton.getScene().getWindow();
		stage.close();
    }

    /**
	 * Wird ausgel�st, wenn der Benutzer auf den abbrechen-Button klickt.
	 * Schliesst die Bild-Auswahl.
	 * @param event ausgel�stes Event
	 */
    @FXML
    void AbbrechenCommand(ActionEvent event) {
    	Stage stage = (Stage) AbbrechenButton.getScene().getWindow();
		stage.close();
    }
    
    /**
     * �ffnet den Standard-File-Chooser
     * @param event ausgel�stes Event
     */
    @FXML
    void FileChooserCommand(ActionEvent event) {
    	fc.setTitle("Open File");
    	fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(new Stage());
       
        // Exception verhindern wenn kein file geladen wurde.. 
        if(file!=null)
        {
        	 ImagePath.setText(file.getPath());
        } 
    }

    /**
	 * Nimmt externe Objekte entgegen die in dieser Instanz gebraucht werden. 
	 * @param controller NoteController, f�r die Callbacks ben�tigt.
	 * @param oldImage Alte Instanz des ImageModels die im Falle einer �nderung als Referenz gebraucht wird.
	 */    
	public void initData(NoteController controller, ImageModel oldImage) 
	{
		//Setzt das callBack-Objekt auf den Controller der als Parameter �bergeben wird.
		callBack = controller;
		this.oldImage = oldImage;
	}
	
	/**
     * Schickt �ber das Callback-Objekt vom Interface-Typ DialogCallBack das ausgew�hlte Bild an die zugewiesene Methode im NoteController.
     * Nebst dem wird das alte ImageModel-Objekt �bergeben, um das Update des Bildes durchf�hren zu k�nnen.
     */
    public void sendCallBack() {
    	ImageModel il = new ImageModel("File:" + ImagePath.textProperty().get());
        callBack.dialogCallBackMessage(il, oldImage);
    }
}
