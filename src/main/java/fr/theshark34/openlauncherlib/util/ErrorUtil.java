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

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Error Util
 *
 * <p>
 *     This class contains some methods to easily catch errors.
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public class ErrorUtil {

    /**
     * The directory to write the crashes
     */
    private File gameDir;

    /**
     * Basic contructor
     *
     * @param gameDir
     *            The directory to write the crashes
     */
    public ErrorUtil(File gameDir) {
        this.gameDir = gameDir;
    }

    /**
     * Catch an error and write it to a crash
     *
     * @param e
     *            The error to catch
     * @param message
     *            The error message
     */
    public void catchError(Exception e, String message) {
        e.printStackTrace();
        String str = "\nLe crash report se trouve dans le dossier "
                + gameDir.getAbsolutePath()
                + "/Launcher/bootstrap-crash-(NOMBRE).txt";
        try {
            writeError(e);
        } catch (IOException e2) {
            e.printStackTrace();
            str = "\nDe plus impossible d'ecrire le crash report :(";
        }
        JOptionPane.showMessageDialog(null, message + "\n" + e + "\n" + str,
                "Erreur", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    /**
     * Write a crash to a file
     *
     * @param e
     *            The error
     * @throws IOException
     *            If it failed to write the crash
     */
    private void writeError(Exception e) throws IOException {
        File file;
        int number = 0;
        while ((file = new File(gameDir, "Launcher/bootstrap-crash-"
                + number + ".txt")).exists())
            number++;
        FileWriter fw = new FileWriter(file);
        fw.write(e.toString());
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement element : stackTrace)
            fw.write("\n    " + element);
        fw.close();
    }

}
