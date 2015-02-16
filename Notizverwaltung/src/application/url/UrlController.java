package application.url;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UrlController {

	private StringProperty UrlProperty;

	@FXML
	private Button OkButton;

	@FXML
	private Button AbbrechenButton;

	@FXML
	private TextField TextEingabe;

	@FXML
	void OkCommand(ActionEvent event) {
		UrlProperty.set(TextEingabe.getText());
	}

	@FXML
	void AbbrechenCommand(ActionEvent event) {
		Stage stage = (Stage) AbbrechenButton.getScene().getWindow();
		stage.close();
	}

}