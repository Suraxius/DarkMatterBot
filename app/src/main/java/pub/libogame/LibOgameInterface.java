package pub.libogame;

interface LibOgameInterface
{
    public ReturnCode connect( String ogameWebsiteURL );
    public ReturnCode login( int ogameServerIndex, String username, String password );
    public ReturnCode upgrade( int planetIndex, PlantID id );
    public ReturnCode upgrade( int planetIndex, FacilityID id );
    public ReturnCode downgrade( int planetIndex, PlantID id );
    public ReturnCode downgrade( int planetIndex, FacilityID id );
    public ReturnCode research( TechnologyID id );
    public String     getServerName( int index );
    public String     getPlanetName( int index );
    public String     getErrorMessages();
    public int        getErrorID();
    public int        getNumServers();
    public int        getNumPlanets();
    public int        getNumShipsOnRoute();
    public int        getNumShips(int planetIndex, ShipID id);
    public int        getLevel(TechnologyID id);
    public int        getLevel(int planetIndex, PlantID id);
    public int        getLevel(int planetIndex, FacilityID id);
}
