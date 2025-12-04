package com.knightgame.controller;

import com.knightgame.data.GameData;
import com.knightgame.model.Knight;
import com.knightgame.view.GameView;

public class GameController {
    private GameData data;
    private CombatEngine engine;
    private GameView view;

    public GameController(GameData data, GameView view, CombatEngine engine) {
        this.data = data;
        this.view = view;
        this.engine = engine;
    }

    protected boolean processCommand(String command) {
        String[] parts = command.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();

        switch (cmd) {
            case "list":
                view.listKnights(data.getKnights());
                break;
            case "show":
                if (parts.length < 2) {
                    System.out.println("Usage: show <knight id or name>");
                } else {
                    processShowKnight(parts[1]);
                }
                break;
            case "active":
                view.listKnights(data.getActiveKnights());
                break;
            case "setactive":
                if (parts.length < 2) {
                    System.out.println("Usage: setactive <knight id or name>");
                } else {
                    processSetActive(parts[1]);
                }
                break;
            case "removeactive":
                if (parts.length < 2) {
                    System.out.println("Usage: removeactive <knight id or name>");
                } else {
                    processRemoveActive(parts[1]);
                }
                break;
            case "battle":
                engine.initialize();
                engine.runCombat();
                engine.clear();
                break;
            case "fortunes":
                view.printFortunes(data.getActiveKnights());
                break;
            case "help":
                view.printHelp();
                break;
            case "quit":
            case "exit":
                return false;
            default:
                System.out.println("Unknown command. Type 'help' for available commands.");
                break;
        }
        return true;
    }

    private void processRemoveActive(String identifier) {
        Knight knight = data.getActive(identifier);
        if (knight != null) {
            data.removeActive(knight);
            System.out.println(knight.getName() + " removed from active roster.");
        } else {
            view.knightNotFound();
        }
    }

    private void processSetActive(String identifier) {
        Knight knight = data.getKnight(identifier);
        if (knight != null) {
            boolean success = data.setActive(knight);
            if (success) {
                System.out.println(knight.getName() + " set as active.");
            } else {
                view.setActiveFailed();
            }
        } else {
            view.knightNotFound();
        }
    }

    private void processShowKnight(String identifier) {
        Knight knight = data.getKnight(identifier);
        if (knight != null) {
            view.showKnight(knight);
        } else {
            view.knightNotFound();
        }
    }

    public void start() {
        view.splashScreen();
        boolean running = true;
        while (running) {
            String command = view.displayMainMenu();
            running = processCommand(command);
        }
        view.endGame();
    }
}
