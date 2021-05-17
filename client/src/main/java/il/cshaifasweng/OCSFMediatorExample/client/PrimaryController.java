/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="catalogBtn"
    private Button catalogBtn; // Value injected by FXMLLoader

    @FXML
    void showCatalog() throws IOException {
    	App.setRoot("catalog");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert catalogBtn != null : "fx:id=\"catalogBtn\" was not injected: check your FXML file 'primary.fxml'.";

    }
}
