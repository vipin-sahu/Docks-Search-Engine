package AfterCompleteSpeed;

import java.io.Serializable;

public class Docks implements Serializable {
	public String dockName;
	public int frequency;
	
	public Docks(String docks, int frequency){
		this.dockName = docks;
		this.frequency = frequency;
	}
}
