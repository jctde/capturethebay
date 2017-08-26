package de.obdachioser.capturethebay.kits;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by ObdachIoser at 16:43 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class KitState {

    @Getter
    private List<String> kits = Lists.newArrayList();

    public KitState(Player player) {
        kits.add(Kits.getDefaultKit().getKit().getClass().getSimpleName());
        kits.add("Schmied");
    }

    public Kit getKit(String name) {

        if(kits.contains(name)) return Kits.getKit(name);
        return null;
    }

    public void addKit(Kit kit) {
        kits.add(kit.getClass().getSimpleName());
    }

    @Getter @Setter
    private Kit currentKit = null;

    public boolean hasKit(String name) {
        return kits.contains(name);
    }
}
