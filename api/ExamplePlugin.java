package <PACKAGE>;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import vk.com.korne3v.skyblock.api.island.*;
import vk.com.korne3v.skyblock.api.events.*;

public final class ExamplePlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        // Get island
        Island island = SkyblockAPI.getIsland(event.getPlayer());

        // Check island
        if(island != null){

            event.getPlayer().sendMessage(ChatColor.GREEN + "You have an island :)");

            // Get rank
            MemberRank rank = island.getMembers().get(event.getPlayer().getName());
            switch (rank){
                case DEFAULT:
                    event.getPlayer().sendMessage(ChatColor.GRAY + "You are a member! ");
                    break;
                case MODER:
                    event.getPlayer().sendMessage(ChatColor.GRAY + "You are a moderator! ");
                    break;
                case OWNER:
                    event.getPlayer().sendMessage(ChatColor.GRAY + "You are a owner! ");
                    break;
            }

            // Change rank
            island.getMembers().put(event.getPlayer().getName(), MemberRank.MODER);

        }else {
            event.getPlayer().sendMessage(ChatColor.RED + "You don't have an island :(");
        }

    }

    // You can view all events here: https://github.com/korne3v/crockskyblock/blob/main/api/events
    @EventHandler
    public void onCreate(IslandCreateEvent event){

        Location home = event.getIsland().getHome();

        home.getWorld().playEffect(home, Effect.HEART, 0);
        home.getWorld().playSound(home, Sound.LEVEL_UP, 1F, 0F);

    }

}
