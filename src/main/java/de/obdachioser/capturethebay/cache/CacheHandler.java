package de.obdachioser.capturethebay.cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by ObdachIoser at 14:11 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class CacheHandler<K, V> {

    private HashMap<K, V> cacheHashMap = Maps.newHashMap();
    private CacheConsumer<K, V> cacheConsumer;

    private ExecutorService executorService = Executors.newFixedThreadPool(24);

    public CacheHandler(CacheConsumer<K, V> cacheConsumer) {
        this.cacheConsumer = cacheConsumer;
    }

    public V add(K key) {

        Future<V> future = executorService.submit((() -> cacheConsumer.handle(key)));

        try {

            V value = future.get();
            cacheHashMap.put(key, value);
            return value;

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return null;
    }

    public V remove(K key) {

        if(cacheHashMap.containsKey(key)) return cacheHashMap.remove(key);
        return null;
    }

    public V get(K key) {
        return (contains(key) ? cacheHashMap.get(key) : null);
    }

    public boolean contains(K key) {
        return cacheHashMap.containsKey(key);
    }

    public boolean containsValue(V value) {
        return cacheHashMap.containsValue(value);
    }

    public void all(BiConsumer<K, V> biConsumer) {
        cacheHashMap.forEach(biConsumer);
    }

    public void allKeys(Consumer<K> consumer) {

        executorService.execute(() -> {
            for(K k : keys()) consumer.accept(k);
        });
    }

    public void allValues(Consumer<V> consumer) {

        executorService.execute(() -> {
            for(V v : values()) consumer.accept(v);
        });
    }

    public List<K> keys() {
        return Lists.newArrayList(cacheHashMap.keySet());
    }

    public List<V> values() {
        return Lists.newArrayList(cacheHashMap.values());
    }

}
