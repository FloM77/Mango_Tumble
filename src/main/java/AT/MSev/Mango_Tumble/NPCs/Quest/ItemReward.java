package AT.MSev.Mango_Tumble.NPCs.Quest;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemReward {
    ItemStack Reward;
    int Amount = 1;

    public ItemReward(ItemStack i, int a)
    {
        Reward = i;
        Amount = a;
    }

    public void Give(Player p)
    {
        for(int i=0;i<Amount;i++)
            p.getInventory().addItem(Reward);
    }
}
