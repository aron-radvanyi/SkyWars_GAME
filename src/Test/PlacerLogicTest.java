package Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Control.PlacerLogic;
import Control.Utility;
import Model.AllyShip;
import Model.EnemyShip;

class PlacerLogicTest {
	/*
	 * testing mastership placer
	 */
	@Test
	void testMasterShipPlacer() {
		PlacerLogic logic = new PlacerLogic();
		Utility utility = new Utility();
		ArrayList<int[]> possibleCoordinatesList = utility.getListPossibleCoordinates();
		logic.MasterShipPlacer();

		/*
		 * testing if allyship is placed by initialising the allyship and getting the
		 * arraylist of allyships on each possible coordinate if the size of teh
		 * arraylist is less than 1, there is no allyship initialised
		 */
		for (int i = 0; i > possibleCoordinatesList.size(); i++) {
			int[] possibleCoordinates = possibleCoordinatesList.get(i);

			ArrayList<AllyShip> theAllyShips = logic.getTheAllyShipsOnGrid(possibleCoordinates[0],
					possibleCoordinates[1]);
			int expectedTheAllyShipsSize = 1;
			assertFalse(expectedTheAllyShipsSize != theAllyShips.size());

		}
	}
	
	/*
	 * tests whether the enemyships are placed on the Grid
	 * and if it placed on teh right position
	 */

	@Test
	void testEnemyShipPlacer() {
		PlacerLogic logic = new PlacerLogic();
		int row = 1;
		int square = 1;
		logic.EnemyShipPlacer();

		// testing if enemyShips are placed
		ArrayList<EnemyShip> theEnemyShips = logic.getTheEnemyShipsOnGrid(row, square);
		int expectedTheEnemyShipsSize = 1;
		assertFalse(theEnemyShips.size() != expectedTheEnemyShipsSize);
		
		//testing if it placed it on (1,1)
		int actualEnemyShipRow;
		int actualEnemyShipSquare;
		for (EnemyShip enemyShip : theEnemyShips) {
			actualEnemyShipRow = enemyShip.getRow();
			actualEnemyShipSquare = enemyShip.getSquare();
			assertFalse(row != actualEnemyShipRow && square != actualEnemyShipSquare);
		}
	}
	
	
	/*
	 * testing if the testShipMovingGeneral() gives back possible coordinates
	 */
	@Test
	void testShipMovingGeneral() {
		PlacerLogic logic = new PlacerLogic();
		int row = 1;
		int square = 1;
		int[] expectedCoordinates = logic.shipMovingGeneral(row, square);

		final int MAX_GRID_NUM = 4;
		final int MIN_GRID_NUM = 1;

		// testing if the expectedCoordinates are within the min 1 and max 4 range
		assertFalse(expectedCoordinates[0] < MIN_GRID_NUM && expectedCoordinates[0] > MAX_GRID_NUM);
		assertFalse(expectedCoordinates[1] < MIN_GRID_NUM && expectedCoordinates[1] > MAX_GRID_NUM);

	}

}
