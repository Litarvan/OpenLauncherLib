/*
 * Copyright 2015 TheShark34
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package fr.theshark34.openlauncherlib;

/**
 * The Game Folder containing the game folder organisation
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public abstract class GameFolder {

    /**
     * The name of the folder containing the assets
     */
    private String assetsFolder;

    /**
     * The name of the folder containing the librairies
     */
    private String libsFolder;

    /**
     * The name of the folder containing the natives
     */
    private String nativesFolder;

    /**
     * The name of the main jar
     */
    private String mainJar;

    /**
     * The Main Constructor
     *
     * @param assetsFolder
     *            The name of the folder containing the assets
     * @param libsFolder
     *            The name of the folder containing the librairies
     * @param nativesFolder
     *            The name of the folder containing the natives
     * @param mainJar
     *            The name of the main Jar
     */
    public GameFolder(String assetsFolder, String libsFolder, String nativesFolder, String mainJar) {
        this.assetsFolder = assetsFolder;
        this.libsFolder = libsFolder;
        this.nativesFolder = nativesFolder;
        this.mainJar = mainJar;
    }

    /**
     * Returns the name of the folder containing the assets
     *
     * @return The name of the assets folder
     */
    public String getAssetsFolder() {
        return assetsFolder;
    }

    /**
     * Returns the name of the folder containing the librairies
     *
     * @return The name of the librairies folder
     */
    public String getLibsFolder() {
        return libsFolder;
    }

    /**
     * Returns the name of the folder containing the natives
     *
     * @return The name of the natives folder
     */
    public String getNativesFolder() {
        return nativesFolder;
    }

    public String getMainJar() {
        return mainJar;
    }

}
