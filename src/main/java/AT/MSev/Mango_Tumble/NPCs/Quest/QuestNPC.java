package AT.MSev.Mango_Tumble.NPCs.Quest;

import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import AT.MSev.Mango_Tumble.NPCs.Quest.Quests.KillQuest;
import AT.MSev.Mango_Tumble.NPCs.Shop.Shops.TestShop;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class QuestNPC extends VillagerNPC {

    public QuestNPC(World world) {
        super(world);
    }

    @Override
    public Boolean OnInteract(PlayerInteractEntityEvent e) {
        Boolean result = super.OnInteract(e);
        if(result)
        {
            KillQuest kq = new KillQuest(e.getPlayer(), "Zombie", 2);
            kq.Start();
            QuestHandler.AddQuest(e.getPlayer(), kq);
            QuestHandler.SaveState();
        }
        return result;
    }
}