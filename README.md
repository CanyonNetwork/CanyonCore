
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
        super("example", "example-alias", "infinitely-many-aliases");
        this.setPermission("example.permission"); // Don't set for no permission
    }
    
    @Override
    public void runPlayer(@NotNull Player player, String[] args) {
        player.sendMessage("Hello, " + player.getName());
    }
    
    @Override
    public void runConsole(@NotNull CommandSender sender, String[] args) {
        sender.sendMessage("Hello, Console");
    }
    
}

// Registering the command
CanyonCore.getCommandManager().register(new ExampleCommand());
```
```kotlin
class ExampleCommand : CanyonCommand("example", "example-alias", "infinitely-many-aliases") {
    
    init {
        this.permission = "example.permission" // Don't set for no permission
    }
    
    override fun runPlayer(player: Player, args: Array<String>) {
        player.sendMessage("Hello, ${player.name}")
    }
    
    override fun runConsole(sender: CommandSender, args: Array<String>) {
        sender.sendMessage("Hello, Console")
    }
    
}

// Registering the command
CanyonCore.commandManager.register(ExampleCommand())
```
- Packet API
    Canyon Core features a complete packet abstraction utilizing PacketEvents 2.0. Canyon Core's async packet listeners allow for handling packet outside of the netty thread on a completely separate thread . This calls for asyncronous design to be kept in mind
```java
public final class ExamplePacketReceiveListener implements AsyncPacketReceiveListener {
    
    @Override 
    public void accept(final PacketReceiveEvent event) {
        event.getUser().sendMessage("Hello, " + event.getUser().getName());
    }
}

public final class ExamplePacketSendListener implements AsyncPacketSendListener {
    
    @Override 
    public void accept(final PacketSendEvent event) {
        event.getUser().sendMessage("Hello, " + event.getUser().getName());
    }
}

// Registering the listeners
CanyonCore.getPacketManager().addListener(new ExamplePacketReceiveListener());
CanyonCore.getPacketManager().addListener(new ExamplePacketSendListener());

// Sending asyncronous titles
CanyonCore.getPacketManager().sendTitle(player, "Hello", "World", 10, 20, 10);
```
``
```kotlin
class ExamplePacketReceiveListener : AsyncPacketReceiveListener {
    
    override fun accept(event: PacketReceiveEvent) {
        event.user.sendMessage("Hello, ${event.user.name}")
    }
}

class ExamplePacketSendListener : AsyncPacketSendListener {
    
    override fun accept(event: PacketSendEvent) {
        event.user.sendMessage("Hello, ${event.user.name}")
    }
}

// Registering the listeners
CanyonCore.packetManager.addListener(ExamplePacketReceiveListener())
CanyonCore.packetManager.addListener(ExamplePacketSendListener())

// Sending asyncronous titles
CanyonCore.packetManager.sendTitle(player, "Hello", "World", 10, 20, 10)
```

- GUI API
    CanyonCore has a simple yet powerful GUI API for creating GUI Inventories. The API is designed to be as simple as possible, while still being powerful enough to create complex GUIs
```java
public final class ExampleGUI extends GUIInventory {

    public ExampleGUI() {
        super(InventoryType.ANVIL, "Example GUI");
        super(4, "Example GUI"); // For chest inventories, use this constructor. 4 here is the amount of rows
    }

    @Override
    public void setContents() {
        ItemStack exampleItem = new ItemStack(Material.DIAMOND);
        setItem(0, exampleItem); // TODO: create a method to define actions here
    }

    @Override
    public void onAction(InventoryAction action, Player player, ItemStack item, int slot) {
        player.sendMessage("You clicked on slot " + slot);
    }
}
// After this simply instantiate of cache your gui and call GUIInventory#display(Player)
```
```kotlin
class ExampleGUI : GUIInventory(InventoryType.ANVIL, "Example GUI") {
    
    init {
        super(4, "Example GUI") // For chest inventories, use this constructor. 4 here is the amount of rows
    }

    override fun setContents() {
        val exampleItem = ItemStack(Material.DIAMOND)
        setItem(0, exampleItem) // TODO: create a method to define actions here
    }

    override fun onAction(action: InventoryAction, player: Player, item: ItemStack, slot: Int) {
        player.sendMessage("You clicked on slot $slot")
    }
}
// After this simply instantiate of cache your gui and call GUIInventory#display(Player)
```