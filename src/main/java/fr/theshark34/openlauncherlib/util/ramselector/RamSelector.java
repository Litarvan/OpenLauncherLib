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
package fr.theshark34.openlauncherlib.util.ramselector;

import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.openlauncherlib.util.LogUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import javax.swing.JFrame;

/**
 * The RAM Selector
 *
 * <p>
 *     A Tool to select the RAM for your project.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class RamSelector
{

    /**
     * The RAM !
     */
    public static final String[] RAM_ARRAY = new String[]{"1Go", "2Go", "3Go", "4Go", "5Go", "6Go", "7Go", "8Go", "9Go", "10Go"};

    /**
     * The file where to save the ram
     */
    private File file;

    /**
     * The class of the selector frame
     */
    private Class<? extends AbstractOptionFrame> frameClass = OptionFrame.class;

    /**
     * The created frame
     */
    private AbstractOptionFrame frame;

    /**
     * The RAM Selector with a file to save the RAM
     *
     * @param file The file where to save the RAM
     */
    public RamSelector(File file)
    {
        this.file = file;
    }

    /**
     * Display the selector
     *
     * @return The displayed frame, an instance of the given
     * frame class (by default OptionFrame)
     *
     * @see #setFrameClass(Class)
     * @see #getFrameClass()
     */
    public JFrame display()
    {
        if (frame == null)
            try
            {
                Constructor[] contructors = frameClass.getDeclaredConstructors();

                Constructor constructor = null;
                for (Constructor c : contructors)
                    if (c.getParameterTypes().length == 1 && c.getParameterTypes()[0] == RamSelector.class)
                        constructor = c;

                if (constructor == null)
                    throw new IllegalStateException("Can't load the OptionFrame class, it needs to have a constructor with just a RamSelector as argument.");

                frame = (AbstractOptionFrame) constructor.newInstance(this);
                frame.setSelectedIndex(readRam());
            }
            catch (Exception e)
            {
                System.err.println("[OpenLauncherLib] Can't display the Ram Selector !");
                System.err.println(CrashReporter.makeCrashReport("OpenLauncherLib Ram Selector", e));

                return null;
            }

        frame.setVisible(true);

        return frame;
    }

    /**
     * Get the generated RAM arguments
     *
     * @return An array of two strings containing the arguments
     */
    public String[] getRamArguments()
    {
        int maxRam = Integer.parseInt(frame == null ? RAM_ARRAY[readRam()].replace("Go", "") : RAM_ARRAY[frame.getSelectedIndex()].replace("Go", "")) * 1024;
        int minRam = maxRam - 512;

        return new String[]{"-Xms" + minRam + "M", "-Xmx" + maxRam + "M"};
    }

    /**
     * Read the saved ram
     *
     * @return An int, of the selected index of RAM_ARRAY
     */
    private int readRam()
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(file));
            String ramText = br.readLine();

            if (ramText != null)
                return Integer.parseInt(ramText);
            else
                LogUtil.err("warn", "ram-empty");
        }
        catch (IOException e)
        {
            System.err.println("[OpenLauncherLib] WARNING: Can't read ram : " + e);
        }
        finally
        {
            if (br != null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    System.err.println("[OpenLauncherLib] WARNING: Can't close the file : " + e);
                }
        }

        return 0;
    }

    /**
     * Save the RAM
     */
    public void save()
    {
	if (frame == null)
            return;

        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(frame.getSelectedIndex()));
        }
        catch (IOException e)
        {
            System.err.println("[OpenLauncherLib] WARNING: Can't save ram : " + e);
        }
        finally
        {
            if (bw != null)
                try
                {
                    bw.close();
                }
                catch (IOException e)
                {
                    System.err.println("[OpenLauncherLib] WARNING: Can't close the file : " + e);
                }
        }
    }

    /**
     * Return the file where to save the ram
     *
     * @return The file where the ram is saved
     *
     * @see #setFile(File)
     */
    public File getFile()
    {
        return file;
    }

    /**
     * Set the file where to save the ram
     *
     * @param file The new file where the ram is saved
     *
     * @see #getFile()
     */
    public void setFile(File file)
    {
        this.file = file;
    }

    /**
     * Return the class of the selector Frame (? extends JFrame)
     *
     * @return The selector frame class
     *
     * @see #setFrameClass(Class)
     */
    public Class<? extends JFrame> getFrameClass()
    {
        return frameClass;
    }

    /**
     * Set the class of the selector Frame (need to be a JFrame)
     *
     * @param frameClass The new class of the selector
     *
     * @see #getFrameClass()
     */
    public void setFrameClass(Class<? extends AbstractOptionFrame> frameClass)
    {
        this.frameClass = frameClass;
    }
}
