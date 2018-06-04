package AT.MSev.Mango_Tumble;

import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Tumble.NPCUtils.CustomItemNPCStick;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Handler implements Listener {

    //Debug ONLY
    @EventHandler
    void OnLogin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if(p.getDisplayName().equalsIgnoreCase("Flomich"))
        {
            ItemBase.Get("SpawnStick").Give(p, true);
        }
    }
}
