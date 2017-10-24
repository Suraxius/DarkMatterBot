package pub.libogame;

/*
** Public methods in this class are calls to equivelant methods in actionGenerator.
** The purpose of this class is to check the datastore before calling methods in
** actionGenerator to determine if a call to the ogame server is really neccessarry.
** This class tries to minimize the requests send to the ogame server.
** If a function deems it fit not to call its counterpart from ActionGenerator, it
** should return the ReturnCode.REFUSED to indicate that the DataStore is still up to date.
** METHODS IN THIS CLASS SHOULD ONLY READ FROM DATASTORE NOT WRITE!
*/
class ActionValidator
{
    private ActionGenerator ag;
    private DataStore       ds;

    public ActionValidator( DataStore ds )
    {
        this.ds = ds;
        this.ag = new ActionGenerator( ds );
    }

    public ReturnCode initialize(String websiteURL) {
        if(!ds.initialized)
            return ag.initialize(websiteURL);
        else
            return ReturnCode.REFUSED;
    }

    public ReturnCode login(String server, String username, String password) {
        if(!ds.initialized)
            return ErrorHandler.log("Function can not be called; Ogame Library not initialized!");

        if(!ds.authenticated) return ag.login(server, username, password);
        else return ReturnCode.REFUSED;
    }
}
