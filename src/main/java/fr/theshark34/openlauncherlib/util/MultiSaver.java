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
package fr.theshark34.openlauncherlib.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Multi Saver
 *
 * <p>
 *     This class can save some strings and load them.
 * <p/>
 *
 * To save them :
 *
 * <pre>
 *     MultiSaver.set(tosave);
 * </pre>
 *
 * To load it :
 *
 * <pre>
 *     String[] loaded = MultiSaver.get(defaultValues);
 * </pre>
 *
 * The default value is returned if the file doesn't exist or an exception happened
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 * @since 2.0.0-SNAPSHOT
 */
public class MultiSaver
{
    /**
     * The file where the values are saved/loaded
     */
    private File file;

    /**
     * The Multi Saver
     *
     * @param file The file where to save
     */
    public MultiSaver(File file)
    {
        this.file = file;
    }

    /**
     * Loads the saved values
     *
     * @param def The default values
     *
     * @return The loaded value, or the defaults if an exception happened
     */
    public String[] get(String[] def)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String loaded = br.readLine();

            br.close();

            return loaded.split("\\|");
        }
        catch (IOException ignored)
        {
        }
        return def;
    }

    /**
     * Saves the given strings
     *
     * @param toSave The strings to save
     */
    public void set(String[] toSave)
    {
        try
        {
            FileWriter fw = new FileWriter(file);

            String finalStr = "";
            for(String str : toSave)
                finalStr += str + "|";

            fw.write(finalStr);
            fw.close();
        }
        catch (IOException ignored)
        {
        }
    }
}
