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
package fr.theshark34.openlauncherlib.launcher.util;

import fr.theshark34.openlauncherlib.launcher.GameInfos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Username Saver
 *
 * <p>
 *     This class can save a username and load it.
 * </p>
 *
 * To save it :
 *
 * <code>
 *     UsernameSaver.setUsername(username);
 * </code>
 *
 * To load it
 *
 * <code>
 *     String username = UsernameSaver.getUsername(defaultValue);
 * </code>
 *
 * The default value is returned if the file doesn't exist or an exception happened
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public final class UsernameSaver {

    /**
     * The file where the username is saved/loaded
     */
    private File usernameFile;

    /**
     * First constructor, gets the game dir by the given game infos
     *
     * @param gameInfos The game infos to get the game dir
     */
	public UsernameSaver(GameInfos gameInfos) {
        this.usernameFile = new File(gameInfos.getGameDir(), "username.txt");
    }

    /**
     * Second constructor, the username file is directly given
     *
     * @param usernameFile The file where the username is saved/loaded
     */
    public UsernameSaver(File usernameFile) {
        this.usernameFile = usernameFile;
    }

    /**
     * Loads the saved username
     *
     * @param def
     *            The default value
     *
     * @return The loaded username, or the default value if an exception happened
     */
	public String getUsername(String def) {
		try {
            // Initializing the reader
			BufferedReader br = new BufferedReader(new FileReader(usernameFile));

            // Getting the username file first line
			String username = br.readLine();

            // Closing the reader
			br.close();

            // Returning what we read
			return username;
		} catch (IOException e) {
            // If an IOException happen that's not grave
		}
		return def;
	}

    /**
     * Saves the given username
     *
     * @param username
     *            The username to save
     */
	public void setUsername(String username) {
		try {
            // Initializing the writer
			FileWriter fw = new FileWriter(usernameFile);
			fw.write(username);
			fw.close();
		} catch(IOException e) {
		}
	}
	
}
