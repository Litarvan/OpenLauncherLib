/*
 * Copyright 2015 Adrien Navratil
 *
 * This file is part of the OpenLauncherLib.

 * The OpenLauncherLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The OpenLauncherLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the OpenLauncherLib.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.theshark34.openlauncherlib.util.explorer;

import fr.theshark34.openlauncherlib.FailException;
import java.io.File;
import java.util.ArrayList;

/**
 * The Files Util class
 *
 * <p>
 *     Contains some useful methods about the files.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class FilesUtil
{
    /**
     * List the sub-files and sub-directory of a given one, recursively
     *
     * @param directory The directory to list
     *
     * @return The generated list of files
     */
    public static ArrayList<File> listRecursive(File directory)
    {
        ArrayList<File> files = new ArrayList<File>();
        File[] fs = directory.listFiles();
        if (fs == null)
            return files;

        for (File f : fs)
        {
            if (f.isDirectory())
                files.addAll(listRecursive(f));

            files.add(f);
        }

        return files;
    }

    /**
     * Get a file in a directory and checks if it is existing
     * (throws a FailException if not)
     *
     * @param root The root directory where the file is supposed to be
     * @param file The name of the file to get
     *
     * @return The found file
     */
    public static File get(File root, String file)
    {
        File f = new File(root, file);
        if (!f.exists())
            throw new FailException("Given file/directory doesn't exist !");

        return f;
    }

    /**
     * Return the given directory, but check if it is a directory
     *
     * @param d The directory to check
     *
     * @return The given directory
     */
    public static File dir(File d)
    {
        if (!d.isDirectory())
            throw new FailException("Given directory is not one !");

        return d;
    }

    /**
     * Mix between the get method and the dir method
     *
     * @param root The directory where the other one is supposed to be
     * @param dir  The name of the directory to get
     *
     * @return The got directory
     *
     * @see #get(File, String)
     * @see #dir(File)
     */
    public static File dir(File root, String dir)
    {
        return dir(get(root, dir));
    }

    /**
     * Return the list of the files of the given directory, but
     * checks if it is a directory, and return an empty file list if listFiles returns null
     *
     * @param dir The directory to list
     *
     * @return The files in the given directory
     *
     * @see #dir(File)
     */
    public static File[] list(File dir)
    {
        File[] files = dir(dir).listFiles();

        return files == null ? new File[0] : files;
    }
}
