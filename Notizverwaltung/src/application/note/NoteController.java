package application.note;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NoteController implements Initializable {
	private Note selectedNote;

	public void setSelectedNote(Note newNote) {
		selectedNote = newNote;

		title.textProperty().bind(selectedNote.getTitle());

		// Mit dieser Zeile kann der Content nicht mehr bearbeitet werden
		// noteContent.textProperty().bind(selectedNote.getContent());

		// Obere durch diese Zeile ersetzt
		noteContent.setText(selectedNote.getContent().getValue());

	}

	public Note getSelectedNote() {
		return selectedNote;
	}

	@FXML
	private Button newImage;
	@FXML
	private Button newUrl;
	@FXML
	private ListView<Hyperlink> noteUrlList;
	@FXML
	private TextArea noteContent;
	@FXML
	private TextField title;
	@FXML
	private ListView<Image> noteImagesList;

	public String getContent() {
		return noteContent.getText();
	}

	public String getTitle() {
		return title.textProperty().get();
	}

	@FXML
	void newImageCommand(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load((getClass()
					.getResource("/application/path/Image.fxml")));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Neues Bild hinzufügen");
			// Setzt das ProgrammIcon
			stage.getIcons().add(
					new Image("application/images/Icon.png"));

			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void newUrlCommand(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load((getClass()
					.getResource("/application/url/Url.fxml")));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Neuer URL hinzufügen");
			// Setzt das ProgrammIcon
			stage.getIcons().add(
					new Image("application/images/Icon.png"));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// ChangeListener für TextArea noteContent
		// Schreibt den geänderten Text in die Aktulle Note
		noteContent.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				selectedNote.setContent(noteContent.getText());
			}
		});
	}
}
