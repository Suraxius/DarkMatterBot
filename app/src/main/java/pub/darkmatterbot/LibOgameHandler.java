package pub.darkmatterbot;

import pub.libogame.LibOgame;
import pub.libogame.LibOgameException;

class LibOgameHandler {
    protected static LibOgame libOgame;

    protected static void initialize() throws LibOgameException
    {
        libOgame = new LibOgame("https://en.ogame.gameforge.com/");
    }
}