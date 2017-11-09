package pub.libogame;

public class Research
{
    public final Technology energyTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology laserTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology ionTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology hyperspaceTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology plasmaTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology combustionDrive = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology impulseDrive = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology hyperspaceDrive = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology espionageTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology computerTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology astrophysics = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology intergalacticResearchCenter = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology gravitonTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology weaponsTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology shieldingTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public final Technology armourTechnology = new Technology() {
        @Override
        public ReturnCode upgrade() throws LibOgameException {
            return ReturnCode.REFUSED;
        }
    };

    public static abstract class Technology extends ResourceUser {
        protected int currentLevel;

        public int getCurrentLevel() { return currentLevel; }

        public abstract ReturnCode upgrade() throws LibOgameException;
    }
}