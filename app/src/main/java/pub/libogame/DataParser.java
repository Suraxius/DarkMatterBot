package pub.libogame;

class DataParser
{
    private DataStore ds;

    public DataParser( DataStore dataStore ) {
        this.ds = dataStore;
    }

    public ReturnCode parse(String pageContent) {
        if (pageContent.contains("<form id=\"loginForm\" name=\"loginForm\" method=\"post\""))
            return LoginPageHandler.process(ds, pageContent);
        else if (pageContent.contains("<body id=\"overview\""))
            return OverviewPageHandler.process(ds, pageContent);
        else if (pageContent.contains("<body id=\"resources\""))
            return ResourcesPageHandler.process(ds, pageContent);
        else if (pageContent.contains("<body id=\"resourceSettings\""))
            return ResourceSettingsPageHandler.process(ds, pageContent);
        else if (pageContent.contains("<body id=\"station\""))
            return FacillitiesPageHandler.process(ds, pageContent);
        else if (pageContent.contains("<body id=\"research\""))
            return ResearchPageHandler.process(ds, pageContent);
        else if (pageContent.contains("<body id=\"shipyard\""))
            return ShipyardPageHandler.process(ds, pageContent);
        else if (pageContent.contains("<body id=\"defense\""))
            return DefencePageHandler.process(ds, pageContent);
        else if (pageContent.contains("<body id=\"fleet1\""))
            return FleetPageHandler.process(ds, pageContent, 1);
        else return PageHandler.process(ds, pageContent);
    }
    //-----------------------------------

    private static class PageHandler {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            return ErrorHandler.log("DataParser: Failed to recognize the HTML content received!");
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
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print(" Hello from LoginPageHandler! ");
            if(parseServerList(ds, pageContent) == ReturnCode.SUCCESS &&
               parseRequestURL(ds, pageContent) == ReturnCode.SUCCESS)
            {
                ds.initialized = true;
                return ReturnCode.SUCCESS;
            }
            else return ErrorHandler.log("DataParser: Failed to interprete html code as login html code");
        }
        /* Parse the server List from the login page html code. */
        protected static ReturnCode parseServerList(DataStore ds, String pageContent) {
            String entry, value, name;
            int count = 1;
            while((entry = getTagContent(pageContent, "<option", "</option>", count++)) != null) {
                entry = entry.replaceAll("\\s+","");
            	value = getTagContent(entry, "value=\"", "\"", 1);
            	name  = entry.substring(entry.indexOf(">")+1);

                //If name or value could not be parsed, end in error.
                if(name == null || value == null)
                	return ErrorHandler.log("DataParser.LoginPageHandler: Failed to Parse the Server List!");
                else
                    ds.serverList.add(name,value);
            }
            if(ds.serverList.size() == 0)
                return ErrorHandler.log("DataParser.LoginPageHandler.parseServerList: Failed to find server List in HTML!");
            else
                return ReturnCode.SUCCESS;
        }
        /**/
        protected static ReturnCode parseRequestURL(DataStore ds, String pageContent) {
            String str = getTagContent(pageContent, "<form id=\"loginForm\" name=\"loginForm\" method=\"post\" action=\"", "\">", 1);
            if(str != null) {
                ds.requestURL = str;
                return ReturnCode.SUCCESS;
            }
            else return ErrorHandler.log("DataParser.parseRequestURL: Unable to find HTML Tag to parse.:");
        }
    }

    private static class OverviewPageHandler extends PageHandler
    {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print("Hello from OverviewPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static  class ResourcesPageHandler extends PageHandler
    {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print("Hello from ResourcesPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class ResourceSettingsPageHandler extends PageHandler
    {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print("Hello from ResourceSettingsPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class FacillitiesPageHandler extends PageHandler
    {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print("Hello from FacilliteisPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class ResearchPageHandler extends PageHandler
    {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print("Hello from ResearchPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class ShipyardPageHandler extends PageHandler
    {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print("Hello from ShipyardPageHandler!");
            return ReturnCode.ERROR;
        }
    }
    /**/
    private static class DefencePageHandler extends PageHandler
    {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print("Hello from DefencePageHandler!");
            return ReturnCode.ERROR;
        }
    }
    //The following class handles multiple pages so it needs to be passed the page identifier:
    private static class FleetPageHandler extends PageHandler
    {
        protected static ReturnCode process(DataStore ds, String pageContent) {
            Logger.print("Hello from FleetPageHandler!");
            return ReturnCode.ERROR;
        }
        //create alternative process method:
        protected static ReturnCode process(DataStore ds, String pageContent, int pageID) {
            Logger.print("Hello from Fleet1 PageHandler!");
            return ReturnCode.ERROR;
        }
    }
}
