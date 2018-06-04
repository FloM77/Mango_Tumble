package AT.MSev.Mango_Tumble.NPCUtils;

import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestNPC;
import AT.MSev.Mango_Tumble.NPCs.Shop.ShopNPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CustomItemNPCStick extends ItemInteractable {
    public CustomItemNPCStick() {
        super("SpawnStick", Material.STICK);
        Cooldown = 2000;
    }

    @Override
    protected Boolean OnRightClick(PlayerInteractEvent e) {
        Boolean result = super.OnRightClick(e);
        if(result)
        {
            if(e.getClickedBlock()!=null)
            {
                Location location = e.getClickedBlock().getLocation();
                new ShopNPC(((CraftWorld)location.getWorld()).getHandle()).Spawn(location.clone().add(0,1,0));
                new QuestNPC(((CraftWorld)location.getWorld()).getHandle()).Spawn(location.clone().add(4,1,0));
            }
        }
        return result;
    }

    @Override
    protected void OnDrop(PlayerDropItemEvent e) {
        super.OnDrop(e);
        e.getItemDrop().remove();
    }
}
