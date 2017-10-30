package pub.libogame;

import java.util.ArrayList;

public class LibOgame
{
    protected final ActionGenerator ag  = new ActionGenerator( this );
    protected ArrayList<Planet> planets = new ArrayList<Planet>();
    protected boolean initialized       = false;
    public final Auth auth              = new Auth(this);
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

    public static class ServerList {
        private ArrayList<String[]> list = new ArrayList<String[]>();
        protected void add( String name, String value ) { list.add( new String[] {name, value} ); }
        protected void clear()                          { list.clear(); }
        protected String getLink ( int index )          { return list.get(index)[1]; }
        public String getName ( int index )             { return list.get(index)[0]; }
        public int count ()                             { return list.size(); }
    }
}
