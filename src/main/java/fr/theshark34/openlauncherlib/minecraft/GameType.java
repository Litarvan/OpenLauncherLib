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
     * The 1.5.2 or Lower game type
     */
    @SuppressWarnings("unused")
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
            return new ArrayList<String>() {{
                add(authInfos.getUsername());

                add("token:" + authInfos.getAccessToken() + ":" + authInfos.getUuid());

                add("--gameDir");
                add(infos.getGameDir().getAbsolutePath());

                File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
                add("--assetsDir");
                add(assetsDir.getAbsolutePath() + "/virtual/legacy/");
            }};
        }
    };

    /**
     * The 1.7.2 or Lower game type
     */
    @SuppressWarnings("unused")
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
            return new ArrayList<String>() {{
                add("--username=" + authInfos.getUsername());

                add("--accessToken");
                add(authInfos.getAccessToken());

                add("--version");
                add(infos.getGameVersion().getName());

                add("--gameDir");
                add(infos.getGameDir().getAbsolutePath());

                File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
                add("--assetsDir");
                add(assetsDir.getAbsolutePath() + "/virtual/legacy/");

                add("--userProperties");
                add("{}");

                add("--uuid");
                add(authInfos.getUuid());

                add("--userType");
                add("legacy");
            }};
        }
    };

    /**
     * The 1.7.10 Game Type
     */
    @SuppressWarnings("unused")
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
            return new ArrayList<String>() {{
                add("--username=" + authInfos.getUsername());

                add("--accessToken");
                add(authInfos.getAccessToken());

                if (authInfos.getClientToken() != null)
                {
                    add("--clientToken");
                    add(authInfos.getClientToken());
                }

                add("--version");
                add(infos.getGameVersion().getName());

                add("--gameDir");
                add(infos.getGameDir().getAbsolutePath());

                File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
                add("--assetsDir");
                add(assetsDir.getAbsolutePath());

                add("--assetIndex");
                add(infos.getGameVersion().getName());

                add("--userProperties");
                add("{}");

                add("--uuid");
                add(authInfos.getUuid());

                add("--userType");
                add("legacy");
            }};
        }
    };

    /**
     * The 1.8 or higher Game Type
     */
    @SuppressWarnings("unused")
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
            return new ArrayList<String>() {{
                add("--username=" + authInfos.getUsername());

                add("--accessToken");
                add(authInfos.getAccessToken());

                if (authInfos.getClientToken() != null)
                {
                    add("--clientToken");
                    add(authInfos.getClientToken());
                }

                add("--version");
                add(infos.getGameVersion().getName());

                add("--gameDir");
                add(infos.getGameDir().getAbsolutePath());

                File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
                add("--assetsDir");
                add(assetsDir.getAbsolutePath());

                add("--assetIndex");

                String version = infos.getGameVersion().getName();
                int first = version.indexOf('.');
                int second = version.lastIndexOf('.');

                if (first != second)
                {
                    version = version.substring(0, version.lastIndexOf('.'));
                }
                if (infos.getGameVersion().getName().equals("1.13.1") || infos.getGameVersion().getName().equals("1.13.2"))
                    version = "1.13.1";

                add(version);

                add("--userProperties");
                add("{}");

                add("--uuid");
                add(authInfos.getUuid());

                add("--userType");
                add("legacy");
            }};
        }
    };

	/**
	 * This is a workaround until a new version of the lib using versions JSON is published
	 */
	@SuppressWarnings("unused")
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
			return new ArrayList<String>() {{
                add("--username=" + authInfos.getUsername());

                add("--accessToken");
                add(authInfos.getAccessToken());

                if (authInfos.getClientToken() != null)
                {
                    add("--clientToken");
                    add(authInfos.getClientToken());
                }

                add("--version");
                add(infos.getGameVersion().getName());

                add("--gameDir");
                add(infos.getGameDir().getAbsolutePath());

                File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
                add("--assetsDir");
                add(assetsDir.getAbsolutePath());

                add("--assetIndex");
                add("1.13.1");

                add("--userProperties");
                add("{}");

                add("--uuid");
                add(authInfos.getUuid());

                add("--userType");
                add("legacy");

                add("--launchTarget");
                add("fmlclient");

                add("--fml.forgeVersion");
                add("25.0.219");

                add("--fml.mcVersion");
                add("1.13.2");

                add("--fml.forgeGroup");
                add("net.minecraftforge");

                add("--fml.mcpVersion");
                add("20190213.203750");
            }};
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
