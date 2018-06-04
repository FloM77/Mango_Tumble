package AT.MSev.Mango_Tumble;

import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import AT.MSev.Mango_Core.Utils.NMSUtils;
import AT.MSev.Mango_Tumble.NPCUtils.CustomItemNPCStick;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestNPC;
import AT.MSev.Mango_Tumble.NPCs.Shop.ShopNPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Mango_Tumble extends JavaPlugin {
    public static NamespacedKey key;
    public static Plugin plugin;

    void RegEntities()
    {
        NMSUtils.registerEntity("npc_shop", NMSUtils.Type.VILLAGER, ShopNPC.class, false);
        NMSUtils.registerEntity("npc_quest", NMSUtils.Type.VILLAGER, QuestNPC.class, false);
    }

    @Override
    public void onLoad()
    {
        RegEntities();
        EntitiesLoaded = true;
    }

    Boolean EntitiesLoaded = false;

    @Override
    public void onEnable() {
        plugin = this;
        key = new NamespacedKey(this, this.getDescription().getName());

        getServer().getPluginManager().registerEvents(new Handler(), this);

        if(!EntitiesLoaded)
        {
            RegEntities();
        }
    }

    @Override
    public void onDisable() {

    }

    static {
        new CustomItemNPCStick();
    }
}
