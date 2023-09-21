package Model;

public class MasterShip extends AllyShip {
	/*
	 * url to visualise the mastership with an icon from a CDN link
	 */
	private String imageURL = "<img src=\"https://icons.iconarchive.com/icons/jonathan-rey/star-wars-vehicles/48/X-Wing-02-icon.png\" width=\"48\" height=\"48\">";

	//constructor
	public MasterShip(String type, int row, int square) {
		super(type, row, square);	
		this.shipMode = new DefensiveMode();
		this.shipMode = new OffensiveMode();
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
