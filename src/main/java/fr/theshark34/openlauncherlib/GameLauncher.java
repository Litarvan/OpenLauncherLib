/*
 * Copyright 2015 TheShark34
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
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
