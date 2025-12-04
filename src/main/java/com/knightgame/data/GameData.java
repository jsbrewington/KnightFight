package com.knightgame.data;

import com.knightgame.model.Fortune;
import com.knightgame.model.Knight;
import com.knightgame.model.MOB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GameData {
    protected List<Knight> activeKnights;
    protected List<Fortune> fortunes;
    protected List<Knight> knights;
    private final int MAX_ACTIVE = 5;
    protected List<MOB> monsters;
    private Random random;

    public GameData() {
        this.activeKnights = new ArrayList<>();
        this.fortunes = new ArrayList<>();
        this.knights = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.random = new Random();
    }

    protected Knight findKnight(String identifier, List<Knight> knightList) {
        try {
            int id = Integer.parseInt(identifier);
            for (Knight knight : knightList) {
                if (knight.getId() == id) {
                    return knight;
                }
            }
        } catch (NumberFormatException e) {
            for (Knight knight : knightList) {
                if (knight.getName().equalsIgnoreCase(identifier)) {
                    return knight;
                }
            }
        }
        return null;
    }

    public Knight getActive(String identifier) {
        return findKnight(identifier, activeKnights);
    }

    public List<Knight> getActiveKnights() {
        return new ArrayList<>(activeKnights);
    }

    public Knight getKnight(String identifier) {
        return findKnight(identifier, knights);
    }

    public List<Knight> getKnights() {
        return new ArrayList<>(knights);
    }

    public Fortune getRandomFortune() {
        if (fortunes.isEmpty()) {
            return null;
        }
        return fortunes.get(random.nextInt(fortunes.size()));
    }

    public List<MOB> getRandomMonsters() {
        return getRandomMonsters(random.nextInt(3) + 1);
    }

    public List<MOB> getRandomMonsters(int count) {
        List<MOB> selectedMonsters = new ArrayList<>();
        if (monsters.isEmpty()) {
            return selectedMonsters;
        }
        for (int i = 0; i < count; i++) {
            MOB monster = monsters.get(random.nextInt(monsters.size()));
            selectedMonsters.add(monster.copy());
        }
        return selectedMonsters;
    }

    public void removeActive(Knight knight) {
        activeKnights.remove(knight);
    }

    public boolean setActive(Knight knight) {
        if (activeKnights.size() >= MAX_ACTIVE) {
            return false;
        }
        if (!activeKnights.contains(knight)) {
            activeKnights.add(knight);
            return true;
        }
        return false;
    }

    public abstract void save(String filename);
}
