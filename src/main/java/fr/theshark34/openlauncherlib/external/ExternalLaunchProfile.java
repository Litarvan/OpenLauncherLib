/*
 * Copyright 2015 Adrien Navratil
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
package fr.theshark34.openlauncherlib.external;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The External Launch Profile
 *
 * <p>
 *     Contains the informations about an external launching.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class ExternalLaunchProfile
{
    /**
     * The program main class
     */
    private String mainClass;

    /**
     * The program classpath (you can create it using the {@link ClasspathConstructor})
     *
     * @see ClasspathConstructor
     */
    private String classPath;

    /**
     * The vm arguments (optional)
     */
    private List<String> vmArgs;

    /**
     * The program arguments (optional)
     */
    private List<String> args;

    /**
     * If the error stream need to be redirected
     */
    private boolean redirectErrorStream = false;

    /**
     * The mac dock name (optional)
     */
    private String macDockName;

    /**
     * The directory where the process need to be launched (optional)
     */
    private File directory;

    /**
     * Basic launch profile
     *
     * @param mainClass Your program main class
     * @param classPath The classpath (you can create it using the {@link ClasspathConstructor})
     *                  It contains the path to your dependencies and your main jar
     */
    public ExternalLaunchProfile(String mainClass, String classPath)
    {
        this(mainClass, classPath, null, null);
    }

    /**
     * Medium launch profile
     *
     * @param mainClass Your program main class
     * @param classPath The classpath (you can create it using the {@link ClasspathConstructor})
     *                  It contains the path to your dependencies and your main jar
     * @param vmArgs    The VM arguments (optional)
     * @param args      Your program arguments (optional)
     */
    public ExternalLaunchProfile(String mainClass, String classPath, List<String> vmArgs, List<String> args)
    {
        this(mainClass, classPath, vmArgs, args, false, null, null);
    }

    /**
     * Full launch profile
     *
     * @param mainClass           Your program main class
     * @param classPath           The classpath (you can create it using the {@link ClasspathConstructor})
     *                            It contains the path to your dependencies and your main jar
     * @param vmArgs              The VM arguments (optional)
     * @param args                Your program arguments (optional)
     * @param redirectErrorStream If the error stream need to be redirected
     * @param macDockName         The name in the mac dock
     * @param directory           The directory where the process need to be launched
     */
    public ExternalLaunchProfile(String mainClass, String classPath, List<String> vmArgs, List<String> args, boolean redirectErrorStream, String macDockName, File directory)
    {
        this.mainClass = mainClass;
        this.classPath = classPath;
        this.vmArgs = vmArgs;
        this.args = args;
        this.redirectErrorStream = redirectErrorStream;
        this.macDockName = macDockName;
        this.directory = directory;
    }

    /**
     * Return the program main class
     *
     * @return The main class
     */
    public String getMainClass()
    {
        return mainClass;
    }

    /**
     * Set the program main class
     *
     * @param mainClass The new main class
     */
    public void setMainClass(String mainClass)
    {
        this.mainClass = mainClass;
    }

    /**
     * Return the program classpath
     *
     * @return The classpath
     */
    public String getClassPath()
    {
        return classPath;
    }

    /**
     * Set the program classpath
     *
     * @param classPath The new classpath (you can create it using the {@link ClasspathConstructor})
     */
    public void setClassPath(String classPath)
    {
        this.classPath = classPath;
    }

    /**
     * Return the vm arguments (can be null)
     *
     * @return The VM arguments
     */
    public List<String> getVmArgs()
    {
        return vmArgs;
    }

    /**
     * Set the vm arguments (can be null)
     *
     * @param vmArgs The new VM arguments
     */
    public void setVmArgs(List<String> vmArgs)
    {
        this.vmArgs = vmArgs;
    }

    /**
     * Return the program arguments (can be null)
     *
     * @return The arguments
     */
    public List<String> getArgs()
    {
        return args;
    }

    /**
     * Set the program arguments (can be null)
     *
     * @param args The new arguments
     */
    public void setArgs(List<String> args)
    {
        this.args = args;
    }

    /**
     * Return if the error stream is redirected (default is false)
     *
     * @return If the error stream is redirected
     */
    public boolean isRedirectErrorStream()
    {
        return redirectErrorStream;
    }

    /**
     * Set if the error stream is redirected (default is false)
     *
     * @param redirectErrorStream The new value
     */
    public void setRedirectErrorStream(boolean redirectErrorStream)
    {
        this.redirectErrorStream = redirectErrorStream;
    }

    /**
     * Return the mac dock name (can be null)
     *
     * @return The mac dock name
     */
    public String getMacDockName()
    {
        return macDockName;
    }

    /**
     * Set the mac dock name (can be null)
     *
     * @param macDockName The new dock name
     */
    public void setMacDockName(String macDockName)
    {
        this.macDockName = macDockName;
    }

    /**
     * Return the directory where the program will be launched (can be null)
     *
     * @return The program directory
     */
    public File getDirectory()
    {
        return directory;
    }

    /**
     * Set the directory where the program will be launched (can be null)
     *
     * @param directory The program directory
     */
    public void setDirectory(File directory)
    {
        this.directory = directory;
    }
}
