package AT.MSev.Mango_Tumble.NPCs.Quest.Quests.KillQuests;

import AT.MSev.Mango_Tumble.NPCs.Quest.IQuest;
import AT.MSev.Mango_Tumble.NPCs.Quest.IQuestInstance;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestHandler;
import AT.MSev.Mango_Tumble.NPCs.Quest.Quests.KillQuest;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Zombie4 implements IQuestInstance {
    @Override
    public void StartQuest(Player player) {
        IQuest quest = new KillQuest(player, "Zombie", 4, IQuest.QuestState.UNTAKEN);
        IQuestInstance.Register(quest, player);
    }
}
