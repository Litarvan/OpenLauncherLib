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

/**
 * @author NeutronStars
 * @version 1.0.0
 */
public interface Configuration
{
    /**
     * Check if the nodes exist in the configuration.
     * @param nodes
     *      Key for retrieve a value in the configuration.
     * @return
     *      True -> if the nodes exist.
     */
    boolean has(String... nodes);

    /**
     * Retrieve the value with nodes in the configuration.
     * @param def
     *      default value if nodes not exist.
     * @param nodes
     *      key for retrieve the value in the configuration.
     * @param <T>
     *     Type of value. <p>Warning: Can return {@link ClassCastException}</p>
     * @return
     *     The value with nodes or def.
     */
    <T> T get(T def, String... nodes);

    /**
     * Retrieve the value with nodes in the configuration. If the nodes not exist then the default value is added in the configuration.
     *
     * <p>Warning: This function dont save. You need call the function {@link #save()}</p>
     *
     * @param def
     *      default value if nodes not exist.
     * @param nodes
     *      key for retrieve the value in the configuration.
     * @param <T>
     *     Type of value. <p>Warning: Can return {@link ClassCastException}</p>
     * @return
     *     The value with nodes or def.
     */
    <T> T getOrSet(T def, String... nodes);

    /**
     * Retrieve the value with nodes in the configuration. If the nodes not exist then the default value is added in the configuration.
     *
     * @param def
     *      default value if nodes not exist.
     * @param save
     *      if True then save the configuration.
     * @param nodes
     *      key for retrieve the value in the configuration.
     * @param <T>
     *     Type of value. <p>Warning: Can return {@link ClassCastException}</p>
     * @return
     *     The value with nodes or def.
     */
    <T> T getOrSet(T def, boolean save, String... nodes);

    /**
     * Add new value in the configuration with the keys nodes.
     *
     * <p>Warning: This function dont save. You need call the function {@link #save()}</p>
     *
     * @param value
     *         The new value.
     * @param nodes
     *         The keys in the configuration.
     */
    void set(Object value, String... nodes);

    /**
     * Add new value in the configuration with the keys nodes.
     *
     * @param value
     *         The new value.
     * @param save
     *         if True then save the configuration.
     * @param nodes
     *         The keys in the configuration.
     */
    void set(Object value, boolean save, String... nodes);

    /**
     * Save the configuration.
     *
     * @throws IOException
     *          if cant save the return this exception.
     */
    void save() throws IOException;
}
