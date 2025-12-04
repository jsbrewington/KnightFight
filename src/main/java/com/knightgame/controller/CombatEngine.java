package com.knightgame.controller;

import com.knightgame.data.GameData;
import com.knightgame.model.Fortune;
import com.knightgame.model.Knight;
import com.knightgame.model.MOB;
import com.knightgame.view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatEngine {
    private GameData data;
    private Random rnd;
    private GameView view;

    public CombatEngine(GameData data, GameView view) {
        this.data = data;
        this.view = view;
        this.rnd = new Random();
    }

    public void clear() {
        List<Knight> activeKnights = data.getActiveKnights();
        for (Knight knight : activeKnights) {
            knight.resetDamage();
            knight.setActiveFortune(null);
        }
    }

    private int doBattle(List<MOB> team1, List<MOB> team2) {
        List<MOB> allCombatants = new ArrayList<>();
        allCombatants.addAll(team1);
        allCombatants.addAll(team2);

        while (!team1.isEmpty() && !team2.isEmpty()) {
            for (MOB attacker : allCombatants) {
                if (attacker.getHP() <= 0) {
                    continue;
                }

                List<MOB> targets = team1.contains(attacker) ? team2 : team1;
                if (targets.isEmpty()) {
                    break;
                }

                MOB target = targets.get(rnd.nextInt(targets.size()));

                int attackRoll = rnd.nextInt(20) + 1 + attacker.getHitModifier();
                if (attackRoll >= target.getArmor()) {
                    int damage = attacker.getDamageDie().Roll();
                    target.addDamage(damage);
                    System.out.printf("%s hits %s for %d damage! (%s: %d/%d HP)\n",
                            attacker.getName(), target.getName(), damage,
                            target.getName(), target.getHP(), target.getMaxHP());

                    if (target.getHP() <= 0) {
                        System.out.printf("%s has been defeated!\n", target.getName());
                        targets.remove(target);
                    }
                } else {
                    System.out.printf("%s misses %s!\n", attacker.getName(), target.getName());
                }

                if (team1.isEmpty() || team2.isEmpty()) {
                    break;
                }
            }

            allCombatants.removeIf(mob -> mob.getHP() <= 0);
        }

        if (!team1.isEmpty()) {
            return 1;
        } else if (!team2.isEmpty()) {
            return 2;
        }
        return 0;
    }

    public void initialize() {
        List<Knight> activeKnights = data.getActiveKnights();
        for (Knight knight : activeKnights) {
            Fortune fortune = data.getRandomFortune();
            knight.setActiveFortune(fortune);
        }
        view.printFortunes(activeKnights);
    }

    public void runCombat() {
        List<Knight> activeKnights = data.getActiveKnights();
        if (activeKnights.isEmpty()) {
            System.out.println("No active knights! Please set knights as active first.");
            return;
        }

        List<MOB> monsters = data.getRandomMonsters();
        List<MOB> knightCopies = new ArrayList<>();
        for (Knight knight : activeKnights) {
            knightCopies.add(knight.copy());
        }

        view.printBattleText(monsters, activeKnights);

        int result = doBattle(knightCopies, monsters);

        if (result == 1) {
            System.out.println("\nVictory! The knights have triumphed!");
            int xpGained = monsters.size() * 10;
            for (Knight knight : activeKnights) {
                knight.addXP(xpGained);
            }
            System.out.printf("Each knight gains %d XP!\n", xpGained);
        } else {
            view.printDefeated();
            for (Knight knight : activeKnights) {
                data.removeActive(knight);
            }
        }
    }
}
