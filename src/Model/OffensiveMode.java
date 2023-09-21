package Model;
import java.io.Serializable;

public class OffensiveMode implements AllyShipMode,Serializable {

	@Override
	public String AllyShipMode() {
		String shipMode = "offensive";
		return shipMode;
	}

	
}
