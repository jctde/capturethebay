package de.obdachioser.capturethebay.projectlisteners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.bay.SimpleBay;
import de.obdachioser.capturethebay.events.BayCaptureEvent;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import sun.swing.CachedPainter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ObdachIoser at 20:15 on 28.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class BayCapturedListener implements Listener {

    @EventHandler
    public void bayCaptured(BayCaptureEvent event) {

        event.getTeam().getCapturedBays().add(event.getBay());

        try {

            Executors.newCachedThreadPool().execute(() -> Bukkit.getScheduler().scheduleSyncDelayedTask(CaptureTheBay.getInstance(),
                    () -> this.d(((SimpleBay) event.getBay()).getBiObject().getValue0().getLocation().clone(), (DefinedTeam) event.getTeam())));

        } catch (Exception exc) {}

        Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.WITHER_HURT, 1F, 1F));

        Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "Die Bucht §e" + event.getBay().getName()
                + " §7wurde von Team " + ((DefinedTeam) event.getTeam()).getTeamDisplayName() + " §7eingenommen!");

    }

    private void d(Location location, DefinedTeam definedTeam) {

        location.setY(location.getY()+4);
        ItemStack itemStack = ItemStackCreator.d(Material.WOOL, definedTeam.getTeamColor());

        try {

            for(Integer i = 0; i != 9; i++) {

                TimeUnit.MILLISECONDS.sleep(100L);

                Bukkit.getWorld("world").playEffect(location, Effect.TILE_BREAK, 1);

                location.setY(location.getY()+1);
                location.getBlock().setType(itemStack.getType());
                location.getBlock().setData(itemStack.getData().getData());
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}