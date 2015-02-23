package application.path;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageController {

	FileChooser fc = new FileChooser();
	
    @FXML
    private Button OkButton;

    @FXML
    private Button AbbrechenButton;

    @FXML
    void OkCommand(ActionEvent event) {
    	
    }

    @FXML
    void AbbrechenCommand(ActionEvent event) {
    	Stage stage = (Stage) AbbrechenButton.getScene().getWindow();
		stage.close();
    }

}
