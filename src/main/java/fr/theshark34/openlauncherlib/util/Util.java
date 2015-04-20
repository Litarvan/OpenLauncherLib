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
package fr.theshark34.openlauncherlib.util;

import java.io.*;
import java.util.ArrayList;

/**
 * The Util class
 *
 * <p>
 *     This class contains a method to lists files in a folder, sub-folder included,
 *     And a method to print the output of a process.
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public class Util {

    /**
     * Lists files in a folder including his sub-folder
     *
     * @param folder
     *            The folder where the files are
     * @return A list of the listed files
     */
    public static ArrayList<File> list(File folder) {
        ArrayList<File> files = new ArrayList<File>();
        if(folder.isDirectory())
            return files;

        File[] folderFiles = folder.listFiles();
        if(folderFiles != null)
            for(File f : folderFiles)
                if(f.isDirectory())
                    files.addAll(list(f));
                else
                    files.add(f);

        return files;
    }

    /**
     * Print a process output and write it to a log file
     *
     * @param process
     *            The process to get the output
     * @param logsFile
     *            The log file to write to output
     * @throws IOException
     *            If it failed to write on the file
     */
    public static void printAndWriteProcessOutput(Process process, File logsFile) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        final BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
        final FileWriter fw = new FileWriter(logsFile);
        new Thread() {
            public void run() {
                try {
                    String line = "";
                    try {
                        while ((line = reader.readLine()) != null) {
                            fw.write(line);
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
                            fw.write(line);
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
