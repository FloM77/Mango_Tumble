package AT.MSev.Mango_Tumble.NPCs.Shop;

import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.NMSUtils;
import AT.MSev.Mango_Tumble.NPCs.Shop.Shops.TestShop;
import com.earth2me.essentials.Essentials;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ShopNPC extends VillagerNPC {

    public ShopNPC(World world) {
        super(world);
        this.setCustomName("Blacksmith");
        this.setCustomNameVisible(true);
    }

    @Override
    public Boolean OnInteract(PlayerInteractEntityEvent e) {
        Boolean result = super.OnInteract(e);
        if(result)
        {
            MangoUtils.Log(e.getRightClicked().getName());
            e.getPlayer().openInventory(TestShop.Shop());
        }
        return result;
    }
}
