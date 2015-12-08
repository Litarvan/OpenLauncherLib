package fr.theshark34.openlauncherlib.util.explorer;

import fr.theshark34.openlauncherlib.FailException;
import java.io.File;

public class FileCheck
{
    public static File get(File root, String file)
    {
        File f = new File(root, file);
        if (!f.exists())
            throw new FailException("Given file/directory doesn't exist !");

        return f;
    }

    public static File dir(File d)
    {
        if (!d.isDirectory())
            throw new FailException("Given file/directory doesn't exist !");

        return d;
    }

    public static File dir(File root, String dir)
    {
        return dir(get(root, dir));
    }

    public static File[] list(File dir)
    {
        File[] files = dir.listFiles();

        return files == null ? new File[0] : files;
    }
}
