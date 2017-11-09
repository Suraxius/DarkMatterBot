package pub.libogame;

public enum ReturnCode
{
    SUCCESS,
    ERROR,
    REFUSED;

    public static ReturnCode Error(int errorCode, String errorMessage) {
        Logger.log(errorCode, errorMessage);
        return ERROR;
    }
};
