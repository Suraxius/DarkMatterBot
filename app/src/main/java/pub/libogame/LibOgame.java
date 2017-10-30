package pub.libogame;

import java.util.ArrayList;

public class LibOgame
{
    private final ActionGenerator ag    = new ActionGenerator( this );
    protected ArrayList<Planet> planets = new ArrayList<Planet>();
    protected boolean initialized       = false;
    public final Auth auth              = new Auth();
    public final Research research      = new Research();
    public final ServerList servers     = new ServerList();

    //Read only Accessors (Getters)
    public boolean isInitialized()  { return initialized; }
    public Planet planet(int index) { return planets.get(index); }
    public int planetCount() { return planets.size(); }

    public LibOgame( String ogameWebsiteURL )
    {
        if(ogameWebsiteURL == null) {
            ReturnCode.Error(0, "Website URL not set!");
        }
        else {
            planets.add(new Planet("Test World")); //Add test planet
            if (ag.initialize(ogameWebsiteURL) == ReturnCode.SUCCESS) initialized = true;
        }
    }

    public class ServerList {
        private ArrayList<String[]> list = new ArrayList<String[]>();
        protected void add( String name, String value ) { list.add( new String[] {name, value} ); }
        protected void clear()                          { list.clear(); }
        protected String getLink ( int index )          { return list.get(index)[1]; }
        public String getName ( int index )             { return list.get(index)[0]; }
        public int count ()                             { return list.size(); }
    }

    public class Auth
    {
        protected String loginURL;
        protected String requestURL;
        protected String username;
        protected String password;
        protected int serverIndex;
        protected boolean authenticated = false;
        public boolean isAuthenticated() { return authenticated; }

        public ReturnCode login( int serverIndex, String username, String password ) {
            if(serverIndex > 0 && username != null && username != "" && password != null && password != "" )
            {
                this.username = username;
                this.password = password;
                this.serverIndex = serverIndex;
                ReturnCode rc = ag.login();
                if(rc == ReturnCode.SUCCESS)
                    auth.authenticated = true;
                return rc;
            }
            else return ReturnCode.Error(0, "Login Function: server index, username or password are not set!");
        }
    }

    public static class Planet
    {
        protected String   name;
        protected int      id;
        protected int[]    coordinates               = new int[3];
        public final Resource metal                  = new Resource();
        public final Resource crystal                = new Resource();
        public final Resource deuterium              = new Resource();
        public final Resource darkMatter             = new Resource();
        public final Resource energy                 = new Resource();
        public final Plant    metalMine              = new Plant();
        public final Plant    crystalMine            = new Plant();
        public final Plant    deuteriumSynthesizer   = new Plant();
        public final Plant    solarPowerPlant        = new Plant();
        public final Plant    fusionReactor          = new Plant();
        public final Plant    solarSatalite          = new Plant();
        public final Plant    metalStorage           = new Plant();
        public final Plant    crystalStorage         = new Plant();
        public final Plant    DeuteriumStorage       = new Plant();
        public final Facility roboticsFactory        = new Facility();
        public final Facility shipyard               = new Facility();
        public final Facility reasearchLab           = new Facility();
        public final Facility allianceDepot          = new Facility();
        public final Facility missileSilo            = new Facility();
        public final Facility naniteFactory          = new Facility();
        public final Facility terraformer            = new Facility();
        public final Facility spaceDock              = new Facility();
        //Ships:
        public final Asset    lightFighters          = new Asset();
        public final Asset    heavyFighters          = new Asset();
        public final Asset    cruisers               = new Asset();
        public final Asset    battleships            = new Asset();
        public final Asset    battlecruisers         = new Asset();
        public final Asset    bombers                = new Asset();
        public final Asset    destroyers             = new Asset();
        public final Asset    deathstars             = new Asset();
        public final Asset    smallCargos            = new Asset();
        public final Asset    largeCargos            = new Asset();
        public final Asset    colonyShips            = new Asset();
        public final Asset    recyclers              = new Asset();
        public final Asset    espionageProbes        = new Asset();
        //Defences:
        public final Asset    rocketLaunchers        = new Asset();
        public final Asset    lightLasers            = new Asset();
        public final Asset    heavyLasers            = new Asset();
        public final Asset    gaussCannons           = new Asset();
        public final Asset    ionCannons             = new Asset();
        public final Asset    plasmaTurrets          = new Asset();
        public final Asset    smallShield            = new Asset();
        public final Asset    largeShield            = new Asset();
        public final Asset    antiBallisticMissiles  = new Asset();
        public final Asset    interplanetaryMissiles = new Asset();

        public Planet( String name ) { this.name = name; }

        public String   getName()                   { return name;        }
        public int      getId()                     { return id;          }
        public int[]    getCoordinates()            { return coordinates; }

        public static class Resource {
            public int count;
            public int productionRatePerHour;
        }

        public static class Asset {
            protected int count;
            protected int metalNeeded;
            protected int crystalNeeded;
            protected int deuteriumNeeded;
            protected int energyNeeded;

            public int count()           { return count;           }
            public int metalNeeded()     { return metalNeeded;     }
            public int crystalNeeded()   { return crystalNeeded;   }
            public int deuteriumNeeded() { return deuteriumNeeded; }
            public int energyNeeded()    { return energyNeeded;    }

        }
        public static class Plant extends Upgradable {}
        public static class Facility extends Upgradable {}
    }

    public static class Research
    {
        protected Technology energyTechnology = new Technology();

        public static class Technology extends Upgradable {}
    }

    public static abstract class Upgradable {
        protected int currentLevel;
        protected int metalNeeded;
        protected int crystalNeeded;
        protected int deuteriumNeeded;
        protected int energyNeeded;

        public int getCurrentLevel()    { return currentLevel;    }
        public int getMetalNeeded()     { return metalNeeded;     }
        public int getCrystalNeeded()   { return crystalNeeded;   }
        public int getDeuteriumNeeded() { return deuteriumNeeded; }
        public int getEnergyNeeded()    { return energyNeeded;    }
    }
}
