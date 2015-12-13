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

import fr.theshark34.openlauncherlib.JavaUtil;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ClasspathConstructor;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.internal.InternalLaunchProfile;
import fr.theshark34.openlauncherlib.util.LogUtil;
import fr.theshark34.openlauncherlib.util.explorer.Explorer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The Minecraft Launcher
 *
 * <p>
 *     Contains some methods to create internal/external launch profile for Minecraft.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class MinecraftLauncher
{
    /**
     * Generate an Internal Launch Profile for Minecraft
     *
     * @param infos     The GameInfos (contains your game infos)
     * @param folder    The GameFolder (contains your game folder organization)
     * @param authInfos The AuthInfos (contains the user infos)
     *
     * @return The generated profile
     *
     * @throws LaunchException If it failed
     */
    public static InternalLaunchProfile createInternalProfile(GameInfos infos, GameFolder folder, AuthInfos authInfos) throws LaunchException
    {
        LogUtil.info("mc-int", infos.getGameVersion().getName());
        LogUtil.info("mc-check", infos.getGameDir().getAbsolutePath());

        checkFolder(folder, infos.getGameDir());

        LogUtil.info("mc-cp");
        List<File> libs = Explorer.dir(infos.getGameDir()).sub(folder.getLibsFolder()).allRecursive().files().match("^(.*\\.((jar)$))*$").get();
        libs.add(Explorer.dir(infos.getGameDir()).get(folder.getMainJar()));

        List<String> arguments = infos.getGameVersion().getGameType().getLaunchArgs(infos, folder, authInfos);

        if(infos.getGameTweaks() != null)
            for (GameTweak tweak : infos.getGameTweaks())
            {
                arguments.add("--tweakClass");
                arguments.add(tweak.getTweakClass(infos));
            }

        String mainClass = infos.getGameTweaks() == null || infos.getGameTweaks().length == 0 ? infos.getGameVersion().getGameType().getMainClass(infos) : GameTweak.LAUNCHWRAPPER_MAIN_CLASS;
        String[] args = arguments.toArray(new String[arguments.size()]);

        InternalLaunchProfile profile = new InternalLaunchProfile(mainClass, args);
        profile.setClasspath(libs);

        System.setProperty("fml.ignoreInvalidMinecraftCertificates", "true");

        LogUtil.info("nat");
        try
        {
            JavaUtil.setLibraryPath(new File(infos.getGameDir(), folder.getNativesFolder()).getAbsolutePath());
        }
        catch (Exception e)
        {
            throw new LaunchException("Can't register the natives", e);
        }

        LogUtil.info("done");

        return profile;
    }

    /**
     * Generate an External Launch Profile for Minecraft
     *
     * @param infos     The GameInfos (contains your game infos)
     * @param folder    The GameFolder (contains your game folder organization)
     * @param authInfos The AuthInfos (contains the user infos)
     *
     * @return The generated profile
     *
     * @throws LaunchException If it failed
     */
    public static ExternalLaunchProfile createExternalProfile(GameInfos infos, GameFolder folder, AuthInfos authInfos) throws LaunchException
    {
        LogUtil.info("mc-ext", infos.getGameVersion().getName());
        LogUtil.info("mc-check", infos.getGameDir().getAbsolutePath());

        checkFolder(folder, infos.getGameDir());

        LogUtil.info("mc-cp");

        ClasspathConstructor constructor = new ClasspathConstructor();
        constructor.add(Explorer.dir(infos.getGameDir()).sub(folder.getLibsFolder()).allRecursive().files().match("^(.*\\.((jar)$))*$").get());
        constructor.add(Explorer.dir(infos.getGameDir()).get(folder.getMainJar()));

        String mainClass = infos.getGameTweaks() == null || infos.getGameTweaks().length == 0 ? infos.getGameVersion().getGameType().getMainClass(infos) : GameTweak.LAUNCHWRAPPER_MAIN_CLASS;
        String classpath = constructor.make();
        List<String> args = infos.getGameVersion().getGameType().getLaunchArgs(infos, folder, authInfos);
        List<String> vmArgs = new ArrayList<String>();
        vmArgs.add("-Djava.library.path=" + Explorer.dir(infos.getGameDir()).sub(folder.getNativesFolder()).get().getAbsolutePath());
        vmArgs.add("-Dfml.ignoreInvalidMinecraftCertificates=true");

        if(infos.getGameTweaks() != null)
            for (GameTweak tweak : infos.getGameTweaks())
            {
                args.add("--tweakClass");
                args.add(tweak.getTweakClass(infos));
            }

        ExternalLaunchProfile profile = new ExternalLaunchProfile(mainClass, classpath, vmArgs, args, true, infos.getServerName(), infos.getGameDir());

        LogUtil.info("done");

        return profile;
    }

    /**
     * Checks the given folder organization
     *
     * @param folder    The folder organization
     * @param directory The directory to check
     *
     * @throws FolderException If it failed
     */
    public static void checkFolder(GameFolder folder, File directory) throws FolderException
    {
        File assetsFolder = new File(directory, folder.getAssetsFolder());
        File libsFolder = new File(directory, folder.getLibsFolder());
        File nativesFolder = new File(directory, folder.getNativesFolder());
        File minecraftJar = new File(directory, folder.getMainJar());

        if (!assetsFolder.exists() || assetsFolder.listFiles() == null)
            throw new FolderException("Missing/Empty assets folder (" + assetsFolder.getAbsolutePath() + ")");
        else if (!libsFolder.exists() || libsFolder.listFiles() == null)
            throw new FolderException("Missing/Empty libraries folder (" + libsFolder.getAbsolutePath() + ")");
        else if (!nativesFolder.exists() || nativesFolder.listFiles() == null)
            throw new FolderException("Missing/Empty natives folder (" + nativesFolder.getAbsolutePath() + ")");
        else if (!minecraftJar.exists())
            throw new FolderException("Missing main jar (" + minecraftJar.getAbsolutePath() + ")");
    }
}
