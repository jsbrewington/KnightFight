package com.knightgame.model;

public class Fortune implements Attributes {
    private int armor;
    private DiceType dtype;
    private int hitModifier;
    private int hpBonus;
    private String name;

    public Fortune(String name, int armor, int hitModifier, int hpBonus) {
        this(name, armor, hitModifier, hpBonus, DiceType.D6);
    }

    public Fortune(String name, int armor, int hitModifier, int hpBonus, DiceType dtype) {
        this.name = name;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.hpBonus = hpBonus;
        this.dtype = dtype;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public DiceType getDamageDie() {
        return dtype;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s (Armor: %d, HP Bonus: %d, Hit Mod: %d, Damage Die: %s)",
                name, armor, hpBonus, hitModifier, dtype);
    }
}
