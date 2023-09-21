package Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.AllyShip;
import Model.AllyShipMode;
import Model.DefensiveMode;

class AllyShipTest {

	@Test
	void testGetType() {
		AllyShip allyShip = new AllyShip("Mastership", 1, 1) {
		};

		String name = "Mastership";
		allyShip.setType(name);

		String actualName = allyShip.getType();
		String expectedName = name;

		assertTrue(actualName == expectedName);

	}

	@Test
	void testGetShipMode() {
		AllyShip allyShip = new AllyShip("Mastership", 1, 1) {
		};

		AllyShipMode shipMode = new DefensiveMode();
		allyShip.setShipMode(shipMode);

		AllyShipMode actualShipMode = shipMode;
		AllyShipMode expectedShipMode = allyShip.getShipMode();

		assertTrue(actualShipMode == expectedShipMode);

	}

}
