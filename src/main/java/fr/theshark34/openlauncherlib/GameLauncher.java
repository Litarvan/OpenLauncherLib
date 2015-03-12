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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Game Launcher
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class GameLauncher {

	/**
	 * The version of the launched minecraft
	 */
	private String gameVersion;

	/**
	 * The game directory
	 */
	private File gameDir;

	/**
	 * The title in dock of the minecraft window on mac
	 */
	private String gameTitle;

	/**
	 * Player's username
	 */
	private String username;

	/**
	 * The access token obtained when authenticating
	 */
	private String accessToken;

	/**
	 * The UUID
	 */
	private String uuid;

	/**
	 * Arguments for jvm (ex: -Xms512M & -Xmx1024M)
	 */
	private String[] jvmArgs;

	/**
	 * If the launcher add forge support
	 */
	private boolean forgeSupport;
	
	/**
	 * If the version need the legacy assets system
	 */
	private boolean legacyAssets;

	/**
	 * If it need to have the new tweak class (>= Minecraft 1.8 + Forge)
	 */
	private boolean newTweakClass;

	/**
	 * Base constructor
	 * 
	 * @param gameVersion
	 *            The version of the launched minecraft
	 * @param gameDir
	 *            The game directory
	 * @param gameTitle
	 *            The title in dock of the minecraft window on mac
	 * @param username
	 *            Player's username
	 * @param accessToken
	 *            The access token obtained when authenticating
	 * @param uuid
	 *            The UUID obtained when authenticating
	 * @param jvmArgs
	 *            Arguments for jvm (ex: -Xms512M & -Xmx1024M)
	 * @param forgeSupport
	 *            If the launcher add forge support
	 * @param legacyAssets
	 * 			  If the assets use the legacy system
	 * @param newTweakClass
	 *            If the launched Minecraft user Forge 1.8, set it to true
	 */
	public GameLauncher(String gameVersion, File gameDir, String gameTitle,
			String username, String accessToken, String uuid, String[] jvmArgs,
			boolean forgeSupport, boolean legacyAssets, boolean newTweakClass) {
		this.gameVersion = gameVersion;
		this.gameDir = gameDir;
		this.gameTitle = gameTitle;
		this.username = username;
		this.accessToken = accessToken;
		this.uuid = uuid;
		this.jvmArgs = jvmArgs;
		this.forgeSupport = forgeSupport;
		this.legacyAssets = legacyAssets;
		this.newTweakClass = newTweakClass;
	}

	/**
	 * Launch minecraft !
	 * 
	 * @return The created minecraft process
	 * @throws InterruptedException
	 *             If the process is interrupted and throw on
	 * @throws IOException
	 *             If the process throw one because it failed to start
	 */
	public Process launchMinecraft() throws InterruptedException, IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.directory(this.gameDir);
		pb.command(getLaunchCommand());
		Process process = pb.start();
		return process;
	}

	/**
	 * Create the launch command
	 * 
	 * @return The created launch comand
	 */
	private ArrayList<String> getLaunchCommand() {
		ArrayList<String> commands = new ArrayList<String>();
		// Adding java path
		if (System.getProperty("os.name").toLowerCase().contains("win"))
			commands.add("\"" + System.getProperty("java.home") + "/bin/java"
					+ "\"");
		else
			commands.add(System.getProperty("java.home") + "/bin/java");

		// Adding little arguments for mac
		if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			commands.add("-Xdock:name=" + gameTitle);
			commands.add("-XX:+UseConcMarkSweepGC");
			commands.add("-XX:+CMSIncrementalMode");
			commands.add("-XX:-UseAdaptiveSizePolicy");
		}

		// Adding jvm args
		commands.addAll(Arrays.asList(jvmArgs));

		// Adding natives
		commands.add("-Djava.library.path=" + gameDir.getAbsolutePath()
				+ "/natives/");

		// Adding classpath
		commands.add("-cp");
		commands.add(getClassPath());

		// Adding main class
		if (forgeSupport)
			commands.add("net.minecraft.launchwrapper.Launch");
		else
			commands.add("net.minecraft.client.main.Main");

		// Adding minecraft arguments
		commands.add("--username=" + username);
		commands.add("--accessToken");
		commands.add(accessToken);
		commands.add("--version");
		commands.add(gameVersion);
		commands.add("--gameDir");
		commands.add(gameDir.getAbsolutePath());
		commands.add("--assetsDir");
		commands.add(gameDir.getAbsolutePath() + "/assets");
		if(!legacyAssets) {
			commands.add("--assetIndex");
			commands.add(gameVersion);
		}
		commands.add("--userProperties");
		commands.add("{}");
		commands.add("--uuid");
		commands.add(uuid);
		commands.add("--userType");
		commands.add("legacy");

		// Adding forge support
		if (forgeSupport) {
			commands.add("--tweakClass");
			if(newTweakClass)
				commands.add("net.minecraftforge.fml.common.launcher.FMLTweaker");
			else
				commands.add("cpw.mods.fml.common.launcher.FMLTweaker");
		}
		return commands;
	}

	/**
	 * Create the class path
	 * 
	 * @return The created class path
	 */
	private String getClassPath() {
		String str = "";
		File libFolder = new File(gameDir, "libs");
		File[] libs = libFolder.listFiles();
		String osName = System.getProperty("os.name").toLowerCase();
		for (int i = 0; i < libs.length; i++)
			str += libs[i].getAbsolutePath()
					+ (osName.contains("win") ? ";" : ":");
		str += gameDir.getAbsolutePath() + "/minecraft.jar";
		return str;
	}

	/**
	 * Calling this will create two thread that print the process output and
	 * writing to "minecraftlogs.txt" in the gameDir
	 * 
	 * @param process
	 *            The process to read output
	 * @throws IOException
	 *             If it fails to read the output
	 */
	public void printProcessOutput(Process process) throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		final BufferedReader errorReader = new BufferedReader(
				new InputStreamReader(process.getErrorStream()));
		final FileWriter fw = new FileWriter(new File(gameDir,
				"minecraftlogs.txt"));
		new Thread() {
			public void run() {
				try {
					String line = "";
					try {
						while ((line = reader.readLine()) != null) {
							fw.write(line + "\n");
							System.out.println(line);
						}
					} finally {
						fw.close();
						reader.close();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}.start();
		new Thread() {
			public void run() {
				try {
					String line = "";
					try {
						while ((line = errorReader.readLine()) != null) {
							fw.write(line + "\n");
							System.err.println(line);
						}
					} finally {
						errorReader.close();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}.start();
	}

}
