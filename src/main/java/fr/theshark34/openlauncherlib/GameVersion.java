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
