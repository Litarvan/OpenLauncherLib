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

public class ClasspathConstructor extends FileList
{
    public ClasspathConstructor()
    {
        super();
    }

    public ClasspathConstructor(List<File> classPath)
    {
        super(classPath);
    }

    public String make()
    {
        String classPath = "";

        for (int i = 0; i < files.size(); i++)
            classPath += files.get(i).getAbsolutePath() + (i + 1 == files.size() ? "" : File.pathSeparator);

        return classPath;
    }
}
