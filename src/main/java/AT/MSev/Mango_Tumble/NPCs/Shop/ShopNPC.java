package AT.MSev.Mango_Tumble.NPCs.Shop;

import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.NMSUtils;
import com.earth2me.essentials.Essentials;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.UUID;

public class ShopNPC extends VillagerNPC {
    String ShopID;

    public ShopNPC(World world) {
        super(world);
        this.setCustomName("Shop");
        this.setCustomNameVisible(true);
    }

    public ShopNPC(World world, String shopID) {
        this(world);
        ShopID = shopID;
    }

    @Override
    public Boolean OnInteract(PlayerInteractEntityEvent e) {
        Boolean result = super.OnInteract(e);
        if(result)
        {
            e.getPlayer().openInventory(
                    Shops.GetShop(ShopID).Shop
            );
        }
        return result;
    }

    /*
     * Save NBT data
     */
    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);

        nbttagcompound.setString("shopID", ShopID);
    }

    /*
     * Load NBT data
     */
    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        ShopID = nbttagcompound.getString("shopID");
    }
}
