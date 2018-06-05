package AT.MSev.Mango_Tumble.Items;

import AT.MSev.Mango_Core.Items.Interactable.ItemInteractable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class CustomItemFallDamageTicket extends ItemInteractable {
    public CustomItemFallDamageTicket() {
        super("FallDamageTicket", Material.PAPER);
    }

    @Override
    protected Boolean OnDamageReceivedWhileInInventory(EntityDamageEvent e, ItemStack actual) {
        Boolean result = super.OnDamageReceivedWhileInInventory(e, actual);
        if(result && e.getCause().equals(EntityDamageEvent.DamageCause.FALL) && HandleCooldown((Player)e.getEntity(), "damage effect", 10000))
        {
            e.setCancelled(true);
            actual.setAmount(actual.getAmount()-1);
        }
        return result;
    }
}
