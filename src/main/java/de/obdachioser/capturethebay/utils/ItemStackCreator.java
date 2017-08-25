package de.obdachioser.capturethebay.utils;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

        itemMeta.spigot().setUnbreakable(true);

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

        itemMeta.spigot().setUnbreakable(true);

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

        itemMeta.spigot().setUnbreakable(true);

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

        itemMeta.setDisplayName("");

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}