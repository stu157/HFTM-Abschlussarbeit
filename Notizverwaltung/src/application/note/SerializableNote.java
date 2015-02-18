package application.note;

import java.io.Serializable;
import java.util.List;

import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;

public class SerializableNote implements Serializable{
	private String content;
	private String title;
	private List<Hyperlink> urls;
	private List<Image> images;
	
	public SerializableNote(Note n)
	{
		content = n.getContent().get();
		title = n.getTitle().get();
		urls = n.getUrls();
		images = n.getImages();
	}
	
	public List<Image> getImages() {
		return images;
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public List<Hyperlink> getUrls() {
		return urls;
	}
	
	public void setUrls(List<Hyperlink> urls) {
		this.urls = urls;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
