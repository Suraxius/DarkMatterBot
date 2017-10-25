package pub.libogame;

import android.util.Log;

public class LibOgameTest
{
    private static LibOgame libOgame;

    public static void main(String [] args)
    {
        Log.println(Log.WARN, "LibOgameTest", "Starting LibOgame Tests...");
        libOgame = new LibOgame("https://en.ogame.gameforge.com/");
        if( test_login() )
            Log.println(Log.WARN, "LibOgameTest", "All tests succeeded!");

        for(String message : Logger.getErrorMessages()) { Log.println(Log.WARN, "LibOgameTest", message); }
    }

    public static boolean test_login() {
        Log.println(Log.WARN, "LibOgameTest", "Testing login... ");
        if (libOgame.auth.login(1, "surax1", "surax230") == ReturnCode.SUCCESS) {
            Log.println(Log.WARN, "LibOgameTest", "[OK]");
            return true;
        }
        else {
            Log.println(Log.WARN, "LibOgameTest", "[FAILED]");
            return false;
        }
    }
}
