package pub.libogame;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
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
    private DataStore   ds;
    private String username, password;

    public ActionGenerator( DataStore ds) {
	this.hc = new HTTPSClient();
	this.dp = new DataParser(ds);
	this.ds = ds; //used to access ds.requestURL. Nothing else!
    }

    public ReturnCode initialize(String websiteURL) {
        //hc.setURL(websiteURL);
        //if(hc.runRequest() != ReturnCode.SUCCESS) return ReturnCode.ERROR;
        //String tmp = hc.getData();

        String tmp = fromSampleFile("samplePages/login.html");
        if (tmp != null) return dp.parse(tmp);
        else return ErrorHandler.log("ActionGenerator: No HTML Content to work with!");
    }

    public ReturnCode login(String server, String username, String password)
    {
        hc.setURL(ds.requestURL);

        hc.addPostData("kid", "");
        hc.addPostData("login", username);
        hc.addPostData("pass", password);
        hc.addPostData("uni", server);

        if(hc.runRequest() != ReturnCode.SUCCESS) return ReturnCode.ERROR;

        Logger.println(hc.getData());

        String tmp = null;

        //String tmp = hc.getData();
        if (tmp != null) return dp.parse(tmp);
        else return ErrorHandler.log("ActionGenerator: No HTML Content to work with!");
    }

    /* A Debug function. Can be removed in release. */
    public static String fromSampleFile(String filename) {
        //Load ogame homepage from index.html for testing:
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(filename));
            return new String(encoded);
        } catch(IOException e) { Logger.println("Sample File Missing!"); }
        return null;
    }
}
