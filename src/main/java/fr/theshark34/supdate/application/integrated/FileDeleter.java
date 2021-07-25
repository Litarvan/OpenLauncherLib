/*
 * Copyright 2015 TheShark34
 *
 * This file is part of S-Update.

 * S-Update is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * S-Update is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with S-Update.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.theshark34.supdate.application.integrated;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.Application;
import fr.theshark34.supdate.application.event.ApplicationEvent;
import fr.theshark34.supdate.application.event.FileCheckingEvent;
import fr.theshark34.supdate.application.event.fileaction.FileActionEvent;
import fr.theshark34.supdate.exception.BadServerResponseException;
import fr.theshark34.supdate.exception.FileNoPermissionException;

import static fr.theshark34.supdate.SUpdate.logger;

/**
 * The FileDeleter
 *
 * <p>
 *     This is application deletes all files not on the server
 *     excepted the ones in the ignore list on the server.
 *     Kinda bulldozer.
 * </p>
 *
 * <p>
 *   _[TT]_j__,(  <br />
 *  (_)oooo(_)'
 * </p>
 *
 * @version 3.0.0-BETA
 * @author TheShark34
 */
public class FileDeleter extends Application {

    /**
     * The list of files to not delete
     */
    private List<String> ignoreList = new ArrayList<String>();

    @Override
    public String getName() {
        return "FileDeleter";
    }

    @Override
    public boolean isServerRequired() {
        return true;
    }

    @Override
    public void onInit(ApplicationEvent event) {
    }

    @Override
    public void onStart(ApplicationEvent event) {
        logger.info("[FileDeleter] Getting the ignore list");
        try {
            // Sending a get ignore list request
            Object list = event.getSUpdate().getServerRequester().sendRequest("get-ignore-list", new TypeToken<List<String>>() {}.getType());

            // If the response is a string (so its the raw response because the JSON parse failed)
            if(list instanceof String)
                // Throwing a BadServerResponse exception
                throw new BadServerResponseException((String) list);

            // Getting the list
            @SuppressWarnings("unchecked")
			List<String> fileList = (List<String>) list;

            // For each file in the list
            for(String file : fileList)
                /*

                Deleted code, because using glob that need Java 7

                // If this is a glob
                if(file.startsWith("glob:"))
                    // Doing a glob search, and adding all the result to the list
                    ignoreList.addAll(doGlob(file, event.getSUpdate().getOutputFolder().getAbsolutePath()));

                // Else if not
                else*/
                    // Adding it to the list
                    ignoreList.add(file);
        } catch (IOException e) {
            logger.warn("[FileDeleter] Unable to get the ignore list, desactivating the FileDeleter. Error : ", e);

            // Setting the list to null, sign of desactivation
            ignoreList = null;
        } catch (BadServerResponseException e) {
            logger.warn("[FileDeleter] Unable to get the ignore list, desactivating the FileDeleter. Error : ", e);

            // Setting the list to null, sign of desactivation
            ignoreList = null;
        }
    }

    @Override
    public boolean onFileChecking(FileCheckingEvent event) {
        // If the ignore list is null, the application as been desactivated
        if(ignoreList == null)
            // So stopping
            return event.getCheckResult();

        // If a file is checked, it is present on the server,
        // so adding it to the ignore list
        ignoreList.add(event.getCheckedFilePath());

        // Don't modifying the check result
        return event.getCheckResult();
    }

    @Override
    public void onFileAction(FileActionEvent event) {
    }

    @Override
    public void onUpdateEnd(ApplicationEvent event) {
        logger.info("[FileDeleter] Deleting the unknown files");

        // Listing the local folder
        ArrayList<File> files = listFiles(event.getSUpdate().getOutputFolder());

        // For each file
        for(File file : files)
            // If it is not in the ignore list
            if(!isOnIgnoreList(event.getSUpdate(), file))
                // Deleting it
                try {
                    logger.info("[FileDeleter] Deleting file '%s'.", file.getAbsolutePath());
                    event.getSUpdate().getFileManager().delete(file);
                } catch (FileNoPermissionException e) {
                    logger.warn("[FileDeleter] The file '" + file.getAbsolutePath() + "' wasn't deleted, error :",  e);
                }
    }

    /**
     * List all files (recursively) in a folder
     *
     * @param folder
     *            The folder to list
     * @return A list of the listed files
     */
    public static ArrayList<File> listFiles(File folder) {
        File[] files = folder.listFiles();

        ArrayList<File> list = new ArrayList<File>();

        if(files == null)
            return list;

        for(File f : files)
            if(f.isDirectory())
                list.addAll(listFiles(f));
            else
                list.add(f);

        return list;
    }

    /*

    Disabled because it need Java 7

    public static ArrayList<String> doGlob(String glob, final String location) throws IOException {
        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
        final ArrayList<String> results = new ArrayList<String>();

        Files.walkFileTree(Paths.get(location), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(path))
                    results.add(path.toString().replaceFirst(location.replace("\\", "\\\\"), ""));

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });

        return results;
    }*/

    /**
     * Checks if a file is on the ignore list
     *
     * @param file
     *            The file to check
     * @return True if it is, false if not
     */
    public boolean isOnIgnoreList(SUpdate sUpdate, File file) {
        // For each file in the ignore list
        for(String ignoredFilePath : ignoreList) {
            // Getting the local file for the file in the list
            File ignoredFile = new File(sUpdate.getOutputFolder(), ignoredFilePath);

            // If their paths are equals, or the file path contains the ignored file path
            if(ignoredFile.getAbsolutePath().equals(file.getAbsolutePath()) || file.getAbsolutePath().contains(ignoredFile.getAbsolutePath()))
                // Returning true :3
                return true;
        }

        // If this line is executed, so return true wasn't, so
        // returning false.
        return false;
    }

}
