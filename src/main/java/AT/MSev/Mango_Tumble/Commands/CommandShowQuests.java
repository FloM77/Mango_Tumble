package AT.MSev.Mango_Tumble.Commands;

import AT.MSev.Mango_Tumble.NPCs.Quest.IQuest;
import AT.MSev.Mango_Tumble.NPCs.Quest.QuestHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandShowQuests implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        ArrayList<IQuest> quests = QuestHandler.GetQuests(p);
        if(quests==null)
        {
            p.sendMessage("You don't have any quests.");
        }
        else {
            for(IQuest iq : quests)
            {
                p.sendMessage(iq.UniqueID() + " : "  + iq.Summary());
            }
        }
        return true;
    }
}
