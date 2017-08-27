package de.obdachioser.capturethebay.bay;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.Team;

import java.util.Random;

/**
 * Created by ObdachIoser at 13:48 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class RandomBayName {

    public static String getRandomName() {
        return CaptureTheBay.getGamePlaySession().getBayNames().get(new Random().nextInt(CaptureTheBay.getGamePlaySession().getBayNames().size()));
    }
}
