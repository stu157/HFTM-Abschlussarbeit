package ch.hftm.notizverwaltung.image;

import java.io.File;
import java.net.URI;

import ch.hftm.notizverwaltung.interfaces.DialogCallBack;
import ch.hftm.notizverwaltung.note.NoteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImageController 
{
	private DialogCallBack callBack;
	private FileChooser fc = new FileChooser();
	private ImageModel oldImage;
	
    @FXML private Button okButton;
    @FXML private Button abbrechenButton;
    @FXML private Button fileChooserButton;
    @FXML private TextField imagePath;

    /**
	 * Wird ausgel�st, wenn der Benutzer auf den OK-Button klickt.
	 * Schliesst die Ansicht und gibt das ausgew�hlte Bild als ImageModel via Callback an den MainController zur�ck.
	 * @param event ausgel�stes Event
	 */
    @FXML
    void okCommand(ActionEvent event) 
    {
    	sendCallBack();
    	Stage stage = (Stage) abbrechenButton.getScene().getWindow();
		stage.close();
    }

    /**
	 * Wird ausgel�st, wenn der Benutzer auf den abbrechen-Button klickt.
	 * Schliesst die Bild-Auswahl.
	 * @param event ausgel�stes Event
	 */
    @FXML
    void abbrechenCommand(ActionEvent event) {
    	Stage stage = (Stage) abbrechenButton.getScene().getWindow();
		stage.close();
    }
    
    /**
     * �ffnet den Standard-File-Chooser
     * @param event ausgel�stes Event
     */
    @FXML
    void fileChooserCommand(ActionEvent event) {
    	fc.setTitle("Open File");
    	fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(new Stage());
       
        // Exception verhindern wenn kein file geladen wurde.. 
        if(file!=null)
        {
        	 imagePath.setText(file.getPath());
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
    	ImageModel il = new ImageModel("File:" + imagePath.textProperty().get());
        callBack.dialogCallBackMessage(il, oldImage);
    }
}
