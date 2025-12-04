package com.knightgame.model;

public class MOB implements Attributes {
    protected int armor;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    protected int maxHP;
    private String name;

    public MOB(String name, int maxHP, int armor, int hitModifier, DiceType damageDie) {
        this.name = name;
        this.maxHP = maxHP;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
        this.damage = 0;
    }

    public void addDamage(int damage) {
        this.damage += damage;
        if (this.damage > maxHP) {
            this.damage = maxHP;
        }
    }

    public MOB copy() {
        MOB copy = new MOB(this.name, this.maxHP, this.armor, this.hitModifier, this.damageDie);
        copy.damage = this.damage;
        return copy;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public DiceType getDamageDie() {
        return damageDie;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    public int getHP() {
        return maxHP - damage;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    public String getName() {
        return name;
    }

    public void resetDamage() {
        this.damage = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (HP: %d/%d, Armor: %d, Hit Mod: %d, Damage Die: %s)",
                name, getHP(), maxHP, armor, hitModifier, damageDie);
    }
}
