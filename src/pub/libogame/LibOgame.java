package pub.libogame;

import java.util.ArrayList;

/**
 * Entry point for LibOgame. Instantiate this class
 * and access all functionality through it.
 */
public class LibOgame
{
    protected final HTTPSClient       hc          = new HTTPSClient();
	protected final DataParser        dp          = new DataParser( this );
    protected final ArrayList<Planet> planets     = new ArrayList<>();
    public    final Authentication    auth        = new Authentication();
    public    final Research          research    = new Research();
    public    final ServerList        servers     = new ServerList();

    //Read only Accessors (Getters)
    public Planet  planet(int index) { return planets.get(index); }
    public int     planetCount()     { return planets.size();     }

    /**
     * LibOgame Constructor
     * @param ogameWebsiteURL The https link of the Ogame login page to interface with.
     * @throws LibOgameException
     */
    public LibOgame( String ogameWebsiteURL ) throws LibOgameException
    {
        if(ogameWebsiteURL == null || ogameWebsiteURL.equals("")) {
            throw new LibOgameException("Website URL not set!");
        }
        else {
            hc.setURL( ogameWebsiteURL );
            int returnCode = hc.runRequest();
            if(returnCode == 200 && hc.returnedData != null && !hc.returnedData.equals("")) {
                Logger.println("LibOgame.constructor: Data downloaded!");
                dp.parse(hc.returnedData);
            }
            else throw new LibOgameException("LibOgame.constructor: Failed to fetch needed data!");
        }
    }

    /**
     * A Custom List Class for storage and access of ogame servers links and associated names.
     */
    public static class ServerList {
        private ArrayList<String[]> list = new ArrayList<>();
        protected void   add( String name, String value ) { list.add( new String[] {name, value} ); }
        protected void   clear()                          { list.clear();                           }
        protected String getLink ( int index )            { return list.get(index)[1];              }
        public    String getName ( int index )            { return list.get(index)[0];              }
        public    int    count()                          { return list.size();                     }
    }

    /**
     * Authentication Handler Class. Provides the methods and properties required to
     * authenticate against an Ogame Server and a mechanism for the rest of the library
     * to keep track of the authentication status.
     */
    public class Authentication
    {
        protected String  username;
        protected String  password;
        protected int     serverIndex;
        protected boolean authenticated = false;

        /**
         * Provides access to the "authenticated" flag which indicates wether or
         * not authentication against the Ogame Server succeeded or not.
         * @return Returns the value of the "authenticated" flag property.
         */
        public boolean isAuthenticated() { return authenticated; }

        /**
         * Sets the server index, username and password to be used for authentication.
         * @param serverIndex the index location of the server to use from the server list.
         * @param username Username to be used.
         * @param password Password to be used.
         * @return Returns ErrorCode.REFUSED if invalid uparameter was
         * specified and ReturnCode.SUCCESS otherwise.
         */
        public void setCredentials( int serverIndex, String username, String password ) throws LibOgameException
        {
            setUsername(username);
            setPassword(password);
            setServer(serverIndex);
        }

        public void setUsername(String username) throws LibOgameException
        {
            if(username != null && !username.equals("") ) this.username = username;
            else throw new LibOgameException("auth.setUsername(index): Username not specified!");
        }

        public void setPassword(String password) throws LibOgameException
        {
            if(password != null && !password.equals("") ) this.password = password;
            else throw new LibOgameException("auth.setPassword(index): Password not specified!");
        }

        public void setServer(int serverIndex) throws LibOgameException
        {
            if( serverIndex >= 0 && serverIndex <  servers.count() ) this.serverIndex = serverIndex;
            else throw new LibOgameException("auth.setServer(index): Server index out of bounds!");
        }

        /**
         * Authenticates against an OGame server.
         * @return Returns ErrorCode.SUCCESS on Success or ErrorCode.Error otherwise.
         * @throws LibOgameException
         */
        public ReturnCode login() throws LibOgameException
        {
            if(serverIndex >= 0 && serverIndex < servers.count() &&
                   username != null && password != null)
            {
                this.serverIndex = serverIndex;
                //Do the login stuff:
                hc.addPostData("kid", "");
                hc.addPostData("login", username);
                hc.addPostData("pass", password);
                hc.addPostData("uni", servers.getLink(serverIndex));

                if( hc.runRequest() == 303 &&
                    hc.runRequest() == 302 &&
                    hc.runRequest() == 200 &&
                    hc.returnedData != null) {
                    dp.parse(hc.returnedData);
                    return ReturnCode.SUCCESS;
                }
                else throw new LibOgameException("auth.login(): Login failed!");
            }
            else throw new LibOgameException("auth.login(): server index, username or password are not set!");
        }

        /**
         *
         * @param serverIndex Index position of Server to authenticate against.
         * @param username Username to use for authentication.
         * @param password Password to  use for authentication.
         * @return Returns ReturnCode.Refused if a parameter is invalid or the same return
         * codes as login() without parameters otherwise.
         * @throws LibOgameException
         */
        public ReturnCode login( int serverIndex, String username, String password ) throws LibOgameException
        {
            setCredentials( serverIndex, username, password );
            return login();
        }
    }
}
