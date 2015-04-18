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

/**
 * The Game Version
 *
 * <p>
 *     This class contains the specificals informations about a version
 *     of Minecraft
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public abstract class GameVersion {

    public static final GameVersion V1_7_2_LOWER = new GameVersion() {
        public String getVMArguments(GameLauncher gameLauncher) {
            StringBuilder builder = new StringBuilder();
            builder.append("");
            return "";
        }

        public String getLaunchArguments(GameLauncher gameLauncher) {
            StringBuilder builder = new StringBuilder();
            builder.append("");
            return "";
        }
    };

    public static final GameVersion V1_7_10 = new GameVersion() {
        public String getVMArguments(GameLauncher gameLauncher) {
            StringBuilder builder = new StringBuilder();
            builder.append("");
            return "";
        }

        public String getLaunchArguments(GameLauncher gameLauncher) {
            StringBuilder builder = new StringBuilder();
            builder.append("");
            return "";
        }
    };

    public static final GameVersion V1_8_BETTER = new GameVersion() {
        public String getVMArguments(GameLauncher gameLauncher) {
            StringBuilder builder = new StringBuilder();
            builder.append("");
            return "";
        }

        public String getLaunchArguments(GameLauncher gameLauncher) {
            StringBuilder builder = new StringBuilder();
            builder.append("");
            return "";
        }
    };

    public abstract String getVMArguments(GameLauncher gameLauncher);
    public abstract String getLaunchArguments(GameLauncher gameLauncher);
}
