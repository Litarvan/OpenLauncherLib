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

/**
 * The Game Tweak
 *
 * <p>
 *     This class contains the 'tweak',
 * </p>
 *
 * @author TheShark34
 * @version 2.1-SNAPSHOT
 */
public abstract class GameTweak {

    /**
     * The LaunchWrapper main class
     */
    public static final String LAUNCHWRAPPER_MAIN_CLASS = "net.minecraft.launchwrapper.Launch";

    /**
     * The Forge GameTweak
     */
    public static final GameTweak FORGE = new GameTweak() {
        @Override
        public String getName() {
            return "FML Tweaker";
        }

        @Override
        public String getTweakClass(GameLauncher gameLauncher) {
            if(gameLauncher.getGameInfos().getGameVersion().getName().contains("1.8"))
                return "net.minecraftforge.fml.common.launcher.FMLTweaker";
            else
                return "cpw.mods.fml.common.launcher.FMLTweaker";
        }
    };

    /**
     * The Optifine GameTweak
     */
    public static final GameTweak OPTIFINE = new GameTweak() {
        @Override
        public String getName() {
            return "Optifine Tweaker";
        }

        @Override
        public String getTweakClass(GameLauncher gameLauncher) {
            return "optifine.OptiFineTweaker";
        }
    };

    /**
     * The Shader GameTweak
     */
    public static final GameTweak SHADER = new GameTweak() {
        @Override
        public String getName() {
            return "Shader Tweaker";
        }

        @Override
        public String getTweakClass(GameLauncher gameLauncher) {
            return "shadersmodcore.loading.SMCTweaker";
        }
    };

    /**
     * Return the name of the tweak
     *
     * @return The tweak name
     */
    public abstract String getName();

    /**
     * Return the name of the tweak class to give to the launch wrapper
     *
     * @return The tweak class
     */
    public abstract String getTweakClass(GameLauncher gameLauncher);

}
