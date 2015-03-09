/*
 * Copyright 2015 TheShark34
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package fr.theshark34.openlauncherlib;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import net.kronos.mclib.auth.yggdrasil.AuthYggdrasilException;
import net.kronos.mclib.auth.yggdrasil.model.YggdrasilError;
import net.kronos.mclib.auth.yggdrasil.model.response.YggdrasilAuthenticateResponse;
import net.kronos.mclib.auth.yggdrasil.model.response.YggdrasilRefreshResponse;
import fr.theshark34.s_update.S_Update;

/**
 * A Console Launcher
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class Demo {

	/**
	 * The Minecraft directory
	 */
	public static final File GAME_DIR = new File(
			System.getProperty("user.home") + "/.ooldemo");

	/**
	 * The S-Update object
	 */
	private S_Update su;

	/**
	 * The main method
	 * 
	 * @param args
	 *            The given args (useless)
	 */
	public static void main(String[] args) {
		new Demo().start();
	}

	/**
	 * Starts the launcher
	 */
	private void start() {
		// Printing a beautiful message
		System.out.println("\n----- OpenLauncherLib Console Launcher -----\n");
		
		// Trying the refresh
		Object rep = refresh();

		// If the refresh failed trying to authenticate
		if (rep == null)
			rep = auth();

		// Updating Minecraft with S-Update
		update();

		// Launching the game
		launchGame(rep);
	}

	/**
	 * Authenticates the player if the refresh played
	 * 
	 * @return The Authenticator response
	 */
	private YggdrasilAuthenticateResponse auth() {
		// Printing a message
		System.out.println(" > Authenticating : ");
		// Initializing a null response
		YggdrasilAuthenticateResponse rep = null;

		// Initializing the scanner
		Scanner sc = new Scanner(System.in);

		// While true (while the response is not good)
		while (true) {
			// Waiting for user to type username
			System.out.print("	Username : ");
			String username = sc.nextLine();

			// Waiting for user to type password
			System.out.print("	Password : ");
			String password = sc.nextLine();
			
			// Printing a message
			System.out.println(" > Connecting");
			
			try {
				// Trying to authenticate with the given args
				rep = Authenticator.auth(GAME_DIR, username,
						password);

				// If the response is null, aborting
				if (rep == null)
					throw new AuthYggdrasilException(new YggdrasilError());

				// Closing the scanner
				sc.close();

				// Getting out of the loop
				break;
			} catch (AuthYggdrasilException e) {
				// If it fails, printing the error, so the loop will restart
				System.out.println(" > ERROR : "
						+ e.getErrorModel().getErrorMessage());
			}
		}

		// If the authentication successed printing the response
		System.out.println(" > Authentification success :" + "\n	Username : "
				+ rep.getSelectedProfile().getName() + "\n	Access token : "
				+ rep.getAccessToken() + "\n	UUID : "
				+ rep.getSelectedProfile().getId());

		// Returning the response
		return rep;
	}

	/**
	 * It read the saved session created when authenticated before, and try to
	 * refresh it. So if the player authenticated before, it will automatically
	 * reauthenticate
	 * 
	 * @return The Authenticator response
	 */
	private YggdrasilRefreshResponse refresh() {
		// Printing a message
		System.out.println(" > Trying to refresh the session");
		
		// Initializing a null response
		YggdrasilRefreshResponse rep = null;

		try {
			// Trying to refresh
			rep = Authenticator.refresh(GAME_DIR);

			// If it failed aborting
			if (rep == null) {
				// Printing a message
				System.out.println(" > Refresh failed");
				return null;
			}

			// If the refresh successed, printing the response
			System.out.println(" > Refresh success :" + "\n	Username : "
					+ rep.getSelectedProfile().getName() + "\n	Access token : "
					+ rep.getAccessToken() + "\n	UUID : "
					+ rep.getSelectedProfile().getId());
		} catch (AuthYggdrasilException e) {
			// Printing a message
			System.out.println(" > Refresh failed");
		}

		// Returning the response
		return rep;
	}

	/**
	 * Updates Minecraft with S-Update
	 */
	private void update() {
		// Printing a message
		System.out.println("\n-- Starting updating with S-Update --\n");
		
		// Initializing the S-Update object
		su = new S_Update(
				"https://dl.dropboxusercontent.com/u/31232797/OOLDemo/",
				GAME_DIR);
		
		try {
			// If there is an update to do
			if (su.checkForUpdate()) {
				// Creating the lists
				su.createLists();

				// And updating
				su.update();
			}
		} catch (IOException e) {
			// If it failed printing the error
			System.out.println(" > ERROR: Unable to update, Aborting : " + e);

			// Printing a beautiful message
			System.out
					.println("\n--- OpenLauncherLib Console Launcher End ---");

			// Exiting with error code
			System.exit(1);
		}
		// Printing a message
		System.out.println("\n------ Finished updating ------\n");
	}

	/**
	 * Launchs the game
	 * 
	 * @param rep
	 */
	private void launchGame(Object rep) {
		// Printing a message
		System.out.println(" > Launching Minecraft 1.7.10 with Forge");

		// Initializing a null GameLauncher
		GameLauncher gl = null;

		// If the response is an authenticate response
		if (rep instanceof YggdrasilAuthenticateResponse)
			/* Else, initializing the GameLauncher with casts as an Authenticate response, and args :
			 *  - Version		: 1.7.10
			 *  - Game Dir 		: GAME_DIR
			 *  - Title 		: "OpenLauncherLib Demo"
			 *  - Auth Infos	: The username, the access token, and the session id
			 *  - JVM Args 		: "-Xms512M" and "-Xmx1024M"
			 *  - Forge 		: true
			 *  - Legacy Assets : false
			 */
			gl = new GameLauncher("1.7.10", GAME_DIR, "OpenLauncherLib Demo",
					((YggdrasilAuthenticateResponse) rep).getSelectedProfile()
							.getName(),
					((YggdrasilAuthenticateResponse) rep).getAccessToken(),
					((YggdrasilAuthenticateResponse) rep).getSelectedProfile()
							.getId(), new String[] { "-Xms512M", "-Xmx1024M" },
					true, false, false);
		else
			/* Else, initializing the GameLauncher with casts as a Refresh response, and args :
			 *  - Version		: 1.7.10
			 *  - Game Dir 		: GAME_DIR
			 *  - Title 		: "OpenLauncherLib Demo"
			 *  - Auth Infos	: The username, the access token, and the session id
			 *  - JVM Args 		: "-Xms512M" and "-Xmx1024M"
			 *  - Forge 		: true
			 *  - Legacy Assets : false
			 */
			gl = new GameLauncher("1.7.10", GAME_DIR, "OpenLauncherLib Demo",
					((YggdrasilRefreshResponse) rep).getSelectedProfile()
							.getName(),
					((YggdrasilRefreshResponse) rep).getAccessToken(),
					((YggdrasilRefreshResponse) rep).getSelectedProfile()
							.getId(), new String[] { "-Xms512M", "-Xmx1024M" },
					true, false, false);
		try {
			// Starting minecraft
			Process p = gl.launchMinecraft();

			// Printing a message
			System.out.println(" > Launched ! Minecraft will appear after some seconds...\n");
			
			// Printing the minecraft output
			gl.printProcessOutput(p);
			
			// Waiting for minecraft to quit
			p.waitFor();
		} catch (InterruptedException e) {
		} catch (IOException e) {
			// If it failed, printing the error
			System.out.println("ERROR: Unable to launch Minecraft : " + e + "\n");
		}

		// Printing a beautiful message
		System.out.println("\n--- OpenLauncherLib Console Launcher End ---\n");
	}

}
