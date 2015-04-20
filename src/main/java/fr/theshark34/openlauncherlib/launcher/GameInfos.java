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
package fr.theshark34.openlauncherlib.launcher;

import fr.theshark34.openlauncherlib.util.GameDir;

import java.io.File;

/**
 * The Game Infos
 *
 * <p>
 *     The Game Infos like the server name, if forge is enabled, etc...
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public class GameInfos {

    /**
     * The server name
     */
    private String serverName;

    /**
     * The Game Directory
     */
    private File gameDir;

    /**
     * If forge is enabled
     */
    private boolean forgeEnabled;

    /**
     * The Game Version containing launch informations
     */
    private GameVersion gameVersion;

    /**
     * Basic constructor
     *
     * @param serverName
     *            The server name
     * @param gameVersion
     *            The Game Version containing the launch informations
     * @param forgeEnabled
     *            If forge is enabled
     */
    public GameInfos(String serverName, GameVersion gameVersion, boolean forgeEnabled) {
        this.serverName = serverName;
        this.gameDir = GameDir.createGameDir(serverName);
        this.gameVersion = gameVersion;
        this.forgeEnabled = forgeEnabled;
    }

    /**
     * Advanced constructor
     *
     * @param serverName
     *            The server name
     * @param gameDir
     *            The game directory
     * @param gameVersion
     *            The Game Version containing the launch informations
     * @param forgeEnabled
     *            If forge is enabled
     */
    public GameInfos(String serverName, File gameDir, GameVersion gameVersion, boolean forgeEnabled) {
        this.serverName = serverName;
        this.gameDir = gameDir;
        this.gameVersion = gameVersion;
        this.forgeEnabled = forgeEnabled;
    }

    /**
     * Returns the server name
     *
     * @return The server name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Returns the Game Directory
     *
     * @return The Game Directory
     */
    public File getGameDir() {
        return this.gameDir;
    }

    /**
     * Returns the Game Version containing the launch informations
     *
     * @return The Game Version
     */
    public GameVersion getGameVersion() {
        return gameVersion;
    }

    /**
     * Returns true if forge is enabled, false if not
     *
     * @return If forge is enabled
     */
    public boolean isForgeEnabled() {
        return forgeEnabled;
    }

}
