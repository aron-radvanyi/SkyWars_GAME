import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PlayTest {
	/*
	 * test if the getMasterShipPosition() gives back 2 coordinates of (row,square)
	 * and if the coordinates are the same where the ship actually is
	 */

	@Test
	void testGetMasterShipPosition() {
		Play theGame = new Play();
		Utility utility = new Utility();
		PlacerLogic logic = new PlacerLogic();
		ArrayList<int[]> possibleCoordinatesList = new ArrayList<>();
		int[] masterShipPossition = theGame.getMasterShipPosition();
		int exptMasterShipPossLength = 2;

		assertFalse(exptMasterShipPossLength != masterShipPossition.length);

		for (int i = 0; i > possibleCoordinatesList.size(); i++) {
			int[] possibleCoordinates = possibleCoordinatesList.get(i);
			ArrayList<AllyShip> theAllyShipsList = logic.getTheAllyShipsOnGrid(possibleCoordinates[0],
					possibleCoordinates[1]);
			for (AllyShip allyShip : theAllyShipsList) {
				int row = allyShip.getRow();
				int square = allyShip.getSquare();

				assertFalse(masterShipPossition[0] != row && masterShipPossition[1] != square);
			}
		}

	}

}
