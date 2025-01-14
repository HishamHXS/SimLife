package com.maingame.Helper;

import java.util.Random;

public class ActionChance {

     public static boolean roll(int odds) {
        int rolledOdds = rollInt(0, 100);
        int currentOdds = 0;

        currentOdds += odds;

        return rolledOdds < currentOdds;
    }

    public static int rollInt(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(lowerBound, upperBound);
    }
}
