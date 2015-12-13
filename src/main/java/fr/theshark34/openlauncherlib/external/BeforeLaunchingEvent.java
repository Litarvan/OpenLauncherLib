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
package fr.theshark34.openlauncherlib.external;

/**
 * The Before Launching Event
 *
 * <p>
 *     Event that can be set to the external launching system that
 *     will be launched just before the process is started.
 *     Useful to customize the ProcessBuilder.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public interface BeforeLaunchingEvent
{
    /**
     * The onLaunching event
     *
     * @param builder The current process builder after that it was configured
     */
    void onLaunching(ProcessBuilder builder);
}
