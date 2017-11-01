package pub.libogame;

public class Planet
{
    protected LibOgame context;
    protected String   name;
    protected int      id;
    protected int[]    coordinates               = new int[3];
    public final Resource metal                  = new Resource();
    public final Resource crystal                = new Resource();
    public final Resource deuterium              = new Resource();
    public final Resource darkMatter             = new Resource();
    public final Resource energy                 = new Resource();

    public final Upgradable metalMine = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable crystalMine = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable deuteriumSynthesizer = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable solarPowerPlant = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable fusionReactor = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable solarSatalite = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable metalStorage = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable crystalStorage = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable DeuteriumStorage = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable roboticsFactory = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable shipyard = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable reasearchLab = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable allianceDepot = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable missileSilo = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable naniteFactory = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable terraformer = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    public final Upgradable spaceDock = new Upgradable() {
        @Override
        public void upgrade() {

        }
    };

    //Ships:
    public final Asset lightFighters = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset heavyFighters = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset cruisers = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset battleships = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset battlecruisers = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset bombers = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset destroyers = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset deathstars = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset smallCargos = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset largeCargos = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset colonyShips = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset recyclers = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset espionageProbes = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    //Defences:
    public final Asset rocketLaunchers = new Asset() {
        @Override
        public void upgrade() {

        }
    };
    public final Asset lightLasers = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset heavyLasers = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset gaussCannons = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset ionCannons = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset plasmaTurrets = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset smallShield = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset largeShield = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset antiBallisticMissiles = new Asset() {
        @Override
        public void upgrade() {

        }
    };

    public final Asset interplanetaryMissiles = new Asset() {
        @Override
        public void upgrade() throws LibOgameException {

        }
    };

    public Planet( LibOgame context, String name ) {
        this.name = name;
        this.context = context;
    }

    public String   getName()                   { return name;        }
    public int      getId()                     { return id;          }
    public int[]    getCoordinates()            { return coordinates; }

    public static class Resource {
        protected int count;
        protected int productionRatePerHour;
        public int count() { return count; }
        public int productionRatePerHour() { return productionRatePerHour; }
    }

    public abstract static class Asset {
        protected int count;
        protected int metalNeeded;
        protected int crystalNeeded;
        protected int deuteriumNeeded;
        protected int energyNeeded;

        public abstract void upgrade() throws LibOgameException;

        public int count()           { return count;           }
        public int metalNeeded()     { return metalNeeded;     }
        public int crystalNeeded()   { return crystalNeeded;   }
        public int deuteriumNeeded() { return deuteriumNeeded; }
        public int energyNeeded()    { return energyNeeded;    }

    }
}