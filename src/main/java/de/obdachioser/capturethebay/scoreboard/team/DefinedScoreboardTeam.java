package de.obdachioser.capturethebay.scoreboard.team;

import java.util.List;

/**
 * Created by ObdachIoser at 22:41 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public interface DefinedScoreboardTeam {

    void add(String player);
    void remove(String player);
    void setPrefix(String prefix);
    void setSuffix(String suffix);
    String getSuffix();
    String getPrefix();
    List<String> getPlayers();
}
