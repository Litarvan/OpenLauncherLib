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
package fr.theshark34.openlauncherlib.language.api;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * The abstract class of LanguageManager.
 *
 * @author NeutronStars
 * @version 1.0.0
 */
public abstract class LanguageManager
{

    /**
     * instance of logger.
     */
    private final Logger logger;

    protected LanguageManager(Logger logger)
    {
        this.logger = logger;
    }

    /**
     * Retrieve the logger.
     * @return the logger.
     */
    public Logger getLogger()
    {
        return logger;
    }

    /**
     * Retrieve the list of languages registered.
     * @return a {@link java.util.ArrayList} of languages.
     */
    public abstract List<Language> getLanguages();

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
    public abstract Language getLanguage(LanguageInfo name);

    /**
     * Retrieve the default language if exist.
     *
     * <p>Warning: Can return a {@link NullPointerException}</p>
     *
     * @return an optional of language.
     */
    public abstract Language getDefaultLanguage();

    /**
     * Check if the name is the default language.
     * @param name
     *      The name of language.
     * @return true -> if the name is default language.
     */
    public abstract boolean isDefaultLanguage(LanguageInfo name);

    /**
     * Modify the default language.
     * @param language
     *          The language.
     */
    public abstract void setDefaultLanguage(Language language);

    /**
     * Register a new language or if the name already exist then add of the translated by identify.
     * @param identify
     *          The identifier of language.
     * @param name
     *          The name of language.
     * @param path
     *          The path in the folder resource of project.
     */
    public abstract void registerLanguage(LanguageInfo identify, LanguageInfo name, String path);
}
