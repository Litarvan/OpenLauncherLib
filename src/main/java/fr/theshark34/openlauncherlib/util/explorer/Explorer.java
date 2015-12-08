package fr.theshark34.openlauncherlib.util.explorer;

import java.io.File;

public class Explorer
{
    public static ExploredDirectory dir(String dir)
    {
        return dir(new File(dir));
    }

    public static ExploredDirectory dir(File dir)
    {
        return new ExploredDirectory(FileCheck.dir(dir));
    }
}
