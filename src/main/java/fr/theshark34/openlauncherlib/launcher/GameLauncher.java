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

import fr.theshark34.openlauncherlib.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Game Launcher
 *
 * <p>
 *     The main class to launch the game with a game version and
 *     a game folder.
 * </p>
 * 
 * @author TheShark34
 * @version 2.1-SNAPSHOT
 */
public class GameLauncher {

    /**
     * The Game Infos like the server name and if forge is enabled
     */
    private GameInfos gameInfos;

	/**
	 * The Game Folder containing game folder organisation
	 */
	private GameFolder gameFolder;

    /**
     * The Auth Infos like the player username or the access token
     */
    private AuthInfos authInfos;

    /**
     * The Java VM arguments
     */
    private List<String> vmArgs;

	/**
	 * Basic Constructor
	 *
     * @param gameInfos
     *            The Game Infos like the server name and if forge is enabled
	 * @param gameFolder
	 *            The Game Folder containing game folder organisation
     * @param authInfos
     *            The Auth Infos like the player username or the access token
	 */
	public GameLauncher(GameInfos gameInfos, GameFolder gameFolder, AuthInfos authInfos) {
		this(gameInfos, gameFolder, authInfos, new String[] {});
	}

    /**
     * Normal Constructor
     *
     * @param gameInfos
     *            The Game Infos like the server name and if forge is enabled
     * @param gameFolder
     *            The Game Folder containing game folder organisation
     * @param authInfos
     *            The Auth Infos like the player username or the access token
     * @param vmArgs
     *            The Java VM arguments
     */
    public GameLauncher(GameInfos gameInfos, GameFolder gameFolder, AuthInfos authInfos, String[] vmArgs) {
        this(gameInfos, gameFolder, authInfos, Arrays.asList(vmArgs));
    }

    /**
     * Advanced Constructor
     *
     * @param gameInfos
     *            The Game Infos like the server name and if forge is enabled
     * @param gameFolder
     *            The Game Folder containing game folder organisation
     * @param authInfos
     *            The Auth Infos like the player username or the access token
     * @param vmArgs
     *            The Java VM arguments
     */
    public GameLauncher(GameInfos gameInfos, GameFolder gameFolder, AuthInfos authInfos, List<String> vmArgs) {
        if(gameInfos == null)
            throw new IllegalArgumentException("gameInfos == null !");
        this.gameInfos = gameInfos;

        if(gameFolder == null)
            throw new IllegalArgumentException("gameFolder == null !");
        this.gameFolder = gameFolder;

        if(authInfos == null)
            throw new IllegalArgumentException("authInfos == null !");
        this.authInfos = authInfos;

        if(vmArgs == null)
            throw new IllegalArgumentException("vmArgs == null !");
        this.vmArgs = vmArgs;
    }

    /**
     * Launches Minecraft !
     *f
     * @return The created Process
     */
    public Process launch() throws IOException {
        printInfos();

        ProcessBuilder pb = new ProcessBuilder();
        ArrayList<String> commands = new ArrayList<String>();

        commands.add(getJavaPath());
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
            commands.addAll(Arrays.asList(getMacArgs()));
        if(vmArgs != null)
            commands.addAll(vmArgs);
        commands.add("-Djava.library.path=" + new File(gameInfos.getGameDir(), gameFolder.getNativesFolder()).getAbsolutePath());
        commands.add("-cp");
        commands.add(constructClasspath());
        if(gameInfos.isTweakingEnabled())
            commands.add(GameTweak.LAUNCHWRAPPER_MAIN_CLASS);
        else
            commands.add(gameInfos.getGameVersion().getGameType().getMainClass(this));
        commands.addAll(gameInfos.getGameVersion().getGameType().getLaunchArgs(this));
        if(gameInfos.isTweakingEnabled())
            for(GameTweak tweak : gameInfos.getGameTweaks()) {
                commands.add("--tweakClass");
                commands.add(tweak.getTweakClass(this));
            }

        String entireCommand = "";
        for(String cmd : commands)
            entireCommand += cmd + "\n";

        System.out.println("[OpenLauncherLib] Entire command : ");
        System.out.println(entireCommand);

        System.out.println("[OpenLauncherLib] Launching Minecraft");
        pb.directory(gameInfos.getGameDir());
        pb.command(commands);
        Process p = pb.start();

        System.out.println("[OpenLauncherLib] Successfully launched");
        File logsFile = new File(gameInfos.getGameDir(), "launcherlogs.txt");
        Util.printAndWriteProcessOutput(p, logsFile);

        return p;
    }

