package AT.MSev.Mango_Tumble.NPCs.Quest;

import org.bukkit.entity.Player;

public interface IQuestInstance {
    void StartQuest(Player player);

    static void Register(IQuest quest, Player player)
    {
        quest.Start();
        QuestHandler.AddQuest(player, quest);
        QuestHandler.SaveState();
    }
}
