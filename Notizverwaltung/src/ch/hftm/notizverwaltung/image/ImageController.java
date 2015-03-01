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
	 * Wird ausgelöst, wenn der Benutzer auf den OK-Button klickt.
	 * Schliesst die Ansicht und gibt das ausgewählte Bild als ImageModel via Callback an den MainController zurück.
	 * @param event ausgelöstes Event
	 */
    @FXML
    void okCommand(ActionEvent event) 
    {
    	sendCallBack();
    	Stage stage = (Stage) abbrechenButton.getScene().getWindow();
		stage.close();
    }

    /**
	 * Wird ausgelöst, wenn der Benutzer auf den abbrechen-Button klickt.
	 * Schliesst die Bild-Auswahl.
	 * @param event ausgelöstes Event
	 */
    @FXML
    void abbrechenCommand(ActionEvent event) {
    	Stage stage = (Stage) abbrechenButton.getScene().getWindow();
		stage.close();
    }
    
    /**
     * Öffnet den Standard-File-Chooser
     * @param event ausgelöstes Event
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
	 * @param controller NoteController, für die Callbacks benötigt.
	 * @param oldImage Alte Instanz des ImageModels die im Falle einer Änderung als Referenz gebraucht wird.
	 */    
	public void initData(NoteController controller, ImageModel oldImage) 
	{
		//Setzt das callBack-Objekt auf den Controller der als Parameter übergeben wird.
		callBack = controller;
		this.oldImage = oldImage;
	}
	
	/**
     * Schickt über das Callback-Objekt vom Interface-Typ DialogCallBack das ausgewählte Bild an die zugewiesene Methode im NoteController.
     * Nebst dem wird das alte ImageModel-Objekt übergeben, um das Update des Bildes durchführen zu können.
     */
    public void sendCallBack() {
    	ImageModel il = new ImageModel("File:" + imagePath.textProperty().get());
        callBack.dialogCallBackMessage(il, oldImage);
    }
}
