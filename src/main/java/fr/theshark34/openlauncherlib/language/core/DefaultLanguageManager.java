/**
 * Copyright 2019 NeutronStars
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.theshark34.openlauncherlib.language.core;

import fr.theshark34.openlauncherlib.configuration.api.Configuration;
import fr.theshark34.openlauncherlib.configuration.api.ConfigurationManager;
import fr.theshark34.openlauncherlib.configuration.core.SimpleConfiguration;
import fr.theshark34.openlauncherlib.language.api.Language;
import fr.theshark34.openlauncherlib.language.api.LanguageInfo;
import fr.theshark34.openlauncherlib.language.api.LanguageManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default Class of LanguageManager
 *
 * @author NeutronStars
 * @version 1.0.0
 */
public class DefaultLanguageManager extends LanguageManager
{
    /**
     * Map of Language registered.
     */
    private final Map<String, Language> langMap = new HashMap<String, Language>();

    /**
     * Instance of ConfigurationManger.
     */
    private final ConfigurationManager configurationManager;

    /**
     * instance of default language. Can be null.
     */
    private Language defaultLanguage;

    public DefaultLanguageManager(Logger logger, ConfigurationManager configurationManager)
    {
        super(logger);
        this.configurationManager = configurationManager;
    }

    /**
     * Retrieve the list of languages registered.
     * @return a {@link java.util.ArrayList} of languages.
     */
    @Override
    public List<Language> getLanguages(){
        return new ArrayList<Language>(langMap.values());
    }

    /**
     * Retrieve the language by name.
     *
     * <p>Warning: Can return a {@link NullPointerException}</p>
     *
     * @param name
     *       The name of language.
     * @return
     *       The language by name.
     */
    @Override
    public Language getLanguage(LanguageInfo name){
        return langMap.get(name.get().toLowerCase());
    }

    /**
     * Retrieve the default language if exist.
     *
     * <p>Warning: Can return a {@link NullPointerException}</p>
     *
     * @return an optional of language.
     */
    @Override
    public Language getDefaultLanguage()
    {
        return defaultLanguage;
    }

    /**
     * Check if the name is the default language.
     * @param name
     *      The name of language.
     * @return true -> if the name is default language.
     */
    @Override
    public boolean isDefaultLanguage(LanguageInfo name)
    {
        return defaultLanguage != null && defaultLanguage.getName().equalsIgnoreCase(name.get());
    }

    /**
     * Modify the default language.
     * @param language
     *          The language.
     */
    @Override
    public void setDefaultLanguage(Language language)
    {
        this.defaultLanguage = language;
    }

    /**
     * Register a new language or if the name already exist then add of the translated by identify.
     * @param identify
     *          The identifier of language.
     * @param name
     *          The name of language.
     * @param path
     *          The path in the folder resource of project.
     */
    @Override
    public void registerLanguage(LanguageInfo identify, LanguageInfo name, String path)
    {
        try {
            Configuration configuration = new SimpleConfiguration(configurationManager.getLogger(), new BufferedReader(new InputStreamReader(DefaultLanguageManager.class.getResourceAsStream(path+name.get()+".json"), Charset.forName("UTF-8"))));
            if(!langMap.containsKey(name.get().toLowerCase())){
                langMap.put(name.get().toLowerCase(), new SimpleLanguage(name, this, identify, configuration));
            }else {
                ((SimpleLanguage)langMap.get(name.get().toLowerCase())).registerLanguage(identify, configuration);
            }
        }catch (IOException e){
            configurationManager.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
