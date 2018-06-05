package AT.MSev.Mango_Tumble.NPCs.Quest;

import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class QuestNPC extends VillagerNPC {

    String QuestID;
    public QuestNPC(World world) {
        super(world);
        this.setCustomName("Quest");
        this.setCustomNameVisible(true);
    }

    public QuestNPC(World world, String questID) {
        this(world);
        QuestID = questID;
    }


    @Override
    public Boolean OnInteract(PlayerInteractEntityEvent e) {
        Boolean result = super.OnInteract(e);
        if(result)
        {
            QuestHandler.StartQuest(e.getPlayer(),
                    QuestHandler.GetQuest(QuestID)
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

        nbttagcompound.setString("questID", QuestID);
    }

    /*
     * Load NBT data
     */
    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        QuestID = nbttagcompound.getString("questID");
    }
}