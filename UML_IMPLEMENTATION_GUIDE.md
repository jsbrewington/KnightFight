# Knight Game - UML to Implementation Guide

This document explains how the UML diagram provided enough detail to implement the complete Knight Game system.

## What the UML Provides Explicitly

### 1. Complete Class Structure

- **11 classes total** across 3 diagrams (Model, View/Data, Controller)
- **Exact method signatures** with return types and parameters
- **Field types and visibility** (public +, private -, protected #, static $)
- **Inheritance relationships** (MOB extends Attributes, Knight extends MOB, etc.)
- **Interface implementations** (ConsoleView implements GameView, etc.)

### 2. Design Patterns

- **MVC Architecture**: Model (Knight, MOB, Fortune), View (GameView/ConsoleView), Controller (GameController)
- **Strategy Pattern**: GameData is abstract with CSVGameData implementation
- **Interface segregation**: Attributes interface, GameView interface

### 3. Domain Logic Hints

From the method names and structure, we can infer:

- **Combat system** exists (CombatEngine, doBattle, addDamage, getHP)
- **Fortune/buff system** (Knight has activeFortune, getRandomFortune)
- **XP/progression** (addXP, getXP methods)
- **Active roster management** (setActive, removeActive, MAX_ACTIVE constant)
- **Persistence** (save method, toCSV, loadSaveData/loadGameData)

## What Needs to be Inferred/Decided

### 1. Implementation Details Not in UML

**Dice Rolling Logic:**
```java
// UML shows: +Roll() int
// Implementation: random number from 1 to SIZE
```

**Combat Mechanics:**
- UML shows `doBattle(List<MOB>, List<MOB>) int` but not the algorithm
- Need to decide: turn order, hit calculations (d20 + hitMod vs armor?), damage resolution

**Fortune Distribution:**
- When are fortunes given? After battle? Random?
- The UML shows `getRandomFortune()` but not when it's called

**CSV Format:**
- UML shows `loadGameData` and `save` but not the file structure
- Design decision: `id,name,armor,hp,hitMod,damageDie,xp` format

### 2. User Interface Text

```java
// UML shows: splashScreen() void
// Implementation: ASCII art banner, welcome message

// UML shows: displayMainMenu() String
// Implementation: menu text, command list, prompt
```

### 3. Game Flow Logic

```java
// UML shows GameController.start() and processCommand(String)
// Implementation: main loop, command parsing, state management
```

### 4. Business Rules

- **MAX_ACTIVE value** - UML declares it but not the value (implementation chose 3-5)
- **Monster generation** - How many? What stats? (getRandomMonsters exists)
- **Fortune effects** - Do they stack with base Knight stats? (getArmor in both)

## Build Order Step by Step

1. **Start with enums/simple classes**: DiceType first
2. **Build the model hierarchy**: Attributes → MOB → Knight, Fortune
3. **Implement data layer**: GameData abstract → CSVGameData
4. **Create view layer**: GameView interface → ConsoleView
5. **Build controllers**: CombatEngine, GameController
6. **Wire it together**: Main class
7. **Add sample data**: Create gamedata.csv with monsters/fortunes

## What Makes This UML "Good Enough"

✅ **Complete method signatures** - Exact inputs/outputs are specified
✅ **Clear relationships** - Inheritance and composition are explicit
✅ **MVC separation** - Clean architecture is specified
✅ **Type information** - All fields have types (DiceType, List<Knight>, etc.)
✅ **Visibility modifiers** - Public/private/protected boundaries are clear

## Questions That Would Still Need Clarification

❓ Combat rules (how does armor reduce damage? Is it d20 rolls to hit?)
❓ Fortune mechanics (one-time use? Permanent? Stack with base stats?)
❓ Win/loss conditions (defeat all monsters? Knights all die?)
❓ Game theme/flavor text style

## Key Insight

The UML provides the **structure and contracts** (what methods exist, what they return), and implementation uses **common RPG/game conventions** plus **reasonable defaults** to fill in the behavior.

### Examples of Convention-Based Inference:

- Seeing `getHitModifier()` + `getArmor()` → Assume d20-based combat system
- Seeing `Fortune` with stat bonuses → Implement as temporary buff system
- Seeing `toCSV()` → Design a sensible CSV format based on class fields
- Seeing `DiceType` enum with D4-D20 → Standard tabletop RPG dice
- Seeing `CombatEngine.doBattle()` → Turn-based combat with hit/damage rolls

## Architecture Overview

```
Model Layer (Data)
├── Attributes (Interface)
├── DiceType (Enum)
├── Fortune (Attributes implementation)
├── MOB (Attributes implementation)
└── Knight (MOB extension)

View Layer
├── GameView (Interface)
└── ConsoleView (GameView implementation)

Data Layer
├── GameData (Abstract)
└── CSVGameData (GameData implementation)

Controller Layer
├── CombatEngine
├── GameController
└── Main
```

## Implementation Philosophy

When implementing from UML:

1. **Follow the contracts strictly** - Method signatures are sacred
2. **Use domain knowledge** - RPG conventions guide combat mechanics
3. **Keep it simple** - Don't over-engineer beyond what UML specifies
4. **Make reasonable assumptions** - CSV format, dice mechanics, UI text
5. **Stay consistent** - Match naming conventions and patterns from UML

---

*Generated based on the Knight Game UML diagram and implementation analysis*
