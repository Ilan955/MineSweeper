package mines;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Controller {
String h;
String w;
String m;
int flag=0;
GridPane grid;
@FXML
private HBox hB;
ImageView flagg;
Image ImageDecline = new Image("file:flag.png");
BackgroundImage myBI= new BackgroundImage(new Image("file:blue.jpg"),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
Image bomb=new Image("file:mine.png");
DropShadow shadow = new DropShadow();

@FXML
private HBox Hbo;
@FXML
private GridPane Grid;
	@FXML
	private StackPane stck;
    @FXML
    private TextField heightProp;

    @FXML
    private TextField widthProp;

    @FXML
    private TextField MinesProp;

    @FXML
    private Button resetButton;

    @FXML
    void WhenReset(ActionEvent event) {
    	flag=0;
    	Mines mine;
    	stck.getChildren().clear();
    	int height,width,mines;
    	Button [][]b;
    	int n=0;
    	 
           //initializing the width height and mines set from the text field
    	m=MinesProp.getText();
    	h=heightProp.getText();
    	w=widthProp.getText();
    	height=Integer.parseInt(h);
    	width=Integer.parseInt(w);
    	mines=Integer.parseInt(m);
    	if(height*width<=mines)
    	{
    		AlertBox.Display("Wrong Input", "All bord is full of mines", 0);
    		return;
    	}
    	stck.setPrefWidth(width);
    	b=new Button[height][width];
    	GridPane grid=new GridPane();
    	grid.setPrefSize(width, height);
    	 mine=new Mines(height,width,mines);
    	grid.setVgap(2);
    	Button tmp=new Button();
    	
 //--------------------------------------Creating the buttons------------------------------// 
    	
    	
    	for(int i=0;i<height;i++)
    	{
    		for(int j=0;j<width;j++)
    		{
    			
    			b[i][j]=new Button();
    			 b[i][j].setMinWidth(65);
    			 b[i][j].setMinHeight(65);
    			 b[i][j].setText(".");
    			 
    			
    			 b[i][j].setStyle("-fx-font-size: 13; -fx-font-weight: bolder;");
    			 b[i][j].prefWidth(20);
    			
    			 b[i][j].setPadding(new Insets(24));
    			 
    			grid.add(b[i][j], j, i);
    		}
    		}
    	ButtonX but=new ButtonX(height,width);
    	but.init(b);
    	for(int i=0;i<height;i++)
    	{
    		for(int j=0;j<width;j++)
    		{
    			//will check if the user clicked ont the right click button, if so we want to add/delete flag
    			//the flag icon will appear, or disapear depend on what was there
    			Button x=b[i][j];
    			b[i][j].setOnMouseClicked(e_->{
    				
    				 if(e_.getButton()==MouseButton.SECONDARY)
    				 {
    					
    					if(!(x.getGraphic()==null)) {
    						x.setGraphic(null);
    						 x.setText(".");
    					}
    						
    					else
    					 
    					
    					x.setGraphic(new ImageView(ImageDecline));
    					
    					
    				 }
    				 else {
    					 
    				 
    				for(int k=0;k<height;k++) {
    					for(int m=0;m<width;m++) {
    						
    						//finding the right button, and check if there is a mine
    						
    	    		if(x.equals(but.button[k][m])) {
    	    			
    	    			//if there is a mine show all, and open all too.
    	    			if(!(mine.open(k, m))) {
    	    				
    	    				mine.setShowAll(true);
    	    				
    	    			//if there is amine on the specific place put a Bomb Icon(or X if not working)
    	    			for(int o=0;o<height;o++) {
    	    				for(int u=0;u<width;u++) {
    	    					mine.open(o, u);
    	    					if(mine.get(o, u).equals("X"))
    	    						but.button[o][u].setGraphic(new ImageView(bomb));
    	    					
    	    					but.button[o][u].setText(mine.get(o, u));
    	    					
    	    					
    	    					}
    	    				
    	    				}
    	    			}
    	    			//if there is no bomb in this place, go and check for the button, and open it
    	    			for(int v=0;v<height;v++) {
    	    				for(int g=0;g<width;g++) {
    	    					if(mine.bord[v][g].isOpen()) {
    	    						 but.button[v][g].setEffect(shadow);
    	    						but.button[v][g].setAlignment(Pos.CENTER);
    	    						
    	    						if(mine.get(v, g).equals("1"))
    	    							but.button[v][g].setTextFill(Color.BLUE);
    	    						else if(mine.get(v, g).equals("2"))
    	    							but.button[v][g].setTextFill(Color.GREEN);
    	    						else
    	    							but.button[v][g].setTextFill(Color.RED);
    	    						but.button[v][g].setText(mine.get(v, g));
    	    						
    	    					}
    	    					}
    	    			
    	    		}
    	    					
    	    		//	but.button[k][m].setText(mine.get(k, m));
    	    			if(mine.bord[k][m].hasMine()&&flag==0)
    	    			{
    	    				//calling the alert box class and place the string and the End game title for the Scene 
    	    				AlertBox.Display("End Game", "Sorry you lost",0);
    	    				flag++;
    	    			}
    	    			if(mine.isDone()&&flag!=1){
    	    				AlertBox.Display("End Game", "You Just Won, Congratulations!!",1);
    	    				
    	    			}
    	    		}
    	    		
    	    			
    					}
    				}
    			}
    			});
    			
    		}
    		
    	}
    	
    	// adding the grid to the empty stack.
    	stck.getChildren().add(grid);
    	stck.getScene().getWindow().sizeToScene();
    	this.grid=grid;

 
    
    }
    
    //creating private class to save the height width and button
    private class ButtonX {
    	private int heightX;
    	private int widthY;
    	Button [][]button;
    	public ButtonX(int height,int width) {
			this.heightX=height;
			this.widthY=width;
		}
    	public void init(Button [][]button)
    	{
    		this.button=button;
    	}
    }
    

    	
    

}
