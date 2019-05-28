/*
 * Copyright 2015-2017 Adrien "Litarvan" Navratil
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
import java.util.Locale;

/**
 * The Abstract Option Frame
 *
 * <p>
 *     The base class to use with the Ram Selector to select RAM.
 * </p>
 *
 * @author Litarvan
 * @version 3.0.4
 * @since 3.0.0-BETA
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
        ENGLISH.put("hi-int", "OpenLauncherLib 3.0.5 by Adrien 'Litarvan' Navratil - Internal Launching System");
        ENGLISH.put("hi-ext", "OpenLauncherLib 3.0.5 by Adrien 'Litarvan' Navratil - External Launching System");
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
        ENGLISH.put("support-forge", "Forge is a free open source library, consider supporting them at http://patreon.com/lexmanos");
        ENGLISH.put("old-tweak", "WARNING: You selected tweaking with a version under or equals as 1.5.2, this isn't fully supported, and could cause bugs.");
	    ENGLISH.put("forge-optifine", "WARNING: You selected Forge tweak with Optifine/Shader, they are ONLY FOR VANILLA, the game wil probably not start, so for security, Optifine/Shader was/were disabled");
		ENGLISH.put("tweak-deprec", "WARNING: You selected GameTweaks other than forge with Minecraft 1.13 or greater, the tweaking system has been deleted since, game will probably not start");
		ENGLISH.put("forge-old", "WARNING: You selected Forge tweaking with a version under or equals as 1.5.2, forge is supposed to be installed in the jar (not with a tweaker), the game MAY NOT START !");

        FRENCH.put("hi-int", "OpenLauncherLib 3.0.4 par Adrien 'Litarvan' Navratil - Systeme de lancement interne");
        FRENCH.put("hi-ext", "OpenLauncherLib 3.0.4 par Adrien 'Litarvan' Navratil - Systeme de lancement externe");
        FRENCH.put("options", "Options");
        FRENCH.put("ram", "RAM");
        FRENCH.put("warn", "Attention");
        FRENCH.put("splash-interrupted", "Le temps d'attente du splash a ete interrompu !");
        FRENCH.put("ex-caught", "Exception attrapee !");
        FRENCH.put("report-error", "Impossible d'ecrire le crash report !");
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
		FRENCH.put("support-forge", "Forge est une librairie open source, supportez son developpement sur https://patreon.com/lexmanos");
		FRENCH.put("old-tweak", "ATTENTION: Vous avez choisi d'utiliser le tweaking avec une version inferieure ou egale a la 1.5.2, ce n'est pas vraiment supporte, des bugs peuvent survenir");
		FRENCH.put("forge-optifine", "ATTENTION: Vous avez selectionne les tweaks Forge avec Optifine/Shader, ces derniers sont SEULEMENT POUR LE VANILLA, le jeu ne demarrera sûrement pas, donc Optifine/Shader ont ete desactives");
		FRENCH.put("tweak-deprec", "ATTENTION: Vous avez selectione des GameTweaks autre que Forge avec Minecraft 1.13 ou superieur, le systeme de tweaking a ete supprime depuis, le jeu ne demarrera probablement pas");
		FRENCH.put("forge-old", "ATTENTION: Vous avez selectionne le tweak Forge avec une version inferieure ou egale a la 1.5.2, forge est sense être installe a l'interieur du jar (et non pas via tweaking), le jeu POURAIT NE PAS DEMARRER !");
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

    // Setting system lang
    static
    {
        if (Locale.getDefault().getLanguage().toLowerCase().startsWith("fr"))
        {
            setLang(FRENCH);
        }
    }
}
