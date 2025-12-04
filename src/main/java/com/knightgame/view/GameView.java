package com.knightgame.view;

import com.knightgame.model.Knight;
import com.knightgame.model.MOB;

import java.util.List;

public interface GameView {
    boolean checkContinue();
    String displayMainMenu();
    void endGame();
    void knightNotFound();
    void listKnights(List<Knight> knights);
    void printBattleText(List<MOB> monsters, List<Knight> knights);
    void printBattleText(MOB mob);
    void printDefeated();
    void printFortunes(List<Knight> knights);
    void printHelp();
    void setActiveFailed();
    void showKnight(Knight knight);
    void splashScreen();
}
