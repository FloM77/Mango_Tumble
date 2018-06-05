package AT.MSev.Mango_Tumble.NPCs.Quest;

import AT.MSev.Mango_Tumble.Mango_Tumble;
import AT.MSev.Mango_Tumble.NPCs.Quest.Quests.KillQuest;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuestHandler {

    public static HashMap<String, ArrayList<IQuest>> PlayerQuests = new HashMap<String, ArrayList<IQuest>>();

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

