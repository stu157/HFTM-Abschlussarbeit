package application.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.swing.text.Position;

import application.note.SerializableNote;

public class Settings implements Serializable {

	private final String USERPATH = System.getProperty("user.home")
			+ "\\Desktop\\";
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

	public void saveSettings() {

		try {
			OutputStream output = new FileOutputStream(USERPATH + "notes.dat");
			ObjectOutputStream oos = new ObjectOutputStream(output);
			oos.writeObject(height);
			oos.writeObject(width);
			oos.writeObject(positionX);
			oos.writeObject(positionY);
			oos.close();
			output.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void loadSettings() {
		try {
			InputStream input = new FileInputStream(USERPATH + "notes.dat");
			ObjectInputStream ois = new ObjectInputStream(input);
			height = (Double) ois.readObject();
			width = (Double) ois.readObject();
			positionX = (Double) ois.readObject();
			positionY = (Double) ois.readObject();
			ois.close();
			input.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
