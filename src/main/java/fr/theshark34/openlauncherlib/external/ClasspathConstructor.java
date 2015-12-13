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
package fr.theshark34.openlauncherlib.external;

import fr.theshark34.openlauncherlib.util.explorer.FileList;
import java.io.File;
import java.util.List;

/**
 * The Classpath Constructor
 *
 * <p>
 *     A FileList but that can construct a classpath String (libs.jar;test.jar;libs/myjar.jar)
 *     (with : instead of ; on Mac and Linux)
 * </p>
 *
 * @see FileList
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class ClasspathConstructor extends FileList
{
    /**
     * Empty Classpath Constructor
     */
    public ClasspathConstructor()
    {
        super();
    }

    /**
     * Classpath Constructor with pre-defined files
     *
     * @param classPath The files to add
     */
    public ClasspathConstructor(List<File> classPath)
    {
        super(classPath);
    }

    /**
     * Make the classpath
     *
     * @return Something like libs.jar;test.jar;libs/myjar.jar (with : instead of ; on Mac and Linux)
     */
    public String make()
    {
        String classPath = "";

        for (int i = 0; i < files.size(); i++)
            classPath += files.get(i).getAbsolutePath() + (i + 1 == files.size() ? "" : File.pathSeparator);

        return classPath;
    }
}
