package AT.MSev.Mango_Tumble.NPCs.Shop;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Shops {
    static ArrayList<ShopInventory> ShopPool = new ArrayList<ShopInventory>()
    {{
        add((
            new ShopInventory(9, "TestShop", "Blacksmith") {{
                AddItem(
                        new ShopItem(Material.DIAMOND, new BigDecimal(100), new ItemStack(Material.DIAMOND_BLOCK)).Physical
                );
            }}
        ));
    }};

    public static ShopInventory GetShop(String shopID)
    {
        for(ShopInventory si : ShopPool)
        {
            if(si.UniqueID.equalsIgnoreCase(shopID)) return si;
        }
        return null;
    }
}
