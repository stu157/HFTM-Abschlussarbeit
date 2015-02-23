package application.note;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Note implements Serializable{
	private String content;
	private String title;
	private List<Hyperlink> urls;
	private List<Image> images;
	private UUID id;
	
	public Note()
	{
		urls = new ArrayList<Hyperlink>();
		images = new ArrayList<Image>();
		id = UUID.randomUUID();
	}
	
	public Note(Note n)
	{
		content = n.getContent();
		title = n.getTitle();
		urls = n.getUrls();
		images = n.getImages();
		id = n.getId();
	}
	
	public String getContent()
	{
		return content;
	}
	
	public String getTitle()
	{
		return title;		
	}
	
	public List<Hyperlink> getUrls()
	{
		return urls;		
	}
	
	public List<Image> getImages()
	{
		return images;		
	}
	
	public UUID getId()
	{
		return id;
	}
	
	public void setContent(String newContent)
	{
		content = newContent;
	}
	
	public void setTitle(String newTitle)
	{
		title = newTitle;		
	}
	
	public void setUrls(List<Hyperlink> newUrlSet)
	{
		urls = newUrlSet;		
	}
	
	public void setImages(List<Image> newImageSet)
	{
		images = newImageSet;		
	}
	
	public void addUrl(Hyperlink newHyperlink)
	{
		urls.add(newHyperlink);		
	}
	
	public void addImage(Image newImage)
	{
		images.add(newImage);
	}
	
	public void removeUrl(Hyperlink hyperlink)
	{
		urls.remove(hyperlink);		
	}
	
	public void removeImage(Image image)
	{
		images.remove(image);
	}
	
	@Override
	public String toString()
	{
		return title + " - " + content;
	}
}
