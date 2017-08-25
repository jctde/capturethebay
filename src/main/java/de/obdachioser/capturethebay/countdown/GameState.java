package de.obdachioser.capturethebay.countdown;

import lombok.Getter;

/**
 * Created by ObdachIoser at 13:50 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public enum GameState {

    LOBBY(61, 0), INGAME((60*30)+1, 1), END(16, 2);

    @Getter
    private Integer time;

    private Integer state;

    public Integer toInteger() {
        return state;
    }

    GameState(Integer integer, Integer state) {
        this.time = integer;
        this.state = state;
    }
}
