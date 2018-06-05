package AT.MSev.Mango_Tumble.NPCs.Shop;

import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import com.earth2me.essentials.api.UserDoesNotExistException;
import org.bukkit.Material;
import net.ess3.api.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShopItem extends ItemInteractable {
    BigDecimal BuyPrice;
    ItemStack Real;

    public ShopItem(Material appearance, BigDecimal buyPrice, ItemStack real) {
        super("unnamed", appearance);
        BuyPrice = buyPrice;
        MangoUtils.ItemRelore(Physical, new ArrayList<String>() {{ add("Buy for " + BuyPrice); }});
        MangoUtils.ItemRename(Physical, real.getItemMeta().getDisplayName());
        Real = real;
    }

    @Override
    protected Boolean OnInventoryClick(InventoryClickEvent e) {
        Boolean result = super.OnInventoryClick(e);
        e.setCancelled(true);
        try {
            Player p = ((Player) e.getWhoClicked());
            BigDecimal  playerMoney = Economy.getMoneyExact(p.getName());
            if (playerMoney.compareTo(BuyPrice)>=0) {
                p.getInventory().addItem(Real);

                BigDecimal newMoney = playerMoney.subtract(BuyPrice);
                Economy.setMoney(p.getName(), newMoney);

                p.sendMessage("You bought " + Real.getType().toString() + " for " + BuyPrice.toString());
            }
            else
            {
                BigDecimal needed = BuyPrice.subtract(playerMoney);

                p.sendMessage("You need " + needed.toString() + " more to buy " + Real.getType().toString());
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return result;
    }
}
