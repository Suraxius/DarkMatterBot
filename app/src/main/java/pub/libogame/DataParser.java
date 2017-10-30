package pub.libogame;

import android.util.Log;

import pub.darkmatterbot.OverviewFragment;

class DataParser
{
    private LibOgame context;
    private String   pageContent;
    private LoginPageHandler            loginPageHandler            = new LoginPageHandler();
    private OverviewPageHandler         overviewPageHandler         = new OverviewPageHandler();
    private ResourcesPageHandler        resourcesPageHandler        = new ResourcesPageHandler();
    private ResourceSettingsPageHandler resourceSettingsPageHandler = new ResourceSettingsPageHandler();
    private FacilitiesPageHandler       facilitiesPageHandler       = new FacilitiesPageHandler();
    private ResearchPageHandler         researchPageHandler         = new ResearchPageHandler();
    private ShipyardPageHandler         shipyardPageHandler         = new ShipyardPageHandler();
    private DefencePageHandler          defencePageHandler          = new DefencePageHandler();
    private FleetPageHandler            fleetPageHandler            = new FleetPageHandler();

    public DataParser( LibOgame context ) {
        this.context = context;
    }

    public void parse(String pageContent) throws LibOgameException {
        this.pageContent = pageContent;

        if (pageContent.contains("<form id=\"loginForm\" name=\"loginForm\" method=\"post\""))
            loginPageHandler.process();
        else if (pageContent.contains("<body id=\"overview\""))
            overviewPageHandler.process();
        else if (pageContent.contains("<body id=\"resources\""))
            resourcesPageHandler.process();
        else if (pageContent.contains("<body id=\"resourceSettings\""))
            resourceSettingsPageHandler.process();
        else if (pageContent.contains("<body id=\"station\""))
            facilitiesPageHandler.process();
        else if (pageContent.contains("<body id=\"research\""))
            researchPageHandler.process();
        else if (pageContent.contains("<body id=\"shipyard\""))
            shipyardPageHandler.process();
        else if (pageContent.contains("<body id=\"defense\""))
            defencePageHandler.process();
        else if (pageContent.contains("<body id=\"fleet1\""))
            fleetPageHandler.process(1);
        else throw new LibOgameException("DataParser.parse(): HTML content could not be identified!");
    }
    //-----------------------------------

    private abstract class PageHandler
    {
        protected abstract void process();

        //Returns the nth occurred of a substring between two other strings.
        protected String getTagContent(String pageContent, String tagStart, String tagEnd, int accuranceN) {
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
            return getTagContent(pageContent, "<div id=\"selectedPlanetName\" class=\"textCenter\">", "</div>", 1);
        }
    }

    private class LoginPageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", " Hello from LoginPageHandler! ");
            parseServerList(context, pageContent);
            parseRequestURL(context, pageContent);
        }
        /* Parse the server List from the login page html code. */
        protected ReturnCode parseServerList(LibOgame context, String pageContent) {
            String entry, value, name;
            int count = 1;
            while((entry = getTagContent(pageContent, "<option", "</option>", count++)) != null) {
                entry = entry.replaceAll("\\s+","");
            	value = getTagContent(entry, "value=\"", "\"", 1);
            	name  = entry.substring(entry.indexOf(">")+1);

                //If name or value could not be parsed, end in error.
                if(name == null || value == null)
                	return ReturnCode.Error(0, "DataParser.LoginPageHandler: Failed to Parse the Server List!");
                else
                    context.servers.add(name,value);
            }
            if(context.servers.count() == 0)
                return ReturnCode.Error(0, "DataParser.LoginPageHandler.parseServerList: Failed to find server List in HTML!");
            else
                return ReturnCode.SUCCESS;
        }
        /**/
        private ReturnCode parseRequestURL(LibOgame context, String pageContent) {
            String str = getTagContent(pageContent, "<form id=\"loginForm\" name=\"loginForm\" method=\"post\" action=\"", "\">", 1);
            if(str != null) {
                context.hc.requestURL = str;
                return ReturnCode.SUCCESS;
            }
            else return ReturnCode.Error(0, "DataParser.parseRequestURL: Unable to find HTML Tag to parse.");
        }
    }

    private class OverviewPageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", "Hello from OverviewPageHandler!");
        }
    }
    /**/
    private class ResourcesPageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", "Hello from ResourcesPageHandler!");
        }
    }
    /**/
    private class ResourceSettingsPageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", "Hello from ResourceSettingsPageHandler!");
        }
    }
    /**/
    private class FacilitiesPageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", "Hello from facilities!");
        }
    }
    /**/
    private class ResearchPageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", "Hello from ResearchPageHandler!");
        }
    }
    /**/
    private class ShipyardPageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", "Hello from ShipyardPageHandler!");
        }
    }
    /**/
    private class DefencePageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", "Hello from DefencePageHandler!");
        }
    }
    //The following class handles multiple pages so it needs to be passed the page identifier:
    private class FleetPageHandler extends PageHandler
    {
        protected void process() {
            Log.println(Log.WARN, "DataParser", "Hello from FleetPageHandler!");
        }
        //create alternative process method:
        protected void process(int pageID) {
            Log.println(Log.WARN, "DataParser", "Hello from Fleet1 PageHandler!");
        }
    }
}
