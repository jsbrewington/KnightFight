package com.knightgame.data;

import com.knightgame.model.DiceType;
import com.knightgame.model.Fortune;
import com.knightgame.model.Knight;
import com.knightgame.model.MOB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVGameData extends GameData {

    public CSVGameData(String gameDataFile, String saveDataFile) {
        super();
        loadGameData(gameDataFile);
        loadSaveData(saveDataFile);
    }

    public void loadGameData(String filename) {
        Scanner scanner = readFile(filename);
        if (scanner == null) {
            return;
        }

        while (scanner.hasNextLine()) {
            parseGameDataLine(scanner);
        }
        scanner.close();
    }

    public void loadSaveData(String filename) {
        Scanner scanner = readFile(filename);
        if (scanner == null) {
            return;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 7) {
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int maxHP = Integer.parseInt(parts[2].trim());
                    int armor = Integer.parseInt(parts[3].trim());
                    int hitMod = Integer.parseInt(parts[4].trim());
                    DiceType die = DiceType.valueOf(parts[5].trim());
                    int xp = Integer.parseInt(parts[6].trim());

                    Knight knight = new Knight(id, name, maxHP, armor, hitMod, die, xp);
                    knights.add(knight);
                } catch (Exception e) {
                    System.err.println("Error parsing save data line: " + line);
                }
            }
        }
        scanner.close();
    }

    private void parseGameDataLine(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.isEmpty() || line.startsWith("#")) {
            return;
        }

        String[] parts = line.split(",");
        if (parts.length < 2) {
            return;
        }

        String type = parts[0].trim().toLowerCase();
        try {
            if (type.equals("fortune") && parts.length >= 5) {
                String name = parts[1].trim();
                int armor = Integer.parseInt(parts[2].trim());
                int hitMod = Integer.parseInt(parts[3].trim());
                int hpBonus = Integer.parseInt(parts[4].trim());
                if (parts.length >= 6) {
                    DiceType die = DiceType.valueOf(parts[5].trim());
                    fortunes.add(new Fortune(name, armor, hitMod, hpBonus, die));
                } else {
                    fortunes.add(new Fortune(name, armor, hitMod, hpBonus));
                }
            } else if (type.equals("monster") && parts.length >= 6) {
                String name = parts[1].trim();
                int maxHP = Integer.parseInt(parts[2].trim());
                int armor = Integer.parseInt(parts[3].trim());
                int hitMod = Integer.parseInt(parts[4].trim());
                DiceType die = DiceType.valueOf(parts[5].trim());
                monsters.add(new MOB(name, maxHP, armor, hitMod, die));
            }
        } catch (Exception e) {
            System.err.println("Error parsing game data line: " + line);
        }
    }

    private Scanner readFile(String filename) {
        try {
            File file = new File(filename);
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            return null;
        }
    }

    @Override
    public void save(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("# Knight save data: id,name,maxHP,armor,hitMod,damageDie,xp");
            for (Knight knight : knights) {
                writer.println(knight.toCSV());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving to file: " + filename);
        }
    }
}
