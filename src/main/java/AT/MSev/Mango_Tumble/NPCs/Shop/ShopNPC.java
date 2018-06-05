package AT.MSev.Mango_Tumble.NPCs.Shop;

import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.NMSUtils;
import AT.MSev.Mango_Tumble.NPCs.Shop.Shops.TestShop;
import com.earth2me.essentials.Essentials;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.UUID;

public class ShopNPC extends VillagerNPC {
    IShop Shop;

    public ShopNPC(World world) {
        super(world);
        this.setCustomName("Shop");
        this.setCustomNameVisible(true);
    }

    public ShopNPC(World world, IShop shop) {
        this(world);
        Shop = shop;
    }

    @Override
    public Boolean OnInteract(PlayerInteractEntityEvent e) {
        Boolean result = super.OnInteract(e);
        if(result)
        {
            e.getPlayer().openInventory(Shop.Shop());
        }
        return result;
    }

    /*
     * Save NBT data
     */
    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);

        nbttagcompound.setString("shopName", Shop.getClass().getName());
    }

    /*
     * Load NBT data
     */
    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        try {
            Shop = (IShop) Class.forName(nbttagcompound.getString("shopName")).newInstance();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
