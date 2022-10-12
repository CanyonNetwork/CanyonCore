package net.canyon.gui

import net.canyon.utils.MessageContainer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class GUIInventory(type: InventoryType, title: String) : InventoryHolder {

    private val inventory: Inventory = Bukkit.createInventory(this, type, MessageContainer.deserialize(title))

    abstract fun setContents() // Set the contents of the inventory
    abstract fun onAction(action: InventoryAction, player: Player, item: ItemStack, slot: Int) // Handle the click event

    fun display(player: Player) {
        if (inventory.isEmpty) setContents()
        player.openInventory(inventory)
    }

    protected fun setItem(slot: Int, item: ItemStack) {
        inventory.setItem(slot, item)
    }

    protected fun nullifyItem(slot: Int) {
        inventory.setItem(slot, null)
    }

    protected fun setItemRange(start: Int, end: Int, item: ItemStack) {
        for (i in start..end) {
            inventory.setItem(i, item)
        }
    }

    protected fun nullifyItemRange(start: Int, end: Int) {
        for (i in start..end) {
            inventory.setItem(i, null)
        }
    }

    protected fun setItemRow(row: Int, item: ItemStack) {
        for (i in 0..8) {
            inventory.setItem(row * 9 + i, item)
        }
    }

    protected fun fillWithItem(item: ItemStack) {
        for (i in 0..inventory.size) {
            inventory.setItem(i, item)
        }
    }

    override fun getInventory(): Inventory {
        return inventory
    }
}