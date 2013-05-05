package me.libraryaddict.Hungergames.Abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.libraryaddict.Hungergames.Types.AbilityListener;
import me.libraryaddict.Hungergames.Types.HungergamesApi;

public class Libraryaddict extends AbilityListener {
	
	@EventHandler
	public void onOpenBook(PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		ItemStack item = ev.getItem();
		if (ev.getAction() == Action.RIGHT_CLICK_AIR && item != null && item.getType() == Material.BOOKSHELF && hasAbility(ev.getPlayer())) {
			item.setAmount(item.getAmount() -1);
			if (item.getAmount() == 0)
				ev.getPlayer().setItemInHand(new ItemStack(0));
			Location loc = p.getLocation();
			ItemStack stack = new ItemStack(Material.BOOKSHELF, 1); 
			Item bookshelf = p.getWorld().dropItem(p.getLocation(), stack); 
			bookshelf.setVelocity(new Vector(3,0,2));
			bookshelf.setPickupDelay(72000);
			final Location bookloc = bookshelf.getLocation();
			Bukkit.getScheduler().scheduleSyncDelayedTask(HungergamesApi.getHungergames(),
                    new Runnable() {
                        public void run() {
                        	Bukkit.broadcastMessage("BOOK EXPLODE");
                        	bookloc.getWorld().createExplosion(bookloc, 5);
                        	ItemStack stack = new ItemStack(Material.BOOK, 5);
                        	
                        }
                    }, 20 * 3);
			
			//Figure out
			//Launch item like a throwing knife.
			//Explode + spawn 5 books on a circle
			//explode 5 books
		}
		
	}

}
