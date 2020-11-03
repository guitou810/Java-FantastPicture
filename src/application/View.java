package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class View extends Application {
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		try {
		Parent rootFXML = FXMLLoader.load(getClass().getResource("application.fxml"));
		Scene sceneFXML = new Scene(rootFXML);
		
		arg0.setTitle("Fantast Picture");
		arg0.setScene(sceneFXML);
		arg0.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}  
	
	public static void main(String[] args) {
		launch(args);
	}
}
