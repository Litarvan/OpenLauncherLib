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

/**
 * The Bootstrap main class
 *
 * <p>
 *     This is the main bootstrap class, to launch the launcher !
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public class Bootstrap {

    /**
     * The launcher classpath containing the launcher file, and the libs folder
     */
    private LauncherClasspath launcherClasspath;

    /**
     * The launcher infos containing the server name, and the main class
     */
    private LauncherInfos launcherInfos;

    /**
     * Basic constructor
     *
     * @param launcherClasspath
     * @param launcherInfos
     */
    public Bootstrap(LauncherClasspath launcherClasspath, LauncherInfos launcherInfos) {
        this.launcherClasspath = launcherClasspath;
        this.launcherInfos = launcherInfos;
    }

    /**
     * Returns the launcher classpath containing the launcher file, and the libs folder
     *
     * @return The launcher classpath
     */
    public LauncherClasspath getLauncherClasspath() {
        return this.launcherClasspath;
    }

    /**
     * Returns the launcher infos containing the server name, and the main class
     *
     * @return The launcher infos
     */
    public LauncherInfos launcherInfos() {
        return this.launcherInfos;
    }

}
