package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class KazancController {

	private Pane anaPencere;

	@FXML
	private TextField kazancText;

	private Dialog<ButtonType> kazancDialog;

	private Controller controller;

	int toplamHasilat = 0;

	public void toplamHasılat(int toplamHasilat2) throws IOException {
		kazancText.setText(String.valueOf(toplamHasilat2));
		kazancText.setEditable(false);

	}

	public void setDialog(Dialog<ButtonType> dialogStage) {
		this.kazancDialog = dialogStage;

	}

	public void setAna(Controller controller2) {
		this.controller = controller2;

	}

}
