package application.image;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Test

public class ImageModel implements Serializable
{
	private String imageUrl;
	private Image image;
	private ImageView imageView;
	
	/**
	 * Gibt die Bild-Url der ImageModel-Instanz zurück
	 * @return Bild-Url der ImageModel-Instanz
	 */
	public String getImageUrl() 
	{
		return imageUrl;
	}

	/**
	 * Setzt in der aktuellen ImageModel-Instanz die Bild-Url die im Parameter übergeben wird
	 * @param imageUrl neue Bild-Url
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Gibt das Image-Objekt der ImageModel-Instanz zurück
	 * @return Image-Instanz der ImageModel-Instanz
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Setzt in der aktuellen ImageModel-Instanz das Image-Objekt das im Parameter übergeben wird
	 * @param image neue Image-Instanz
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Gibt das ImageView-Objekt der ImageModel-Instanz zurück
	 * @return ImageView-Instanz der ImageModel-Instanz
	 */
	public ImageView getImageView() {
		return imageView;
	}

	/**
	 * Setzt in der aktuellen ImageModel-Instanz das ImageView-Objekt das im Parameter übergeben wird
	 * @param imageView neue ImageView-Instanz
	 */
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}	
	
	/**
	 * erzeugt eine neue ImageModel-Instanz auf Basis der im Parameter übergebenen Url
	 * @param url Url der neuen ImageModel-Instanz
	 */
	public ImageModel(String url) 
	{	
		this.imageUrl = url;
		this.image = new Image(url);
		Image imageThumbnail = new Image(url, 100, 100, true, true);
		this.imageView = new ImageView(imageThumbnail);
	}
}

