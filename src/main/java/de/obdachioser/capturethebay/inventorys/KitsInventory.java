package de.obdachioser.capturethebay.inventorys;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.kits.Kit;
import de.obdachioser.capturethebay.kits.Kits;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by ObdachIoser at 14:48 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class KitsInventory implements PlayerInventory {

    private Inventory inventory;
    private Player player;

    public KitsInventory(Player player) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, 9*5, "§bDeine Kits");
    }

    @Override
    public void prepare() {

        for(Integer i = 0; i != 9; i++) inventory.setItem(i, ItemStackCreator.c());
        for(Integer i = 36; i != 45; i++) inventory.setItem(i, ItemStackCreator.c());

        inventory.setItem(4, ItemStackCreator.a(Material.CHEST, "§aWähle dir dein Kit aus"));

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());

        Integer i = 8;

        for(Kit kit : Kits.getKitHashMap().values()) {

            i++;

            if(playerCache.getKitState().hasKit(kit.getClass().getSimpleName())) {

                ItemStack itemStack = ItemStackCreator.a(kit.material(), kit.displayName());
                ItemMeta itemMeta = itemStack.getItemMeta();

                List<String> lore = kit.description();
                lore.add("§aGEKAUFT");
                itemMeta.setLore(lore);

                itemStack.setItemMeta(itemMeta);
                inventory.setItem(i, itemStack);

            } else if(!playerCache.getKitState().hasKit(kit.getClass().getSimpleName())) {

                ItemStack itemStack = ItemStackCreator.a(kit.material(), kit.displayName());
                ItemMeta itemMeta = itemStack.getItemMeta();

                List<String> lore = kit.description();
                lore.add("§eKit für §6" + kit.price() + " Coins §ekaufen");
                itemMeta.setLore(lore);

                itemStack.setItemMeta(itemMeta);
                inventory.setItem(i, itemStack);
            }
        }
    }

    public boolean selectKit(ItemStack itemStack) {

        Kit kit = getKit(itemStack);

        ItemMeta itemMeta = itemStack.getItemMeta();

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());

        if(playerCache.getKitState().getCurrentKit() == kit) return false;

        if(playerCache.getKitState().getCurrentKit() != null) {

            ItemStack itemStack1 = getItem(playerCache.getKitState().getCurrentKit());
            ItemMeta itemMeta1 = itemStack1.getItemMeta();

            itemMeta1.getLore().remove((itemMeta1.getLore().size()-1));
            itemMeta1.getLore().remove((itemMeta1.getLore().size()-1));

            itemMeta1.setLore(itemMeta1.getLore());
            itemStack1.setItemMeta(itemMeta1);
        }

        itemMeta.getLore().add(" ");
        itemMeta.getLore().add("§a§lAUSGEWÄHLT");

        itemMeta.setLore(itemMeta.getLore());
        itemStack.setItemMeta(itemMeta);

        playerCache.getKitState().setCurrentKit(kit);

        return true;
    }

    public Kit getKit(ItemStack itemStack) {

        for(Kit kit : Kits.getKitHashMap().values()) {
            if(kit.material() == itemStack.getType()) return kit;
        }

        return null;
    }

    public ItemStack getItem(Kit kit) {

        for(ItemStack itemStack : inventory.getContents()) if(itemStack.getType() == kit.material()) return itemStack;
        return null;
    }

    @Override
    public Inventory get() {
        return inventory;
    }
}
