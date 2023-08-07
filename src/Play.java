import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * The Play class facilitates the main game. 
 * Ship initialisation, moving, placing and ship interaction/fight is happening here using 
 * the logic from the PlacerLogic class and using the Utility class as a helper for commonly used methods. 
 */
public class Play implements Observable, Serializable {

	Play() {
	};

	private ArrayList<Scores> theScores = new ArrayList<Scores>();
	private PlacerLogic logic = new PlacerLogic();
	private Utility utility = new Utility();
	private int counterPoints = 0;

	/*
	 * observer methods
	 */
	public void registerObserver(Scores scores) {
		this.theScores.add(scores);
	}

	public void removeObserver(Scores score) {
		this.theScores.remove(score);
	}

	public void notifyObserver() {
		for (Scores scores : this.theScores) {
			scores.update(counterPoints);
		}
	}

	/*
	 * to call method to place the MasterShip on the grid
	 */
	public void initializeMasterShip() {
		logic.MasterShipPlacer();
	}

	/*
	 * to move the masterShip on the Grid
	 */
	public void masterShipMoving() {
		int[] theCoordinates = getMasterShipPosition();

		int row = theCoordinates[0];
		int square = theCoordinates[1];

		logic.AllyShipMoving(row, square);
	}

	/*
	 * to get the coordinates of the masterShip
	 */
	public int[] getMasterShipPosition() {
		int[] theCoordinates = new int[2];
		ArrayList<AllyShip> allyShips = new ArrayList<>();

		int[] rows = { 1, 2, 3, 4 };
		int[] squares = { 1, 2, 3, 4 };

		for (int rowNumber : rows) {
			for (int squareNumber : squares) {

				ArrayList<AllyShip> theAllyShipsPlaying = logic.getTheAllyShipsOnGrid(rowNumber, squareNumber);
				allyShips.addAll(theAllyShipsPlaying);
			}
		}
		for (AllyShip allyShip : allyShips) {
			if (allyShip.getType().equals("Master Ship")) {
				theCoordinates[0] = allyShip.getRow();
				theCoordinates[1] = allyShip.getSquare();
				break;
			}
		}
		return theCoordinates;
	}

	/*
	 * changes the ShipMode of the MasterShip to offensive/defensive mode respc. to
	 * its previous state
	 */
	public void changeMastershipMode() {
		int[] theCoordinates = getMasterShipPosition();
		int row = theCoordinates[0];
		int square = theCoordinates[1];

		ArrayList<AllyShip> theAllyShipsPlaying = logic.getTheAllyShipsOnGrid(row, square);

		for (AllyShip allyShip : theAllyShipsPlaying) {
			if (allyShip.getType().equals("Master Ship")) {

				if (allyShip.getShipMode().AllyShipMode().equals("defensive")) {
					allyShip.setShipMode(new OffensiveMode());
				} else {
					allyShip.setShipMode(new DefensiveMode());
				}
			}
		}
	}

	/*
	 * to get the shipMode of the MasterShip as String
	 */
	public String getMastersShipmModeAsString(int row, int square) {
		String masterShipMode = " Ship Mode: ";

		ArrayList<AllyShip> theAllyShipsPlaying = logic.getTheAllyShipsOnGrid(row, square);

		for (AllyShip allyShip : theAllyShipsPlaying) {
			if (allyShip.getType().equals("Master Ship")) {
				masterShipMode = masterShipMode + allyShip.getShipMode().AllyShipMode();
			}
		}
		return masterShipMode;
	}

	/*
	 * to initialise enemyShips
	 */
	public void initializeEnemyShip() {
		// 1 in 3 chance that enemy ship will occur
		int random0to2 = utility.randomNextInt3();

		if (random0to2 == 0) {
			logic.EnemyShipPlacer();
		}

	}

	/*
	 * to get the Arraylist of AllEnemyShipPositions and iterate through its
	 * treeMaps and move the EnemyShips on that position
	 */
	public void enemyShipMoving() {
		ArrayList<HashMap<EnemyShip, int[]>> allEnemyShipsAndPositions = getAllEnemyShipsAndPositions();
		for (HashMap<EnemyShip, int[]> treeMap : allEnemyShipsAndPositions) {
			for (Map.Entry<EnemyShip, int[]> entry : treeMap.entrySet()) {
				EnemyShip enemyShip = entry.getKey();
				int[] theCoordinates = entry.getValue();
				int row = theCoordinates[0];
				int square = theCoordinates[1];

				logic.enemyShipMoving(row, square);
			}
		}
	}

