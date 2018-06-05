package AT.MSev.Mango_Tumble.NPCs.Quest;

import AT.MSev.Mango_Core.Entity.EntityNPC.VillagerNPC;
import AT.MSev.Mango_Tumble.NPCs.Quest.Quests.KillQuest;
import AT.MSev.Mango_Tumble.NPCs.Shop.IShop;
import AT.MSev.Mango_Tumble.NPCs.Shop.Shops.TestShop;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class QuestNPC extends VillagerNPC {

    IQuestInstance Quest;
    public QuestNPC(World world) {
        super(world);
        this.setCustomName("Quest");
        this.setCustomNameVisible(true);
    }

    public QuestNPC(World world, IQuestInstance quest) {
        this(world);
        Quest = quest;
    }


    @Override
    public Boolean OnInteract(PlayerInteractEntityEvent e) {
        Boolean result = super.OnInteract(e);
        if(result)
        {
            Quest.StartQuest(e.getPlayer());
        }
        return result;
    }

    /*
     * Save NBT data
     */
    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);

        nbttagcompound.setString("questName", Quest.getClass().getName());
    }

    /*
     * Load NBT data
     */
    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        try {
            Quest = (IQuestInstance) Class.forName(nbttagcompound.getString("questName")).newInstance();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}