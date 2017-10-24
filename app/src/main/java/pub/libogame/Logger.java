package pub.libogame;

public class Logger
{
    private static String textLog = "";

    public static void print( String text )
    {
        System.out.println(text);
        //textLog += text;
    }

    public static void println( String text )
    {
        System.out.println(text);
        //textLog += text + "\n";
    }

    public static void show()
    {
        System.out.println(textLog);
    }
}
