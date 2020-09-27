package mines;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class main extends Application {
		public static void main(String[] args) {
			
		launch(args);
		}
		@Override
		public void start(Stage primaryStage) throws Exception {
			HBox grid;
			Controller c=new Controller();
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("check.fxml"));
				grid =  loader.load();

			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
		
		Scene s = new Scene(grid);
		primaryStage.sizeToScene();
		primaryStage.setScene(s);
		primaryStage.setTitle("Mines Sweeper");
		primaryStage.show();
		
			
		}

}
