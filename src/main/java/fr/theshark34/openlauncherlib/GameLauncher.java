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
package fr.theshark34.openlauncherlib;

/**
 * The Game Launcher
 *
 * <p>
 *     The main class to launch the game with a game version and
 *     a game folder.
 * </p>
 * 
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public class GameLauncher {

    /**
     * The Game Infos like the server name and if forge is enabled
     */
    private GameInfos gameInfos;

	/**
	 * The Game Folder containing game folder organisation
	 */
	private GameFolder gameFolder;

	/**
	 * Basic Constructor
	 *
     * @param gameInfos
     *            The Game Infos like the server name and if forge is enabled
	 * @param gameFolder
	 *            The Game Folder containing game folder organisation
	 */
	public GameLauncher(GameInfos gameInfos, GameFolder gameFolder) {
		this.gameInfos = gameInfos;
		this.gameFolder = gameFolder;
	}

	/**
	 * Returns the Game Folder containing the game folder organisation
	 *
	 * @return The Game Folder
	 */
	public GameFolder getGameFolder() {
		return gameFolder;
	}

	/**
	 * Returns the Game Infos like the server name and if forge is enabled
	 *
	 * @return The Game Infos
	 */
	public GameInfos getGameInfos() {
		return gameInfos;
	}

}
