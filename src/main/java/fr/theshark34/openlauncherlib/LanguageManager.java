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
package fr.theshark34.openlauncherlib;

import java.util.HashMap;

/**
 * The Abstract Option Frame
 *
 * <p>
 *     The base class to use with the Ram Selector to select RAM.
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class LanguageManager {

    /**
     * The ENGLISH Lang set
     */
    public static final HashMap<String, String> ENGLISH = new HashMap<String, String>();

    /**
     * The FRENCH Lang set
     */
    public static final HashMap<String, String> FRENCH = new HashMap<String, String>();

    /**
     * The current language set
     */
    private static HashMap<String, String> currentLangSet = ENGLISH;

    /**
     * Get a translated string
     *
     * @param key
     *            The key of the string
     * @return The translated string
     */
    public static String lang(String key) {
        String text = currentLangSet.get(key);
        if(text == null)
            return ENGLISH.get(key);
        else
            return text;
    }

    /**
     * Set the language
     *
     * @param langSet
     *            The new language set
     */
    public static void setLang(HashMap<String, String> langSet) {
        currentLangSet = langSet;
    }

    /**
     * Return the current language, as a lang set
     *
     * @return The current lang set
     */
    public static HashMap<String, String> getCurrentLangSet() {
        return currentLangSet;
    }

    /**
     * Setup the default languages
     */
    static {
        ENGLISH.put("options", "Options");
        ENGLISH.put("ram", "RAM");
        ENGLISH.put("warn", "Warning");
        ENGLISH.put("splash-interrupted", "Splash wait time was interrupted !");

        FRENCH.put("options", "Options");
        FRENCH.put("ram", "RAM");cd 
        ENGLISH.put("warn", "Attention");
        ENGLISH.put("splash-interrupted", "Le temps d'attente du splash a été intérrompu !");
    }
}
