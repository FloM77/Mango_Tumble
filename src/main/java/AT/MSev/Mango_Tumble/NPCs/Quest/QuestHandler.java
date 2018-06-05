package AT.MSev.Mango_Tumble.NPCs.Quest;

import AT.MSev.Mango_Core.Items.ItemBase;
import AT.MSev.Mango_Tumble.Items.CustomItemFallDamageTicket;
import AT.MSev.Mango_Tumble.Mango_Tumble;
import AT.MSev.Mango_Tumble.NPCs.Quest.Quests.KillQuest;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuestHandler {

    public static HashMap<String, ArrayList<IQuest>> PlayerQuests = new HashMap<String, ArrayList<IQuest>>();

    public static ArrayList<IQuest> QuestPool = new ArrayList<IQuest>()
    {{
        add(
                new KillQuest(null, "Zombie", 4, IQuest.QuestState.UNTAKEN, "ZombieQuest")
        );
    }};

    public static HashMap<String, ArrayList<ItemReward>> RewardMap = new HashMap<String, ArrayList<ItemReward>>()
    {{
        put("ZombieQuest", new ArrayList<ItemReward>() {{add(
                new ItemReward(ItemBase.Get("FallDamageTicket").Physical, 5)
        );}});
    }};

    public static IQuest GetQuest(String id)
    {
        for(IQuest quest : QuestPool)
        {
            if(quest.UniqueID().equalsIgnoreCase(id)) return quest;
        }
        return null;
    }

    public static void StartQuest(Player player, IQuest quest)
    {
        if(!QuestHandler.HasQuestWithID(player, quest.UniqueID())) {
            quest.SetQuester(player);
            quest.Start();
            QuestHandler.AddQuest(player, quest);
            QuestHandler.SaveState();
        }
        else
        {
            player.sendMessage("You already started the quest " + quest.UniqueID());
        }
    }

    public static void AddQuest(Player player, IQuest quest)
    {
        if(!PlayerQuests.containsKey(player.getUniqueId().toString())){
            ArrayList<IQuest> toadd = new ArrayList<IQuest>();
            toadd.add(quest);
            PlayerQuests.put(player.getUniqueId().toString(), toadd);
        }
        else {
            PlayerQuests.get(player.getUniqueId().toString()).add(quest);
        }
    }

    public static ArrayList<IQuest> GetQuests(Player player)
    {
        if(PlayerQuests.containsKey(player.getUniqueId().toString()))
        {
            return PlayerQuests.get(player.getUniqueId().toString());
        }
        return null;
    }

    public static Boolean HasQuestWithID(Player player, String id)
    {
        Boolean ret = false;
        ArrayList<IQuest> pool = GetQuests(player);
        if(pool == null) return false;
        for(IQuest iq : pool)
        {
            if(iq.UniqueID().equalsIgnoreCase(id)) ret = true;
        }
        return ret;
    }

    public static void SaveState()
    {
        Mango_Tumble.plugin.getConfig().createSection("QuestMap", PlayerQuests);
        Mango_Tumble.plugin.saveConfig();
    }

    public static void LoadState()
    {
        ConfigurationSection playerQuests = Mango_Tumble.plugin.getConfig().getConfigurationSection("QuestMap");
        if(playerQuests==null) return;
        HashMap<String, ArrayList<IQuest>> temp = new HashMap<String, ArrayList<IQuest>>();
        for(String inv : playerQuests.getKeys(false)) {
            temp.put(inv, (ArrayList<IQuest>) playerQuests.get(inv));
        }
        PlayerQuests = temp;
    }
}


