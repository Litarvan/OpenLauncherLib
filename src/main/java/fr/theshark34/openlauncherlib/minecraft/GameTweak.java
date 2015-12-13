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
package fr.theshark34.openlauncherlib.minecraft;

/**
 * The Game Tweak
 *
 * <p>
 *     The Game Tweak is like a class, that patch Minecraft at launching.
 *     If one tweak is enabled, the system will launch the launchwrapper library,
 *     that is the library that patches the game.
 *
 *     One or more tweaking class will be given depending on the given Game Tweaks.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-SNAPSHOT
 * @since 2.1.0-SNAPSHOT
 */
public abstract class GameTweak
{

    /**
     * The LaunchWrapper main class
     */
    public static final String LAUNCHWRAPPER_MAIN_CLASS = "net.minecraft.launchwrapper.Launch";

    /**
     * The Forge GameTweak
     */
    public static final GameTweak FORGE = new GameTweak()
    {
        @Override
        public String getName()
        {
            return "FML Tweaker";
        }

        @Override
        public String getTweakClass(GameInfos infos)
        {
            if (infos.getGameVersion().getName().contains("1.8"))
                return "net.minecraftforge.fml.common.launcher.FMLTweaker";
            else
                return "cpw.mods.fml.common.launcher.FMLTweaker";
        }
    };

    /**
     * The Optifine GameTweak
     */
    public static final GameTweak OPTIFINE = new GameTweak()
    {
        @Override
        public String getName()
        {
            return "Optifine Tweaker";
        }

        @Override
        public String getTweakClass(GameInfos infos)
        {
            return "optifine.OptiFineTweaker";
        }
    };

    /**
     * The Shader GameTweak
     */
    public static final GameTweak SHADER = new GameTweak()
    {
        @Override
        public String getName()
        {
            return "Shader Tweaker";
        }

        @Override
        public String getTweakClass(GameInfos infos)
        {
            return "shadersmodcore.loading.SMCTweaker";
        }
    };

    /**
     * The AssetPatcher GameTweak
     */
    public static final GameTweak ASSET_PATCHER = new GameTweak()
    {
        @Override
        public String getName()
        {
            return "Asset Patcher";
        }

        @Override
        public String getTweakClass(GameInfos infos)
        {
            return "fr.theshark34.openlauncherlib.assetpatcher.AssetPatcherTweaker";
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
     * @param infos The current GameInfos
     *
     * @return The tweak class
     */
    public abstract String getTweakClass(GameInfos infos);

}
