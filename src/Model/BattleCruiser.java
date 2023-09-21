package Model;

public class BattleCruiser extends EnemyShipDecorator {
	/*
	 * url to visualise the enemyship with an icon from a CDN link
	 */
	private String imageURL = "<img src=\"https://icons.iconarchive.com/icons/jonathan-rey/star-wars-vehicles/48/Tie-Fighter-01-icon.png\" width=\"48\" height=\"48\">";

	public BattleCruiser(EnemyShip enemyShip, String type, int row, int square) {
		super(enemyShip, type, row, square);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.print("This is a BattleCruiser.");
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
