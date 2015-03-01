package ch.hftm.notizverwaltung.note;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ch.hftm.notizverwaltung.image.ImageModel;
import ch.hftm.notizverwaltung.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Note implements Serializable{
	private String content;
	private String title;
	private Date date;
	private List<String> urls;
	private List<String> images;
	private UUID id;
	
	/**
	 * Erstellt eine leere Notiz
	 */
	public Note()
	{
		urls = new ArrayList<String>();
		images = new ArrayList<String>();
		id = UUID.randomUUID();
	}
	
	/**
	 * Gibt den Content der aktuellen Instanz zurück
	 * @return Content der Notiz-Instanz
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Gibt den Titel der aktuellen Instanz zurück
	 * @return Titel der Notiz-Instanz
	 */
	public String getTitle()
	{
		return title;		
	}
	
	/**
	 * Gibt die Url-Liste der aktuellen Instanz zurück
	 * @return Url-Liste der Notiz-Instanz
	 */
	public List<String> getUrls()
	{		
		return urls;		
	}
	
	/**
	 * Gibt Bild-Liste der aktuellen Instanz zurück
	 * @return Bild-Liste der Notiz-Instanz
	 */
	public List<String> getImages()
	{
		return images;		
	}
	
	/**
	 * Gibt die Id der aktuellen Instanz zurück
	 * @return Id der Notiz-Instanz
	 */
	public UUID getId()
	{
		return id;
	}

	/**
	 * Gibt das gesetzte Datum der Instanz zurück, ist dies null, wird ein neues Datum generiert.	
	 * @return aktuelles Datum
	 */
	public Date getDate() {
		return date == null ? new Date() : date;
	}
	
	/**
	 * Setzt den Content der aktuellen Instanz.
	 * @param newContent neuer Content
	 */
	public void setContent(String newContent)
	{
		content = newContent;
	}
	
	/**
	 * Setzt den Titel der aktuellen Instanz.
	 * @param newTitle neuer Titel
	 */
	public void setTitle(String newTitle)
	{
		title = newTitle;		
	}
	
	/**
	 * Setzt die Url-List der aktuellen Instanz.
	 * @param newUrlSet neue Url-Liste
	 */
	public void setUrls(List<String> newUrlSet)
	{
		urls = newUrlSet;		
	}
	
	/**
	 * Setzt die Bild-Liste der aktuellen Instanz.
	 * @param newImageSet neue Bild-Liste
	 */
	public void setImages(List<String> newImageSet)
	{
		images = newImageSet;		
	}
	
	/**
	 * Setzt in der Notiz-Instanz das im Paramter übergebene Datum
	 * @param date neues Datum
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Fügt der Notiz-Instanz einen neuen Link hinzu
	 * @param newHyperlink neuer Link
	 */
	public void addUrl(String newHyperlink)
	{
		urls.add(newHyperlink);		
	}
	
	/**
	 * Fügt der Notiz-Instanz die im Parameter übergebene Bild-Url hinzu.
	 * @param newImage neuer Link
	 */
	public void addImage(String newImage)
	{
		images.add(newImage);
	}
	
	/**
	 * Entfernt den Link aus der Notiz-Instanz
	 * @param hyperlink zu entfernender Link
	 */
	public void removeUrl(String hyperlink)
	{
		urls.remove(hyperlink);		
	}
	
	/**
	 * Entfernt die im Parameter überegebene Bild-Url 
	 * @param image zu entfernende Bild-Url
	 */
	public void removeImage(String image)
	{
		images.remove(image);
	}

	/**
	 * Überschreibt die toString-Methode und bereitet einen Text auf, der die Notiz-Instanz widerspiegelt
	 */
	@Override
	public String toString()
	{
		if(content.toString().length() > 20)
		{
			return title + " - " + content.toString().replaceAll("\n", " ").substring(0, 20);
		}
		
		return title + " - " + content.replaceAll("\n", " ");
	}
}
