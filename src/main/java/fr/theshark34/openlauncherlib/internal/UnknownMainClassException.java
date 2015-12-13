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
package fr.theshark34.openlauncherlib.internal;

import fr.theshark34.openlauncherlib.LaunchException;

/**
 * The Unknown Main Class Exception
 *
 * <p>
 *     Thrown when the main class is not found
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class UnknownMainClassException extends LaunchException
{
    /**
     * The Unknown main class exception
     *
     * @param message The message
     * @param t       The cause
     */
    public UnknownMainClassException(String message, Throwable t)
    {
        super(message, t);
    }
}
