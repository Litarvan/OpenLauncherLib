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
package fr.theshark34.openlauncherlib.configuration.api;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author NeutronStars
 * @version 1.0.0
 */

public abstract class ConfigurationManager
{
    /**
     * Instance of Logger.
     */
    private final Logger logger;

    protected ConfigurationManager(Logger logger){
        this.logger = logger;
    }

    /**
     * Retrieve the logger.
     * @return The logger.
     */
    public Logger getLogger()
    {
        return logger;
    }

    /**
     * Retrieve the configuration with path. If the file not exist the retrieve a new configuration.
     * @param path
     *      The path of the configuration.
     * @return The configuration.
     * @throws IOException
     *      if the path is not valid the return this exception.
     */
    public abstract Configuration getConfiguration(String path) throws IOException;
}
