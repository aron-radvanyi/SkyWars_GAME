
public abstract class AllyShip extends Ship {

	private String type;
	protected AllyShipMode shipMode;
	
	//constructor
	public AllyShip(String type, int row, int square) {
		super(row, square);
		setType(type);

	}
	
	//method to make the AllyShip be able to choose def/off mode in runtime 
	public void performAllyShipMode() {
		this.shipMode.AllyShipMode();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AllyShipMode getShipMode() {
		return shipMode;
	}

	public void setShipMode(AllyShipMode shipMode) {
		this.shipMode = shipMode;
	}

}
