/*
 * Copyright 2015 TheShark34
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
package fr.theshark34.openlauncherlib.bootstrap;

import java.io.File;

/**
 * The Launcher Classpath
 *
 * <p>
 *     This class contains the launcher classpath, the launcher path and its libraries
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public class LauncherClasspath {

    /**
     * The launcher
     */
    private File launcher;

    /**
     * The folder containing the libraries
     */
    private File libsFolder;

    /**
     * Basic constructor
     *
     * @param launcher
     *            The launcher
     */
    public LauncherClasspath(File launcher) {
        this(launcher, null);
    }

    /**
     * Advanced constructor
     *
     * @param launcher
     *            The launcher
     * @param libsFolder
     *            The folder containing the libraries
     */
    public LauncherClasspath(File launcher, File libsFolder) {
        this.launcher = launcher;
        this.libsFolder = libsFolder;
    }

    /**
     * Returns the launcher file
     *
     * @return The launcher
     */
    public File getLauncher() {
        return launcher;
    }

    /**
     * Returns the folder containing the libraries
     *
     * @return The libs folder
     */
    public File getLibsFolder() {
        return libsFolder;
    }

}
