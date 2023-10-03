# SkyWars Java Game 👾🚀

![Static Badge](https://img.shields.io/badge/Version%20-%20v1.1.0%20-%20%20%2315202b%20)

## Project Description 
Welcome to my personal project: SkyWars Java Game. This application is a computer game developed using **Java in Eclipse IDE and WindowBuilder™** (SWT Designer and Java Swing).

![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![Eclipse](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

The game consists of a sky, a master spaceship and several enemy spaceships (currently Battle Destroyer, Battle Star, and Battle Shooter). The player can start the game which will deploy a master ship (the player's ship) and the player can make turns which randomly move the master ship while each turn has a 1 in 3 chance that a random enemy ship will be deployed in the top left corner. The player can also change the ship mode of the master ship to defensive or offensive mode. If in defensive mode, the master ship will destroy the enemy ships in the position it flies if the number of enemy ships is less or equal to two. If more than two, the master ship will be destroyed and the game is over. If in offensive mode, the master ship can destroy three enemy ships. It is possible to save the game and there is a counter of each enemy ship destroyed. 

## What did I learn/expertise during this project? 📖 👨‍🎓

* **Polymorphic programming-->**
A solid grasp on the understanding of polymorphic programming by using a number of design patterns such as the Strategy pattern, the Decorator pattern or the Observer pattern and advanced data structures.

* **HTML Hacking Java-->** Using HTML tags and external CDN links with http requests in Java and Eclipse IDE.

* **Debugging with Eclipse IDE, Abstract problem solving and algorithm design-->**
How to design a complex application with a simple algorithm to facilitate the game interactions between objects. Furthermore, due to the complexity of the app, a lot of time was spent on debugging and error handling. 

* **Expertise Object Oriented Programming-->**
Firm understanding of data structures, collections and how to manipulate them. 

* **Java Object Sreialisation-->**
To be able to save the current state of objects of a Java application and reload it.    

## Game design 🌐

### General 
The overall architecture of the game consists of a Model View Controller design (MVC) in separate packages to understand better the important sections of the project. The Model part has all the classes for objects and the data structures. The View package has the Graphical User Interface (GUI) where the player can control the game flow, while the Controller package consists of all the game logic. The GUI is separate from the game logic to split the level of abstraction and increase the reusability of the application. 

Furthermore, the game adheres to good object-oriented design with a number of design patterns used such as the Strategy pattern, Decorator pattern and Observer pattern. 

### Backend and Game logic 
* **Data structures:**  
The Grid object is an ArrayList of the Row objects, which further are ArrayList of the Square object. The Square object has an ArrayList of the EnemyShips and AllyShips, which are accessible to interact with or initialise the ships. The EnemyShip and AllyShip object both inherit from the same superclass Ship.

* **Algorithms:**

There is a simple algorithm written which determines which enemy ship is placed and also determines where the ships are moved during each turn.  

### Grafical User Interface (GUI)
Here is a screenshot of the GUI: 

<img width="980" alt="Screenshot 2023-09-23 102629" src="https://github.com/aron-radvanyi/SkyWars_GAME/assets/108479744/73f670f4-75d9-4d59-80b8-fc382efc3fa3">

## Important System Requirements and Usage
The program might not work the same way as intended without an internet connection. To use and run the program download the project source and run it as a Java Project in Eclipse. Currently, no JAR file is downloadable due to an unsolved error with packages. 


## HTML Hacking in Details

It is only possible to set one ImageIcon to a javax.swing.JLabel using the setIcon() method. However, it is possible to add a stream of String(s) to JLabel’s text using the setText() method. This built-in method only allows adding Strings vertically or horizontally in one line, but it was explored during the research that using “<html>” tags in the setText() method can add multiple lines into the text of Jlabel as it can recognise HTML “<br/>” tags etc. Furthermore, with a trial-error method, I discovered that using an unclosed “<html>” the JLabel text can recognize any HTML tags in the stream of Strings added to it. Therefore, it was possible to add images to any of the labels sourcing an API using HTTP CDN links in “<img> “ tags. Thus, in the program, all ships can have different graphics.

## Contributions and future plans

The project is open for contributions. Feel free to suggest any issues and improvements if you would like. It is planned to improve the GUI, e.g., a better menu when the game starts and an improved save, load and scoresheet menu. Moreover, it is in future plans to improve the core game to be able to manipulate the master ship and not only randomly allocate a position.  






