import java.io.Serializable;

public class DefensiveMode implements AllyShipMode,Serializable {

	@Override
	public String AllyShipMode() {
		String shipMode = "defensive";
		return shipMode;

	}

}
