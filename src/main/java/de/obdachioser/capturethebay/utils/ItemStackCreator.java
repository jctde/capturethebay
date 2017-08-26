package de.obdachioser.capturethebay.utils;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.api.TeamColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Created by ObdachIoser at 15:47 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class ItemStackCreator {

    public static ItemStack a(Material material, String name) {

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);

        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack a(Material material, String name, String[] lore) {

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(Lists.newArrayList(lore));

        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack a(Material material, Short s, String name, String[] lore) {

        ItemStack itemStack = new ItemStack(material, 1, s);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(Lists.newArrayList(lore));

        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack b(ItemStack itemStack, String name) {

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack b(ItemStack itemStack, String[] lore) {

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Lists.newArrayList(lore));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack c() {

        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§c");

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack d(Material material, TeamColor teamColor, String name) {

        ItemStack itemStack = new ItemStack(material);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();

        Color color = null;

        if(teamColor == TeamColor.RED) color = Color.RED;
        if(teamColor == TeamColor.BLUE) color = Color.BLUE;
        if(teamColor == TeamColor.GREEN) color = Color.GREEN;
        if(teamColor == TeamColor.YELLOW) color = Color.YELLOW;

        leatherArmorMeta.setColor(color);
        leatherArmorMeta.setDisplayName(name);

        itemStack.setItemMeta(leatherArmorMeta);
        return itemStack;
    }
}