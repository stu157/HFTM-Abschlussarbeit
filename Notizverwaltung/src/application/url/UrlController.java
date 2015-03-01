package application.url;

import application.interfaces.DialogCallBack;
import application.note.NoteController;
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
	private Button OkButton;

	@FXML
	private Button AbbrechenButton;

	@FXML
	private TextField TextEingabe;

	@FXML
	void OkCommand(ActionEvent event) 
	{
		sendCallBack();
		Stage stage = (Stage) AbbrechenButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void AbbrechenCommand(ActionEvent event) 
	{
		Stage stage = (Stage) AbbrechenButton.getScene().getWindow();
		stage.close();
	}
	
	public void initData(NoteController controller, Hyperlink oldLink){
		//Setzt das callBack-Objekt auf den Controller der als Parameter übergeben wird.
    	callBack = controller;
    	this.oldLink = oldLink;
	}

    //Schickt über das Callback-Objekt vom Interface-Typ DialogCallBack das ausgewählte Bild an die zugewiesene Methode.
    public void sendCallBack() {
        callBack.dialogCallBackMessage(new Hyperlink(TextEingabe.getText()), oldLink);
    }

}