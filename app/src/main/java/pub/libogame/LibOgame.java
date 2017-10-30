package pub.libogame;

import java.util.ArrayList;

public class LibOgame
{
    protected final HTTPSClient       hc          = new HTTPSClient();
	protected final DataParser        dp          = new DataParser( this );
    protected final ArrayList<Planet> planets     = new ArrayList<>();
    public    final Auth              auth        = new Auth();
    public    final Research          research    = new Research();
    public    final ServerList        servers     = new ServerList();

    //Read only Accessors (Getters)
    public Planet  planet(int index) { return planets.get(index); }
    public int     planetCount()     { return planets.size();     }

    public LibOgame( String ogameWebsiteURL ) throws LibOgameException
    {
        if(ogameWebsiteURL == null) {
            throw new LibOgameException("Website URL not set!");
        }
        else {
            hc.requestURL = ogameWebsiteURL;
            hc.runRequest();
            if(hc.returnedData == null || !hc.returnedData.equals(""))
                Logger.println("LibOgame.constr.", "Data downloaded!");
            planets.add(new Planet("Test World")); //Add test planet
            //Need to go get the Ogame homepage so we can populate the server list.
        }
    }

    public static class ServerList {
        private ArrayList<String[]> list = new ArrayList<>();
        protected void   add( String name, String value ) { list.add( new String[] {name, value} ); }
        protected void   clear()                          { list.clear();                           }
        protected String getLink ( int index )            { return list.get(index)[1];              }
        public    String getName ( int index )            { return list.get(index)[0];              }
        public    int    count()                          { return list.size();                     }
    }

    public class Auth
    {
        protected String  username;
        protected String  password;
        protected int     serverIndex;
        protected boolean authenticated = false;

        public boolean isAuthenticated() { return authenticated; }

        public ReturnCode login( int serverIndex, String username, String password )
                throws LibOgameException
        {
            if(serverIndex > 0 &&
               username != null && !username.equals("") &&
               password != null && !password.equals("") )
            {
                this.username = username;
                this.password = password;
                this.serverIndex = serverIndex;
                //Do the login stuff:
                hc.addPostData("kid", "");
                hc.addPostData("login", username);
                hc.addPostData("pass", password);
                hc.addPostData("uni", servers.getLink(serverIndex));

                if (hc.runRequest() == ReturnCode.SUCCESS) {
                    String tmp = hc.getData();
                    Logger.println("auth.login()", tmp);

                    if (tmp != null) {
                        dp.parse(tmp);
                        return ReturnCode.SUCCESS;
                    }
                    else throw new LibOgameException("auth.login(): No HTML Content to work with!");
                } else throw new LibOgameException("auth.login(): Login failed!");
            } else throw new LibOgameException("auth.login(): server index, username or password are not set!");
        }
    }
}
