module RestaurantOrderApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	
	opens application to javafx.graphics, javafx.fxml;
}
