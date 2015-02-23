package application.main;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		checkSingleInstance();

		Settings setting = new Settings();
		setting.loadSettings();

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass()
					.getResource("Main.fxml"));
			Scene scene = new Scene(root, 700, 400);

			primaryStage.minHeightProperty().set(425);
			primaryStage.minWidthProperty().set(320);

			// Setzt Titel
			primaryStage.setTitle("Notizverwaltung");
			
			// Setzt das ProgrammIcon
			primaryStage.getIcons().add(
					new Image("file:///" + System.getProperty("user.home")
							+ "\\Desktop\\" + "Icon.png"));

			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setWidth(setting.getWidth());
			primaryStage.setHeight(setting.getHeight());
			primaryStage.setX(setting.getPositinbX());
			primaryStage.setY(setting.getPositinbY());

			// Wird ausgeführt wenn Fenster geschlossen wird!
			scene.getWindow().setOnCloseRequest(
					new EventHandler<WindowEvent>() {
						public void handle(WindowEvent ev) {
							setting.setHeight(primaryStage.getHeight());
							setting.setWidth(primaryStage.getWidth());
							setting.setPositinbX(primaryStage.getX());
							setting.setPositinbY(primaryStage.getY());
							setting.saveSettings();
						}
					});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

	private void checkSingleInstance() {
		final File file = new File("flag");
		RandomAccessFile randomAccessFile;
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			final FileLock fileLock = randomAccessFile.getChannel().tryLock();

			if (fileLock == null) {
				Platform.exit();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
