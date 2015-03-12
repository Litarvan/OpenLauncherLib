# OpenLauncherLib
An easy minecraft launcher library

## How to test it ?
Clone this repo, compile with maven to have all libs, and in command line, launch demo.bat/bash

## How to use it ?
### Authentification
```java
YggdrasilRefreshResponse rep = null;
			try {
				rep = Authenticator.refresh(new File("c:/MyMinecraftFiles"));
			} catch (AuthYggdrasilException e1) {
			}
			if(rep == null) {
				YggdrasilAuthenticateResponse rep1 = null;
				try {
					rep1 = Authenticator.auth(new File("c:/MyMinecraftFiles"), "username", "password");
				} catch (AuthYggdrasilException e1) {
				}
				if(rep1 != null) {
					String username = rep1.getSelectedProfile().getName();
					String accessToken = rep1.getAccessToken();
					String UUID = rep1.getSelectedProfile().getId();
				}
			}
			else {
				String username = rep.getSelectedProfile().getName();
				String accessToken = rep.getAccessToken();
				String UUID = rep.getSelectedProfile().getId();
			}
```

### Launching Minecraft

You need to create a GameLauncher object like this :
```java
GameLauncher gl = new GameLauncher(minecraftVersion, gameDirectory, windowTitle, username, accesstoken, uuid, jre arguments, forgeSupport, legacyAssets, newTweakClass);
```
Replace forgeSupport with true if your using MC with Forge
Replace legacyAssets with true if your using MC 1.7.2 or older version.
Replace newTweakClass with true if your using MC 1.8 with forge
Else replace with false

```java
GameLauncher gl = new GameLauncher("1.7.10", new File(
					"C:/MyMinecraftFiles"), "My Minecraft", refRep.getSelectedProfile().getName(), refRep.getAccessToken(), refRep.getSelectedProfile().getId(), new String[] {"-Xms512M", "-Xmx1024M"}, true, false, false);
			try {
				Process p = gl.launchMinecraft();
				gl.printProcessOutput(p);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
```

Will launch Minecraft with Forge 1.7.10 by Exemple
