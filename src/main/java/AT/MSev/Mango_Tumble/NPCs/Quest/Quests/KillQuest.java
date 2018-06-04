package AT.MSev.Mango_Tumble.NPCs.Quest.Quests;

import AT.MSev.Mango_Core.Blocks.BlockInstance;
import AT.MSev.Mango_Tumble.Mango_Tumble;
import AT.MSev.Mango_Tumble.NPCs.Quest.IQuest;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginManager;

import java.util.LinkedHashMap;
import java.util.Map;

public class KillQuest implements Listener, IQuest {

    String TargetName;
    int Amount;

    public KillQuest(Player quester, String targetName, int amount) {
        questPlayer = quester;
        TargetName = targetName;
        Amount = amount;
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
                    Quester().sendMessage(ChatColor.DARK_GREEN + "You killed a " + e.getEntity().getName() + ". You need to kill " + Amount + " more.");
                    if(Amount<1)
                    {
                        Complete();
                    }
                    QuestHandler.SaveState();
                    return true;
                }
            }
        }
        return false;
    }

    Player questPlayer;
    QuestState state = QuestState.UNTAKEN;

    @Override
    public QuestState State() {
        return state;
    }

    @Override
    public Player Quester() {
        return questPlayer;
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
        Quester().sendMessage(ChatColor.DARK_GREEN + "Finished quest to kill " + TargetName + ".");
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("quester", Quester().getUniqueId().toString());
        result.put("amount", Amount);
        result.put("target", TargetName);
        return result;
    }

    public static KillQuest deserialize(Map<String, Object> args) {
        Player quester = Bukkit.getPlayer((String)args.get("quester"));
        int amount = ((Integer)args.get("amount"));
        String target = ((String)args.get("target"));
        KillQuest ret = new KillQuest(quester, target, amount);
        return ret;
    }
}
