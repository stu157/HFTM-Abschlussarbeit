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
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}	
	
	public ImageModel(String url) 
	{	
		this.imageUrl = url;
		this.image = new Image(url);
		Image imageThumbnail = new Image(url, 100, 100, true, true);
		this.imageView = new ImageView(imageThumbnail);
	}
}

