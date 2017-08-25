package de.obdachioser.capturethebay.inventorys;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * Created by ObdachIoser at 17:59 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class TeamInventory implements DefinedInventory {

    private Inventory inventory;
    private Integer slots[] = {19, 22, 25};

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
                            "§8- §7Freier Platz", "§8- §7Freier Platz",}));
        }
    }

    @Override
    public Inventory get() {
        return null;
    }

    @Override
    public void update() {

    }
}
