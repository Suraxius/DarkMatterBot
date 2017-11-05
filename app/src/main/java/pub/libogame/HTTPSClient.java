package pub.libogame;
import javax.net.ssl.HttpsURLConnection;

import java.net.URL;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;

class HTTPSClient
{
    private   String postData;
    private   URL    url;
    protected String returnedData;

    protected HTTPSClient()
    {
        CookieHandler.setDefault(new CookieManager());
    }

    protected ReturnCode setURL( String requestURL )
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
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 Firefox/10.0");
            connection.setInstanceFollowRedirects(true);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);

            if(this.postData != null) {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream( connection.getOutputStream() );
                wr.writeBytes(this.postData);
                wr.flush();
                wr.close();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader( connection.getInputStream() ) );
            //Replace with new string to remove old data:
            if(returnedData == null || !returnedData.equals("")) returnedData = new String();
            String tmp;
            while( (tmp = br.readLine()) != null ) returnedData += tmp;
            br.close();
            connection.disconnect();
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
