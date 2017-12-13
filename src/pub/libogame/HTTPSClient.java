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
import java.io.DataOutputStream;
import java.util.zip.GZIPInputStream;

//For testing:
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.util.List;

public class HTTPSClient
{
    private   String        postData;
    private   URL           url;
    private   CookieManager cm;
    private   CookieStore   cs;
    protected String        returnedData;

    public HTTPSClient()
    {
        cm = new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        CookieHandler.setDefault(cm);
        cs = cm.getCookieStore();
    }

    public ReturnCode setURL( String requestURL )
    {
        try {
            url = new URL( requestURL );
            return ReturnCode.SUCCESS;
        } catch ( MalformedURLException e ) { return ReturnCode.REFUSED; }
    }

    public int runRequest() throws LibOgameException
    {
        int returnCode = 0;
        Logger.println("---------------------------");
        
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
            
            Logger.println("Communication URL: " + url.toString());
            Logger.println("Cookies before: " + Integer.toString(cs.getCookies().size()));
            
            
            List<HttpCookie> cookies = cs.get(url.toURI());
            Logger.println(cookies.size() + " Cookies will be send.");
            for( final HttpCookie cookie : cookies ) {
                Logger.println("Cookie Name:" + cookie.getName() +
                               " Value:" + cookie.getValue() +
                               " Domain:" + cookie.getDomain());
            }
            
            
            //connection.setRequestProperty("Cookie", "PHPSESSID=" + cs.getCookies().get(cs.g));
            
            //Switch to Post mode if there is post data to be send:
            if(this.postData != null) {
                Logger.println("Switching to POST mode to transfer post data...");
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

            
            Logger.println("Cookies after: " + Integer.toString(cs.getCookies().size()));
            returnCode = connection.getResponseCode();
            Logger.println("Http ReturnCode: " + Integer.toString(returnCode));
            //Logger.println("Header", connection.getHeaderField("Location"));
            
            
            switch(returnCode) {
                case 302:
                case 303:
                    url = new URL(connection.getHeaderField("Location"));
                    return returnCode;
                case 200:
                    //Read Data:
                    if(returnedData == null || !returnedData.equals("")) returnedData = new String();
                    String tmp;
                    while( (tmp = br.readLine()) != null ) returnedData += tmp;
                    br.close();

                    //Print read data to console:
                    //Logger.println("Returned Data:\n", returnedData + "\n------------\n");
                    
                    //Test code:
                    PrintWriter writer = new PrintWriter("out.htm", "UTF-8");
                    writer.print(returnedData);
                    writer.close();
                    //End of Test Code
                    return returnCode;
            }
            purgePostData();
            connection.disconnect();
        }
        catch (Exception e) { throw new LibOgameException("HTTPSClient.runRequest(): " + e.toString()); }
        throw new LibOgameException("HTTPSClient.runRequest(): Unknown HTTP Return Code: " + returnCode);
    }

    public void addPostData( String name, String value ) {
        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
        }
        catch(UnsupportedEncodingException e) {}

        if(postData == null) postData = (name + "=" + value);
        else postData += ("&" + name + "=" + value);
    }

    public void purgePostData() { postData = null; }
    public String getPostData() { return postData; }
}
