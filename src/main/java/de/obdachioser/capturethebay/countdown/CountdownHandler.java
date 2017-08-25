package de.obdachioser.capturethebay.countdown;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ObdachIoser at 13:47 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter @Setter
public class CountdownHandler {

    private Integer[] i = {60, 45, 30, 15, 10, 5, 4, 3, 2, 1};

    private GameState gameState;

    private boolean running = false;

    private Integer time;
    private CountdownInitializer countdownInitializer;

    public CountdownHandler(GameState startState, CountdownInitializer countdownInitializer) {

        this.time = gameState.getTime();
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

            } catch (Exception exc) {
                exc.printStackTrace();
            }

        });

    }

    public void reset() {
        time = gameState.getTime();
    }

    public void switchState(GameState gameState) {

        this.gameState = gameState;
        this.running = false;

        init();
    }
}
