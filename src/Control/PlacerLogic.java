import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * This class has the logic to place and move the allyShips and EnemyShips on the grid. 
 */
public class PlacerLogic implements Serializable {
	private Grid theGrid = new Grid();
	private Utility utility = new Utility();

	/*
	 * to place ship randomly on any (row,square) position
	 */
	public void MasterShipPlacer() {
		int row, square;
		row = utility.RandomNumGenerator4();
		square = utility.RandomNumGenerator4();
		MasterShip masterShip = new MasterShip("Master Ship", row, square);

		masterShip.setShipMode(new DefensiveMode());

		theGrid.addAllyShipToGrid(masterShip, row, square);
	}

	/*
	 * to move ships to any neighbouring (row,square) position
	 */
	public void AllyShipMoving(int row, int square) {

		ArrayList<AllyShip> theAllyShips = this.theGrid.getAllyShipsFromgrid(row, square);

		// copy of the list to avoid ConcurrentModificationException
		List<AllyShip> AllyShipsCopy = new ArrayList<>(theAllyShips);
		// ships are moved if there are ships on that position
		if (this.theGrid.areThereAllyShips(row, square)) {
			for (AllyShip allyShip : AllyShipsCopy) {
				int nextRow, nextSquare;

				AllyShipMode shipMode = allyShip.getShipMode();

				this.theGrid.removeTheAllyShipFromGrid(allyShip, row, square);

				int[] theCoordinates = shipMovingGeneral(row, square);
				nextRow = theCoordinates[0];
				nextSquare = theCoordinates[1];

				this.theGrid.addAllyShipToGrid(allyShip, nextRow, nextSquare);
				allyShip.setRow(nextRow);
				allyShip.setSquare(nextSquare);
				allyShip.setShipMode(shipMode);
			}
		}
	}

	/*
	 * calls method from utility to place enemy ships randomly
	 */
	public void EnemyShipPlacer() {
		Ship ship = utility.randomWhichEnemyShip();
		theGrid.addEnemyShipToGrid((EnemyShip) ship, 1, 1);
	}

	/*
	 * to move enemy ships on the grid
	 */
	public void enemyShipMoving(int row, int square) {
		ArrayList<EnemyShip> theEnemyShips = this.theGrid.getTheEnemyShipsFromGrid(row, square);

		// copy of the list to avoid ConcurrentModificationException
		List<EnemyShip> enemyShipsCopy = new ArrayList<>(theEnemyShips);
		// ships are moved if there are ships on that position
		if (this.theGrid.areThereEnemyShips(row, square)) {
			for (EnemyShip enemyShip : enemyShipsCopy) {
				int nextRow, nextSquare;
				int[] theCoordinates = shipMovingGeneral(row, square);
				nextRow = theCoordinates[0];
				nextSquare = theCoordinates[1];

				this.theGrid.removeTheEnemeyShipFromGrid(enemyShip, row, square);
				this.theGrid.addEnemyShipToGrid(enemyShip, nextRow, nextSquare);
				enemyShip.setRow(nextRow);
				enemyShip.setSquare(nextSquare);

				String name = enemyShip.getType();
			}
		}
	}

	/*
	 * method to generate coordinates for moving any ship in general
	 */
	public int[] shipMovingGeneral(int currentRow, int currentSquare) {
		int nextRow, nextSquare;
		final int MAX_GRID_NUM = 4;
		final int MIN_GRID_NUM = 1;
		int[] theCoordinates;
		theCoordinates = new int[2];

		nextRow = currentRow + utility.RandNumGenOneOrMinusOne();
		nextSquare = currentSquare + utility.RandNumGenOneOrMinusOne();
		while (nextRow < MIN_GRID_NUM || nextRow > MAX_GRID_NUM || nextSquare < MIN_GRID_NUM
				|| nextSquare > MAX_GRID_NUM) {
			nextRow = currentRow + utility.RandNumGenOneOrMinusOne();
			nextSquare = currentSquare + utility.RandNumGenOneOrMinusOne();
		}
		theCoordinates[0] = nextRow;
		theCoordinates[1] = nextSquare;
		return theCoordinates;
	}

	public void removeAllAllyShipsOnGrid(int row, int square) {
		this.theGrid.removeAllAllyShipsFromGrid(row, square);
	}

	public void removeAllEnemyShipsOnGrid(int row, int square) {
		this.theGrid.removeAllEnemyShipsFromGrid(row, square);
	}

	public ArrayList<AllyShip> getTheAllyShipsOnGrid(int row, int square) {
		return this.theGrid.getAllyShipsFromgrid(row, square);
	}

	public ArrayList<EnemyShip> getTheEnemyShipsOnGrid(int row, int square) {
		return this.theGrid.getTheEnemyShipsFromGrid(row, square);
	}

	public boolean areThereAllyShipsOnThisGrid(int row, int square) {
		return this.areThereAllyShipsOnThisGrid(row, square);
	}

	public boolean areThereEnemyShipsOnThisGrid(int row, int square) {
		return this.areThereEnemyShipsOnThisGrid(row, square);
	}

}// end of class
