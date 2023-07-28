
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Main extends JFrame {

	private JPanel contentPane;
	
	private String girilenSayi="";
	double birinciSayi=0;
	double ikinciSayi=0;
	String islem="";
	double sonuc=0;
	private String sayi[][];
	private int x;
	private String yazdirilacakSayi="";
	private boolean islemYapildiMi = false;
	private boolean noktaMi = false;
	boolean esittirCalistiMi=false;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//ekranaYaz("123");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					
					
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	/*
	public static void ekranaYaz(String sayi) {
		
		DrawingCanvas dc=new DrawingCanvas();
		
		int lengthSayi=sayi.length();
		
		
		
		int sayiDizi[][]=new int[10][1];
		
		for(int i=0;i<10-lengthSayi;i++) {
			
			sayiDizi[i][0]=-1;
			
		}
		
		int count=0;
		for(int i=10-lengthSayi;i<10;i++) {
			
			sayiDizi[i][0]=Integer.parseInt(sayi.substring(count,count+1));
			count++;
		}
		
		count=0;
		
		dc.setSayi(sayiDizi);
		
		

		
		panel_1.add(dc);
		System.out.println("Neden olmuyor");
		dc.repaint();
		panel_1.setVisible(true);
		
		  
		
		
	    
	}
	*/
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	

	public String[][] getSayi() {
		return sayi;
	}

	public void setSayi(String sayi[][]) {
		this.sayi = sayi;
	}
	
	public String getGirilenSayi() {
		
		
		return girilenSayi;
	}
	
	public void setGirilenSayi(String sayi) {
		
		this.girilenSayi=sayi;
		
	}
	
		
		public void paint(Graphics g) {
			
			Graphics2D g2d=(Graphics2D)g; 
			g2d.setPaint(Color.blue);
			
			//drawing 0
			g2d.clearRect(0, 0,800 , 345);
			
			g2d.setStroke(new BasicStroke(5));
			
			int lengthSayi=yazdirilacakSayi.length();
			
			
			
			String sayiDizi[][]=new String[10][1];
			
			for(int i=0;i<10-lengthSayi;i++) {
				
				sayiDizi[i][0]="-1";
				
			}
			
			int count=0;
			for(int i=10-lengthSayi;i<10;i++) {
				
				sayiDizi[i][0]=yazdirilacakSayi.substring(count,count+1);
				count++;
			}
			
			count=0;
			
			setSayi(sayiDizi);
			
			
			
			
			for(int i=0;i<10;i++) {
				
				if(sayi[i][0].equals("-1")) {
					
					
				}
				
				else {
					
					int x=2*i;
					
					switch(sayi[i][0]) {
					
					case "0":
						g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
						g2d.drawLine(50+(39*x), 120, 50+(39*x), 240);
						g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
						g2d.drawLine(0+(39*x), 240, 0+(39*x), 120);
						break;
					
					case "1":
						//drawing 1
						
						g2d.drawLine(20+(39*x), 120, 20+(39*x), 240);
						break;
					
					case "2":
						
						//drawing2
						
						g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
						g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 0+(39*x), 180);
						g2d.drawLine(0+(39*x), 180, 0+(39*x), 240);
						g2d.drawLine(0+(39*x), 240, 50+(39*x), 240);
						break;
						
					case "3":
						
						//drawing3
						
						g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
						g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 0+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
						g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
						
						break;
					
					case "4":
						//drawing4
						
						g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
						g2d.drawLine(0+(39*x), 180, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
						
						break;
						
					case "5":
						
						//drawing5
						
						g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
						g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
						g2d.drawLine(0+(39*x), 180, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
						g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
						
						break;
						
					case "6":
						
						
						//drawing6
						
						g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
						g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
						g2d.drawLine(0+(39*x), 180, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
						g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
						g2d.drawLine(0+(39*x), 180, 0+(39*x), 240);
						
						break;
					
					case "7":
						
						//drawing7
						
						
						g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
						g2d.drawLine(50+(39*x), 120, 50+(39*x), 240);
						
						break;
						
					case "8":
						
						//drawing8
						
						g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
						g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
						g2d.drawLine(0+(39*x), 180, 0+(39*x), 240);
						g2d.drawLine(0+(39*x), 180, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
						g2d.drawLine(0+(39*x), 240, 50+(39*x), 240);
						
						break;
						
					case "9":
						
						//drawing9
						
						g2d.drawLine(0+(39*x), 120, 50+(39*x), 120);
						g2d.drawLine(50+(39*x), 120, 50+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 0+(39*x), 180);
						g2d.drawLine(0+(39*x), 120, 0+(39*x), 180);
						g2d.drawLine(50+(39*x), 180, 50+(39*x), 240);
						g2d.drawLine(50+(39*x), 240, 0+(39*x), 240);
						
						break;
						
					case ".":
						
						g2d.fillOval(25+(39*x), 200, 10, 10);
						
						break;
						
					case "-":
						g2d.drawLine(25+(39*x),180 , 50+(39*x), 180);
						break;
											
					}
					
					/*
					if(noktaMi) {
						g2d.fillOval(25+(39*x), 200, 10, 10);
						noktaMi = false;
					}
					*/
					
						
					
				}
				
				
			}
		
	}
	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 274, 786, 655);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton buton0 = new JButton("0");
		buton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
					
					setGirilenSayi(getGirilenSayi().concat("0"));
					setYazdirilacakSayi(getGirilenSayi());
					
					repaint();
					//System.out.println(getGirilenSayi());
					
					//ekranaYaz(getGirilenSayi());
					
				
			}
		});
		
		
		buton0.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton0.setBounds(21, 534, 145, 94);
		panel.add(buton0);
		
		JButton buton1 = new JButton("1");
		buton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
				setGirilenSayi(getGirilenSayi().concat("1"));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
				
				

			}
		});
		
		buton1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton1.setBounds(21, 409, 145, 94);
		panel.add(buton1);
		
		JButton buton4 = new JButton("4");
		buton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
				setGirilenSayi(getGirilenSayi().concat("4"));
				
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		buton4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton4.setBounds(21, 290, 145, 94);
		panel.add(buton4);
		
		JButton buton7 = new JButton("7");
		buton7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				setGirilenSayi(getGirilenSayi().concat("7"));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		buton7.setBounds(21, 175, 145, 94);
		panel.add(buton7);
		
		JButton butonC = new JButton("C");
		butonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setGirilenSayi("");
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
				sonuc=0;
				birinciSayi=0;
				ikinciSayi=0;
				islem="";
			}
		});
		butonC.setFont(new Font("Tahoma", Font.PLAIN, 40));
		butonC.setBounds(21, 39, 755, 94);
		panel.add(butonC);
		
		JButton buton8 = new JButton("8");
		buton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
				setGirilenSayi(getGirilenSayi().concat("8"));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		buton8.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton8.setBounds(228, 175, 145, 94);
		panel.add(buton8);
		
		JButton buton9 = new JButton("9");
		buton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
				setGirilenSayi(getGirilenSayi().concat("9"));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		buton9.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton9.setBounds(429, 175, 145, 94);
		panel.add(buton9);
		
		
		JButton butonArtý = new JButton("+");
		butonArtý.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				esittirCalistiMi=false;
				//System.out.println(girilenSayi);
				setYazdirilacakSayi(getGirilenSayi());
				//repaint();
				birinciSayi=Double.parseDouble(girilenSayi);
				
				setGirilenSayi("");
				islem="toplama";
			}
		});
		butonArtý.setFont(new Font("Tahoma", Font.PLAIN, 30));
		butonArtý.setBounds(631, 175, 145, 94);
		panel.add(butonArtý);
		
		JButton buton5 = new JButton("5");
		buton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
				
				setGirilenSayi(getGirilenSayi().concat("5"));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		buton5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton5.setBounds(228, 290, 145, 94);
		panel.add(buton5);
		
		JButton buton6 = new JButton("6");
		buton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
				setGirilenSayi(getGirilenSayi().concat("6"));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		buton6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton6.setBounds(429, 290, 145, 94);
		panel.add(buton6);
		
		
		JButton buton2 = new JButton("2");
		buton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
				setGirilenSayi(getGirilenSayi().concat("2"));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		buton2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton2.setBounds(228, 409, 145, 94);
		panel.add(buton2);
		
		JButton buton3 = new JButton("3");
		buton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(esittirCalistiMi) {
					
					girilenSayi="";
				}
				esittirCalistiMi=false;
				
				setGirilenSayi(getGirilenSayi().concat("3"));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		buton3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buton3.setBounds(429, 409, 145, 94);
		panel.add(buton3);
		
		JButton butonCarpma = new JButton("x");
		butonCarpma.setFont(new Font("Tahoma", Font.PLAIN, 35));
		butonCarpma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esittirCalistiMi=false;
				birinciSayi=Double.parseDouble(girilenSayi);
				girilenSayi="";
				islem="carpma";
				
			}
		});
		butonCarpma.setBounds(631, 409, 145, 94);
		panel.add(butonCarpma);
		
		JButton butonNokta = new JButton(".");
		butonNokta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setGirilenSayi(getGirilenSayi().concat("."));
				//System.out.println(getGirilenSayi());
				setYazdirilacakSayi(getGirilenSayi());
				repaint();
			}
		});
		butonNokta.setFont(new Font("Tahoma", Font.PLAIN, 30));
		butonNokta.setBounds(228, 534, 145, 94);
		panel.add(butonNokta);
		
		JButton butonEsittir = new JButton("=");
		butonEsittir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ikinciSayi=Double.parseDouble(girilenSayi);
				
				if(islem.equals("toplama")) {
					
					sonuc=birinciSayi+ikinciSayi;
					
				}
				
				
				else if(islem.equals("cikarma")) {
					
					sonuc=birinciSayi-ikinciSayi;
					//System.out.println("Evet çalýþtý");
					
				}
				
				else if(islem.equals("bolme")) {
					
					sonuc=birinciSayi/ikinciSayi;
					
				}
				
				else if(islem.equals("carpma")) {
					
					sonuc=birinciSayi*ikinciSayi;
					
				}
				else if(islem.equals("")) {
					
					sonuc=birinciSayi;
				}
				

				
				if(sonuc==(int)sonuc) {
					
					//System.out.println((int)sonuc);
					setYazdirilacakSayi(String.valueOf((int)sonuc));
					repaint();
					
				}
				else {
					
					//System.out.println(sonuc);
					
					setYazdirilacakSayi(String.valueOf(sonuc));
					repaint();
					
				}
				
				islem="";
				girilenSayi=Double.toString(sonuc);
				esittirCalistiMi=true;
				
			}
		});
		
		butonEsittir.setFont(new Font("Tahoma", Font.PLAIN, 30));
		butonEsittir.setBounds(429, 534, 145, 94);
		panel.add(butonEsittir);
		
		JButton butonBolme = new JButton("/");
		butonBolme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esittirCalistiMi=false;
				birinciSayi=Double.parseDouble(girilenSayi);
				girilenSayi="";
				islem="bolme";
			}
		});
		butonBolme.setFont(new Font("Tahoma", Font.PLAIN, 30));
		butonBolme.setBounds(631, 534, 145, 94);
		panel.add(butonBolme);
		
		JButton butonCikarma = new JButton("-");
		butonCikarma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esittirCalistiMi=false;
				if(!islem.equals("")) {
					islem="cikarma";
					
				}
				else {
					islem="cikarma";
					birinciSayi=Double.parseDouble(girilenSayi);
					girilenSayi="";
				}
				
				//System.out.println();
				//System.out.println("Çalýþtýý");
			}
		});
		butonCikarma.setFont(new Font("Tahoma", Font.PLAIN, 30));
		butonCikarma.setBounds(631, 290, 145, 94);
		panel.add(butonCikarma);
		
		

		
		
	}
	
	private void islemKontrol() {
		if(islemYapildiMi) {
			
			setGirilenSayi("");
			setYazdirilacakSayi(getGirilenSayi());
			repaint();
			sonuc=0;
			birinciSayi=0;
			ikinciSayi=0;
			islem="";
			
			islemYapildiMi = false;
		}
	}

	public String getYazdirilacakSayi() {
		return yazdirilacakSayi;
	}

	public void setYazdirilacakSayi(String yazdirilacakSayi) {
		this.yazdirilacakSayi = yazdirilacakSayi;
	}

}

