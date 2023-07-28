import java.io.Serializable;

public class Player implements Serializable{
	
	float puan; // her taS yedikçe oyuncunun puani taSin puanina gore artar.
	private String name;
	private String color;
	
	
	public Player( String name, String color) {
		
		this.name=new String(name);
		this.color=new String(color);
		this.puan=0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	

}
