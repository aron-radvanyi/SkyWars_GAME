package Control;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.ImageIcon;

import Model.EnemyShip;
import Model.BattleCruiser;
import Model.BattleShooter;
import Model.BattleStar;
import Model.EnemyShip;
import Model.BasicEnemyShip;

/*
 * The Utility class is a helper class for all other classes by facilitating methods which are comonly used more than once.
 * It helps mainly the Play, PlacerLogic and GraficalInterface classes. 
 */
public class Utility implements Serializable {

	Random random = new Random();

	// method to declare random number
	public int RandomNumGenerator4() {
		int randomInt = random.nextInt(4);
		return randomInt + 1;
		// +1 becasue random.nextInt(10) will generate int between 0-3, and we need
		// range 1-4
	}

	// method to generate -1/1/0 for ship moving
	public int RandNumGenOneOrMinusOne() {
		int randomNum = -1 + randomNextInt3();
		return randomNum;
	}

	public int randomNextInt3() {
		return random.nextInt(3);
	}

	public EnemyShip randomWhichEnemyShip() {
		int number = randomNextInt3();
		if (number == 0) {
			EnemyShip battleStar = new BattleStar(new BasicEnemyShip("BattleStar", 1, 1), "BattleStar", 1, 1);
			return battleStar;
		} else if (number == 1) {
			EnemyShip battleCruiser = new BattleCruiser(new BasicEnemyShip("BattleCruiser", 1, 1), "BattleCruiser", 1,
					1);
			return battleCruiser;
		} else {
			EnemyShip battleShooter = new BattleShooter(new BasicEnemyShip("BattleShooter", 1, 1), "BattleShooter", 1,
					1);
			return battleShooter;
		}
	}

	/*
	 * all galaxy icons in the resources directory have their names
	 * "galaxy(1 to 16).png" Therefore using a foor loop the String.format()
	 * function can be used to change the string holding the
	 * "resources/galaxy(n).png" directory by adding the number of iteration(i) to
	 * the (n). %d means that there is 1 decimal number.
	 */
	public ArrayList<ImageIcon> getGalaxyPictures() {
		ArrayList<ImageIcon> theGalaxyIcons = new ArrayList<>();
		for (int i = 1; i <= 16; i++) {
			String file = String.format("/resources/galaxy%d.png", i);
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(file));
			theGalaxyIcons.add(imageIcon);
		}
		return theGalaxyIcons;
	}

	/*
	 * gives back an ArrayList with all possible coordinates change
	 * MAX_ROWS/MAX_SQUARES if the possible coordinates changed
	 */
	public ArrayList<int[]> getListPossibleCoordinates() {
		ArrayList<int[]> possibleCoordinates = new ArrayList<>();

		final int MAX_ROWS = 4;
		final int MAX_SQUARES = 4;

		for (int i = 1; i <= MAX_ROWS; i++) {
			for (int j = 1; j <= MAX_SQUARES; j++) {
				int[] theCoordinate = new int[2];
				theCoordinate[0] = i;
				theCoordinate[1] = j;
				possibleCoordinates.add(theCoordinate);
			}
		}
		return possibleCoordinates;
	}

}
