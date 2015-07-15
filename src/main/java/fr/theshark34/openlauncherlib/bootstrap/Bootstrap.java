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
package fr.theshark34.openlauncherlib.bootstrap;

import fr.theshark34.openlauncherlib.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Bootstrap main class
 *
 * <p>
 *     This is the main bootstrap class, to launch the launcher !
 * </p>
 *
 * @author TheShark34
 * @version 2.1-SNAPSHOT
 */
public class Bootstrap {

    /**
     * The launcher classpath containing the launcher file, and the libs folder
     */
    private LauncherClasspath launcherClasspath;

    /**
     * The launcher infos containing the server name, and the main class
     */
    private LauncherInfos launcherInfos;

    /**
     * The launch infos containing the launch infos like the arguments or the VM arguments
     */
    private LaunchInfos launchInfos;

    /**
     * Basic constructor
     *
     * @param launcherClasspath
     *            The launcher classpath containing the launcher file, and the libs folder
     * @param launcherInfos
     *            The launcher infos containing the server name, and the main class
     */
    public Bootstrap(LauncherClasspath launcherClasspath, LauncherInfos launcherInfos) {
        this.launcherClasspath = launcherClasspath;
        this.launcherInfos = launcherInfos;
    }

    /**
     * Advanced constructor
     *
     * @param launcherClasspath
     *            The launcher classpath containing the launcher file, and the libs folder
     * @param launcherInfos
     *            The launcher infos containing the server name, and the main class
     * @param launchInfos
     *            The launch infos containing the launch infos like the arguments or the VM arguments
     */
    public Bootstrap(LauncherClasspath launcherClasspath, LauncherInfos launcherInfos, LaunchInfos launchInfos) {
        this.launcherClasspath = launcherClasspath;
        this.launcherInfos = launcherInfos;
        this.launchInfos = launchInfos;
    }

    /**
     * Launches the launcher !
     *
     * @return The created Process
     */
    public Process launch() throws IOException {
        printInfos();

        ProcessBuilder pb = new ProcessBuilder();
        ArrayList<String> commands = new ArrayList<String>();
        commands.add(getJavaPath());
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
            commands.addAll(Arrays.asList(getMacArgs()));
        if(launchInfos != null)
            commands.addAll(Arrays.asList(launchInfos.getVmArgs()));
        commands.add("-cp");
        commands.add(constructClasspath());
        commands.add(launcherInfos.getMainClass());
        if(launchInfos != null)
            commands.addAll(Arrays.asList(launchInfos.getArgs()));

        String entireCommand = "";
        for(String cmd : commands)
            entireCommand += cmd + "\n";

        System.out.println("[OpenLauncherLib] Entire command : ");
        System.out.println(entireCommand);

        System.out.println("[OpenLauncherLib] Launching launcher");
        pb.directory(launcherClasspath.getLauncher().getParentFile());
        pb.command(commands);
        Process p = pb.start();

        System.out.println("[OpenLauncherLib] Successfully launched");
        File logsFile = new File(launcherClasspath.getLauncher().getParentFile(), "bootstraplogs.txt");
        Util.printAndWriteProcessOutput(p, logsFile);

        return p;
    }

    /**
     * Print all the bootstrap infos
     */
    private void printInfos() {
        System.out.println("[OpenLauncherLib] OpenLauncherLib v2.0 Bootstrap");
        System.out.println("[OpenLauncherLib] Generating command with : ");
        System.out.println("[OpenLauncherLib]    Launcher Classpath :");
        System.out.println("[OpenLauncherLib]        Launcher     : " + launcherClasspath.getLauncher().getAbsolutePath());
        System.out.println("[OpenLauncherLib]        Libs Folder  : " + launcherClasspath.getLibsFolder().getAbsolutePath());
        System.out.println("[OpenLauncherLib]    Launcher Infos :");
        System.out.println("[OpenLauncherLib]        Server Name  : " + launcherInfos.getServerName());
        System.out.println("[OpenLauncherLib]        Main Class   : " + launcherInfos.getMainClass());
        if(launchInfos != null) {
            System.out.println("[OpenLauncherLib]    Launch Infos :");
            System.out.print("[OpenLauncherLib]        Arguments    : ");
            for (String arg : launchInfos.getArgs())
                System.out.print(arg);
            System.out.print("\n[OpenLauncherLib]        VM Arguments : ");
            for (String arg : launchInfos.getVmArgs())
                System.out.print(arg);
        }
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
                "-Xdock:name=" + launcherInfos.getServerName(),
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
        ArrayList<File> libs = Util.list(launcherClasspath.getLibsFolder());
        String separator = System.getProperty("path.separator");

        for(File lib : libs)
            classpath += lib.getAbsolutePath() + separator;

        classpath += launcherClasspath.getLauncher().getAbsolutePath();

        return classpath;
    }

    /**
     * Returns the launcher classpath containing the launcher file, and the libs folder
     *
     * @return The launcher classpath
     */
    public LauncherClasspath getLauncherClasspath() {
        return this.launcherClasspath;
    }

    /**
     * Returns the launcher infos containing the server name, and the main class
     *
     * @return The launcher infos
     */
    public LauncherInfos launcherInfos() {
        return this.launcherInfos;
    }

    /**
     * Returns the launch infos containing the launch infos like the arguments or the VM arguments
     *
     * @return The launch infos
     */
    public LaunchInfos getLaunchInfos() {
        return this.launchInfos;
    }

}
