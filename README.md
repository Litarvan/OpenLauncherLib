![OpenLauncherLib logo](http://image.noelshack.com/fichiers/2015/17/1429612277-oolbaniere.png)
=====

# Launch everything you want

## Internal Java Launching

You can launch a Java program using the internal system. It loads some given jars, then it cans launch any method from any class you wan't, it can be non-static, so it will use the constructor you want.

    Comming soon

## External Java Launching

You can launch a Java program using the external system. It launch directly the java program, to run a simple runnable jar with its librairies.

    Comming soon

## Any Program Launching

You can run any executable or command, and then use the OpenLauncherLib tools with it.

    Comming soon

## Tools

There is a lot of tools to use before or after the program launching.

### The Saver

The Saver use Java Properties to save or load some datas. It is useful if your launch has authentication by exemple to save the user name, or things like this.

    Saver saver = new Saver(new File("myfile.properties"));
    saver.set("username", "jack");
    String age = saver.get("age");
    saver.save();

### The Logger

You can use the Logger to activate logging for any program you want.

    Comming soon

### The Log Saver

You can use the Log Saver to save the logs to a file.

    Comming soon

### The Crash Detector

The Crash Detector detects the most common crashes or errors

    Comming soon

### The Splash Screen

The Splash Screen can be used to display a simple splash that you can personalize.

    SplashScreen splash = new SplashScreen("MySplashTitle", mySplashImage);
    splash.add(new JProgressBar());
    splash.displayFor(5000L);


## Minecraft

There is a support for Minecraft launching, you can use these tools to create a Minecraft Launcher :

### Pre-defined Arguments

You can use the pre-defined arguments to adapt the launching to each Minecraft version

    Comming soon

### Minecraft Crash Detector

The Crash Detector detect the common Minecraft crashes

    Comming soon