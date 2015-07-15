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
 * The Launcher Infos
 *
 * <p>
 *     This class contains the launcher infos like the main class, and the server name.
 * </p>
 *
 * @author TheShark34
 * @version 2.1-SNAPSHOT
 */
public class LauncherInfos {

    /**
     * The server name
     */
    private String serverName;

    /**
     * The launcher main class
     */
    private String mainClass;

    /**
     * Basic constructor
     *
     * @param serverName
     *            The server name
     * @param mainClass
     *            The launcher main class
     */
    public LauncherInfos(String serverName, String mainClass) {
        this.serverName = serverName;
        this.mainClass = mainClass;
    }

    /**
     * Returns the server name
     *
     * @return The server name
     */
    public String getServerName() {
        return this.serverName;
    }

    /**
     * Returns the launcher main class
     *
     * @return The launcher main class
     */
    public String getMainClass() {
        return this.mainClass;
    }

}
