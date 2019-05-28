/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil
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

import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.LogUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * The Game Infos
 *
 * <p>
 *     The Game Infos like the server name, the version, the tweaks, etc...
 * </p>
 *
 * @author Litarvan
 * @version 3.0.4
 * @since 2.0.0-SNAPSHOT
 */
public class GameInfos
{
    /**
     * The server name
     */
    private String serverName;

    /**
     * The Game Directory
     */
    private File gameDir;

    /**
     * The current tweaks (Shader, Optifine, Forge, or just Vanilla)
     */
    private GameTweak[] tweaks;

    /**
     * The Game Version containing launch informations
     */
    private GameVersion gameVersion;

    /**
     * Basic constructor
     *
     * @param serverName  The server name
     * @param gameVersion The Game Version containing the launch informations
     * @param tweaks      The current tweaks (Shader, Optifine, Forge, or just Vanilla)
     */
    public GameInfos(String serverName, GameVersion gameVersion, GameTweak[] tweaks)
    {
        this(serverName, GameDirGenerator.createGameDir(serverName), gameVersion, tweaks);
    }

    /**
     * Advanced constructor
     *
     * @param serverName  The server name
     * @param gameDir     The game directory
     * @param gameVersion The Game Version containing the launch informations
     * @param tweaks      The current tweaks (Shader, Optifine, Forge, or just Vanilla)
     */
    public GameInfos(String serverName, File gameDir, GameVersion gameVersion, GameTweak[] tweaks)
    {
        this.serverName = serverName;
        this.gameDir = gameDir;
        this.gameVersion = gameVersion;
        this.tweaks = tweaks;

        if (tweaks != null)
        {
            boolean forge = false;
            boolean shaderOrOptifine = false;
			if (gameVersion.getGameType() == GameType.V1_13_HIGHER_FORGE)
				if (tweaks.length == 1 && tweaks[0] == GameTweak.FORGE)
					tweaks = new GameTweak[0];
				else if (tweaks.length != 0)
					LogUtil.info("tweak-deprec");

            for (GameTweak tweak : tweaks)
                if (tweak.equals(GameTweak.FORGE))
                {
                    if (gameVersion.getGameType() == GameType.V1_5_2_LOWER)
                    	LogUtil.info("forge-old");

                    forge = true;
                }
                else if (tweak == GameTweak.OPTIFINE || tweak == GameTweak.SHADER)
                {
                    shaderOrOptifine = true;
                }

            if (forge || gameVersion.getGameType() == GameType.V1_13_HIGHER_FORGE)
            	LogUtil.info("support-forge");

			if (tweaks.length > 0 && gameVersion.getGameType() == GameType.V1_5_2_LOWER)
				LogUtil.info("old-tweaking");

            if (shaderOrOptifine && forge)
            {
            	LogUtil.info("forge-optifine");

                ArrayList<GameTweak> tweakList = new ArrayList<GameTweak>();

                for (GameTweak tweak : tweaks)
                    if (tweak != GameTweak.OPTIFINE && tweak != GameTweak.SHADER)
                        tweakList.add(tweak);

                this.tweaks = tweakList.toArray(new GameTweak[tweakList.size()]);
            }
        }
    }

    /**
     * Returns the server name
     *
     * @return The server name
     */
    public String getServerName()
    {
        return serverName;
    }

    /**
     * Returns the Game Directory
     *
     * @return The Game Directory
     */
    public File getGameDir()
    {
        return this.gameDir;
    }

    /**
     * Returns the Game Version containing the launch informations
     *
     * @return The Game Version
     */
    public GameVersion getGameVersion()
    {
        return gameVersion;
    }

    /**
     * Returns the current tweaks (Shader, Optifine, Forge, or just Vanilla)
     *
     * @return The current tweaks
     */
    public GameTweak[] getGameTweaks()
    {
        return tweaks;
    }

    /**
     * Check if the game has a given tweak
     *
     * @param tweak The tweak to check if the game has it
     *
     * @return True if it has, false if not
     */
    public boolean hasGameTweak(GameTweak tweak)
    {
        for (GameTweak gameTweak : tweaks)
            if (gameTweak.equals(tweak))
                return true;

        return false;
    }
}
