package de.obdachioser.capturethebay.kits;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * Created by ObdachIoser at 15:04 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Kits {

    @Getter @Setter
    private static DefaultKit defaultKit;

    @Getter
    private static HashMap<String, Kit> kitHashMap = Maps.newHashMap();

    public static Kit getKit(String s) {
        return (kitHashMap.containsKey(s) ? kitHashMap.get(s) : null);
    }

    public static void addKit(Kit kit) {
        kitHashMap.put(kit.getClass().getSimpleName(), kit);
    }
}