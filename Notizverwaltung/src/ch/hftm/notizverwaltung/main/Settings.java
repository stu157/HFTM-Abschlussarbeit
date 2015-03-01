package ch.hftm.notizverwaltung.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.text.Position;

public class Settings implements Serializable 
{
	private final String USERPATH = System.getProperty("user.home")+"\\AppData\\Local\\Notizverwaltung\\";
	private Double height = 0.0;
	private Double width = 0.0;
	private Double positionX = 0.0;
	private Double positionY = 0.0;

	/**
	 * Gibt die gespeicherte Höhe des Fensters zurück.
	 * @return Gespeicherte Höhe des Fensters zurück.
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * Setzt die aktuelle Höhe des Fensters
	 * @param height Neue Höhe des Fensters
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * Gibt die gespeicherte Breite des Fensters zurück.
	 * @return Gespeicherte Breite des Fensters zurück.
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * Setzt die aktuelle Breite des Fensters
	 * @param width neue Breite des Fensters
	 */
	public void setWidth(Double width) {
		this.width = width;
	}

	/**
	 * Gibt die aktuelle Position auf der X-Achse zurück
	 * @return aktuelle Position auf der X-Achse
	 */
	public Double getPositinbX() {
		return positionX;
	}

	/**
	 * Setzt die neue Position des Fensters auf der X-Achse
	 * @param positinbX neue Position auf der X-Achse
	 */
	public void setPositinbX(Double positinbX) {
		this.positionX = positinbX;
	}

	/**
	 * Gibt die aktuelle Position auf der Y-Achse zurück
	 * @return aktuelle Position auf der Y-Achse
	 */
	public Double getPositinbY() {
		return positionY;
	}

	/**
	 * Setzt die neue Position des Fensters auf der Y-Achse
	 * @param positinbY die neue Position des Fensters auf der Y-Achse
	 */
	public void setPositinbY(Double positinbY) {
		this.positionY = positinbY;
	}

	/**
	 * Erstellt den Applikationsordner im Ordner AppData, wenn dieser noch nicht exisitert
	 * @param folder Der Ordnerpfad des zu erstellenden Ordners
	 */
	public void createProjectFolder(String folder)
	{
		File file = new File(folder);

		if(!file.exists())
		{
			file.mkdir();
		}		
	}
	
	/**
	 * Speichert die eingestellten Eigenschaften aus dieser Klasse in eine Datei im Applikationsordner
	 */
	public void saveSettings() {

		try 
		{
			OutputStream output = new FileOutputStream(USERPATH + "settings.dat");
			ObjectOutputStream oos = new ObjectOutputStream(output);
			oos.writeObject(height);
			oos.writeObject(width);
			oos.writeObject(positionX);
			oos.writeObject(positionY);
			oos.close();
			output.close();
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
	}

	/**
	 * Lädt die zuletzt gespeicherten Einstellungen aus dem Applikationsordner
	 */
	public void loadSettings() 
	{
		createProjectFolder(USERPATH);
		
		try 
		{
			InputStream input = new FileInputStream(USERPATH + "settings.dat");
			ObjectInputStream ois = new ObjectInputStream(input);
			height = (Double) ois.readObject();
			width = (Double) ois.readObject();
			positionX = (Double) ois.readObject();
			positionY = (Double) ois.readObject();
			ois.close();
			input.close();
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
}
