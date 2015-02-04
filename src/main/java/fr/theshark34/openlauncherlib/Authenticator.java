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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.kronos.mclib.auth.yggdrasil.AuthYggdrasil;
import net.kronos.mclib.auth.yggdrasil.AuthYggdrasilException;
import net.kronos.mclib.auth.yggdrasil.model.YggdrasilAgent;
import net.kronos.mclib.auth.yggdrasil.model.response.YggdrasilAuthenticateResponse;
import net.kronos.mclib.auth.yggdrasil.model.response.YggdrasilRefreshResponse;

/**
 * The Authenticator
 * 
 * @author TheShark34
 * @version ALPHA 0.0.1
 */
public class Authenticator {

	/**
	 * Authenticate
	 * 
	 * @param gameDir
	 *            The game directory
	 * @param username
	 *            The username
	 * @param password
	 *            The password
	 * @return A response of the auth (null if it failed)
	 * @throws AuthYggdrasilException
	 *             If it failed
	 */
	public static YggdrasilAuthenticateResponse auth(File gameDir,
			String username, String password) throws AuthYggdrasilException {
		AuthYggdrasil auth = new AuthYggdrasil(false);
		YggdrasilAuthenticateResponse rep = auth.authenticate(
				YggdrasilAgent.AGENT_MINECRAFT, username, password, "");
		File tokenFile = new File(gameDir, "token.txt");
		tokenFile.getParentFile().mkdirs();
		try {
			FileWriter fw = new FileWriter(tokenFile);
			fw.write(rep.getAccessToken());
			fw.close();
		} catch (IOException e) {
		}
		return rep;
	}

	/**
	 * Refresh a saved session
	 * 
	 * @param gameDir
	 *            The game directory
	 * @return A response of the refresh (null if it failed)
	 * @throws AuthYggdrasilException
	 *             If it failed
	 */
	public static YggdrasilRefreshResponse refresh(File gameDir)
			throws AuthYggdrasilException {
		File tokenFile = new File(gameDir, "token.txt");
		AuthYggdrasil auth = new AuthYggdrasil(false);
		String token = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(tokenFile));
			token = br.readLine();
			br.close();
		} catch (IOException e) {
			return null;
		}
		YggdrasilRefreshResponse rep = auth.refresh(token, "");
		try {
			FileWriter fw = new FileWriter(tokenFile);
			fw.write(rep.getAccessToken());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rep;
	}

}
