package AT.MSev.Mango_Tumble.NPCs.Quest;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

public interface IQuest extends ConfigurationSerializable {
    enum QuestState { RUNNING, COMPLETED, UNTAKEN }
    QuestState State();

    Player Quester();
    void SetQuester(Player p);

    void Start();

    void Complete();

    String Summary();
    String UniqueID();
}
