package com.knightgame.view;

import com.knightgame.model.Knight;
import com.knightgame.model.MOB;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private Scanner in;

    public ConsoleView() {
        this.in = new Scanner(System.in);
    }

    @Override
    public boolean checkContinue() {
        System.out.print("Continue? (y/n): ");
        String response = in.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }

    @Override
    public String displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("Commands: list, show <id>, active, setactive <id>, removeactive <id>, battle, fortunes, help, quit");
        System.out.print("> ");
        return in.nextLine().trim();
    }

    @Override
    public void endGame() {
        System.out.println("\nThank you for playing! Farewell, brave knight!");
        in.close();
    }

    @Override
    public void knightNotFound() {
        System.out.println("Knight not found!");
    }

    @Override
    public void listKnights(List<Knight> knights) {
        System.out.println("\n=== All Knights ===");
        if (knights.isEmpty()) {
            System.out.println("No knights available.");
        } else {
            for (Knight knight : knights) {
                System.out.println(knight);
            }
        }
    }

    @Override
    public void printBattleText(List<MOB> monsters, List<Knight> knights) {
        System.out.println("\n=== Battle! ===");
        System.out.println("Monsters:");
        for (MOB mob : monsters) {
            System.out.println("  " + mob);
        }
        System.out.println("\nKnights:");
        for (Knight knight : knights) {
            System.out.println("  " + knight);
        }
    }

    @Override
    public void printBattleText(MOB mob) {
        System.out.println("A " + mob.getName() + " appears!");
    }

    @Override
    public void printDefeated() {
        System.out.println("\nYour knights have been defeated!");
    }

    @Override
    public void printFortunes(List<Knight> knights) {
        System.out.println("\n=== Fortune Distribution ===");
        for (Knight knight : knights) {
            if (knight.getActiveFortune() != null) {
                System.out.printf("%s received: %s\n", knight.getName(), knight.getActiveFortune());
            }
        }
    }

    @Override
    public void printHelp() {
        System.out.println("\n=== Help ===");
        System.out.println("list - List all knights");
        System.out.println("show <id> - Show details of a specific knight");
        System.out.println("active - List all active knights");
        System.out.println("setactive <id> - Set a knight as active");
        System.out.println("removeactive <id> - Remove a knight from active roster");
        System.out.println("battle - Start a battle with active knights");
        System.out.println("fortunes - Display fortune distribution for active knights");
        System.out.println("help - Show this help message");
        System.out.println("quit - Exit the game");
    }

    @Override
    public void setActiveFailed() {
        System.out.println("Failed to set knight as active. Maximum active knights reached or knight not found.");
    }

    @Override
    public void showKnight(Knight knight) {
        System.out.println("\n" + knight);
    }

    @Override
    public void splashScreen() {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║                                       ║");
        System.out.println("║    KNIGHTS & FORTUNES GAME SYSTEM     ║");
        System.out.println("║                                       ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("\nWelcome, brave adventurer!");
    }
}
