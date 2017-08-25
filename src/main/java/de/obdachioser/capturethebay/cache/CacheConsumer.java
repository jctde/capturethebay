package de.obdachioser.capturethebay.cache;

/**
 * Created by ObdachIoser at 14:13 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public interface CacheConsumer<O, V> {
    V handle(O operator);
}
