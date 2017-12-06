package pub.libogame;

//import android.util.Log;

class DataParser
{
    private LibOgame context;
    private String   pageContent;
    private LoginPH            loginPH            = new LoginPH();
    private OverviewPH         overviewPH         = new OverviewPH();
    private ResourcesPH        resourcesPH        = new ResourcesPH();
    private ResourceSettingsPH resourceSettingsPH = new ResourceSettingsPH();
    private FacilitiesPH       facilitiesPH       = new FacilitiesPH();
    private ResearchPH         researchPH         = new ResearchPH();
    private ShipyardPH         shipyardPH         = new ShipyardPH();
    private DefencePH          defencePH          = new DefencePH();
    private FleetPH            fleetPH            = new FleetPH();

    public DataParser( LibOgame context ) {
        this.context = context;
    }

    public void parse(String pageContent) throws LibOgameException {
        this.pageContent = pageContent;

        if (pageContent.contains("<form id=\"loginForm\" name=\"loginForm\" method=\"post\""))
            loginPH.process();
        else if (pageContent.contains("<body id=\"overview\""))
            overviewPH.process();
        else if (pageContent.contains("<body id=\"resources\""))
            resourcesPH.process();
        else if (pageContent.contains("<body id=\"resourceSettings\""))
            resourceSettingsPH.process();
        else if (pageContent.contains("<body id=\"station\""))
            facilitiesPH.process();
        else if (pageContent.contains("<body id=\"research\""))
            researchPH.process();
        else if (pageContent.contains("<body id=\"shipyard\""))
            shipyardPH.process();
        else if (pageContent.contains("<body id=\"defense\""))
            defencePH.process();
        else if (pageContent.contains("<body id=\"fleet1\""))
            fleetPH.process(1);
        else throw new LibOgameException(
                "DataParser.parse(): HTML content could not be identified!");
    }
    //-----------------------------------

    private abstract class PageHandler
    {
        protected abstract void process();

        //Returns the nth occurred of a substring between two other strings.
        protected String getTagContent(String pageContent,
                                       String tagStart,
                                       String tagEnd,
                                       int accuranceN)
        {
            if(accuranceN < 1) return null;
            for (int i = 0; i < accuranceN; i++) {
                int prefixIndex = pageContent.indexOf(tagStart);
                if( prefixIndex == -1 ) return null;
                prefixIndex += tagStart.length();
                pageContent  = pageContent.substring(prefixIndex);

            }
            int suffixIndex = pageContent.indexOf(tagEnd);
            pageContent = pageContent.substring(0, suffixIndex);
            return pageContent;
        }

        protected String getSelectedPlanet(String pageContent) {
            return getTagContent(pageContent,
                    "<div id=\"selectedPlanetName\" class=\"textCenter\">",
                    "</div>", 1);
        }
    }

    private class LoginPH extends PageHandler
    {
        protected void process() {
            Logger.println("DataParser: Hello from LoginPH!");
            parseServerList(context, pageContent);
            parseRequestURL(context, pageContent);
        }
        /* Parse the server List from the login page html code. */
        protected ReturnCode parseServerList(LibOgame context, String pageContent) {
            String entry, value, name;
            int count = 1;
            while((entry = getTagContent(pageContent, "<option",
                    "</option>", count++)) != null)
            {
                entry = entry.replaceAll("\\s+","");
            	value = getTagContent(entry, "value=\"", "\"", 1);
            	name  = entry.substring(entry.indexOf(">")+1);

                //If name or value could not be parsed, end in error.
                if(name == null || value == null)
                	return ReturnCode.Error(0,
                            "DataParser.LoginPH:" +
                            "Failed to Parse the Server List!");
                else
                    context.servers.add(name,value);
            }
            if(context.servers.count() == 0)
                return ReturnCode.Error(0,
                        "DataParser.LoginPH.parseServerList:" +
                                "Failed to find server List in HTML!");
            else
                return ReturnCode.SUCCESS;
        }
        /**/
        private ReturnCode parseRequestURL(LibOgame context, String pageContent) {
            String str = getTagContent(pageContent,
                    "<form id=\"loginForm\" name=\"loginForm\" method=\"post\" action=\"",
                    "\">", 1);
            if(str != null) {
                context.hc.setURL(str);
                return ReturnCode.SUCCESS;
            }
            else return ReturnCode.Error( 0,
                    "DataParser.parseRequestURL: Unable to find HTML Tag to parse.");
        }
    }

    private class OverviewPH extends PageHandler
    {
        protected void process() {
            Logger.println( "DataParser: Hello from OverviewPH!");
        }
    }
    /**/
    private class ResourcesPH extends PageHandler
    {
        protected void process() {
            Logger.println( "DataParser: Hello from ResourcesPH!");
        }
    }
    /**/
    private class ResourceSettingsPH extends PageHandler
    {
        protected void process() {
            Logger.println( "DataParser: Hello from ResourceSettingsPH!");
        }
    }
    /**/
    private class FacilitiesPH extends PageHandler
    {
        protected void process() {
            Logger.println( "DataParser: Hello from facilities!");
        }
    }
    /**/
    private class ResearchPH extends PageHandler
    {
        protected void process() {
            Logger.println( "DataParser: Hello from ResearchPH!");
        }
    }
    /**/
    private class ShipyardPH extends PageHandler
    {
        protected void process() {
            Logger.println( "DataParser: Hello from ShipyardPH!");
        }
    }
    /**/
    private class DefencePH extends PageHandler
    {
        protected void process() {
            Logger.println( "DataParser: Hello from DefencePH!");
        }
    }
    //The following class handles multiple pages so it needs to be passed the page identifier:
    private class FleetPH extends PageHandler
    {
        protected void process() {
            Logger.println( "DataParser: Hello from FleetPH!");
        }
        //create alternative process method:
        protected void process(int pageID) {
            Logger.println( "DataParser: Hello from Fleet1 PH!");
        }
    }
}
