package pub.libogame;
import android.util.Log;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

/*
** METHODS IN THIS CLASS SHOULD NOT READ NOR WRITE TO DATASTORE! DATAPARSER WILL DO THAT!
** We asume that dataValidator has dealt with checking the datastore so if a function
** is called from this class, then we can asume the data in datastore to be reliable.
*/

class ActionGenerator
{
    private HTTPSClient hc;
    private DataParser  dp;
    private LibOgame    context;
    private String username, password;

    public ActionGenerator( LibOgame context ) {
	this.hc = new HTTPSClient();
	this.dp = new DataParser(context);
	this.context = context; //used to access ds.requestURL. Nothing else!
    }

    public ReturnCode initialize(String websiteURL) {
        //hc.setURL(websiteURL);
        //if(hc.runRequest() != ReturnCode.SUCCESS) return ReturnCode.ERROR;
        //String tmp = hc.getData();

        String tmp = fromSampleFile("samplePages/login.html");
        if (tmp != null) return dp.parse(tmp);
        else return ReturnCode.Error(0, "ActionGenerator: No HTML Content to work with!");
    }

    public ReturnCode login()
    {
        hc.addPostData("kid", "");
        hc.addPostData("login", context.auth.username);
        hc.addPostData("pass", context.auth.password);
        hc.addPostData("uni", context.servers.getLink(context.auth.serverIndex));

        if(hc.runRequest(context.auth.loginURL) == ReturnCode.SUCCESS) {
            String tmp = hc.getData();
            Log.println(Log.WARN, "ActionGenerator", tmp);

            if (tmp != null) return dp.parse(tmp);
            else return ReturnCode.Error(1, "ActionGenerator: No HTML Content to work with!");
        }
        else return ReturnCode.Error(0, "ActionGenerator: Login failed!");
    }

    /* A Debug function. Can be removed in release. */
    public static String fromSampleFile(String filename) {
        //Load ogame homepage from index.html for testing:
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(filename));
            return new String(encoded);
        } catch(IOException e) { Log.println(Log.WARN, "ActionGenerator", "Sample File Missing!"); }
        return null;
    }
}
