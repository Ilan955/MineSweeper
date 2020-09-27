package mines;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
public static void Display(String title,String msg,int flag) {
	Image ImageDecline = new Image("file:loser.png");
	BackgroundImage myBI= new BackgroundImage(new Image("file:loser.png"),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	BackgroundImage winner= new BackgroundImage(new Image("file:winner.jpg"),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	Stage Window=new Stage();;
	Window.initModality(Modality.APPLICATION_MODAL);
	Window.setTitle(title);
	Window.setWidth(300);
	Label label=new Label();
	label.setText(msg);
	GridPane vbox=new GridPane();
	label.setAlignment(Pos.CENTER);
	Button button=new Button("Close");
	label.setMaxWidth(Double.MAX_VALUE);
	if(flag==0)
	{
		label.setTextFill(Color.RED);
	vbox.setBackground(new Background(myBI));
}
	else
	{
		vbox.setBackground(new Background(winner));
		label.setTextFill(Color.WHITE);
	label.setAlignment(Pos.CENTER);
	}
	button.setPadding(new Insets(10));
	button.setOnMouseClicked(e_->
	{
		Window.hide();
	});
	button.setAlignment(Pos.CENTER);
	
	vbox.setMaxWidth(400);
	vbox.setPadding(new Insets(10));
	vbox.add(label, 1, 1);
	vbox.add(button, 2, 10,2,1);
	
	Scene scene=new Scene(vbox,250,150);
	Window.setScene(scene);
	Window.showAndWait();
	
}
}
