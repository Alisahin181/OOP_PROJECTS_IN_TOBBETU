
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class DrawingCanvas extends JComponent{
	
	private int sayi[][];
	private int x;

	
	public DrawingCanvas() {
		
		//this.setPreferredSize(new Dimension(500,500));
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2d=(Graphics2D)g; 
		g2d.setPaint(Color.blue);
		
		//drawing 0
		
		g2d.setStroke(new BasicStroke(5));
		
		
		for(int i=0;i<10;i++) {
			
			if(sayi[i][0]==-1) {
				
				
			}
			
			else {
				
				x=2*i;
				
				switch(sayi[i][0]) {
				
				case 0:
					g2d.drawLine(0+(39*x), 0, 50+(39*x), 0);
					g2d.drawLine(50+(39*x), 0, 50+(39*x), 120);
					g2d.drawLine(50+(39*x), 120, 0+(39*x), 120);
					g2d.drawLine(0+(39*x), 120, 0+(39*x), 0);
					break;
				
				case 1:
					//drawing 1
					
					g2d.drawLine(20+(39*x), 0, 20+(39*x), 120);
					break;
				
				case 2:
					
					//drawing2
					
					g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
					g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 0+(39*x), 180);
					g2d.drawLine(0+(39*x), 180, 0+(39*x), 240);
					g2d.drawLine(0+(39*x), 240, 50+(39*x), 240);
					break;
					
				case 3:
					
					//drawing3
					
					g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
					g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 0+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
					g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
					
					break;
				
				case 4:
					//drawing4
					
					g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
					g2d.drawLine(0+(39*x), 180, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
					
					break;
					
				case 5:
					
					//drawing5
					
					g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
					g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
					g2d.drawLine(0+(39*x), 180, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
					g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
					
					break;
					
				case 6:
					
					
					//drawing6
					
					g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
					g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
					g2d.drawLine(0+(39*x), 180, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
					g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
					g2d.drawLine(0+(39*x), 180, 0+(39*x), 240);
					
					break;
				
				case 7:
					
					//drawing7
					
					
					g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
					g2d.drawLine(50+(39*x), 120, 50+(39*x), 240);
					
					break;
					
				case 8:
					
					//drawing8
					
					g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
					g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
					g2d.drawLine(0+(39*x), 180, 0+(39*x), 240);
					g2d.drawLine(0+(39*x), 180, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
					g2d.drawLine(0+(39*x), 240, 50+(39*x), 240);
					
					break;
					
				case 9:
					
					//drawing9
					
					g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
					g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 0+(39*x), 180);
					g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
					g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
					g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
					
					break;
				
				}
					
				
			}
			
			
		}
		
		
		
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	

	public int[][] getSayi() {
		return sayi;
	}

	public void setSayi(int sayi[][]) {
		this.sayi = sayi;
	}
}