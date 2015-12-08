package fr.theshark34.openlauncherlib.util.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ExploredDirectory
{
    private File directory;

    ExploredDirectory(File directory)
    {
        this.directory = directory;
    }

    public FileList list()
    {
        return new FileList((ArrayList<File>) Arrays.asList(FileCheck.list(this.directory)));
    }

    public ExploredDirectory sub(String directory)
    {
        return new ExploredDirectory(FileCheck.dir(this.directory, directory));
    }

    public File get(String file)
    {
        return FileCheck.get(this.directory, file);
    }

    public FileList subs()
    {
        File[] files = FileCheck.list(this.directory);
        ArrayList<File> dirs = new ArrayList<File>();

        for (File f : files)
            if (f.isDirectory())
                dirs.add(f);

        return new FileList(dirs);
    }

    public FileList files()
    {
        File[] files = FileCheck.list(this.directory);
        ArrayList<File> fs = new ArrayList<File>();

        for (File f : files)
            if (!f.isDirectory())
                fs.add(f);

        return new FileList(fs);
    }

    public File get()
    {
        return directory;
    }
}