	/*
	 * to get arrayList of all Enemy ships and their positions
	 */
	public ArrayList<HashMap<EnemyShip, int[]>> getAllEnemyShipsAndPositions() {
		ArrayList<HashMap<EnemyShip, int[]>> allEnemyShipsAndPositions = new ArrayList<>();

		int[] rows = { 1, 2, 3, 4 };
		int[] squares = { 1, 2, 3, 4 };

		for (int row : rows) {
			for (int square : squares) {
				HashMap<EnemyShip, int[]> enemyShipsAndCoordinates = new HashMap<EnemyShip, int[]>();
				ArrayList<EnemyShip> theEnemyShipsOnThePosition = logic.getTheEnemyShipsOnGrid(row, square);

				for (EnemyShip enemyShip : theEnemyShipsOnThePosition) {
					int[] theCoordinates = new int[2];
					theCoordinates[0] = enemyShip.getRow();
					theCoordinates[1] = enemyShip.getSquare();

					enemyShipsAndCoordinates.put(enemyShip, theCoordinates);
				}
				allEnemyShipsAndPositions.add(enemyShipsAndCoordinates);
			}
		}
		return allEnemyShipsAndPositions;
	}

	/*
	 * returns true if there is a ship collision on the grid ergo shipfight adds 1
	 * point to the counter if Mastership destroyed enemy ships DEFMODE_DESTR_MASTER
	 * the number of enemyships to destroy mastership if in defensive mode
	 * OFMODE_DESTR_MASTER the number of enemyships to destroy mastership if in
	 * offensive mode
	 */
	public boolean isThereShipFight(int row, int square) {
		ArrayList<AllyShip> allyShipList = getListOfAllyShips(row, square);
		ArrayList<EnemyShip> enemyShipList = getListOfEnemyShips(row, square);
		final int DEFMODE_DESTR_MASTER = 2;
		final int OFMODE_DESTR_MASTER = 3;
		final String COORDINATES_STRING = " on: (" + row + "," + square + ")";
		String enemyShips = "";

		// printing the enemyships
		for (EnemyShip enemyShip : enemyShipList) {
			enemyShips = enemyShips + " " + enemyShip.getType();
		}
		// checking for collision and shipMode
		for (int i = 0; i < allyShipList.size(); i++) {
			AllyShip allyShip = allyShipList.get(i);
			if (allyShip.getShipMode().AllyShipMode().equals("defensive")) {
				if (enemyShipList.size() >= DEFMODE_DESTR_MASTER) {
					logic.removeAllAllyShipsOnGrid(row, square);
					JOptionPane.showMessageDialog(null, "GAME OVER" + enemyShips + " destroyed mastership:"
							+ COORDINATES_STRING + "\n" + "points: " + this.counterPoints);
					return true;
				}
				if (enemyShipList.size() == 0) {
					break;
				} else {// there are less than 2 enemyships in defensive mode
					logic.removeAllEnemyShipsOnGrid(row, square);
					JOptionPane.showMessageDialog(null, "Mastership destroyed " + enemyShips + COORDINATES_STRING);
					this.counterPoints = this.counterPoints + 1;
					return true;
				}
			}
			if (allyShip.getShipMode().AllyShipMode().equals("offensive")) {
				if (enemyShipList.size() >= OFMODE_DESTR_MASTER) {
					logic.removeAllAllyShipsOnGrid(row, square);
					JOptionPane.showMessageDialog(null, "GAME OVER" + enemyShips + " destroyed mastership:"
							+ COORDINATES_STRING + "\n" + "points: " + this.counterPoints);
					return true;
				}
				if (enemyShipList.size() == 0) {
					break;
				} else {// there are less than 3 enemyships in offensive mode
					logic.removeAllEnemyShipsOnGrid(row, square);
					JOptionPane.showMessageDialog(null, "Mastership destroyed " + enemyShips + COORDINATES_STRING);
					this.counterPoints = this.counterPoints + 1;
					return true;
				}
			}
			break;
		}
		return false;
	}

	public ArrayList<AllyShip> getListOfAllyShips(int row, int square) {
		return logic.getTheAllyShipsOnGrid(row, square);
	}

	public ArrayList<EnemyShip> getListOfEnemyShips(int row, int square) {
		return logic.getTheEnemyShipsOnGrid(row, square);
	}

	public int getCounterPoints() {
		return counterPoints;
	}

	public void setCounterPoints(int counterPoints) {
		this.counterPoints = counterPoints;
	}

}
