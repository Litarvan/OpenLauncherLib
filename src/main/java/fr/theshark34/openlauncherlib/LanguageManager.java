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
public class LanguageManager
{
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
     * Setup the default languages
     */
    static
    {
        ENGLISH.put("hi-int", "OpenLauncherLib 3.0.0-BETA by TheShark34 - Internal Launching System");
        ENGLISH.put("hi-ext", "OpenLauncherLib 3.0.0-BETA by TheShark34 - External Launching System");
        ENGLISH.put("options", "Options");
        ENGLISH.put("ram", "RAM");
        ENGLISH.put("warn", "Warning");
        ENGLISH.put("splash-interrupted", "Splash wait time was interrupted !");
        ENGLISH.put("ex-caught", "Exception caught !");
        ENGLISH.put("report-error", "Unable to write the crash report !");
        ENGLISH.put("ram-empty", "Can't read ram : File is empty");
        ENGLISH.put("writing-crash", "Writing crash report to");
        ENGLISH.put("load-fail", "Can't load the given jar");
        ENGLISH.put("jar-notfound", "Can't find the given jar");
        ENGLISH.put("loading", "Loading file");
        ENGLISH.put("mc-check", "Checking Minecraft directory");
        ENGLISH.put("mc-int", "Creating internal launching profile for Minecraft");
        ENGLISH.put("mc-ext", "Creating external launching profile for Minecraft");
        ENGLISH.put("mc-cp", "Generating classpath");
        ENGLISH.put("log-err", "Error while writing the logs !");
        ENGLISH.put("log-end", "Error, logging ended suddenly");
        ENGLISH.put("launching", "Launching program. It is now");
        ENGLISH.put("init", "Initializing main class");
        ENGLISH.put("start", "Starting");
        ENGLISH.put("total", "Total time");
        ENGLISH.put("security", "Detected certificate information error, please delete META-INF in your JAR");
        ENGLISH.put("nat", "Loading the natives");
        ENGLISH.put("done", "Done");
        ENGLISH.put("ent", "Entire command");

        FRENCH.put("hi-int", "OpenLauncherLib 3.0.0-BETA par TheShark34 - Systeme de lancement interne");
        FRENCH.put("hi-ext", "OpenLauncherLib 3.0.0-BETA par TheShark34 - Systeme de lancement externe");
        FRENCH.put("options", "Options");
        FRENCH.put("ram", "RAM");
        FRENCH.put("warn", "Attention");
        FRENCH.put("splash-interrupted", "Le temps d'attente du splash a été interrompu !");
        FRENCH.put("ex-caught", "Exception attrapee !");
        FRENCH.put("report-error", "Impossible d'écrire le crash report !");
        FRENCH.put("ram-empty", "Impossible de lire la RAM : Le fichier est vide");
        FRENCH.put("writing-crash", "Ecriture du crash report dans");
        FRENCH.put("load-fail", "Impossible de charger le jar");
        FRENCH.put("jar-notfound", "Impossible de trouver le jar");
        FRENCH.put("loading", "Chargement du fichier");
        FRENCH.put("mc-check", "Verification du dossier de Minecraft");
        FRENCH.put("mc-int", "Creation d'un profil de lancement interne pour Minecraft");
        FRENCH.put("mc-ext", "Creation d'un profil de lancement externe pour Minecraft");
        FRENCH.put("mc-cp", "Generation du classpath");
        FRENCH.put("log-err", "Erreur en ecrivant les logs !");
        FRENCH.put("log-end", "Erreur, le systeme de logs s'est brusquement arrete");
        FRENCH.put("launching", "Lancement du programme. Il est actuellement");
        FRENCH.put("init", "Initialization de la classe principale");
        FRENCH.put("start", "Lancement de");
        FRENCH.put("total", "Temps total");
        FRENCH.put("security", "Une erreur de certification a ete detectee, merci de supprimer le dossier META-INF de votre .jar");
        FRENCH.put("nat", "Chargement des natives");
        FRENCH.put("done", "Termine");
        FRENCH.put("ent", "Commande entiere");
    }

    /**
     * Get a translated string
     *
     * @param keys The key of the string
     *
     * @return The translated string
     */
    public static String lang(String... keys)
    {
        String total = "";
        String text;

        for (String key : keys)
        {
            text = currentLangSet.get(key);
            if (text == null)
                text = ENGLISH.get(key);

            total += (text == null ? key : text) + " ";
        }

        return total;
    }

    /**
     * Set the language
     *
     * @param langSet The new language set
     */
    public static void setLang(HashMap<String, String> langSet)
    {
        currentLangSet = langSet;
    }

    /**
     * Return the current language, as a lang set
     *
     * @return The current lang set
     */
    public static HashMap<String, String> getCurrentLangSet()
    {
        return currentLangSet;
    }
}
