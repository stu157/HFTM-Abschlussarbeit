package ch.hftm.notizverwaltung.url;

import ch.hftm.notizverwaltung.interfaces.DialogCallBack;
import ch.hftm.notizverwaltung.note.NoteController;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UrlController 
{
	private DialogCallBack callBack;
	private Hyperlink oldLink;
	
	@FXML
	private Button okButton;

	@FXML
	private Button abbrechenButton;

	@FXML
	private TextField textEingabe;

	/**
	 * Wird ausgelöst, wenn der Benutzer auf den OK-Button klickt.
	 * Schliesst die Ansicht und gibt die eingegebene Url als Callback an den MainController zurück.
	 * @param event ausgelöstes Event
	 */
	@FXML
	void OkCommand(ActionEvent event) 
	{
		sendCallBack();
		Stage stage = (Stage) abbrechenButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * Wird ausgelöst, wenn der Benutzer auf den abbrechen-Button klickt.
	 * Schliesst die Url-Eingabe.
	 * @param event ausgelöstes Event
	 */
	@FXML
	void AbbrechenCommand(ActionEvent event) 
	{
		Stage stage = (Stage) abbrechenButton.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Nimmt externe Objekte entgegen die in dieser Instanz gebraucht werden. 
	 * @param controller NoteController, für die Callbacks benötigt.
	 * @param oldLink Alter Link der im Falle einer Änderung als Referenz gebraucht wird.
	 */
	public void initData(NoteController controller, Hyperlink oldLink)
	{
		//Setzt das callBack-Objekt auf den Controller der als Parameter übergeben wird.
    	callBack = controller;
    	this.oldLink = oldLink;
	}

	
    /**
     * Schickt über das Callback-Objekt vom Interface-Typ DialogCallBack die eingegebene Url an die zugewiesene Methode im NoteController.
     * Nebst dem wird der alte Link übergeben, um das Update des Links durchführen zu können.
     */
    public void sendCallBack() 
    {
        callBack.dialogCallBackMessage(new Hyperlink(textEingabe.getText()), oldLink);
    }

}