package pub.darkmatterbot;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {

    // description of application
    //private GridPane root;
    //private Button btn_insert;
    //private TextArea ta;
    //private TextField[] inputs;


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //inputs = new TextField[4];
        // add listeners for changes to text fields

        GridPane root = new GridPane();

        primaryStage.setTitle( "DMB OGame Bot" );
        primaryStage.getIcons().add(new Image("file:../images/DarkMatterBotLogo.png"));
        primaryStage.setMinHeight( 600 );
        primaryStage.setMaxHeight( 600 );
        primaryStage.setMinWidth( 900 );
        primaryStage.setMaxWidth( 900 );
        //initiateGrid();

        // Create a new empty JavaFX window of sized 900 x 600px.
        // containing our grid panes

        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
}
