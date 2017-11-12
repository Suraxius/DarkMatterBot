package pub.libogame;

//import android.util.Log;

import java.util.ArrayList;

public class Logger
{
    private static final Logger ourInstance = new Logger();
    private static ArrayList<Integer> errorIDList = new ArrayList<Integer>();
    private static ArrayList<String> errorMessageList = new ArrayList<String>();

    public static Logger getInstance() {
        return ourInstance;
    }

    private Logger() {}

    public static void log(int errorCode, String errorMessage) {
        errorIDList.add(errorCode);
        errorMessageList.add(errorMessage);
        //Log.println(Log.WARN, "Error Code " + Integer.toString(errorCode), errorMessage);
    }

    public static int[] getErrorIDs() {
        int listSize = errorIDList.size();
        int[] errorArray = new int[listSize];
        for(int i = 0; i < listSize; i++) { errorArray[i] = errorIDList.get(i); }
        return errorArray;
    }

    public static String[] getErrorMessages() {
        int listSize = errorMessageList.size();
        String[] errorArray = new String[listSize];
        for(int i = 0; i < listSize; i++) { errorArray[i] = errorMessageList.get(i); }
        return errorArray;
    }

    public static void println(String label, String message) {
        //Log.println(Log.WARN, label, message); //Android
	System.out.println( label + ": " + message ); //Java
    }
}
