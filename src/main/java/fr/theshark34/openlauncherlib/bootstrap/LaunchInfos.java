/*
 * Copyright 2015 TheShark34
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
package fr.theshark34.openlauncherlib.bootstrap;

/**
 * The Launch Infos
 *
 * <p>
 *     This class contains the launch infos like the arguments or the VM arguments
 * </p>
 *
 * @author TheShark34
 * @version 2.1-SNAPSHOT
 */
public class LaunchInfos {

    /**
     * The command arguments
     */
    private String[] args;

    /**
     * The VM arguments
     */
    private String[] vmArgs;

    /**
     * Basic constructor
     *
     * @param args
     *            The command arguments
     * @param vmArgs
     *            The VM arguments
     */
    public LaunchInfos(String[] args, String[] vmArgs) {
        this.args = args;
        this.vmArgs = vmArgs;
    }

    /**
     * Returns the command arguments
     *
     * @return The command arguments
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Returns the VM arguments
     *
     * @return The VM arguments
     */
    public String[] getVmArgs() {
        return vmArgs;
    }

}
