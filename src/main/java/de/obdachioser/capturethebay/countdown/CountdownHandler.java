package de.obdachioser.capturethebay.countdown;

import de.obdachioser.capturethebay.events.GameStateChangeEvent;
import jdk.nashorn.internal.runtime.ECMAException;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by ObdachIoser at 13:47 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter @Setter
public class CountdownHandler {

    private GameState gameState;

    private boolean running = false;

    private Integer time;
    private CountdownInitializer countdownInitializer;

    public CountdownHandler(GameState startState, CountdownInitializer countdownInitializer) {

        this.gameState = startState;
        this.time = startState.getTime();

        this.countdownInitializer = countdownInitializer;
    }

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void init() {

        running = true;

        executorService.execute(() -> {

            try {

                while(time != 0) {

                    TimeUnit.MILLISECONDS.sleep(1000L);
                    time--;

                    countdownInitializer.handle(time);
                }

                running = false;

            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
    }

    public void reset() {
        time = gameState.getTime();
    }

    public void switchState(GameState gameState) {

        GameState old = this.gameState;

        this.gameState = gameState;
        this.time = gameState.getTime();

        if(running) {

           this.shutdown();
           this.executorService = Executors.newSingleThreadExecutor();
        }

        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(old, this.gameState));
        init();
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
