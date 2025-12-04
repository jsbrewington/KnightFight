# Knights & Fortunes Game System

A Java-based RPG game system where knights battle monsters with the aid of magical fortunes.

## Project Structure

```
knight-game/
├── src/main/java/com/knightgame/
│   ├── model/
│   │   ├── Attributes.java       - Interface for character attributes
│   │   ├── DiceType.java         - Enum for different dice types
│   │   ├── Fortune.java          - Fortune items that boost knight stats
│   │   ├── MOB.java             - Base class for monsters and characters
│   │   └── Knight.java          - Knight class extending MOB
│   ├── view/
│   │   ├── GameView.java        - View interface
│   │   └── ConsoleView.java     - Console-based view implementation
│   ├── data/
│   │   ├── GameData.java        - Abstract data management class
│   │   └── CSVGameData.java     - CSV-based data implementation
│   ├── controller/
│   │   ├── CombatEngine.java    - Handles combat logic
│   │   └── GameController.java  - Main game flow controller
│   └── Main.java                - Application entry point
├── gamedata.csv                 - Game data (fortunes and monsters)
├── savedata.csv                 - Knight save data
└── README.md
```

## Building and Running

### Compile
```bash
cd knight-game
mkdir -p bin
javac -d bin src/main/java/com/knightgame/**/*.java src/main/java/com/knightgame/*.java
```

### Run
```bash
java -cp bin com.knightgame.Main
```

### Run with custom data files
```bash
java -cp bin com.knightgame.Main -g gamedata.csv -s savedata.csv
```

## Game Commands

- `list` - List all knights
- `show <id>` - Show details of a specific knight
- `active` - List all active knights
- `setactive <id>` - Set a knight as active (max 5)
- `removeactive <id>` - Remove a knight from active roster
- `battle` - Start a battle with active knights
- `fortunes` - Display fortune distribution for active knights
- `help` - Show help message
- `quit` - Exit the game

## Data File Formats

### gamedata.csv
Contains fortunes and monsters:
```csv
fortune,name,armor,hitModifier,hpBonus[,damageDie]
monster,name,maxHP,armor,hitModifier,damageDie
```

### savedata.csv
Contains knight data:
```csv
id,name,maxHP,armor,hitModifier,damageDie,xp
```

## Game Flow

1. Load knights from save data
2. Set knights as active (up to 5)
3. Start battle - each active knight receives a random fortune
4. Knights fight randomly selected monsters
5. Victory awards XP, defeat removes knights from active roster
6. Save knight data on exit

## Architecture

The system follows MVC pattern:
- **Model**: Game entities (Knight, MOB, Fortune, DiceType)
- **View**: User interface (GameView, ConsoleView)
- **Controller**: Game logic (GameController, CombatEngine)
- **Data**: Data management (GameData, CSVGameData)
