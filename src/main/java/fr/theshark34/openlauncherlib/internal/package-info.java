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

/**
 * The Internal Launching Method
 *
 * <p>
 *     Launch a program by adding the jars to the current classpath and
 *     then launching manually a method of the target class.
 *
 *     A lot faster than the external method.
 * </p>
 *
 * Code example :
 *
 * <pre>{@code
 *     List<File> classpath = Explorer.dir("libs").files().match("^(.*\.((jar)$))*$").get();
 *     InternalLaunchProfile profile = new InternalLaunchProfile("fr.theshark34.MyMainClass", classpath);
 *     profile.launch();
 * }</pre>
 *
 * This will launch the main(String[] args) of the given class.
 * You can also choose the method to launch, and its parameters.
 * And, you can receive what it returned.
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
package fr.theshark34.openlauncherlib.internal;