package AT.MSev.Mango_Tumble.NPCs.Shop.Shops;

import AT.MSev.Mango_Tumble.NPCs.Shop.IShop;
import AT.MSev.Mango_Tumble.NPCs.Shop.ShopInventory;
import AT.MSev.Mango_Tumble.NPCs.Shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;

public class TestShop implements IShop {
    public Inventory Shop()
    {
        ShopInventory Blacksmith = new ShopInventory(9, "Blacksmith");
        Blacksmith.AddItem(
                new ShopItem(Material.DIAMOND, new BigDecimal(100), new ItemStack(Material.DIAMOND_BLOCK)).Physical
        );
        return Blacksmith.Shop;
    }
}
