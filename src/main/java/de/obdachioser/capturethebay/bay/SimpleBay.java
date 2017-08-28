package de.obdachioser.capturethebay.bay;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.events.BayCaptureEvent;
import de.obdachioser.capturethebay.events.BayFoundEvent;
import de.obdachioser.capturethebay.events.PrepareBayBuyEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created by ObdachIoser at 13:49 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter
public class SimpleBay implements Bay {

    private String name;
    private Block block;

    private Team team;
    private Player nativeFounder;
    private Player newFounder;
    private Location blockLocation;

    private Integer goldPrice;

    public SimpleBay(Player founder, Block block) {

        this.nativeFounder = founder;
        this.newFounder = founder;
        this.blockLocation = block.getLocation();
        this.block = block;

        this.team = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(founder.getUniqueId()).getCurrentTeam();
        this.name = RandomBayName.getRandomName();

        Bukkit.getPluginManager().callEvent(new BayFoundEvent(founder, name, blockLocation));
    }

    @Override
    public Team getCapturedTeam() {
        return team;
    }

    @Override
    public void setCapturedTeam(Player player) {

        this.newFounder = player;
        this.team = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId()).getCurrentTeam();

        Bukkit.getPluginManager().callEvent(new BayCaptureEvent(this.team, player, this));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getBlockLocation() {
        return blockLocation;
    }

    @Override
    public Integer getGoldPrice() {
        return goldPrice;
    }

    @Override
    public void setGoldPrice(Integer goldPrice) {
        this.goldPrice = goldPrice;
    }
}
