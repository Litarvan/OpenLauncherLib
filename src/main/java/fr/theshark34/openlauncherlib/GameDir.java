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
