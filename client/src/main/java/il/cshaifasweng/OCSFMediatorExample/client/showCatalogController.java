package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class showCatalogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button editBtn;

    @FXML
    private Button homeBtn;

    @FXML
    void editMovie(ActionEvent event) {
    	try {
			App.setRoot("editCatalog");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void goHome(ActionEvent event) {
    	try {
			App.setRoot("primary");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
        assert editBtn != null : "fx:id=\"editBtn\" was not injected: check your FXML file 'showCatalog.fxml'.";
        assert homeBtn != null : "fx:id=\"homeBtn\" was not injected: check your FXML file 'showCatalog.fxml'.";

    }
}
