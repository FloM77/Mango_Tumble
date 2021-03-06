package AT.MSev.Mango_Tumble;

import AT.MSev.Mango_Core.Blocks.BlockInstance;
import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import AT.MSev.Mango_Core.Utils.NMSUtils;
import AT.MSev.Mango_Core.Utils.TimedEvent;
import AT.MSev.Mango_Core.Zones.Interactable.ZoneInteractable;
import AT.MSev.Mango_Core.Zones.ZoneBase;
import AT.MSev.Mango_Tumble.Commands.CommandShowQuests;
import AT.MSev.Mango_Tumble.Items.CustomItemFallDamageTicket;
import AT.MSev.Mango_Tumble.NPCUtils.CustomItemNPCStick;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestHandler;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestNPC;
import AT.MSev.Mango_Tumble.NPCs.Quest.Quests.KillQuest;
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

    static {
        ConfigurationSerialization.registerClass(KillQuest.class, "KillQuest");
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

        this.getCommand("quests").setExecutor(new CommandShowQuests());

        QuestHandler.LoadState();

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
        new CustomItemFallDamageTicket();
    }
}