    /**
     * Print all the launcher infos
     */
    private void printInfos() {
        System.out.println("[OpenLauncherLib] OpenLauncherLib v2.0 Launcher");
        System.out.println("[OpenLauncherLib] Generating command with : ");
        System.out.println("[OpenLauncherLib]    Game Infos :");
        System.out.println("[OpenLauncherLib]        Game Version   : " + gameInfos.getGameVersion().getName());
        System.out.println("[OpenLauncherLib]        Game Dir       : " + gameInfos.getGameDir().getAbsolutePath());
        System.out.println("[OpenLauncherLib]        Server Name    : " + gameInfos.getServerName());
        System.out.println("[OpenLauncherLib]        Game Tweaks    : " + gameInfos.getGameTweaks().length);
        for(GameTweak tweak : gameInfos.getGameTweaks())
            System.out.println("[OpenLauncherLib]            " + tweak.getName());
        System.out.println("[OpenLauncherLib]    Game Folder :");
        System.out.println("[OpenLauncherLib]        Assets Folder  : " + gameFolder.getAssetsFolder());
        System.out.println("[OpenLauncherLib]        Libs Folder    : " + gameFolder.getLibsFolder());
        System.out.println("[OpenLauncherLib]        Natives Folder : " + gameFolder.getNativesFolder());
        System.out.println("[OpenLauncherLib]        Main Jar       : " + gameFolder.getMainJar());
        System.out.println("[OpenLauncherLib]    Auth Infos :");
        System.out.println("[OpenLauncherLib]        Username       : " + authInfos.getUsername());
        System.out.println("[OpenLauncherLib]        Access Token   : " + authInfos.getAccessToken());
        System.out.println("[OpenLauncherLib]        UUID           : " + authInfos.getUuid());
        System.out.println("[OpenLauncherLib] Generating launch command...");
    }

    /**
     * Gets the Java executable path
     *
     * @return The Java executable path
     */
    private String getJavaPath() {
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            return "\"" + System.getProperty("java.home") + "/bin/java"
                    + "\"";
        else
            return System.getProperty("java.home") + "/bin/java";
    }

    /**
     * Gets the mac special arguments
     *
     * @return The mac special arguments
     */
    private String[] getMacArgs() {
        String[] macArgs = new String[] {
                "-Xdock:name=" + gameInfos.getServerName(),
                "-XX:+UseConcMarkSweepGC",
                "-XX:+CMSIncrementalMode",
                "-XX:-UseAdaptiveSizePolicy"
        };

        return macArgs;
    }

    /**
     * Generates the classpath with the libraries and the launcher
     *
     * @return The generated classpath
     */
    public String constructClasspath() {
        String classpath = "";
        ArrayList<File> libs = Util.list(new File(gameInfos.getGameDir(), gameFolder.getLibsFolder()));
        String separator = System.getProperty("path.separator");

        for(File lib : libs)
            classpath += lib.getAbsolutePath() + separator;

        classpath += new File(gameInfos.getGameDir(), gameFolder.getMainJar()).getAbsolutePath();

        return classpath;
    }

	/**
	 * Returns the Game Folder containing the game folder organisation
	 *
	 * @return The Game Folder
	 */
	public GameFolder getGameFolder() {
		return gameFolder;
	}

	/**
	 * Returns the Game Infos like the server name and if forge is enabled
	 *
	 * @return The Game Infos
	 */
	public GameInfos getGameInfos() {
		return gameInfos;
	}

    /**
     * Returns the Auth Infos like the player username or the access token
     *
     * @return The Auth Infos
     */
    public AuthInfos getAuthInfos() {
        return authInfos;
    }

}
