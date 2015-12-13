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

import fr.theshark34.openlauncherlib.JavaUtil;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.util.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The External Launcher
 *
 * <p>
 *     Launch a program using java command launched by a ProcessBuilder.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class ExternalLauncher
{
    /**
     * The Before Launching Event
     *
     * @see BeforeLaunchingEvent
     */
    private BeforeLaunchingEvent launchingEvent;

    /**
     * The launch profile, contains all the information about the launching
     *
     * @see ExternalLaunchProfile
     */
    private ExternalLaunchProfile profile;

    /**
     * The External Launcher
     *
     * @param profile The launch profile
     *
     * @see ExternalLaunchProfile
     */
    public ExternalLauncher(ExternalLaunchProfile profile)
    {
        this(profile, null);
    }

    /**
     * The External Launcher (with Launching Event)
     *
     * @param profile        The launch profile
     * @param launchingEvent The launching event (optional)
     *
     * @see ExternalLaunchProfile
     * @see BeforeLaunchingEvent
     */
    public ExternalLauncher(ExternalLaunchProfile profile, BeforeLaunchingEvent launchingEvent)
    {
        this.profile = profile;
        this.launchingEvent = launchingEvent;
    }

    /**
     * Launch the program !
     *
     * @return The created (and launched) process
     *
     * @throws LaunchException If it failed something
     */
    public Process launch() throws LaunchException
    {
        LogUtil.info("hi-ext");

        ProcessBuilder builder = new ProcessBuilder();
        ArrayList<String> commands = new ArrayList<String>();
        commands.add(JavaUtil.getJavaCommand());
        commands.addAll(Arrays.asList(JavaUtil.getSpecialArgs()));

        if (profile.getMacDockName() != null && System.getProperty("os.name").toLowerCase().contains("mac"))
            commands.add(JavaUtil.macDockName(profile.getMacDockName()));
        if (profile.getVmArgs() != null)
            commands.addAll(profile.getVmArgs());

        commands.add("-cp");
        commands.add(profile.getClassPath());

        commands.add(profile.getMainClass());

        if (profile.getArgs() != null)
            commands.addAll(profile.getArgs());

        if (profile.getDirectory() != null)
            builder.directory(profile.getDirectory());

        if (profile.isRedirectErrorStream())
            builder.redirectErrorStream(true);

        if (launchingEvent != null)
            launchingEvent.onLaunching(builder);

        builder.command(commands);

        String entireCommand = "";
        for(String command : commands)
            entireCommand += command + " ";

        LogUtil.info("ent", ":", entireCommand);
        LogUtil.info("start", profile.getMainClass());

        try
        {
            return builder.start();
        }
        catch (IOException e)
        {
            throw new LaunchException("Cannot launch !", e);
        }
    }

    /**
     * The Before Launching event
     * Null by default, or the given one
     *
     * @return The set launching event
     *
     * @see BeforeLaunchingEvent
     */
    public BeforeLaunchingEvent getLaunchingEvent()
    {
        return launchingEvent;
    }

    /**
     * Set the launching event (executed just before the launching to customize the ProcessBuilder)
     *
     * @param launchingEvent The launching event to use
     *
     * @see BeforeLaunchingEvent
     */
    public void setLaunchingEvent(BeforeLaunchingEvent launchingEvent)
    {
        this.launchingEvent = launchingEvent;
    }

    /**
     * Return the given launch profile (containing all the launch information)
     *
     * @return The launch profile
     *
     * @see ExternalLaunchProfile
     */
    public ExternalLaunchProfile getProfile()
    {
        return profile;
    }

    /**
     * Set a new launch profile
     *
     * @param profile The new profile
     *
     * @see ExternalLaunchProfile
     */
    public void setProfile(ExternalLaunchProfile profile)
    {
        this.profile = profile;
    }
}
