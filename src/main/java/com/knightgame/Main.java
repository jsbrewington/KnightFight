package com.knightgame;

import com.knightgame.controller.CombatEngine;
import com.knightgame.controller.GameController;
import com.knightgame.data.CSVGameData;
import com.knightgame.data.GameData;
import com.knightgame.view.ConsoleView;
import com.knightgame.view.GameView;

public class Main {
    private static String gamedata = "gamedata.csv";
    private static String saveData = "savedata.csv";

    public static void main(String[] args) {
        processArgs(args);

        GameData data = new CSVGameData(gamedata, saveData);
        GameView view = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);

        controller.start();

        data.save(saveData);
    }

    private static void processArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-g") && i + 1 < args.length) {
                gamedata = args[i + 1];
                i++;
            } else if (args[i].equals("-s") && i + 1 < args.length) {
                saveData = args[i + 1];
                i++;
            }
        }
    }
}
