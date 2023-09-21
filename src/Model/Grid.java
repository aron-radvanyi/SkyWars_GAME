package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Grid implements Serializable {
	private ArrayList<Row> Rows = new ArrayList<Row>();
	private final int NUM_OF_ROWS = 4;

	// constructor
	public Grid() {
		Row temporaryRow;
		for (int i = 1; i <= this.NUM_OF_ROWS; i++) {
			temporaryRow = new Row(i);
			this.Rows.add(temporaryRow);
		}
	}// end Grid	

	// method to determine if there is a MasterShip on square
	public boolean areThereAllyShips(int row, int square) {
		for (Row temporaryRow : this.Rows) {
			if (temporaryRow.getPosition() == row) {
				// found the correct row
				for (Square tempSquare : temporaryRow.getSquares()) {
					if (tempSquare.getPosition() == square) {
						// found the correct square
						return tempSquare.areThereAllyShips();
					}
				}
			}
		}
		return false;
	}// end of the isThereAShip

	// method to determine if there is a EnemyShip on square
	public boolean areThereEnemyShips(int row, int square) {
		for (Row temporaryRow : this.Rows) {
			if (temporaryRow.getPosition() == row) {
				// found the correct row
				for (Square tempSquare : temporaryRow.getSquares()) {
					if (tempSquare.getPosition() == square) {
						// found the correct square
						return tempSquare.areThereAllyShips();
					}
				}
			}
		}
		return false;
	}// end of the isThereAShip
	
	// to add AllyShip to the grid
	public void addAllyShipToGrid(AllyShip allyShip, int row, int square) {
		for (Row temporaryRow : this.Rows) {
			if (temporaryRow.getPosition() == row) {
				// found correct row
				for (Square tempSquare : temporaryRow.getSquares()) {
					if (tempSquare.getPosition() == square) {
						// found correct square
						tempSquare.addAllyShipToSquare(allyShip);
					}
				}
			}
		}
	}// end of addShipToGrid

	//to add EnemyShip to teh grid
	public void addEnemyShipToGrid(EnemyShip enemyShip, int row, int square) {
		for (Row temporaryRow : this.Rows) {
			if (temporaryRow.getPosition() == row) {
				// found correct row
				for (Square tempSquare : temporaryRow.getSquares()) {
					if (tempSquare.getPosition() == square) {
						// found correct square
						tempSquare.addEnemyShipToSquare(enemyShip);
					}
				}
			}
		}
	}// end of addShipToGrid
	
	//to set remove the allyship from the grid
	public void removeTheAllyShipFromGrid(AllyShip allyShip, int row, int square) {
		for (Row temporaryRow : this.Rows) {
			if (temporaryRow.getPosition() == row) {
				// found correct row
				for (Square tempSquare : temporaryRow.getSquares()) {
					if (tempSquare.getPosition() == square) {
						// found correct square
						tempSquare.removeTheAllyShip(allyShip);
					}
				}
			}
		}
	}
	
	// removing all allyships
		public void removeAllAllyShipsFromGrid(int row, int square) {
			for (Row temporaryRow : this.Rows) {
				if (temporaryRow.getPosition() == row) {
					// found correct row
					for (Square tempSquare : temporaryRow.getSquares()) {
						if (tempSquare.getPosition() == square) {
							// found correct square
							tempSquare.removeAllAllyShips();
						}
					}
				}
			}
		}
	

	// removing all enemy ships
	public void removeAllEnemyShipsFromGrid(int row, int square) {
		for (Row temporaryRow : this.Rows) {
			if (temporaryRow.getPosition() == row) {
				// found correct row
				for (Square tempSquare : temporaryRow.getSquares()) {
					if (tempSquare.getPosition() == square) {
						// found correct square
						tempSquare.removeAllEnemyShips();
					}
				}
			}
		}
	}
	
	// getting the ally ships
		public ArrayList<AllyShip> getAllyShipsFromgrid(int row, int square) {
			for (Row temporaryRow : this.Rows) {
				if (temporaryRow.getPosition() == row) {
					// found correct row
					for (Square tempSquare : temporaryRow.getSquares()) {
						if (tempSquare.getPosition() == square) {
							// found correct square
							return tempSquare.getAllyShips();
						}
					}
				}
			}
			return null;
		}

	// getting the enemy ships
	public ArrayList<EnemyShip> getTheEnemyShipsFromGrid(int row, int square) {
		for (Row temporaryRow : this.Rows) {
			if (temporaryRow.getPosition() == row) {
				// found correct row
				for (Square tempSquare : temporaryRow.getSquares()) {
					if (tempSquare.getPosition() == square) {
						// found correct square
						return tempSquare.getTheEnemeyShips();
					}
				}
			}
		}
		return null;
	}

	// removing the enemy ships from the grid, setting the Arraylist on the square empty
	public void removeTheEnemeyShipFromGrid(EnemyShip enemyShip, int row, int square) {
		for (Row temporaryRow : this.Rows) {
			if (temporaryRow.getPosition() == row) {
				// found correct row
				for (Square tempSquare : temporaryRow.getSquares()) {
					if (tempSquare.getPosition() == square) {
						// found correct square
						tempSquare.removeTheEnemyShip(enemyShip);
					}
				}
			}
		}
	}

}
