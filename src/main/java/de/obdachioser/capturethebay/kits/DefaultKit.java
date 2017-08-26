package de.obdachioser.capturethebay.kits;

import lombok.Getter;

/**
 * Created by ObdachIoser at 15:10 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public abstract class DefaultKit {

    public DefaultKit() {
        Kits.setDefaultKit(this);
    }

    public abstract Kit getKit();
}
