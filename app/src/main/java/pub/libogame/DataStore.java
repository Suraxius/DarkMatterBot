package pub.libogame;
import java.util.ArrayList;

class DataStore
{
    public boolean initialized = false;
    public boolean authenticated = false;
    public String requestURL = null; //URL used for interaction with the individual ogame servers.
    public Planet  planet[] = new Planet[20];
    public ServerList serverList = new ServerList();

    //private ArrayList<Technology> technologyList = new ArrayList<Technology>();
    /*

    //Add all technologies here as research spans across all planets:
    public Technology energyTec                = new Technology();
    public Technology laserTec                 = new Technology();
    public Technology ionTec                   = new Technology();
    public Technology hyperspaceTec            = new Technology();
    public Technology plasmaTec                = new Technology();
    public Technology combustionDriveTec       = new Technology();
    public Technology impulseDriveTec          = new Technology();
    public Technology hyperspaceDriveTec       = new Technology();
    public Technology espionageTec             = new Technology();
    public Technology computerTec              = new Technology();
    public Technology astrophysicsTec          = new Technology();
    public Technology intergalacticResearchTec = new Technology();
    public Technology gravitonTec              = new Technology();
    public Technology weaponsTec               = new Technology();
    public Technology shieldingTec             = new Technology();
    public Technology armourTec                = new Technology();
    */

    public DataStore() {
        //Add test planet:
        planet[0] = new Planet("HomeWorld");
    }

    public class ServerList {
	private ArrayList<String[]> list = new ArrayList<String[]>();
	public String[] get     (int index)                 { return list.get(index); }
	public int      size    ()                          { return list.size(); }
	public void     add     (String name, String value) { list.add( new String[] {name, value} ); }
	public void     clear   ()                          { list.clear(); }
	public String   getName (int index)                 { return list.get(index)[0]; }
	public String   getValue(int index)                 { return list.get(index)[1]; }
    }

    public class Planet {
        public String   name;
        public int      id;
        public Resource metal                  = new Resource();
        public Resource crystal                = new Resource();
        public Resource deuterium              = new Resource();
        public Resource darkMatter             = new Resource();
        public Resource energy                 = new Resource();
        public Plant    metalMine              = new Plant();
        public Plant    crystalMine            = new Plant();
        public Plant    deuteriumSynthesizer   = new Plant();
        public Plant    solarPowerPlant        = new Plant();
        public Plant    fusionReactor          = new Plant();
        public Plant    solarSatalite          = new Plant();
        public Plant    metalStorage           = new Plant();
        public Plant    crystalStorage         = new Plant();
        public Plant    DeuteriumStorage       = new Plant();
        public Facility roboticsFactory        = new Facility();
        public Facility shipyard               = new Facility();
        public Facility reasearchLab           = new Facility();
        public Facility allianceDepot          = new Facility();
        public Facility missileSilo            = new Facility();
        public Facility naniteFactory          = new Facility();
        public Facility terraformer            = new Facility();
        public Facility spaceDock              = new Facility();
        public Ship     lightFighter           = new Ship();
        public Ship     heavyFighter           = new Ship();
        public Ship     cruiser                = new Ship();
        public Ship     battleship             = new Ship();
        public Ship     battlecruiser          = new Ship();
        public Ship     bomber                 = new Ship();
        public Ship     destroyer              = new Ship();
        public Ship     deathstar              = new Ship();
        public Ship     smallCargo             = new Ship();
        public Ship     largeCargo             = new Ship();
        public Ship     colonyShip             = new Ship();
        public Ship     recycler               = new Ship();
        public Ship     espionageProbe         = new Ship();
        public Defence  rocketLauncher         = new Defence();
        public Defence  lightLaser             = new Defence();
        public Defence  heavyLaser             = new Defence();
        public Defence  gaussCannon            = new Defence();
        public Defence  ionCannon              = new Defence();
        public Defence  plasmaTurret           = new Defence();
        public Defence  smallShield            = new Defence();
        public Defence  largeShield            = new Defence();
        public Defence  antiBallisticMissiles  = new Defence();
        public Defence  interplanetaryMissiles = new Defence();

        public Planet( String name ) {
            this.name = name;
        }

        public class Resource {
            public int value;
            public int timestamp;
            public int productionRate;
        }

        public class Plant extends Upgradable {}
        public class Facility extends Upgradable {}
        public class Ship extends Upgradable {}
        public class Defence extends Upgradable {}
    }

    public class Technology extends Upgradable {}

    public abstract class Upgradable {
        public int currentLevel;
        public int metalNeeded;
        public int crystalNeeded;
        public int deuteriumNeeded;
        public int energyNeeded;
    }
}
