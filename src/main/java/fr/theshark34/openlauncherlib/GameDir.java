/*
 * Copyright 2015 TheShark34
 *
 * This file is part of the OpenLauncherLib.

 * The OpenLauncherLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The OpenLauncherLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the OpenLauncherLib.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.theshark34.openlauncherlib;

import java.io.File;

/**
 * The Game Dir
 *
 * <p>
 *     This class contains a method to generate the game directory of
 *     the current OS like the default of Minecraft.
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public class GameDir {

    /**
     * Generate the game directory of the current OS by the given
     * server name, like the default of Minecraft.
     *
     * @param servername
     *            The server name that will be the directory
     *            name.
     * @return The generated game directory
     */
    public static File createGameDir(String servername) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            return new File(System.getProperty("user.home")
                    + "\\AppData\\Roaming\\." + servername);
        else if (os.contains("mac"))
            return new File(System.getProperty("user.home")
                    + "/Library/Application Support/" + servername);
        else
            return new File(System.getProperty("user.home") + "/." + servername);
    }

}
