package AT.MSev.Mango_Tumble.NPCs.Quest.Quests;

import AT.MSev.Mango_Core.Blocks.BlockInstance;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Tumble.Mango_Tumble;
import AT.MSev.Mango_Tumble.NPCs.Quest.IQuest;
import AT.MSev.Mango_Tumble.NPCs.Quest.ItemReward;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class KillQuest implements Listener, IQuest {
    String TargetName;
    String ID = "noid";
    int Amount;

    public KillQuest(OfflinePlayer quester, String targetName, int amount, QuestState state, String id) {
        questPlayer = quester;
        TargetName = targetName;
        Amount = amount;
        ID = id;
        this.state = state;
        Bukkit.getPluginManager().registerEvents(this, Mango_Tumble.plugin);
    }

    @EventHandler
    Boolean OnEntityKill(EntityDeathEvent e)
    {
        if(State().equals(QuestState.RUNNING) && e.getEntity().getKiller()!= null) {
            if (e.getEntity().getKiller().equals(Quester())) {
                if(e.getEntity().getName().equalsIgnoreCase(TargetName))
                {
                    Amount--;
                    if(Amount<1)
                    {
                        Complete();
                    }
                    else
                    {
                        Quester().sendMessage(ChatColor.DARK_GREEN + "You killed a " + e.getEntity().getName() + ". You need to kill " + Amount + " more.");
                    }
                    QuestHandler.SaveState();
                    return true;
                }
            }
        }
        return false;
    }

    OfflinePlayer questPlayer;
    QuestState state = QuestState.UNTAKEN;

    @Override
    public QuestState State() {
        return state;
    }

    @Override
    public Player Quester() {
        return questPlayer.getPlayer();
    }

    @Override
    public void SetQuester(Player p) {
        questPlayer = p;
    }

    @Override
    public void Start()
    {
        state = QuestState.RUNNING;
        Quester().sendMessage(ChatColor.DARK_GREEN + "Accepted Quest to kill " + Amount + " " + TargetName);
    }

    @Override
    public void Complete()
    {
        state = QuestState.COMPLETED;

        if(QuestHandler.RewardMap.containsKey(UniqueID())) {
            for (ItemReward ir : QuestHandler.RewardMap.get(UniqueID())) {
                ir.Give(questPlayer.getPlayer());
            }
        }

        Quester().sendMessage(ChatColor.DARK_GREEN + "Finished quest to kill " + TargetName + ".");
    }

    @Override
    public String Summary() {
        return "Kill " + Amount + " " + TargetName;
    }

    @Override
    public String UniqueID() {
        return ID;
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("quester", Quester().getUniqueId().toString());
        result.put("amount", Amount);
        result.put("target", TargetName);
        result.put("id", ID);
        result.put("state", State().toString());
        return result;
    }

    public static KillQuest deserialize(Map<String, Object> args) {
        OfflinePlayer quester = Bukkit.getOfflinePlayer(UUID.fromString( (String)args.get("quester") ));
        int amount = ((Integer)args.get("amount"));
        String target = ((String)args.get("target"));
        String id = ((String)args.get("id"));
        QuestState state = QuestState.valueOf((String)args.get("state"));
        KillQuest ret = new KillQuest(quester, target, amount, state, id);
        return ret;
    }
}
