# SkyWars Java Game

![Static Badge](https://img.shields.io/badge/Version%20-%20v1.1.0%20-%20%20%2315202b%20)

## Project Description 
Welcome to my personal project: SkyWars Java Game. This application is a computer game developed using **Java in Eclipse IDE and WindowBuilder™** (SWT Designer and Java Swing).

![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![Eclipse](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

The game consists of a sky, a master spaceship and several enemy spaceships (currently Battle Destroyer, Battle Star, and Battle Shooter). The player can start the game which will deploy a master ship (the player's ship) and the player can make turns which randomly move the master ship while each turn has a 1 in 3 chance that a random enemy ship will be deployed in the top left corner. The player can also change the ship mode of the master ship to defensive or offensive mode. If in defensive mode, the master ship will destroy the enemy ships in the position it flies if the number of enemy ships is less or equal to two. If more than two, the master ship will be destroyed and the game is over. If in offensive mode, the master ship can destroy three enemy ships. It is possible to save the game and there is a counter of each enemy ship destroyed. 

## Game design 

### General 
The overall architecture of the game consists of a Model View Controller design (MVC) in separate packages to understand better the important sections of the project. The Model part has all the classes for objects and the data structures. The View package has the Graphical User Interface where the player can control the game flow, while the Controller package consists of all the game logic.

### Backend and Game logic 
### Grafical User Interface (GUI) 

## Important System Requirements and Usage
The program might not work the same way as intended without an internet connection. To use and run the program download the project source and run it as a Java Project in Eclipse. Currently, no JAR file is downloadable due to an unsolved error with packages. 



