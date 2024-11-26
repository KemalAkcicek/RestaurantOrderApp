package application;

import java.io.IOException;
import java.lang.runtime.TemplateRuntime;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class Controller {

	@FXML
	private Pane anaPencere;

	private Dialog<ButtonType> dialogStage;

	private Dialog<ButtonType> dialogStage2;

	@FXML
	private JFXListView<AktifSiparisler> aktifSiparisList;

	@FXML
	private JFXListView<AktifSiparisler> teslimEdilenSiparisler;

	@FXML
	private TextField kazancText;

	ObservableList<AktifSiparisler> aktifSiparis = FXCollections.observableArrayList();
	ObservableList<AktifSiparisler> teslimEdilen = FXCollections.observableArrayList();

	int toplamHasilat = 0;

	public void setHasılat(int hasilat) {

		this.toplamHasilat += hasilat;

	}

	public void setAktifSiparis(ObservableList<AktifSiparisler> aktifYemekler) {
		aktifSiparis.addAll(aktifYemekler);
		setListViewAktifSiparis();

	}

	public void setListViewAktifSiparis() {
		if (aktifSiparisList == null) {
			System.out.println(aktifSiparis);
		} else {
			// CellFactory sayesinde listedeki elemanları özelleştiriyoruz
			aktifSiparisList.setCellFactory(new Callback<ListView<AktifSiparisler>, ListCell<AktifSiparisler>>() {
				@Override
				public ListCell<AktifSiparisler> call(ListView<AktifSiparisler> arg0) {
					return new ListCell<AktifSiparisler>() {
						@Override
						protected void updateItem(AktifSiparisler siparis, boolean empty) {
							super.updateItem(siparis, empty); // Süper methodu çağırmak önemli

							if (empty || siparis == null) {
								setText(null);

							} else {
								setText(siparis.toString()); // Burada hücre metnini güncelle
								Button button = new Button("Teslim Et");

								setGraphic(button);
								button.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {

										setGraphic(null);
										buttonOnclick(getItem());
									}

								});

							}
						}
					};
				}
			});

			aktifSiparisList.setItems(aktifSiparis);

		}
	}

	@FXML
	void siparisButton(ActionEvent event) throws IOException {
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.initOwner(anaPencere.getScene().getWindow());

		FXMLLoader fxmlLoader = new FXMLLoader();// FXML dosyası yüklemek için fxml nesnesi oluşturduk
		fxmlLoader.setLocation(getClass().getResource("siparis2Dialog.fxml"));// fxml dosyasında yolu belirtiyoruz

		dialog.setTitle("Sipariş Ekranı");
		dialog.getDialogPane().setContent(fxmlLoader.load());

		dialogStage = new Dialog<ButtonType>();
		dialogStage = dialog;

		// Referans tanımladım bu referansı controller verdim
		SiparisController siparisController = fxmlLoader.getController();
		siparisController.setDialog(dialogStage);
		siparisController.setAna(this);

		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

		dialog.showAndWait();

	}

	private void buttonOnclick(AktifSiparisler item) {

		if (item == null) {

		} else {
			teslimEdilen.add(item);
			teslimEdilenSiparisler.setItems(teslimEdilen);
			aktifSiparis.remove(item);

		}

	}

	@FXML
	void gunlukKazancButton(ActionEvent event) throws IOException {

		// Dialog ekranı oluşturken oluşturken controller birbirine bağlamam lazım yoksa
		// null hatası alırsın

		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.initOwner(anaPencere.getScene().getWindow());

		FXMLLoader fxmlLoader = new FXMLLoader();// FXML dosyası yüklemek için fxml nesnesi oluşturduk
		fxmlLoader.setLocation(getClass().getResource("gunlukKazanc.fxml"));// fxml dosyasında yolu belirtiyoruz

		dialog.setTitle("Günlük Kazanç");
		dialog.getDialogPane().setContent(fxmlLoader.load());

		dialogStage2 = new Dialog<ButtonType>();
		dialogStage2 = dialog;

		// Referans tanımladım bu referansı controller verdim
		// KazancController referans vererek controller veriler verdim direk verseydim
		// null hatası alırıdm
		KazancController kazanController = fxmlLoader.getController();
		kazanController.setDialog(dialogStage2);
		kazanController.setAna(this);
		kazanController.toplamHasılat(toplamHasilat);

		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

		dialog.showAndWait();

	}

}
