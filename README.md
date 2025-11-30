#  2DMON

A fully handcrafted 2D Java adventure game built entirely from scratch.
This project showcases custom game logic, a multi-map storyline, interactable items, combat mechanics, puzzle-solving, and a dynamic lighting system.

Note: Some visual assets are sourced from Pokémon and other creators across the internet. Full credit is provided below.

 #   Storyline

You begin your journey as an adventurer exploring mysterious lands.
Your progression spans across four unique maps:

Map 1 – The Discovery

Collect essential objects scattered throughout the map to unlock entry into the Dark Cave.

Map 2 – The Dark Cave

The cave starts completely dark. As soon as you find the Torch, visibility gradually increases.
Inside the cave you must:

Find the Torch

Collect the Sword

Solve a puzzle to escape

Map 3 – The Monster Grounds

Face small monsters, collect the Fireball ability, and use it to defeat enemies within a 4-tile attack range.

Map 4 – The Final Showdown

With all your collected equipment, face the Final Boss and finish your journey.

#   Key Features
 Save & Load System

A complete game-saving feature lets players:

Save anytime, anywhere

Reload and continue exactly where they left off, including items, stats, and map position

 Combat & Abilities

Sword: Unlocked in Map 2

Fireball Attack: Obtained in Map 3, travels up to 4 tiles and damages enemies

Strength Attack: Helps push rocks and move obstacles

Boots: Increase or decrease player movement speed

 Dynamic Lighting System

In the Dark Cave, visibility improves gradually after picking up the Torch, creating a realistic dungeon exploration experience.

 Items

The game includes multiple interactable items:

Healing Potions

Strength Attack

Fireball power

Boots (Speed buff/debuff)

Puzzle-related objects
…and many more.

#   Puzzle Mechanics

A custom-coded puzzle inside the cave must be solved to progress.

 #  Controls

(Shown on the Title Screen of the game)

W / A / S / D : Move player  
E             : Interact / Pick up items  
J             : Sword attack  
K             : Fireball attack  
L             : Strength push  
P             : Save game  
O             : Load game  
ESC           : Pause / Menu

 #   Project Structure
Sanjayzorojuro/
│

├── .settings/           # IDE-specific settings  

├── bin/                 # Compiled .class files  

├── res/                 # All game assets (images, tilesets, audio, etc.)  

├── src/                 # Full Java source code  

│
├── .classpath           # Project classpath  

└── .project             # Project configuration


#   Screenshots


![Title](res/Gameplay/1.png)

![Title](res/Gameplay/2.png)

![Title](res/Gameplay/3.png)

![Title](res/Gameplay/4.png)

![Title](res/Gameplay/5.png)

![Title](res/Gameplay/6.png)

![Title](res/Gameplay/7.png)

![Title](res/Gameplay/8.png)

![Title](res/Gameplay/9.png)

![Title](res/Gameplay/10.png)

![Title](res/Gameplay/11.png)

![Title](res/Gameplay/12.png)

![Title](res/Gameplay/13.png)

![Title](res/Gameplay/14.png)

![Title](res/Gameplay/15.png)

![Title](res/Gameplay/16.png)

![Title](res/Gameplay/17.png)

![Title](res/Gameplay/18.png)

![Title](res/Gameplay/19.png)

![Title](res/Gameplay/20.png)

![Title](res/Gameplay/21.png)

![Title](res/Gameplay/22.png)

![Title](res/Gameplay/23.png)

![Title](res/Gameplay/24.png)

![Title](res/Gameplay/25.png)

![Title](res/Gameplay/26.png)

![Title](res/Gameplay/27.png)

![Title](res/Gameplay/28.png)

![Title](res/Gameplay/29.png)


---

 #   Technologies Used

Java (Core language)

Java AWT / Swing (Rendering & graphics)

Custom-built game engine logic

Tile-based map transitions

#  Credits

Some assets used in this project are not created by me:

Player sprite – from Pokémon series

Some monster sprites – from Pokémon series

Additional assets – collected from various free resources across the internet and lightly edited by my team.

All rights and ownership of these assets belong to their respective creators.

This project is not for commercial use, and the assets are used strictly for learning and personal development.

#  How to Run

Clone the repository:

git clone https://github.com/sanjayzorojuro/2DMON.git


Open the project in any Java-supported IDE (Eclipse, IntelliJ, etc.)

Run the main game file from the /src directory

Enjoy the adventure!

#  Contact

If you want to collaborate, suggest improvements, or report issues, feel free to open an Issue or Pull Request.
