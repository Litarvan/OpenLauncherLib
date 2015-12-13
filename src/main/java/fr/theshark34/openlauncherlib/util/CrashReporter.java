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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * The Crash Reporter
 *
 * <p>
 *     The Crash Reporter can catch errors and save them as a crash report.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class CrashReporter
{

    /**
     * The directory to write the crashes
     */
    private File dir;

    /**
     * The reporter name
     */
    private String name;

    /**
     * Basic constructor
     *
     * @param name The project name
     * @param dir The directory to write the crashes
     */
    public CrashReporter(String name, File dir)
    {
        this.name = name;
        this.dir = dir;
    }

    /**
     * Catch an error and write it to a crash
     *
     * @param e       The error to catch
     * @param message The error message
     */
    public void catchError(Exception e, String message)
    {
        LogUtil.err("ex-caught");

        System.out.println(makeCrashReport(name, e));

        String msg;

        try
        {
            File report = writeError(e);
            msg = "\nThe crash report is in : " + report.getAbsolutePath() + "";
        }
        catch (IOException e2)
        {
            LogUtil.err("report-error");
            e.printStackTrace();
            msg = "\nAnd unable to write the crash report :( : " + e2;
        }

        JOptionPane.showMessageDialog(null, message + "\n" + e + "\n" + msg, "Error", JOptionPane.ERROR_MESSAGE);

        System.exit(1);
    }

    /**
     * Write a stacktrace to a file
     *
     * @param e The exception
     *
     * @return The file where the crash was saved
     *
     * @throws IOException If it failed to write the crash
     */
    public File writeError(Exception e) throws IOException
    {
        File file;
        int number = 0;
        while ((file = new File(dir, "crash-" + number + ".txt")).exists())
            number++;

        LogUtil.info("writing-crash", file.getAbsolutePath());

        file.getParentFile().mkdirs();

        FileWriter fw = new FileWriter(file);

        fw.write(makeCrashReport(name, e));

        fw.close();

        return file;
    }

    /**
     * Return the crash directory
     * @return The crash dir
     */
    public File getDir()
    {
        return dir;
    }

    /**
     * Set the directory where are the crashes
     * @param dir The crash dir
     */
    public void setDir(File dir)
    {
        this.dir = dir;
    }

    /**
     * Return the reporter name
     * @return The name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the reporter name
     * @param name The new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Create a crash report with an exception
     *
     * @param e           The exception to make the crash report
     * @param projectName The name of your project
     *
     * @return The made crash report
     */
    public static String makeCrashReport(String projectName, Exception e)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String report = "# " + projectName + " Crash Report\n\r" +
                        "#\n\r" +
                        "# At : " + dateFormat.format(date) + "\n\r" +
                        "#\n\r" +
                        "# Exception : " + e.getClass().getSimpleName() + "\n\r";

        report += "\n\r# " + e.toString();

        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement element : stackTrace)
            report += "\n\r#     " + element;

        Throwable cause = e.getCause();
        if (cause != null)
        {
            report += "\n\r# Caused by: " + cause.toString();

            StackTraceElement[] causeStackTrace = cause.getStackTrace();
            for (StackTraceElement element : causeStackTrace)
                report += "\n\r#     " + element;
        }

        return report;
    }
}
