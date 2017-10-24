package pub.libogame;

public class LibOgame implements LibOgameInterface
{
    private DataStore       ds  = new DataStore();
    private ActionValidator av  = new ActionValidator(ds);
    private boolean initialized = false;

    public LibOgame()
    {
        //Logger.log("Our Planet is " + ds.planet[0].name);
        //Logger.log("Resources");
        //Logger.log(" " + ds.planet[0].crystal.value);
        //Logger.log(" " + ds.planet[0].metal.value);
        //Logger.log(" " + ds.planet[0].deuterium.value);
    }

    public LibOgame( String ogameWebsiteURL )
    {
        this();
        if( connect( ogameWebsiteURL ) == ReturnCode.SUCCESS)
            this.initialized = true;
    }

    public ReturnCode login( int serverIndex, String username, String password )
    {
        if(
            serverIndex > 0 &&
            username != null &&
            username != "" &&
            password != null &&
            password != ""
            ) {
                ReturnCode rc = av.login(ds.serverList.getValue(serverIndex), username, password);

                if(rc == ReturnCode.SUCCESS)
                    ds.authenticated = true;

                return rc;
            }
        else return ErrorHandler.log("Login Function: server index, username or password are not set!");
    }

    public ReturnCode connect( String ogameWebsiteURL )
    {
        if(ogameWebsiteURL == null)
            return ErrorHandler.log("connect: Website URL not set!");

        return av.initialize(ogameWebsiteURL);
    }

    public String     getServerName(int index) { return ds.serverList.getName(index); }
    public String     getPlanetName( int index ) { return "Function not implemented!"; }
    public String     getErrorMessages() { return "Function not implemented!"; }
    public int        getErrorID() { return -1; }
    public int        getNumServers() { return ds.serverList.size(); }
    public int        getNumPlanets() { return -1; }
    public int        getLevel(TechnologyID id) { return -1; }
    public int        getLevel(int planetIndex, PlantID id) { return -1; }
    public int        getLevel(int planetIndex, FacilityID id) { return -1; }
    public int        getNumShipsOnRoute() { return -1; }
    public int        getNumShips(int planetIndex, ShipID id) { return -1; }
    public ReturnCode upgrade( int planetIndex, PlantID id ) { return ErrorHandler.log("Function not implemented!"); }
    public ReturnCode upgrade( int planetIndex, FacilityID id ) { return ErrorHandler.log("Function not implemented!"); }
    public ReturnCode downgrade( int planetIndex, PlantID id ) { return ErrorHandler.log("Function not implemented!"); }
    public ReturnCode downgrade( int planetIndex, FacilityID id ) { return ErrorHandler.log("Function not implemented!"); }
    public ReturnCode research( TechnologyID id ) { return ErrorHandler.log("Function not implemented!"); }
}
