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

import java.io.File;

/**
 * The Explorer
 *
 * <p>
 *     Use the explorer to explore some directories.
 * </p>
 *
 * Code example :
 *
 * <pre>
 *     Explorer explorer = new Explorer(new File("mydir"));
 *     explorer.cd("mysub");
 *     List files = explorer.files(); // Return the list of files in the mysub directory
 * </pre>
 *
 * The Explorer is extending ExploredDirectory, so you can use .files() or .subs() etc...
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 * @see ExploredDirectory
 */
public class Explorer extends ExploredDirectory
{
    /**
     * The Explorer
     *
     * @param directory The directory to explore
     */
    public Explorer(File directory)
    {
        super(directory);
    }

    /**
     * Change the directory to explore
     *
     * @param cd The directory to explore
     */
    public void cd(File cd)
    {
        this.directory = cd;
    }

    /**
     * Change the directory to explore as the given sub directory
     *
     * @param cd The name of the sub directory
     */
    public void cd(String cd)
    {
        this.directory = FilesUtil.get(directory, cd);
    }

    /**
     * Explore a directory
     *
     * @param dir The path of directory to explore
     *
     * @return An {@link ExploredDirectory} object
     */
    public static ExploredDirectory dir(String dir)
    {
        return dir(new File(dir));
    }

    /**
     * Explore a directory
     *
     * @param dir The directory to explore
     *
     * @return An {@link ExploredDirectory} object
     */
    public static ExploredDirectory dir(File dir)
    {
        return new ExploredDirectory(FilesUtil.dir(dir));
    }
}
