/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil
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

import fr.theshark34.openlauncherlib.configuration.core.DefaultConfigurationManager;
import fr.theshark34.openlauncherlib.language.api.LanguageInfo;
import fr.theshark34.openlauncherlib.language.api.LanguageManager;
import fr.theshark34.openlauncherlib.language.api.LanguageTypes;
import fr.theshark34.openlauncherlib.language.core.DefaultLanguageManager;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * The Log Util
 *
 * <p>
 *     Useful to print some messages :p
 * </p>
 *
 * @author Litarvan
 * @version 3.0.2-BETA
 * @since 3.0.0-BETA
 *
 * Modified by NeutronStars.
 */
public final class LogUtil
{
    /**
     * Instance of LanguageManager
     */
    private static final LanguageManager LANGUAGE_MANAGER = new DefaultLanguageManager(Logger.getLogger("OpenLauncherLib"), new DefaultConfigurationManager(Logger.getLogger("OpenLauncherLib")));

    /**
     * Instance of Identifier.
     */
    private static final LanguageInfo IDENTIFIER = new LanguageInfo()
    {
        @Override
        public String get()
        {
            return "OpenLauncherLib";
        }
    };

    static {
        LANGUAGE_MANAGER.registerLanguage(IDENTIFIER, LanguageTypes.EN, "/assets/languages/");
        LANGUAGE_MANAGER.registerLanguage(IDENTIFIER, LanguageTypes.FR, "/assets/languages/");

        if (Locale.getDefault().getLanguage().toLowerCase().startsWith("fr")) {
            LANGUAGE_MANAGER.setDefaultLanguage(LANGUAGE_MANAGER.getLanguage(LanguageTypes.FR));
        }else {
            LANGUAGE_MANAGER.setDefaultLanguage(LANGUAGE_MANAGER.getLanguage(LanguageTypes.EN));
        }
    }

    /**
     * Retrieve the LanguageManager.
     * @return the language manager.
     */
    public static LanguageManager getLanguageManager()
    {
        return LANGUAGE_MANAGER;
    }

    /**
     * Retrieve the identifier.
     * @return the identifier.
     */
    public static LanguageInfo getIdentifier()
    {
        return IDENTIFIER;
    }

    /**
     * Print a message, with some translated strings
     *
     * @param err      If it is an error message
     * @param messages All the strings keys to translate
     */
    public static void message(boolean err, String... messages)
    {
        StringBuilder builder = new StringBuilder("[OpenLauncherLib]");
        for(String node : messages){
            builder.append(" ").append(LANGUAGE_MANAGER.getDefaultLanguage().get(IDENTIFIER, node));
        }

        if (err)
            System.err.println(builder.toString());
        else
            System.out.println(builder.toString());
    }

    /**
     * Print a message, with [OpenLauncherLib] before
     *
     * @param message The message to print
     */
    public static void rawInfo(String message)
    {
        System.out.println("[OpenLauncherLib] " + message);
    }

    /**
     * Print an error message, with [OpenLauncherLib] before
     *
     * @param message The message to print
     */
    public static void rawErr(String message)
    {
        System.err.println("[OpenLauncherLib] " + message);
    }

    /**
     * Print an info message, with some translated strings
     *
     * @param messages All the strings keys to translate
     */
    public static void info(String... messages)
    {
        message(false, messages);
    }

    /**
     * Print an error message, with some translated strings
     *
     * @param messages All the strings keys to translate
     */
    public static void err(String... messages)
    {
        message(true, messages);
    }
}
