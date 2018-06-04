package AT.MSev.Mango_Tumble.NPCs.Shop;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopInventory {
    public Inventory Shop;

    public ShopInventory(int size, String name)
    {
        Shop = Bukkit.createInventory(null, size, name);
    }

    public void AddItem(ItemStack... item)
    {
        Shop.addItem(item);
    }
}
