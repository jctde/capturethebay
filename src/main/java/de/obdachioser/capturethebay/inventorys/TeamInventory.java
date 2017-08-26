package de.obdachioser.capturethebay.inventorys;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.enums.EnumInventoryType;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by ObdachIoser at 17:59 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class TeamInventory implements DefinedInventory {

    private Inventory inventory;
    private Integer slots[] = {19, 22, 25};

    public TeamInventory() {}

    @Override
    public void prepare() {

        this.inventory = Bukkit.createInventory(null, 9*5, "§bWähle dir dein Team");

        for(Integer i = 0; i != 9; i++) inventory.setItem(i, ItemStackCreator.c());
        for(Integer i = 36; i != 45; i++) inventory.setItem(i, ItemStackCreator.c());

        inventory.setItem(4, ItemStackCreator.a(Material.BOOK, "§aWähle dein Team"));

        Integer i = -1;

        for(DefinedTeam definedTeam : CaptureTheBay.getGameSession().getTeams().all()) {

            i++;

            inventory.setItem(slots[i], ItemStackCreator.a(Material.WOOL, definedTeam.getTeamColor().getSubId(),
                    definedTeam.getTeamDisplayName(),
                    new String[] {"§8- §7Freier Platz", "§8- §7Freier Platz", "§8- §7Freier Platz",
                            "§8- §7Freier Platz", "§8- §7Freier Platz"}));
        }
    }

    @Override
    public Inventory get() {
        return inventory;
    }

    @Override
    public void update() {

        Integer i = -1;

        for(DefinedTeam definedTeam : CaptureTheBay.getGameSession().getTeams().all()) {

            i++;

            ItemStackCreator.b(inventory.getItem(i), new String[] {});
        }
    }

    public Boolean joinTeam(Player player, ItemStack itemStack) {

        DefinedTeam definedTeam = getTeam(itemStack);

        if(definedTeam.size() == 5) {
            return false;
        }

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());

        if(playerCache.getCurrentTeam() != null) {

            removePlayerFromLore(player, (DefinedTeam) playerCache.getCurrentTeam());
            playerCache.getCurrentTeam().removePlayer(player);

            Bukkit.broadcastMessage("RESET OLD TEAM");
        }

        definedTeam.addPlayer(player);
        addPlayerToLore(player, definedTeam);
        return true;
    }

    public void removePlayerFromLore(Player player, DefinedTeam definedTeam) {

        ItemStack itemStack = inventory.getItem(getSlot(definedTeam));
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> newlore = Lists.newArrayList();

        for(String s : itemMeta.getLore()) {
            if(!s.contains(player.getName())) newlore.add(s);
        }

        newlore.add("§8- §7Freier Platz");
        itemMeta.setLore(newlore);

        itemStack.setItemMeta(itemMeta);
    }

    public void addPlayerToLore(Player player, DefinedTeam definedTeam) {

        ItemStack itemStack = inventory.getItem(getSlot(definedTeam));
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> newLore = Lists.newArrayList();

        newLore.add("§8- §7" + player.getName());
        for(String s: itemMeta.getLore()) newLore.add(s);
        newLore.remove(newLore.size()-1);

        itemMeta.setLore(newLore);
        itemStack.setItemMeta(itemMeta);
    }

    public boolean isInEqualsTeam(Player player, ItemStack itemStack) {

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());
        return getTeam(itemStack) == playerCache.getCurrentTeam();
    }

    public Integer getSlot(DefinedTeam definedTeam) {

        for(Integer i = 0; i != 3; i++)
            if(inventory.getItem(slots[i]).getItemMeta().getDisplayName().contains(definedTeam.getName())) return slots[i];

        return null;
    }

    public DefinedTeam getTeam(ItemStack itemStack) {
        return CaptureTheBay.getGameSession().getTeams().getDefinedTeam(replaceAll(itemStack.getItemMeta().getDisplayName()));
    }

    public String replaceAll(String s) {
        return s.replace("§1", "").replace("§2", "").replace("§3", "").replace("§4", "");
    }
}
