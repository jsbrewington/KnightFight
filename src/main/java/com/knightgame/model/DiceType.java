package com.knightgame.model;

import java.util.Random;

public enum DiceType {
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20);

    private final int SIZE;
    private static final Random random_machine = new Random();

    private DiceType(int size) {
        this.SIZE = size;
    }

    public int Roll() {
        return random_machine.nextInt(SIZE) + 1;
    }

    public int getSize() {
        return SIZE;
    }
}
