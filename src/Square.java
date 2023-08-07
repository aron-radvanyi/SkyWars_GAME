import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Square implements Serializable {
	private int position;
	private ArrayList<EnemyShip> theEnemeyShips = new ArrayList<EnemyShip>();
	private ArrayList<AllyShip> theAllyShips = new ArrayList<AllyShip>();

	// constructor
	public Square(int position) {
		setPosition(position);
	}

	// getters and setters square
	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	// methods ships
	public boolean areThereAllyShips() {
		if (theAllyShips == null) {
			return false;
		}
		return true;
	}

	public boolean areThereEnemyShips() {
		if (theEnemeyShips == null) {
			return false;
		}
		return true;
	}

	// takes the AllyShip/EnemyShip and adds to the arrayList
	public void addAllyShipToSquare(AllyShip theAllyShip) {
		this.theAllyShips.add(theAllyShip);
	}

	public void addEnemyShipToSquare(EnemyShip theEnemyShip) {
		this.theEnemeyShips.add(theEnemyShip);
	}

	// removes AllyShip or EnemyShip from the arrayList
	public void removeTheAllyShip(AllyShip theAllyShip) {
		this.theAllyShips.remove(theAllyShip);
	}

	public void removeTheEnemyShip(EnemyShip theEneemyShip) {
		this.theEnemeyShips.remove(theEneemyShip);
	}

	public void removeAllAllyShips() {
		this.theAllyShips.removeAll(theAllyShips);
	}

	public void removeAllEnemyShips() {
		this.theEnemeyShips.removeAll(theEnemeyShips);
	}

	public ArrayList<AllyShip> getAllyShips() {
		return this.theAllyShips;
	}

	public ArrayList<EnemyShip> getTheEnemeyShips() {
		return this.theEnemeyShips;
	}

	public void setAllyShips(ArrayList<AllyShip> theAllyShips) {
		this.theAllyShips = theAllyShips;
	}

	public void setTheEnemeyShips(ArrayList<EnemyShip> theEnemeyShips) {
		this.theEnemeyShips = theEnemeyShips;
	}
	// end of class
}
