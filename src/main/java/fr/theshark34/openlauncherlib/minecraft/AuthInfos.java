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
 * The Auth Infos
 *
 * <p>
 *     The Auth Infos containing the player username, and the access token given
 *     by the authentication.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 * @since 2.0.0-SNAPSHOT
 */
public class AuthInfos
{

    /**
     * The player username
     */
    private String username;

    /**
     * The access token given by the authentication
     */
    private String accessToken;

    /**
     * The uuid given by the authentication
     */
    private String uuid;

    /**
     * Basic constructor
     *
     * @param username    The player username
     * @param accessToken The access token given by the authentication
     * @param uuid        The player UUID
     */
    public AuthInfos(String username, String accessToken, String uuid)
    {
        this.username = username;
        this.accessToken = accessToken;
        this.uuid = uuid;
    }

    /**
     * Returns the player username
     *
     * @return The player username
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Returns the access token given by the authentication
     *
     * @return The access token
     */
    public String getAccessToken()
    {
        return this.accessToken;
    }

    /**
     * Returns the uuid given by the authentication
     *
     * @return The uuid
     */
    public String getUuid()
    {
        return this.uuid;
    }

}
