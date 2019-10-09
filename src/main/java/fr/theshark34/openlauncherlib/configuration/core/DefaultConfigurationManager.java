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
package fr.theshark34.openlauncherlib.configuration.core;

import fr.theshark34.openlauncherlib.configuration.api.Configuration;
import fr.theshark34.openlauncherlib.configuration.api.ConfigurationManager;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Default Class of ConfigurationManager.
 *
 * @author NeutronStars
 * @version 1.0.0
 */
public class DefaultConfigurationManager extends ConfigurationManager
{
    public DefaultConfigurationManager(Logger logger)
    {
        super(logger);
    }

    /**
     * Retrieve the configuration with path. If the file not exist the retrieve a new configuration.
     * @param path
     *      The path of the configuration.
     * @return The configuration.
     * @throws IOException
     *      if the path is not valid the return this exception.
     */
    @Override
    public Configuration getConfiguration(String path) throws IOException
    {
        return new SimpleConfiguration(getLogger(), path);
    }
}
