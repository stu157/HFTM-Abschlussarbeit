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
	
    @FXML
    private Button OkButton;

    @FXML
    private Button AbbrechenButton;
    
    @FXML
    private Button FileChooserButton;

    @FXML
    private TextField ImagePath;

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

	public void initData(NoteController controller) 
	{
		//Setzt das callBack-Objekt auf den Controller der als Parameter übergeben wird.
		callBack = controller;
	}
	
	//Schickt über das Callback-Objekt vom Interface-Typ DialogCallBack die eingegebene URL an die zugewiesene Methode.
    public void sendCallBack() {
    	ImageModel il = new ImageModel("File:" + ImagePath.textProperty().get());
        callBack.dialogCallBackMessage(il);
    }
}
