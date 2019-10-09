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
import fr.theshark34.openlauncherlib.configuration.api.json.JSONReader;
import fr.theshark34.openlauncherlib.configuration.api.json.JSONWriter;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default Class of Configuration
 *
 * @author NeutronStars.
 * @version 1.0.0
 */
public class SimpleConfiguration implements Configuration
{
    /**
     * Instance of Logger.
     */
    private final Logger logger;

    /**
     * File of the configuration
     */
    private final File file;

    /**
     * The data in the configuration.
     */
    private JSONObject object;

    SimpleConfiguration(Logger logger, String path) throws IOException
    {
        this(logger, new File(path));
    }

    private SimpleConfiguration(Logger logger, File file) throws IOException
    {
        this.file = file;
        this.logger = logger;
        if(this.file.exists())
            this.object = new JSONReader(logger, file).toJSONObject();
        else
            this.object = new JSONObject();
    }

    public SimpleConfiguration(Logger logger, BufferedReader reader) throws IOException
    {
        this.file = null;
        this.logger = logger;
        this.object = new JSONReader(logger, reader).toJSONObject();
    }

    public SimpleConfiguration(Logger logger, JSONObject object){
        this.object = object;
        this.file = null;
        this.logger = logger;
    }

    /**
     * Check if the nodes exist in the configuration.
     * @param nodes
     *      Key for retrieve a value in the configuration.
     * @return
     *      True -> if the nodes exist.
     */
    @Override
    public boolean has(String... nodes)
    {
        if(nodes.length == 0) return true;
        JSONObject object = this.object;
        for(int x = 0; x < nodes.length-1;x++){
            if(!object.has(nodes[x])) return false;
            Object o = object.get(nodes[x]);
            if(!(o instanceof JSONObject)) return false;
            object = (JSONObject) o;
        }
        return object.has(nodes[nodes.length-1]);
    }

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
    @Override
    public <T> T get(T def, String... nodes)
    {
        if(nodes.length == 0) return def instanceof JSONObject ? (T) object : def;
        JSONObject object = this.object;
        for(int i = 0; i < nodes.length-1; i++){
            if(!object.has(nodes[i])) return def;
            Object o = object.get(nodes[i]);
            if(!(o instanceof JSONObject)) return def;
            object = (JSONObject) o;
        }
        return object.has(nodes[nodes.length-1]) ? (T) object.get(nodes[nodes.length-1]) : def;
    }

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
    @Override
    public <T> T getOrSet(T def, String... nodes)
    {
        return getOrSet(def, false, nodes);
    }

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
    @Override
    public <T> T getOrSet(T def, boolean save, String... nodes)
    {
        if(!has(nodes)) set(def, save, nodes);
        return get(def, nodes);
    }

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
    @Override
    public void set(Object value, String... nodes){
        set(value, true, nodes);
    }

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
    @Override
    public void set(Object value, boolean save, String... nodes)
    {
        if(nodes.length == 0){
            if(value == null){
                this.object = new JSONObject();
                if(save){
                    try {
                        save();
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, e.getMessage(), e);
                    }
                }
                return;
            }
            else if(!(value instanceof JSONObject)) throw new IllegalArgumentException("Cannot set "+value.getClass().getSimpleName()+" to the root of config.");
            this.object = (JSONObject) value;
            if(save){
                try {
                    save();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                }
            }
            return;
        }
        JSONObject object = this.object;
        for(int i = 0; i < nodes.length - 1; i++){
            if(object.has(nodes[i])){
                Object o = object.get(nodes[i]);
                if(o instanceof JSONObject){
                    object = (JSONObject)o;
                    continue;
                }
            }
            JSONObject o = new JSONObject();
            object.put(nodes[i], o);
            object = o;
        }

        if(value == null)
            object.remove(nodes[nodes.length-1]);
        else {
            object.put(nodes[nodes.length-1], value);
        }

        if(save){
            try {
                save();
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    /**
     * Save the configuration.
     *
     * @throws IOException
     *          if cant save the return this exception.
     */
    @Override
    public void save() throws IOException
    {
        if(this.file == null) return;
        JSONWriter writer = new JSONWriter(this.file);
        writer.write(object);
        writer.flush();
        writer.close();
    }
}
