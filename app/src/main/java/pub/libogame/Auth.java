package pub.libogame;

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

    public ReturnCode login( int serverIndex, String username, String password ) {
        if(serverIndex > 0 && username != null && username != "" && password != null && password != "" )
        {
            this.username = username;
            this.password = password;
            this.serverIndex = serverIndex;
            ReturnCode rc = context.ag.login();
            if(rc == ReturnCode.SUCCESS)
                authenticated = true;
            return rc;
        }
        else return ReturnCode.Error(0, "Login Function: server index, username or password are not set!");
    }
}