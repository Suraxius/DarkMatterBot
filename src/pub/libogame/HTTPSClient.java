package pub.libogame;
import javax.net.ssl.HttpsURLConnection;
//import java.net.HttpURLConnection;

import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.URL;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.io.DataOutputStream;
import java.util.concurrent.TimeUnit;

//For testing:
import java.io.PrintWriter;

public class HTTPSClient
{
    private   String postData;
    private   URL    url;
    private   CookieStore cs;
    protected String returnedData;

    public HTTPSClient()
    {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        cs = ((CookieManager) CookieHandler.getDefault()).getCookieStore();
    }

    public ReturnCode setURL( String requestURL )
    {
        try {
            url = new URL( requestURL );
            return ReturnCode.SUCCESS;
        } catch ( MalformedURLException e ) { return ReturnCode.REFUSED; }
    }

    public ReturnCode runRequest() throws LibOgameException
    {
        if( url == null ) throw new LibOgameException("HttpsClient.runRequest(): url not set!");
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:56.0) Gecko/20100101 Firefox/56.0");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setInstanceFollowRedirects(false);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            
            Logger.println("Communication URL", url.toString());
            Logger.println("Cookies before", Integer.toString(cs.getCookies().size()));

            //Switch to Post mode if there is post data to be send:
            if(this.postData != null) {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream( connection.getOutputStream() );
                wr.writeBytes(this.postData);
                wr.flush();
                wr.close();
            }
            
            //Get page:
            InputStreamReader reader = null;
            if ("gzip".equals(connection.getContentEncoding())) {
                reader = new InputStreamReader(new GZIPInputStream(connection.getInputStream()));
            }
            else {
                reader = new InputStreamReader(connection.getInputStream());
            }

            BufferedReader br = new BufferedReader(reader);

            
            Logger.println("Cookies after", Integer.toString(cs.getCookies().size()));
            
            int returnCode = connection.getResponseCode();

            Logger.println("Http ReturnCode", Integer.toString(returnCode));
            //Logger.println("Header", connection.getHeaderField("Location"));
            
            TimeUnit.SECONDS.sleep(5);
            
            switch(returnCode) {
                case 302:
                case 303:
                    purgePostData();
                    url = new URL(connection.getHeaderField("Location"));
                    runRequest();
                    break;
                case 200:
                    //Read Data:
                    if(returnedData == null || !returnedData.equals("")) returnedData = new String();
                    String tmp;
                    while( (tmp = br.readLine()) != null ) returnedData += tmp;
                    br.close();
                    connection.disconnect();

                    //Print read data to console:
                    //Logger.println("Returned Data:\n", returnedData + "\n------------\n");

                    //Test code:
                    PrintWriter writer = new PrintWriter("out.htm", "UTF-8");
                    writer.print(returnedData);
                    writer.close();
                    //End of Test Code
                    break;
                default: throw new LibOgameException("HTTPSClient.runRequest(): Unknown HTTP Return Code: " + returnCode);
            }
        }
        catch (Exception e) {
            throw new LibOgameException("HttpsClient.runRequest(): " + e.toString()); }

        return ReturnCode.SUCCESS;
    }

    public void addPostData( String name, String value ) {
        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
        }
        catch(UnsupportedEncodingException e) {}

        if(postData == null)
            postData  = (name + "=" + value);
        else
            postData += ("&" + name + "=" + value);


    }

    public void purgePostData() { postData = null; }
    public String getPostData() { return postData; }
}
