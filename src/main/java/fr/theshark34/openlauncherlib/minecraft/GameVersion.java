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

/**
 * The Game Version
 *
 * <p>
 *     This contains the type of version, and its precise name.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 * @since 2.0.0-SNAPSHOT
 */
public class GameVersion
{

    /**
     * The name of the version
     */
    private String name;

    /**
     * The type of the version
     */
    private GameType gameType;

    /**
     * Basic constructor
     *
     * @param name     The name of the version
     * @param gameType The type of the version
     */
    public GameVersion(String name, GameType gameType)
    {
        this.name = name;
        this.gameType = gameType;
    }

    /**
     * Returns the name of the version
     *
     * @return The name of the version
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the type of the version
     *
     * @return The type of the version
     */
    public GameType getGameType()
    {
        return gameType;
    }

}
