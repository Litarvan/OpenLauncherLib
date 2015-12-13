![OpenLauncherLib logo](http://image.noelshack.com/fichiers/2015/17/1429612277-oolbaniere.png)
=====

# Launch everything you want

## Internal Java Launching

You can launch a Java program using the internal system. It loads some given jars, then it can launch any method from any class you want, it can be non-static, so it will use the constructor you want.

```java
List classpath = Explorer.dir("libs").files().match("^(.*\.((jar)$))*$").get();
InternalLaunchProfile profile = new InternalLaunchProfile("fr.theshark34.MyMainClass", classpath);
profile.launch();
```

This will launches the main(String[] args) method of the fr.theshark34.MyMainClass class, after loading all the jars in the libs folder.
You can add a ClassInitializer, an object that will initialize the main class.
You can also choose the method to invoke, and its parameters.

## External Java Launching

You can launch a Java program using the external system. It launches directly the java program, to run a simple runnable jar with its librairies.

```java
ClasspathConstructor constructor = new ClasspathConstructor();
constructor.add(new File("mymainjar.jar"));
constructor.add(Explorer.dir("libs").files());

ExternalLaunchProfile profile = new ExternalLaunchProfile("fr.theshark34.MyClass", classpath.make());
ExternalLauncher launcher = new ExternalLauncher(profile);

Process p = launcher.launch(); // throws LaunchException
```

This will launch a java process with in classpath: mymainjar.jar and all the files of the libs folder.
You can also add program/vm parameters, and a LaunchingEvent to customize the ProcessBuilder just before launching.

## Any Program Launching

You can run any executable or command, and then use the OpenLauncherLib tools with it.

    Coming soon

## Tools

There are a lot of tools that can be used before or after the program launching.

### The Single Saver

The Saver uses Java Properties to save or load some data. It is useful if your launch has authentication by exemple to save the user name, or things like this.

```java
Saver saver = new Saver(new File("myfile.properties"));
saver.set("username", "jack");
String age = saver.get("age");
```

The data is automatically saved when you do set()

### The Logger

You can use the Logger to activate logging for any program you want.

    Coming soon

### The Process Log Manager

You can use the Log Saver to save the logs to a file.

    Coming soon

### The Splash Screen

The Splash Screen can be used to display a simple splash that you can personalize.

```java
SplashScreen splash = new SplashScreen("MySplashTitle", mySplashImage);
splash.add(new JProgressBar());
splash.displayFor(5000L);
```

### The Ram Selector

The Ram Selector can be used to display a Ram Choosing Frame easily

```java
RamSelector selector = new RamSelector(new File("ram.txt"));
selector.display();

// Then when your launching
String[] ramArguments = selector.getRamArguments();
```

## Minecraft

There is a support for Minecraft launching, you can use these tools to create a Minecraft Launcher:

### Minecraft Launcher

You can use the Minecraft Launcher to create internal/external launch profile for Minecraft

```java
GameInfos infos = new GameInfos("MyMinecraft", new GameVersion("1.7.2", GameType.V1_7_2_LOWER), new GameTweak[] {GameTweak.FORGE});
AuthInfos authInfos = new AuthInfos("PlayerUsername", "token", "uuid");

InternalLaunchProfile profile = MinecraftLauncher.createInternalProfile(infos, GameFolder.BASIC, authInfos);
InternalLauncher launcher = new InternalLauncher(profile);

launcher.launch();
```

### Minecraft Crash Detector

The Crash Detector detects the common Minecraft crashes

    Coming soon