# Dark Matter Bot #
### The OGame Bot for Android ###
All the serious players use OGame bots to keep their account safe while they can not be online.
And now you don't even need a desktop Computer running to do so.
Automate your gameplay using Dark Matter Bot!

# LibOGame #

To interface with ogame servers, Dark Matter Bot uses libogame.
The following is the API reference of libogame:

| Type    | Scope    | pub.libogame  |
| ------- | -------- | ------------- |
| Constr. | Initial  | **`LibOgame()`**<br>*A Constructor for LibOgame that requires no arguments. Please not that you have to call the connect( "OGame Website URL" ) method before any other functions will work.*
| Constr. | Initial  | **`LibOgame( String "OGame Website URL (Login Page)" )`**<br>*A Constructor for LibOgame. This constructor can be used instead of the argumentless constructor that would otherwise have to be followed by a call to the connect( String "OGame Website URL") method.*
| Method  | Initial  | **`ReturnCode connect( String "OGame Website URL (Login Page)" )`**<br>*Retrieves the available server list from the Ogame website. This needs to be called if LibOgame was constructed using the argumentless constructor. Returns ReturnCode.SUCCESS on success or ReturnCode.ERROR along with an error message in the error log on failure.*
| | | |
| Method  | Login    | **`int login( int ogameServerIndex, String username, String password )`**<br>*Try to log into an ogame server.*
| Method  | Login    | **`int getNumOgameServers()`**<br>*Get the number of ogame servers available in the list.*
| Method  | Login    | **`String getOgameServerName(int index)`**<br>*Get the name of an Ogame server by list index.*
| | | |
| Method  | Empire   | **`int getLevel(TechnologyID technologyID)`**<br>*Get the current level of the researched Technology of type technologyID.*
| Method  | Empire   | **`ReturnCode research( TechnologyID technologyID )`**<br>*Try to research the technology specified by technologyID. Returns ReturnCode.SUCCESS on success or ReturnCode.ERROR along with an error message in the error log on failure.*
| Method  | Empire   | **`String getPlanetName( int index )`**<br>*Get the name of a planet by list index.*
| Method  | Empire   | **`int getNumPlanets()`**<br>*Get the number of Planets in the list.*
| Method  | Empire   | **`int getNumShipsOnRoute()`**<br>*Get number of ships currently on route between planets.*
| Enum    | Empire   | **`TechnologyID`**<br>*Specifies the following values: NOT ADDED YET*
| Enum    | Empire   | **`ShipID`**<br>*Specifies the following values: NOT ADDED YET*
|| | | |
| Method  | Debug    | **`String getErrorMessage()`**<br>*Returns one or more error messages. Used to retrieve the error message if a method returns ReturnCode.ERROR.*
| Method  | Debug    | **`int getErrorID()`**<br>*Returns the error ID of the last error logged.*
| Enum    | Debug    | **`ReturnCode`**<br>*The Exit status of most LibOgame methods. It has 2 values. Either SUCCESS or ERROR.<br>If a method returns the ERROR state, the function will also have added an error message to the Error log.*
| | | |
| Method  | Planet   | **`ReturnCode upgrade( int planetIndex, PlantID plantID )`**<br>*Try to upgrade the type of plant specified by plantID on planet specified by planetIndex. Returns ReturnCode.SUCCESS on success or ReturnCode.ERROR along with an error message in the error log on failure.*
| Method  | Planet   | **`ReturnCode upgrade( int planetIndex, FacilityID facilityID )`**<br>*Try to upgrade the type of Facility specified by facilityID on planet specified by planetIndex. Returns ReturnCode.SUCCESS on success or ReturnCode.ERROR along with an error message in the error log on failure.*
| Method  | Planet   | **`ReturnCode downgrade( int planetIndex, PlantID plantID )`**<br>*Try to downgrade the type of plant specified by plantID on planet specified by planetIndex. Returns ReturnCode.SUCCESS on success or ReturnCode.ERROR along with an error message in the error log on failure.*
| Method  | Planet   | **`ReturnCode downgrade( int planetIndex, FacilityID facilityID )`**<br>*Try to downgrade the type of Facility specified by facilityID on planet specified by planetIndex. Returns ReturnCode.SUCCESS on success or ReturnCode.ERROR along with an error message in the error log on failure.*
| Method  | Planet   | **`int getNumShips(int planetIndex, ShipID shipID)`**<br>*Get the number of ships of type shipID on planet specified by planetIndex.*
| Method  | Planet   | **`int getLevel(int planetIndex, PlantID plantID)`**<br>*Get the current level of Plant-Structure of type plantID on Planet specified by planetIndex.*
| Method  | Planet   | **`int getLevel(int planetIndex, FacilityID facilityID)`**<br>*Get the current level of Facility-Structure of type facilityID on Planet specified by planetIndex.*
| Enum    | Planet   | **`PlantID`**<br>*Specifies the following values: METAL_MINE, CRYSTAL_MINE, DEUTERIUM_SYNTHESIZER, SOLAR_POWER_PLANT, FUSION_REACTOR, SOLAR_SATALITE, METAL_STORAGE, CRYSTAL_STORAGE, DEUTERIUM_STORAGE*
| Enum    | Planet   | **`FacilityID`**<br>*Specifies the following values: NOT ADDED YET*
| Enum    | Planet   | **`DefenceID`**<br>*Specifies the following values: NOT ADDED YET*
