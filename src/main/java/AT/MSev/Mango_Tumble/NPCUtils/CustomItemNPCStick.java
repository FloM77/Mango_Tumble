package AT.MSev.Mango_Tumble.NPCUtils;

import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestHandler;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestNPC;
import AT.MSev.Mango_Tumble.NPCs.Shop.ShopNPC;
import AT.MSev.Mango_Tumble.NPCs.Shop.Shops;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CustomItemNPCStick extends ItemInteractable {
    public CustomItemNPCStick() {
        super("SpawnStick", Material.STICK);
    }

    String InstaceName = "";

    @Override
    protected Boolean OnRightClick(PlayerInteractEvent e) {
        Boolean result = super.OnRightClick(e);
        if(result && HandleCooldown(e.getPlayer(), "rightclick", 2000))
        {
            if(e.getClickedBlock()!=null)
            {
                Location location = e.getClickedBlock().getLocation();
                if(Shops.GetShop(InstaceName) != null) {

                    new ShopNPC(((CraftWorld) location.getWorld()).getHandle(),
                            InstaceName
                    ).Spawn(location.clone().add(0, 1, 0));
                }
                else
                {
                    e.getPlayer().sendMessage("Shop not found");
                }
            }
        }
        return result;
    }

    @Override
    protected  Boolean OnLeftClick(PlayerInteractEvent e)
    {
        Boolean result = super.OnLeftClick(e);
        if(result && HandleCooldown(e.getPlayer(), "leftclick", 2000))
        {
            if(e.getClickedBlock()!=null) {
                Location location = e.getClickedBlock().getLocation();

                if(QuestHandler.GetQuest(InstaceName)!=null) {

                    new QuestNPC(((CraftWorld) location.getWorld()).getHandle(),
                            InstaceName
                    ).Spawn(location.clone().add(0, 1, 0));
                }
                else
                {
                    e.getPlayer().sendMessage("Quest not found");
                }
            }
        }
        return result;
    }

    @Override
    protected Boolean OnDrop(PlayerDropItemEvent e) {
        Boolean result = super.OnDrop(e);
        e.getItemDrop().remove();
        return result;
    }

    @Override
    protected Boolean OnCommand(PlayerCommandPreprocessEvent e)
    {
        Boolean result = super.OnCommand(e);
        if(result && e.getMessage().split(" ").length > 1)
        {
            e.setCancelled(true);
            InstaceName = e.getMessage().split(" ")[1];
            e.getPlayer().sendMessage("Set to " + InstaceName);
        }
        return result;
    }
}
