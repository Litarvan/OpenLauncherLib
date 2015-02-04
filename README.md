# OpenLauncherLib
An easy minecraft launcher library

## How to use it ?
---------------------------------
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
```java
`GameLauncher gl = new GameLauncher("(example)1.7.10", new File(
					"C:/MyMinecraftFiles"), "My Minecraft", refRep.getSelectedProfile().getName(), refRep.getAccessToken(), refRep.getSelectedProfile().getId(), new String[] {"-Xms512M", "-Xmx1024M"}, true);
			try {
				Process p = gl.launchMinecraft();
				gl.printProcessOutput(p);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
```
