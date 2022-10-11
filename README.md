
Table of Contents
- [Information](#information)
- [Building](#building)
- [Usage](#usage)

Information <a name="information"></a>
------------
Canyon Core is an all purpose Minecraft server core. It includes data structures and utilities for handling Minecraft data, as well as a plugin API for ease of creating plugins.

Building <a name="building"></a>
--------
Canyon Core uses (Gradle)[https://gradle.org/] for building.
Steps to build: 
- Clone the repository if you haven't already
- Make sure you have (JDK 17)[https://adoptium.net/?variant=openjdk17&jvmVariant=hotspot] installed
- Run `gradlew build` in the root directory of the project or alternatively open the folder as an Intellij project and run the `jar` method

Usage <a name="usage"></a>
-----

- Command API:
    Canyon Core features a complete Bukkit command abstraction for ease of creation and command handling. Command auto-completions are completely done asynchronously
```java
public final class ExampleCommand extends CanyonCommand {
    
    public ExampleCommand() {
        super(String name, String... aliases);
        this.setPermission(String permission);
        this.setConsoleOnly(boolean consoleOnly); 
    }
   
    
    @Override
    public CommandResult execute(@NotNull CommandSender sender, @NotNull String[] args) {
        
        // Command results are a simple way of defining errors and success messages in your commands
        return CommandResult.Success(); // Would return a default message to the sender
        return CommandResult.Error("Error message"); // Would return an error message to the sender
        return CommandResult.Success("Success message"); // Would return a success message to the sender
        return CommandResult.Error() // Returns the default error message to the sender
        
    }
    
}
```