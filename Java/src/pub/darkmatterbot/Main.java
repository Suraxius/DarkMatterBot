package pub.darkmatterbot;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.net.URL;
import pub.libogame.*;

public class Main extends Application {

    private LibOgame _libOgame;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Initialize the LibOgame instance:
        try {
            _libOgame = new LibOgame("https://en.ogame.gameforge.com/");
        }
        catch( LibOgameException e ) {
            System.out.println(e.toString());
            System.exit(1);
        }

        //Get image file:
        ClassLoader cl = this.getClass().getClassLoader();
        URL logo = cl.getResource("images/DarkMatterBotLogo.png");

        //Add UI Elements:
        GridPane root = new GridPane();
        ListView<String> serverListView = new ListView<String>();
        root.getChildren().add(serverListView);

	//Add Servers to serverList:
        String[] serverAL = new String[_libOgame.servers.count()];
        for( int i = 0; i < serverAL.length; i++ ) serverAL[i] = _libOgame.servers.getName(i);
        ObservableList<String> oServerAL = FXCollections.observableArrayList(serverAL);
        serverListView.setItems(oServerAL);

        primaryStage.setTitle( "DMB OGame Bot" );
        if(logo != null ) primaryStage.getIcons().add(new Image(logo.openStream()));
        primaryStage.setMinHeight( 600 );
        primaryStage.setMaxHeight( 600 );
        primaryStage.setMinWidth( 900 );
        primaryStage.setMaxWidth( 900 );
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
}
