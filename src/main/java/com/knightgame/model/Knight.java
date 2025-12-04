package com.knightgame.model;

public class Knight extends MOB {
    private Fortune activeFortune;
    protected int id;
    protected int xp;

    public Knight(int id, String name, int maxHP, int armor, int hitModifier, DiceType damageDie, int xp) {
        super(name, maxHP, armor, hitModifier, damageDie);
        this.id = id;
        this.xp = xp;
        this.activeFortune = null;
    }

    public void addXP(int xp) {
        this.xp += xp;
    }

    public Fortune getActiveFortune() {
        return activeFortune;
    }

    @Override
    public int getArmor() {
        int totalArmor = super.getArmor();
        if (activeFortune != null) {
            totalArmor += activeFortune.getArmor();
        }
        return totalArmor;
    }

    @Override
    public DiceType getDamageDie() {
        if (activeFortune != null) {
            return activeFortune.getDamageDie();
        }
        return super.getDamageDie();
    }

    @Override
    public int getHitModifier() {
        int totalModifier = super.getHitModifier();
        if (activeFortune != null) {
            totalModifier += activeFortune.getHitModifier();
        }
        return totalModifier;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int getMaxHP() {
        int totalHP = super.getMaxHP();
        if (activeFortune != null) {
            totalHP += activeFortune.getMaxHP();
        }
        return totalHP;
    }

    public int getXP() {
        return xp;
    }

    public void setActiveFortune(Fortune fortune) {
        this.activeFortune = fortune;
    }

    public String toCSV() {
        return String.format("%d,%s,%d,%d,%d,%s,%d",
                id, getName(), super.getMaxHP(), armor, hitModifier, damageDie, xp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Knight #%d: %s (HP: %d/%d, Armor: %d, Hit Mod: %d, Damage Die: %s, XP: %d)",
                id, getName(), getHP(), getMaxHP(), getArmor(), getHitModifier(), getDamageDie(), xp));
        if (activeFortune != null) {
            sb.append(String.format("\n  Active Fortune: %s", activeFortune.getName()));
        }
        return sb.toString();
    }
}
