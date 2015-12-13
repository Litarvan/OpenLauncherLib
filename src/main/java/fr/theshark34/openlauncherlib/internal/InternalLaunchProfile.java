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
package fr.theshark34.openlauncherlib.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The Internal Launch Profile
 *
 * <p>
 *     Contains all the informations about an internal launching
 * </p>
 *
 * @author TheShark34
 * @version 3.0.0-BETA
 */
public class InternalLaunchProfile
{
    /**
     * The target class to launch
     */
    private String targetClass;

    /**
     * The target method to launch
     */
    private String targetMethod;

    /**
     * The method parameters
     */
    private Object[] parameters;

    /**
     * The parameters of the method
     */
    private Class[] parametersTypes;

    /**
     * The jars to add to the classpath
     */
    private List<File> classpath = new ArrayList<File>();

    /**
     * Simple launch profile, method is main(String[] args) (with empty arguments)
     *
     * @param targetClass The target class to launch
     */
    public InternalLaunchProfile(String targetClass)
    {
        this(targetClass, new String[0]);
    }

    /**
     * Medium launch profile, method is main(String[] args) with the given parameters
     *
     * @param targetClass The target class
     * @param parameters  The parameters to give to the main method (optional)
     */
    public InternalLaunchProfile(String targetClass, String[] parameters)
    {
        this(targetClass, "main", new Object[]{parameters}, new Class[]{String[].class});
    }

    /**
     * Full launch profile
     *
     * @param targetClass     The target class
     * @param targetMethod    The target method
     * @param parameters      The parameters to give to the method
     * @param parametersTypes The type of the method parameters
     */
    public InternalLaunchProfile(String targetClass, String targetMethod, Object[] parameters, Class[] parametersTypes)
    {
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.parameters = parameters;
        this.parametersTypes = parametersTypes;
    }

    /**
     * Return the target class
     *
     * @return The target class
     */
    public String getTargetClass()
    {
        return targetClass;
    }

    /**
     * Set the target class
     *
     * @param targetClass The new target class
     */
    public void setTargetClass(String targetClass)
    {
        this.targetClass = targetClass;
    }

    /**
     * Return the target method
     *
     * @return The target method
     */
    public String getTargetMethod()
    {
        return targetMethod;
    }

    /**
     * Set the target method
     *
     * @param targetMethod The new target method
     */
    public void setTargetMethod(String targetMethod)
    {
        this.targetMethod = targetMethod;
    }

    /**
     * Return the method parameters
     *
     * @return The parameters
     */
    public Object[] getParameters()
    {
        return parameters;
    }

    /**
     * Set the method parameters
     *
     * @param parameters The parameters
     */
    public void setParameters(Object[] parameters)
    {
        this.parameters = parameters;
    }

    /**
     * Return the parameters types
     *
     * @return The parameters types
     */
    public Class[] getParametersTypes()
    {
        return parametersTypes;
    }

    /**
     * Set the parameters types
     *
     * @param parametersTypes The parameters types
     */
    public void setParametersTypes(Class[] parametersTypes)
    {
        this.parametersTypes = parametersTypes;
    }

    /**
     * Return the classpath
     *
     * @return The classpath
     */
    public List<File> getClasspath()
    {
        return classpath;
    }

    /**
     * Set the classpath
     *
     * @param classpath The classpath
     */
    public void setClasspath(List<File> classpath)
    {
        this.classpath = classpath;
    }
}
