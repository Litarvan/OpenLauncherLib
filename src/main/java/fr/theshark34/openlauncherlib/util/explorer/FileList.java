package fr.theshark34.openlauncherlib.util.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileList
{
    private ArrayList<File> files = new ArrayList<File>();

    public FileList()
    {
    }

    public FileList(ArrayList<File> files)
    {
        this.files = files;
    }

    public void add(File[] files)
    {
        this.files.addAll(Arrays.asList(files));
    }

    public void add(ArrayList<File> files)
    {
        this.files.addAll(files);
    }

    public ArrayList<File> get()
    {
        return files;
    }
}
