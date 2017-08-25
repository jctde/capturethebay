package de.obdachioser.capturethebay.countdown;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.CaptureTheBay;
import org.bukkit.Bukkit;

import java.util.List;

/**
 * Created by ObdachIoser at 14:25 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class SimpleCountdownInitializer implements CountdownInitializer {


    private List<Integer> i = Lists.newArrayList(60, 45, 30, 15, 10, 5, 4, 3, 2, 1);

    @Override
    public void handle(Integer time) {

        Bukkit.broadcastMessage("Time: " + time);

        if(i.contains(time)) {
            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "Das Spiel beginnt in §e" + time + " " + (time == 1 ? "Sekunde" : "Sekunden") + "§7!");
        }
    }
}
