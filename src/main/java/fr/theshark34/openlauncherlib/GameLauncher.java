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
	 * The Game Directory
	 */
	private File gameDir;

	/**
	 * The Game Version containing launch informations
	 */
	private GameVersion gameVersion;

	/**
	 * The Game Folder containing game folder organisation
	 */
	private GameFolder gameFolder;

	/**
	 * If forge is enabled
	 */
	private boolean forgeEnabled;

	/**
	 * Basic Constructor
	 *
	 * @param gameDir
	 *            The Game Directory
	 * @param gameVersion
	 *            The Game Version containing the launch informations
	 * @param gameFolder
	 *            The Game Folder containing game folder organisation
	 */
	public GameLauncher(File gameDir, GameVersion gameVersion, GameFolder gameFolder, boolean forgeEnabled) {
		this.gameDir = gameDir;
		this.gameVersion = gameVersion;
		this.gameFolder = gameFolder;
		this.forgeEnabled = forgeEnabled;
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
	 * Returns the Game Folder containing the game folder organisation
	 *
	 * @return The Game Folder
	 */
	public GameFolder getGameFolder() {
		return gameFolder;
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
