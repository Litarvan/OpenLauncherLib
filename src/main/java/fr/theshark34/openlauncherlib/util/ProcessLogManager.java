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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The Process Log Manager
 *
 * <p>
 *     Manager logs of a Process by printing and/or writing them.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 * @see Process
 */
public class ProcessLogManager extends Thread
{
    /**
     * If the logs should be printed
     */
    private boolean print = true;

    /**
     * The reader
     */
    private BufferedReader reader;

    /**
     * The file where to write the logs (optional)
     */
    private File toWrite;

    /**
     * The writer to write the logs
     */
    private BufferedWriter writer;

    /**
     * Simple constructor
     *
     * @param input The input where to read the logs
     */
    public ProcessLogManager(InputStream input)
    {
        this(input, null);
    }

    /**
     * Complete constructor
     *
     * @param input   The input where to read the logs
     * @param toWrite The files where to write the logs (optional)
     */
    public ProcessLogManager(InputStream input, File toWrite)
    {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.toWrite = toWrite;

        if (toWrite != null)
            try
            {
                this.writer = new BufferedWriter(new FileWriter(toWrite));
            }
            catch (IOException e)
            {
                LogUtil.err("log-err", e.toString());
            }
    }

    @Override
    public void run()
    {
        String line;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                if (print)
                    System.out.println(line);

                if (writer != null)
                    try
                    {
                        writer.write(line + "\n");
                    }
                    catch (IOException e)
                    {
                        LogUtil.err("log-err", e.toString());
                    }
            }
        }
        catch (IOException e)
        {
            LogUtil.err("log-end", e.toString());

            this.interrupt();
        }

        if (writer != null)
            try
            {
                writer.close();
            }
            catch (IOException ignored)
            {
            }
    }

    /**
     * If the logs are printed
     *
     * @return True if they are, false if not
     */
    public boolean isPrint()
    {
        return print;
    }

    /**
     * Set if the logs should be printed
     *
     * @param print If they should be printed
     */
    public void setPrint(boolean print)
    {
        this.print = print;
    }

    /**
     * Return the file where the logs are written
     *
     * @return The file where are written the logs
     */
    public File getToWrite()
    {
        return toWrite;
    }

    /**
     * Set the files where to write the logs
     *
     * @param toWrite The new file
     */
    public void setToWrite(File toWrite)
    {
        this.toWrite = toWrite;
    }
}
