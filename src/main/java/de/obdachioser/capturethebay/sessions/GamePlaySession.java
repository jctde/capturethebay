package de.obdachioser.capturethebay.sessions;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.bay.Bay;
import de.obdachioser.capturethebay.bay.Bays;
import de.obdachioser.capturethebay.scoreboard.ScoreboardHandler;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ObdachIoser at 00:38 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter
public class GamePlaySession {

    private List<ItemStack> itemStackDropList = Lists.newArrayList();
    private List<String> bayNames = Lists.newArrayList("Caragu'i", "Kartina", "Floranzadawa", "Kortel'ia", "Sawasaki", "Mahamas",
            "Obakamaha", "Karasaki", "De'eunish", "Marakahm");

    private Bays bays = new Bays();

    private ScoreboardHandler ingameScoreboardHandler = new ScoreboardHandler(
            "§f§lAUIHUB.NET",
            "",
            "",
            "",
            "",
            "");

    public GamePlaySession() {

        itemStackDropList.addAll(Lists.newArrayList(
                ItemStackCreator.a(Material.STONE, 32), ItemStackCreator.a(Material.WOOD, 48), ItemStackCreator.a(Material.IRON_AXE, 1),
                ItemStackCreator.a(Material.IRON_INGOT, 12), ItemStackCreator.a(Material.BRICK, 48),
                ItemStackCreator.a(Material.IRON_SWORD, 1), ItemStackCreator.a(Material.IRON_INGOT, 16),
                ItemStackCreator.a(Material.WOOD, 63),  ItemStackCreator.a(Material.IRON_PICKAXE, 1),
                ItemStackCreator.a(Material.IRON_AXE, 1), ItemStackCreator.a(Material.IRON_SWORD, 1)));
    }
}
