package application.interfaces;

import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;

public interface DialogCallBack {
	void dialogCallBackMessage(Image image);
	void dialogCallBackMessage(Hyperlink url);
}
