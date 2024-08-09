package com.ingsystemcix.backgammon.functions;

import java.util.Random;

/**
 *
 * @author IngSystemCix
 */
public class Dado {
    
    private final Random random = new Random();

    public int get() {
        return random.nextInt(6) + 1;
    }
}
