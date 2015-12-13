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

import fr.theshark34.openlauncherlib.LanguageManager;
import fr.theshark34.openlauncherlib.LaunchException;

public class ClasspathException extends LaunchException
{
    public static final int JAR_NOT_FOUND_ERROR = 0;
    public static final int JAR_LOADING_ERROR = 1;

    public ClasspathException(int type, String str)
    {
        super(type == JAR_NOT_FOUND_ERROR ? LanguageManager.lang("jar-notfound", ":", str) + str : (type == JAR_LOADING_ERROR ? LanguageManager.lang("load-fail", ":", str) : str));
    }

    public ClasspathException(int type, String str, Throwable t)
    {
        super(type == JAR_NOT_FOUND_ERROR ? LanguageManager.lang("jar-notfound", ":", str) + str : (type == JAR_LOADING_ERROR ?  LanguageManager.lang("load-fail", ":", str) : str), t);
    }


}
