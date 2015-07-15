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

import java.io.File;
import java.util.ArrayList;

/**
 * The Game Type
 *
 * <p>
 *     This class contains the specifics informations about a version
 *     of Minecraft
 * </p>
 *
 * @author TheShark34
 * @version 2.1-SNAPSHOT
 */
public abstract class GameType {

    /**
     * The 1.5.2 or Lower game type
     */
    public static final GameType V1_5_2_LOWER = new GameType() {
        public String getName() {
            return "1.5.2 or lower";
        }

        public String getMainClass(GameLauncher gameLauncher) {
            return "net.minecraft.launchwrapper.Launch";
        }

        public ArrayList<String> getLaunchArgs(GameLauncher gameLauncher) {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add(gameLauncher.getAuthInfos().getUsername());

            arguments.add(gameLauncher.getAuthInfos().getUuid());

            arguments.add("--gameDir");
            arguments.add(gameLauncher.getGameInfos().getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(gameLauncher.getGameInfos().getGameDir(), gameLauncher.getGameFolder().getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath() + "/virtual/legacy/");

            return arguments;
        }
    };

    /**
     * The 1.7.2 or Lower game type
     */
    public static final GameType V1_7_2_LOWER = new GameType() {
        public String getName() {
            return "1.7.2 or lower";
        }

        public String getMainClass(GameLauncher gameLauncher) {
            return "net.minecraft.client.main.Main";
        }

        public ArrayList<String> getLaunchArgs(GameLauncher gameLauncher) {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add("--username=" + gameLauncher.getAuthInfos().getUsername());

            arguments.add("--accessToken");
            arguments.add(gameLauncher.getAuthInfos().getAccessToken());

            arguments.add("--version");
            arguments.add(gameLauncher.getGameInfos().getGameVersion().getName());

            arguments.add("--gameDir");
            arguments.add(gameLauncher.getGameInfos().getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(gameLauncher.getGameInfos().getGameDir(), gameLauncher.getGameFolder().getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath() + "/virtual/legacy/");

            arguments.add("--userProperties");
            arguments.add("{}");

            arguments.add("--uuid");
            arguments.add(gameLauncher.getAuthInfos().getUuid());

            arguments.add("--userType");
            arguments.add("legacy");

            return arguments;
        }
    };

    /**
     * The 1.7.10 Game Type
     */
    public static final GameType V1_7_10 = new GameType() {
        public String getName() {
            return "1.7.10";
        }

        public String getMainClass(GameLauncher gameLauncher) {
            return "net.minecraft.client.main.Main";
        }

        public ArrayList<String> getLaunchArgs(GameLauncher gameLauncher) {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add("--username=" + gameLauncher.getAuthInfos().getUsername());

            arguments.add("--accessToken");
            arguments.add(gameLauncher.getAuthInfos().getAccessToken());

            arguments.add("--version");
            arguments.add(gameLauncher.getGameInfos().getGameVersion().getName());

            arguments.add("--gameDir");
            arguments.add(gameLauncher.getGameInfos().getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(gameLauncher.getGameInfos().getGameDir(), gameLauncher.getGameFolder().getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath());

            arguments.add("--assetIndex");
            arguments.add(gameLauncher.getGameInfos().getGameVersion().getName());

            arguments.add("--userProperties");
            arguments.add("{}");

            arguments.add("--uuid");
            arguments.add(gameLauncher.getAuthInfos().getUuid());

            arguments.add("--userType");
            arguments.add("legacy");

            return arguments;
        }
    };

    /**
     * The 1.8 or higher Game Type
     */
    public static final GameType V1_8_HIGHER = new GameType() {
        public String getName() {
            return "1.8 or higher";
        }

        public String getMainClass(GameLauncher gameLauncher) {
            return "net.minecraft.client.main.Main";
        }

        public ArrayList<String> getLaunchArgs(GameLauncher gameLauncher) {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add("--username=" + gameLauncher.getAuthInfos().getUsername());

            arguments.add("--accessToken");
            arguments.add(gameLauncher.getAuthInfos().getAccessToken());

            arguments.add("--version");
            arguments.add(gameLauncher.getGameInfos().getGameVersion().getName());

            arguments.add("--gameDir");
            arguments.add(gameLauncher.getGameInfos().getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(gameLauncher.getGameInfos().getGameDir(), gameLauncher.getGameFolder().getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath());

            arguments.add("--assetIndex");
            arguments.add(gameLauncher.getGameInfos().getGameVersion().getName());

            arguments.add("--userProperties");
            arguments.add("{}");

            arguments.add("--uuid");
            arguments.add(gameLauncher.getAuthInfos().getUuid());

            arguments.add("--userType");
            arguments.add("legacy");

            return arguments;
        }
    };

    /**
     * The name of the Game Type
     *
     * @return Returns the name of the game type
     */
    public abstract String getName();

    /**
     * Returns the main class of the Minecraft Game Type
     *
     * @param gameLauncher
     *            The current Game Launcher instance
     * @return The main class
     */
    public abstract String getMainClass(GameLauncher gameLauncher);

    /**
     * Returns the launch arguments of the Minecraft Game Type
     *
     * @param gameLauncher
     *            The current Game Launcher instance
     * @return The launch arguments
     */
    public abstract ArrayList<String> getLaunchArgs(GameLauncher gameLauncher);


}
