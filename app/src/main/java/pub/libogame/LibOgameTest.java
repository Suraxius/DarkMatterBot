package pub.libogame;

public class LibOgameTest
{
    private static LibOgame libOgame;

    public static void main(String [] args)
    {
        Logger.println("Starting LibOgame Tests...");
        libOgame = new LibOgame();
        if( test_connect() )
            if( test_login() )
                Logger.println("All tests succeeded!");

        for(String message : ErrorHandler.getErrorMessages()) { Logger.println(message); }
    }

    public static boolean test_connect() {
        Logger.print("Testing connect... ");
        if (libOgame.connect("https://en.ogame.gameforge.com/") == ReturnCode.SUCCESS) {

            for(int i = 0; i < libOgame.getNumServers(); i++)
                System.out.println("Server " + i + ": " + libOgame.getServerName(i));

            Logger.println("[OK]");
            return true;
        }
        else {
            Logger.println("[FAILED]");
            return false;
        }
    }

    public static boolean test_login() {
        Logger.print("Testing login... ");
        if (libOgame.login(1, "surax1", "surax230") == ReturnCode.SUCCESS) {
            Logger.println("[OK]");
            return true;
        }
        else {
            Logger.println("[FAILED]");
            return false;
        }
    }
}
