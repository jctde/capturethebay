package de.obdachioser.capturethebay.cache.api;

import com.google.common.collect.Maps;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.enums.EnumPlayerInventoryType;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import de.obdachioser.capturethebay.inventorys.PlayerInventory;
import de.obdachioser.capturethebay.kits.KitState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * Created by ObdachIoser at 14:22 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor

@Getter @Setter
public class PlayerCache {

    private Integer tickets, kills, receivedChests = 0, deaths, lives;
    private Team currentTeam = null;
    private String gameDisplayName = "";

    private EnumPlayerState enumPlayerState;

    private HashMap<EnumPlayerInventoryType, PlayerInventory> playerInventoryMap = Maps.newHashMap();

    private KitState kitState;

}
