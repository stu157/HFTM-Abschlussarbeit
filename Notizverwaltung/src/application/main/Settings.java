package application.main;

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

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getPositinbX() {
		return positionX;
	}

	public void setPositinbX(Double positinbX) {
		this.positionX = positinbX;
	}

	public Double getPositinbY() {
		return positionY;
	}

	public void setPositinbY(Double positinbY) {
		this.positionY = positinbY;
	}

	public void createProjectFolder(String folder)
	{
		File file = new File(folder);

		if(!file.exists())
		{
			file.mkdir();
		}		
	}
	
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
