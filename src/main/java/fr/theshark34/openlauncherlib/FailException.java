package fr.theshark34.openlauncherlib;

/**
 * Created by Adrien on 08/12/2015.
 */
public class FailException extends RuntimeException
{
    public FailException(String message)
    {
        super("Ups ! Looks like you failed : " + message);
    }
}
