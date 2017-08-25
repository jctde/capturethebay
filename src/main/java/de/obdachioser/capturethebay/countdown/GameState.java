package de.obdachioser.capturethebay.countdown;

import lombok.Getter;

/**
 * Created by ObdachIoser at 13:50 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public enum GameState {

    LOBBY(60), INGAME(60*30), END(15);

    @Getter
    private Integer time;

    GameState(Integer integer) {
        this.time = integer;
    }
}
