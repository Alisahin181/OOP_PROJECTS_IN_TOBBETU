import java.io.Serializable;

public abstract class AbstractItem implements ItemInterface,Serializable {
	
	private String position;  // tahtadaki konumu gosterir. ornegin, a1

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		
		this.position = position;
	}
	

	
}
