package pub.libogame;

abstract class Upgradable {
    protected int currentLevel;
    protected int metalNeeded;
    protected int crystalNeeded;
    protected int deuteriumNeeded;
    protected int energyNeeded;

    public abstract void upgrade() throws LibOgameException;

    public int getCurrentLevel()    { return currentLevel;    }
    public int getMetalNeeded()     { return metalNeeded;     }
    public int getCrystalNeeded()   { return crystalNeeded;   }
    public int getDeuteriumNeeded() { return deuteriumNeeded; }
    public int getEnergyNeeded()    { return energyNeeded;    }
}