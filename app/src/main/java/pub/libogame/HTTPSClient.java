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
    private String postData;
    private String returnedData;

    public HTTPSClient() {}

    public ReturnCode runRequest( String urlString ) {
        if( urlString.equals("") || urlString == null ) return ReturnCode.Error(0, "HttpsClient: url not set!");
        try {
            CookieHandler.setDefault(new CookieManager());
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 Firefox/10.0");
            connection.setInstanceFollowRedirects(true);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);

            if(this.postData != null) {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(this.postData);
                wr.flush();
                wr.close();
            }

            BufferedReader br = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
            String tmp;
            while( (tmp = br.readLine()) != null ) returnedData += tmp;
            br.close();
            connection.disconnect();
        }
        catch(MalformedURLException e) { return ReturnCode.Error(0, "RunRequest: MalformedURLException"); }
        catch (IOException e) { return ReturnCode.Error(0, "RunRequest: IOException"); }
        catch (Exception e) { return ReturnCode.Error(0, "RunRequest: Undefined Exception"); }

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

    public String getData() { return returnedData; }
}
