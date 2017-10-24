package pub.libogame;

import java.util.ArrayList;

public class ErrorHandler
{
    private static ArrayList<Integer> errorIDList = new ArrayList<Integer>();
    private static ArrayList<String> errorMessageList = new ArrayList<String>();

    public static ReturnCode log(int errorID) {
        errorIDList.add(errorID);
        return ReturnCode.ERROR;
    }

    public static ReturnCode log(String errorMessage) {
        errorMessageList.add(errorMessage);
        return ReturnCode.ERROR;
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
}
