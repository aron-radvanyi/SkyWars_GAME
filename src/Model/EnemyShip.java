public abstract class EnemyShip extends Ship {

	private String type;	
	
	public EnemyShip(String type,int row, int square) {
		super(row, square);	
		setType(type);
	}

	public String getType() {	
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void assemble() {
		
	}	
	

}
