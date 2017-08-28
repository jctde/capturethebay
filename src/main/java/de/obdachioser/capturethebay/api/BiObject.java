package de.obdachioser.capturethebay.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.block.Block;

/**
 * Created by ObdachIoser at 18:36 on 28.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class BiObject<V, S> {

    private V value0;
    private S value1;

}
