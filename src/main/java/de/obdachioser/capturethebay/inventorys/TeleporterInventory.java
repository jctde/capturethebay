package de.obdachioser.capturethebay.inventorys;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.events.TeamActionEvent;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ObdachIoser at 16:34 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class TeleporterInventory implements DefinedInventory {

    private Inventory inventory;

    public TeleporterInventory() {

        this.inventory = Bukkit.createInventory(null, 9*5, "§bSpieler die im Spiel sind");

        for(Integer i = 0; i != 9; i++) inventory.setItem(i, ItemStackCreator.c());
        for(Integer i = 36; i != 45; i++) inventory.setItem(i, ItemStackCreator.c());

        inventory.setItem(4, ItemStackCreator.a(Material.BEACON, "§aTeleportiere dich zu einem Spieler"));
    }

    @Override
    public Inventory get() {
        return inventory;
    }

    @Override
    public void prepare() {

        CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().all((id, cache) -> {

            if (cache.isIngame()) {
                inventory.addItem(ItemStackCreator.f(Bukkit.getPlayer(id).getName(), "§f§l" + Bukkit.getPlayer(id).getName()));
            }
        });
    }

    @Override
    public void update() {}

    public void callAction(TeamActionEvent.EnumTeamAction enumTeamAction, Player player) {

        if(enumTeamAction == TeamActionEvent.EnumTeamAction.TEAM_JOIN)
            this.inventory.addItem(ItemStackCreator.f(player.getName(), "§f§l" + player.getName()));
        else if(enumTeamAction == TeamActionEvent.EnumTeamAction.TEAM_LEAVE) {

            ItemStack itemStack = get(player);

            if(itemStack == null) return;
            this.inventory.remove(itemStack);
        }
    }

    public void setAll() {

        for(Integer i = 8; i != 35; i++) inventory.setItem(i, new ItemStack(Material.AIR));

        Integer i = 9;

        for(Player player : Bukkit.getOnlinePlayers()) {

            if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId()).isIngame()) {

                inventory.setItem(i, ItemStackCreator.f(player.getName(), "§f§l" + player.getName()));
                i++;
            }
        }
    }

    /**
     *
     *
     *
     * @param player
     * @return
     */
    public ItemStack get(Player player) {

        try {

            for(ItemStack itemStack : inventory.getContents())
                if(itemStack.getItemMeta().getDisplayName().contains(player.getName())) return itemStack;

            return null;

        } catch (Exception exc) {}

        return null;
    }
}
