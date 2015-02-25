package application.path;

import application.interfaces.DialogCallBack;
import application.interfaces.SaveNoteCallBack;
import application.note.NoteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageController 
{
	private DialogCallBack callBack;
	FileChooser fc = new FileChooser();
	
    @FXML
    private Button OkButton;

    @FXML
    private Button AbbrechenButton;

    @FXML
    void OkCommand(ActionEvent event) 
    {
    	sendCallBack();
    	Stage stage = (Stage) AbbrechenButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void AbbrechenCommand(ActionEvent event) {
    	Stage stage = (Stage) AbbrechenButton.getScene().getWindow();
		stage.close();
    }

	public void initData(NoteController controller) 
	{
		//Setzt das callBack-Objekt auf den Controller der als Parameter übergeben wird.
		callBack = controller;
	}
	
	//Schickt über das Callback-Objekt vom Interface-Typ DialogCallBack die eingegebene URL an die zugewiesene Methode.
    public void sendCallBack() {
    	Image i = new Image("Random-URL");
        callBack.dialogCallBackMessage(i);
    }
}
