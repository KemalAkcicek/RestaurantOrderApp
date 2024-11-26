package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SiparisController implements Initializable {

	@FXML
	private JFXListView<Yemek> listViewanaYemek;
	
	@FXML
	private Pane anaPencere;

	@FXML
	private JFXListView<Yemek> listViewbaslangic;

	@FXML
	private JFXListView<Yemek> listViewicecekler;

	@FXML
	private JFXListView<Yemek> listViewtatlilar;

	private Dialog<ButtonType> siparisDialog;

	private Controller controller;

	public void setAna(Controller controller) {
		this.controller = controller;
	}

	ObservableList<Yemek> baslangiclar = FXCollections.observableArrayList();
	ObservableList<Yemek> anaYemekk = FXCollections.observableArrayList();
	ObservableList<Yemek> icecek = FXCollections.observableArrayList();
	ObservableList<Yemek> tatli = FXCollections.observableArrayList();

	int hasilat = 0;

	ObservableList<AktifSiparisler> aktifYemekler = FXCollections.observableArrayList();
	ObservableList<AktifSiparisler> tempaktifYemekler = FXCollections.observableArrayList();

	public void setDialog(Dialog<ButtonType> dialogg) {
		// Farklı bir controller dialog ekranın kapatık
		this.siparisDialog = dialogg;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		baslangiclar.addAll(new Yemek("Tarator", 100), new Yemek("Humus", 100), new Yemek("Salata", 50),
				new Yemek("Çiğköfte", 80));
		anaYemekk.addAll(new Yemek("Tas Kebabı", 300), new Yemek("Patlıcan Mussaka", 200), new Yemek("Türlü", 250),
				new Yemek("Tepsi Kebabı", 400));
		tatli.addAll(new Yemek("Sütlaç", 100), new Yemek("Kazandibi", 150), new Yemek("Muhallebi", 90),
				new Yemek("Baklava", 150));
		icecek.addAll(new Yemek("Su", 10), new Yemek("Coca Cola", 50), new Yemek("Fanta", 40),
				new Yemek("Limonata", 60));

		listViewbaslangic.setItems(baslangiclar);
		listViewanaYemek.setItems(anaYemekk);
		listViewtatlilar.setItems(tatli);
		listViewicecekler.setItems(icecek);

	}

	@FXML
	void siparisTamamlandi(ActionEvent event) {

		Yemek anaYemekList = listViewanaYemek.getSelectionModel().getSelectedItem();
		Yemek baslangic = listViewbaslangic.getSelectionModel().getSelectedItem();
		Yemek icecek = listViewicecekler.getSelectionModel().getSelectedItem();
		Yemek tatli = listViewtatlilar.getSelectionModel().getSelectedItem();

		hasilat += anaYemekList.getFiyat() + baslangic.getFiyat() + icecek.getFiyat() + tatli.getFiyat();

		String gecicAna = anaYemekList.getIsmi();
		String geciciBaslangic = baslangic.getIsmi();
		String geciciİcecek = icecek.getIsmi();
		String geciciTatli = tatli.getIsmi();

		if (anaYemekList != null && baslangic != null && icecek != null && tatli != null) {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Siparişiniz başarıyla seçildi");
			a.show();
			AktifSiparisler aktifSiparis = new AktifSiparisler(geciciBaslangic, gecicAna, geciciTatli, geciciİcecek);
			aktifYemekler.add(aktifSiparis);

			tempaktifYemekler = aktifYemekler;
			controller.setAktifSiparis(tempaktifYemekler);
			controller.setHasılat(hasilat);
			siparisDialog.close();

		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("!!!!!!!'");
			a.setContentText("Lütfen her alandan bir tane seçiniz");
			a.show();
		}
	}

}
