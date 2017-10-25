package pub.libogame;

import android.util.Log;

class DataParser
{
    private LibOgame context;

    public DataParser( LibOgame context ) {
        this.context = context;
    }

    public ReturnCode parse(String pageContent) {
        if (pageContent.contains("<form id=\"loginForm\" name=\"loginForm\" method=\"post\""))
            return LoginPageHandler.process(context, pageContent);
        else if (pageContent.contains("<body id=\"overview\""))
            return OverviewPageHandler.process(context, pageContent);
        else if (pageContent.contains("<body id=\"resources\""))
            return ResourcesPageHandler.process(context, pageContent);
        else if (pageContent.contains("<body id=\"resourceSettings\""))
            return ResourceSettingsPageHandler.process(context, pageContent);
        else if (pageContent.contains("<body id=\"station\""))
            return FacilitiesPageHandler.process(context, pageContent);
        else if (pageContent.contains("<body id=\"research\""))
            return ResearchPageHandler.process(context, pageContent);
        else if (pageContent.contains("<body id=\"shipyard\""))
            return ShipyardPageHandler.process(context, pageContent);
        else if (pageContent.contains("<body id=\"defense\""))
            return DefencePageHandler.process(context, pageContent);
        else if (pageContent.contains("<body id=\"fleet1\""))
            return FleetPageHandler.process(context, pageContent, 1);
        else return PageHandler.process(context, pageContent);
    }
    //-----------------------------------

    private static class PageHandler {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            return ReturnCode.Error(0, "DataParser: Failed to recognize the HTML content received!");
	}

        //Returns the nth accurance of a substring between two other strings.
        protected static String getTagContent(String pageContent, String tagStart, String tagEnd, int accuranceN) {
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

        protected static String getSelectedPlanet(String pageContent) {
            return getTagContent(pageContent, "<div id=\"selectedPlanetName\" class=\"textCenter\">", "</div>", 1);
        }
    }

    private static class LoginPageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", " Hello from LoginPageHandler! ");
            if(parseServerList(context, pageContent) == ReturnCode.SUCCESS &&
               parseRequestURL(context, pageContent) == ReturnCode.SUCCESS)
            {
                context.initialized = true;
                return ReturnCode.SUCCESS;
            }
            else return ReturnCode.Error(0, "DataParser: Failed to interprete html code as login html code");
        }
        /* Parse the server List from the login page html code. */
        protected static ReturnCode parseServerList(LibOgame context, String pageContent) {
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
        private static ReturnCode parseRequestURL(LibOgame context, String pageContent) {
            String str = getTagContent(pageContent, "<form id=\"loginForm\" name=\"loginForm\" method=\"post\" action=\"", "\">", 1);
            if(str != null) {
                context.auth.requestURL = str;
                return ReturnCode.SUCCESS;
            }
            else return ReturnCode.Error(0, "DataParser.parseRequestURL: Unable to find HTML Tag to parse.");
        }
    }

    private static class OverviewPageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", "Hello from OverviewPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static  class ResourcesPageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", "Hello from ResourcesPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class ResourceSettingsPageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", "Hello from ResourceSettingsPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class FacilitiesPageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", "Hello from facilities!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class ResearchPageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", "Hello from ResearchPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class ShipyardPageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", "Hello from ShipyardPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class DefencePageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", "Hello from DefencePageHandler!");
            return ReturnCode.ERROR;
        }
    }
    //The following class handles multiple pages so it needs to be passed the page identifier:
    private static class FleetPageHandler extends PageHandler
    {
        protected static ReturnCode process(LibOgame context, String pageContent) {
            Log.println(Log.WARN, "DataParser", "Hello from FleetPageHandler!");
            return ReturnCode.ERROR;
        }
        //create alternative process method:
        protected static ReturnCode process(LibOgame context, String pageContent, int pageID) {
            Log.println(Log.WARN, "DataParser", "Hello from Fleet1 PageHandler!");
            return ReturnCode.ERROR;
        }
    }
}
