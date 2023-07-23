/*
 * Copyright 2015-2017 Adrien "Litarvan" Navratil
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

import java.io.File;
import java.util.ArrayList;

/**
 * The Game Type
 *
 * <p>
 *     This class contains the specifics informations about a version
 *     or a group of verison of Minecraft.
 *
 *     It contains its main class, and its arguments.
 * </p>
 *
 * @author Litarvan
 * @version 3.0.4
 * @since 2.0.0-SNAPSHOT
 */
public abstract class GameType
{
    /**
     * The 1.5.2 or Lower game type | Forge
     */
    public static final GameType V1_5_2_LOWER = new GameType()
    {
        @Override
        public String getName()
        {
            return "1.5.2 or lower";
        }

        @Override
        public String getMainClass(GameInfos infos)
        {
            return "net.minecraft.launchwrapper.Launch";
        }

        @Override
        public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, AuthInfos authInfos)
        {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add(authInfos.getUsername());

            arguments.add("token:" + authInfos.getAccessToken() + ":" + authInfos.getUuid());

            arguments.add("--gameDir");
            arguments.add(infos.getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath() + "/virtual/legacy/");

            return arguments;
        }
    };

    /**
     * The 1.7.2 or Lower game type | Forge
     */
    public static final GameType V1_7_2_LOWER = new GameType()
    {
        @Override
        public String getName()
        {
            return "1.7.2 or lower";
        }

        @Override
        public String getMainClass(GameInfos infos)
        {
            return "net.minecraft.client.main.Main";
        }

        @Override
        public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, AuthInfos authInfos)
        {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add("--username=" + authInfos.getUsername());

            arguments.add("--accessToken");
            arguments.add(authInfos.getAccessToken());

            arguments.add("--version");
            arguments.add(infos.getGameVersion().getName());

            arguments.add("--gameDir");
            arguments.add(infos.getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath() + "/virtual/legacy/");

            arguments.add("--userProperties");
            arguments.add("{}");

            arguments.add("--uuid");
            arguments.add(authInfos.getUuid());

            arguments.add("--userType");
            arguments.add("legacy");

            return arguments;
        }
    };

    /**
     * The 1.7.10 Game Type | Forge
     */
    public static final GameType V1_7_10 = new GameType()
    {
        @Override
        public String getName()
        {
            return "1.7.10";
        }

        @Override
        public String getMainClass(GameInfos infos)
        {
            return "net.minecraft.client.main.Main";
        }

        @Override
        public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, AuthInfos authInfos)
        {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add("--username=" + authInfos.getUsername());

            arguments.add("--accessToken");
            arguments.add(authInfos.getAccessToken());

            if (authInfos.getClientToken() != null)
            {
                arguments.add("--clientToken");
                arguments.add(authInfos.getClientToken());
            }

            arguments.add("--version");
            arguments.add(infos.getGameVersion().getName());

            arguments.add("--gameDir");
            arguments.add(infos.getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath());

            arguments.add("--assetIndex");
            arguments.add(infos.getGameVersion().getName());

            arguments.add("--userProperties");
            arguments.add("{}");

            arguments.add("--uuid");
            arguments.add(authInfos.getUuid());

            arguments.add("--userType");
            arguments.add("legacy");

            return arguments;
        }
    };

    /**
     * The 1.8 or higher Game Type | Forge
     */
    public static final GameType V1_8_HIGHER = new GameType()
    {
        @Override
        public String getName()
        {
            return "1.8 or higher";
        }

        @Override
        public String getMainClass(GameInfos infos)
        {
            return "net.minecraft.client.main.Main";
        }

        @Override
        public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, AuthInfos authInfos)
        {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add("--username=" + authInfos.getUsername());

            arguments.add("--accessToken");
            arguments.add(authInfos.getAccessToken());

            if (authInfos.getClientToken() != null)
            {
                arguments.add("--clientToken");
                arguments.add(authInfos.getClientToken());
            }

            arguments.add("--version");
            arguments.add(infos.getGameVersion().getName());

            arguments.add("--gameDir");
            arguments.add(infos.getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath());

            arguments.add("--assetIndex");

            String version = infos.getGameVersion().getName();

            int first = version.indexOf('.');
            int second = version.lastIndexOf('.');

            if (first != second)
            {
                version = version.substring(0, version.lastIndexOf('.'));
            }

            if (infos.getGameVersion().getName().equals("1.13.1") || infos.getGameVersion().getName().equals("1.13.2"))
            	version = "1.13.1";

            arguments.add(version);

            arguments.add("--userProperties");
            arguments.add("{}");

            arguments.add("--uuid");
            arguments.add(authInfos.getUuid());

            arguments.add("--userType");
            arguments.add("legacy");

            return arguments;
        }
    };

	/**
	 * This is a workaround until a new version of the lib using versions JSON is published
	 */
	public static final GameType V1_13_HIGHER_FORGE = new GameType()
	{
		@Override
		public String getName()
		{
			return "1.13 or higher with Forge";
		}

		@Override
		public String getMainClass(GameInfos infos)
		{
			return "cpw.mods.modlauncher.Launcher";
		}

		@Override
		public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, AuthInfos authInfos)
		{
			ArrayList<String> arguments = new ArrayList<String>();

			arguments.add("--username=" + authInfos.getUsername());

			arguments.add("--accessToken");
			arguments.add(authInfos.getAccessToken());

			if (authInfos.getClientToken() != null)
			{
				arguments.add("--clientToken");
				arguments.add(authInfos.getClientToken());
			}

			arguments.add("--version");
			arguments.add(infos.getGameVersion().getName());

			arguments.add("--gameDir");
			arguments.add(infos.getGameDir().getAbsolutePath());

			arguments.add("--assetsDir");
			File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
			arguments.add(assetsDir.getAbsolutePath());

			arguments.add("--assetIndex");

			arguments.add("1.13.1");

			arguments.add("--userProperties");
			arguments.add("{}");

			arguments.add("--uuid");
			arguments.add(authInfos.getUuid());

			arguments.add("--userType");
			arguments.add("legacy");

			arguments.add("--launchTarget");
			arguments.add("fmlclient");

			arguments.add("--fml.forgeVersion");
			arguments.add("25.0.219");

			arguments.add("--fml.mcVersion");
			arguments.add("1.13.2");

			arguments.add("--fml.forgeGroup");
			arguments.add("net.minecraftforge");

			arguments.add("--fml.mcpVersion");
			arguments.add("20190213.203750");

			return arguments;
		}
	};

    /**
     * The 1.16 or Higher Game Type | Forge
     */

    public static final GameType V1_16_HIGHER_FORGE = new GameType()
    {
        @Override
        public String getName()
        {
            return "1.16 or higher with Forge";
        }

        @Override
        public String getMainClass(GameInfos infos)
        {
            return "cpw.mods.modlauncher.Launcher";
        }

        @Override
        public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, AuthInfos authInfos)
        {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add("--username=" + authInfos.getUsername());

            arguments.add("--accessToken");
            arguments.add(authInfos.getAccessToken());

            if (authInfos.getClientToken() != null)
            {
                arguments.add("--clientToken");
                arguments.add(authInfos.getClientToken());
            }

            arguments.add("--version");
            arguments.add(infos.getGameVersion().getName());

            arguments.add("--gameDir");
            arguments.add(infos.getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath());

            arguments.add("--assetIndex");

            // Update the asset index version for 1.16 or higher
            arguments.add("1.16.1");

            arguments.add("--userProperties");
            arguments.add("{}");

            arguments.add("--uuid");
            arguments.add(authInfos.getUuid());

            arguments.add("--userType");
            arguments.add("legacy");

            arguments.add("--launchTarget");
            arguments.add("fmlclient");

            arguments.add("--fml.forgeVersion");
            // Update the Forge version for 1.16 or higher
            arguments.add("32.0.108");

            arguments.add("--fml.mcVersion");
            // Update the Minecraft version for 1.16 or higher
            arguments.add("1.16.1");
            arguments.add"1.16.2");
            arguments.add("1.16.5");

            arguments.add("--fml.forgeGroup");
            arguments.add("net.minecraftforge");

            arguments.add("--fml.mcpVersion");
            arguments.add("20210115.111550");

            return arguments;
        }
    };


    /**
     * Fabric Version supported
     * @return Fabric Version
     *
     * 1.19
     * @Not-avalaible 1.20
     */



    /**
     * The 1.19 or Higher Game Type | Fabric
     */

    public static final GameType V1_19_HIGHER_FABRIC = new GameType()
    {
        @Override
        public String getName()
        {
            return "1.19 or higher with Fabric";
        }

        @Override
        public String getMainClass(GameInfos infos)
        {
            return "net.fabricmc.loader.launch.knot.KnotClient";
        }

        @Override
        public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, AuthInfos authInfos)
        {
            ArrayList<String> arguments = new ArrayList<String>();

            arguments.add("--username=" + authInfos.getUsername());

            arguments.add("--accessToken");
            arguments.add(authInfos.getAccessToken());

            if (authInfos.getClientToken() != null)
            {
                arguments.add("--clientToken");
                arguments.add(authInfos.getClientToken());
            }

            arguments.add("--version");
            arguments.add(infos.getGameVersion().getName());

            arguments.add("--gameDir");
            arguments.add(infos.getGameDir().getAbsolutePath());

            arguments.add("--assetsDir");
            File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
            arguments.add(assetsDir.getAbsolutePath());

            arguments.add("--assetIndex");
            // Update the asset index version for 1.19 or higher
            arguments.add("1.19");

            // Update the asset index version for 1.19 or higher
            arguments.add("1.19.1");
            arguments.add("1.19.2");

            arguments.add("--userProperties");
            arguments.add("{}");

            arguments.add("--uuid");
            arguments.add(authInfos.getUuid());

            arguments.add("--userType");
            arguments.add("legacy");

            arguments.add("--tweakClass");
            arguments.add("net.fabricmc.loader.launch.knot.KnotClientTweaker");

            return arguments;
        }
    };

    /**
     * The 1.20 or Higher Game Type | Fabric
     *
     * For the future, not available.
     */







    /**
     * The name of the Game Type
     *
     * @return Returns the name of the game type
     */
    public abstract String getName();

    /**
     * Returns the main class of the Minecraft Game Type
     *
     * @param infos The infos of the game
     *
     * @return The main class
     */
    public abstract String getMainClass(GameInfos infos);

    /**
     * Returns the launch arguments of the Minecraft Game Type
     *
     * @param infos     The infos of the game
     * @param folder    The current GameFolder
     * @param authInfos The current AuthInfos
     *
     * @return The launch arguments
     */
    public abstract ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, AuthInfos authInfos);
}
