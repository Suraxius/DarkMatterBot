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

    public final Structure metalMine = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure crystalMine = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure deuteriumSynthesizer = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure solarPowerPlant = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure fusionReactor = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure solarSatalite = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure metalStorage = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure crystalStorage = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure DeuteriumStorage = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure roboticsFactory = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure shipyard = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure reasearchLab = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure allianceDepot = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure missileSilo = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure naniteFactory = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure terraformer = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Structure spaceDock = new Structure() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }

        @Override
        public ReturnCode downgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    //Ships:
    public final Asset lightFighters = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset heavyFighters = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset cruisers = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset battleships = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset battlecruisers = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset bombers = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset destroyers = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset deathstars = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset smallCargos = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset largeCargos = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset colonyShips = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset recyclers = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset espionageProbes = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    //Defences:
    public final Asset rocketLaunchers = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };
    public final Asset lightLasers = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset heavyLasers = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset gaussCannons = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset ionCannons = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset plasmaTurrets = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset smallShield = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset largeShield = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset antiBallisticMissiles = new Asset() {
        @Override
        public ReturnCode build( int amount ) {
            return ReturnCode.REFUSED;
        }
    };

    public final Asset interplanetaryMissiles = new Asset() {
        @Override
        public ReturnCode build( int amount ) throws LibOgameException {
            return ReturnCode.REFUSED;
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

    public abstract static class Asset extends ResourceUser {
        protected int count;

        public int count() { return count; }

        public abstract ReturnCode build( int amount ) throws LibOgameException;

    }

    public static abstract class Structure extends ResourceUser {
        protected int currentLevel;

        public int getCurrentLevel() { return currentLevel; }

        public abstract ReturnCode upgrade() throws LibOgameException;
        public abstract ReturnCode downgrade() throws LibOgameException;
    }
}
