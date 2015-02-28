package application.interfaces;

import application.image.ImageModel;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;

public interface DialogCallBack {
	void dialogCallBackMessage(ImageModel imageUrl);
	void dialogCallBackMessage(Hyperlink url);
}
