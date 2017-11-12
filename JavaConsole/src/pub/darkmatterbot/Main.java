package pub.darkmatterbot;

import java.util.ArrayList;
import java.net.URL;
import pub.libogame.*;
//import pub.libogame.HTTPSClient;

public class Main {

    public static void main(String[] args)
    {
        //Initialize the LibOgame instance:
        try {
            LibOgame _libOgame = new LibOgame("https://en.ogame.gameforge.com/");
            System.out.println("Now attempting login...");
            _libOgame.auth.login(0, "sven.master@gmail.com", "never123");
            
            /*
            HTTPSClient hc = new HTTPSClient();
            hc.setURL("http://localhost:8080");
            //hc.setURL("http://welcomebay.net/cookiecheck/postprint.php");
            
            hc.addPostData("kid", "");
            hc.addPostData("login", "sven@surax.net");
            hc.addPostData("pass", "testpass");
            hc.addPostData("uni", "testUniString");
            
            hc.runRequest();
            */
        }
        catch( LibOgameException e ) {
            System.out.println(e.toString());
            System.exit(1);
        }
    }
}
