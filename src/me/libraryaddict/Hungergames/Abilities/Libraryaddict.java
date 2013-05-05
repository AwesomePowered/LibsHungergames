package me.libraryaddict.Hungergames.Abilities;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.Hungergames.Types.AbilityListener;

public class Libraryaddict extends AbilityListener {
	
	@EventHandler
	public void onOpenBook(PlayerInteractEvent ev) {
		ItemStack item = ev.getItem();
		if (ev.getAction() == Action.RIGHT_CLICK_AIR && item != null && item.getType() == Material.BOOKSHELF && hasAbility(ev.getPlayer())) {
			item.setAmount(item.getAmount() -1);
			if (item.getAmount() == 0)
				ev.getPlayer().setItemInHand(new ItemStack(0));
			//Figure out
			//Launch item like a throwing knife.
			//Explode + spawn 5 books on a circle
			//explode 5 books
		}
		
	}

}
