package pub.libogame;

import android.util.Log;

class Auth
{
    private LibOgame context;
    protected String loginURL;
    protected String requestURL;
    protected String username;
    protected String password;
    protected int serverIndex;
    protected boolean authenticated = false;

    public Auth(LibOgame context) {
        this.context = context;
    }

    public boolean isAuthenticated() { return authenticated; }

    public ReturnCode login( int serverIndex, String username, String password )
    {
        if(serverIndex > 0 && username != null && username != "" && password != null && password != "" ) {
            this.username = username;
            this.password = password;
            this.serverIndex = serverIndex;
            //Do the login stuff:
            context.hc.addPostData("kid", "");
            context.hc.addPostData("login", username);
            context.hc.addPostData("pass", password);
            context.hc.addPostData("uni", context.servers.getLink(serverIndex));

            if (context.hc.runRequest(loginURL) == ReturnCode.SUCCESS) {
                String tmp = context.hc.getData();
                Logger.println("auth.login()", tmp);

                if (tmp != null) return context.dp.parse(tmp);
                else return ReturnCode.Error(1, "auth.login(): No HTML Content to work with!");
            } else return ReturnCode.Error(1, "auth.login(): Login failed!");
        } else return ReturnCode.Error(1, "auth.login(): server index, username or password are not set!");
    }
}