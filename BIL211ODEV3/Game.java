
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


interface GameObject {
	
    int getX();
    int getY();
    int getSize();
    
}

public class Game {
	
	private JFrame frame;
	private JPanel panel;
	private AirCraft aircraft;
	private ArrayList<Enemy> enemies;
	private ArrayList<Friend> friends;
	//baslangicda aircraftdan uzak bir yerde baslamasini saglar
	private static final int MIN_DISTANCE_FROM_AIRCRAFT = 10;
	private boolean gameOver;
	
	public Game() {
		
		frame= new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameOver=false;

	    
		panel =new JPanel() {
			
			protected void paintComponent(Graphics g) {
				
				super.paintComponent(g);
				
			    if (gameOver) {
			        //g.setColor(Color.RED);
			        //g.setFont(new Font("Arial", Font.BOLD, 30));
			        //g.drawString("GAME OVER", 500 / 2 - 100, 500 / 2);
			    } else {
			    	
					if(aircraft != null) {
						
						aircraft.draw(g);
						
					}
					
					
				    // draw enemies
				    for (Enemy enemy : enemies) {
				        enemy.draw(g);
				    }
				    
				    // draw friends
				    for (Friend friend : friends) {
				        friend.draw(g);
				    }
				    
				    //draw projectiles of aircraft
				    for(Projectile p: aircraft.getProjectiles()) {
				    	
				    	p.draw(g);
				    }
				    
				    // Draw projectiles fired by enemies
				    for (Enemy enemy : enemies) {
				        for (Projectile projectile : enemy.getProjectiles()) {
				            projectile.draw(g);
				            
				        }
				    }

				    // Draw projectiles fired by friends
				    for (Friend friend : friends) {
				        for (Projectile projectile : friend.getProjectiles()) {
				            projectile.draw(g);
				        }
				    }
			        
			    }
			    

			    
			    
			}
			
		};
		
		panel.setPreferredSize(new Dimension(500,500));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		aircraft=new AirCraft();
		aircraft.start();
		
		enemies = new ArrayList<>();
		friends = new ArrayList<>();
		

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                aircraft.move(e.getKeyChar());
                panel.repaint();
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                aircraft.fire();
                panel.repaint();
            }
        });

        panel.setFocusable(true);
        panel.requestFocus();
        
        
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    checkCollisions();
                    panel.repaint();
                }
            }
        });
        timer.start();
        
        Timer projectileUpdateTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    // Update the projectiles for each enemy
                    for (Enemy enemy : enemies) {
                        enemy.updateProjectiles();
                    }
                    
                    // Update the projectiles for each friend
                    for (Friend friend : friends) {
                        friend.updateProjectiles();
                    }
                    
                    // Update the projectiles for the aircraft
                    aircraft.updateProjectiles();

                    panel.repaint();
                }
            }
        });
        projectileUpdateTimer.start();
        

        
        
		
	}
	


	public void checkCollisions() {
		
		for(Enemy enemy : enemies) {
			
			for(Projectile projectile: enemy.getProjectiles()) {
				
				//Enemy projectile aircraftý vurma
				if(projectile.checkCollison(aircraft.getX(), aircraft.getY(), aircraft.SIZE)) {
					
					//Oyun kaybetme bilgisi yazar
			        gameOver = true;
			        JOptionPane.showMessageDialog(frame, "Oyunu kaybettiniz");
			        System.exit(0);
					
				}
				
				//Enemy mermisinin friend vurmasi
				Iterator<Friend> friendsIterator= friends.iterator();
				while(friendsIterator.hasNext()) {
					
					Friend friend = friendsIterator.next();
					if(projectile.checkCollison(friend.getX(), friend.getY(), friend.SIZE)) {
						
						friendsIterator.remove();
						
					}
					
				}
				
				//Enemy mermisinin enemy objesini vurmasi(etkisizdir sadece mermiyi kaldýrýr)
				
				Iterator<Enemy> enemiesIterator= enemies.iterator();
				while(enemiesIterator.hasNext()) {
					
					Enemy otherEnemy= enemiesIterator.next();
					
					if(projectile.checkCollison(otherEnemy.getX(), otherEnemy.getY(), otherEnemy.SIZE)) {
						
						projectile.deactivate();
					}
					
				}

			}

		}
		
		
		//Friend mermilerinin diger objelere vs carpismasi
		
		for(Friend friend: friends) {
			
			for(Projectile projectile: friend.getProjectiles()) {
				
				//Friend mermisi enemy'e vurmasi
				
				Iterator<Enemy> enemiesIterator= enemies.iterator();
				while(enemiesIterator.hasNext()) {
					
					Enemy enemy= enemiesIterator.next();
					
					if(projectile.checkCollison(enemy.getX(), enemy.getY(), enemy.SIZE)) {
						
						enemiesIterator.remove();
					}
					
				}
				
				
				//friend mermisifriend ile çarpýþmasý(mermi kaybolur)
				
				Iterator<Friend> friendsIterator= friends.iterator();
				while(friendsIterator.hasNext()) {
					
					Friend otherFriend= friendsIterator.next();
	                if (projectile.checkCollison(otherFriend.getX(), otherFriend.getY(), Friend.SIZE)) {
	                    projectile.deactivate();
	                }
					
				}
				

			}
			
		}
		
		
		//aircraft mermilerinin diger objeler ile carpismasi
		Iterator<Projectile> aircraftProjectilesIterator= aircraft.getProjectiles().iterator();
		
		while(aircraftProjectilesIterator.hasNext()) {
			
			Projectile projectile= aircraftProjectilesIterator.next();
			
			//Aircraft mermisi enemy ile carpismasi
			Iterator<Enemy> enemiesIterator= enemies.iterator();
			while(enemiesIterator.hasNext()) {
				
				Enemy enemy= enemiesIterator.next();
				if(projectile.checkCollison(enemy.getX(), enemy.getY(), Enemy.SIZE)) {
					
					enemiesIterator.remove();
					
				}
				
				
			}
			
	        // Aircraft mermisi friende carparsa
	        Iterator<Friend> friendsIterator = friends.iterator();
	        
	        while (friendsIterator.hasNext()) {
	            Friend friend = friendsIterator.next();
	            
	            if (projectile.checkCollison(friend.getX(), friend.getY(), Friend.SIZE)) {
	                //friendsIterator.remove();
	            }
	        }
	        
	        
	        // Aircraft mermisi kendine carparsa(sadece mermi kaybolacak) 
	        if (projectile.checkCollison(aircraft.getX(), aircraft.getY(), AirCraft.SIZE)) {
	            projectile.deactivate();
	        }
			
		}
		
		
		//Enemy ve Friend arasýndaki carpismalar
		Iterator<Enemy> enemiesIterator= enemies.iterator();
		
		while(enemiesIterator.hasNext()) {
			
			
			Enemy enemy= enemiesIterator.next();
			Iterator<Friend> friendsIterator= friends.iterator();
			boolean enemyCollidedWithFriend= false;
			
			while(friendsIterator.hasNext()) {
				
				Friend friend= friendsIterator.next();
				
	            if (enemy.checkCollison(friend.getX(), friend.getY(), Friend.SIZE)) {
	                enemyCollidedWithFriend = true;
	                friendsIterator.remove();
	            }
				
			}
			
	        // friend enemy ile carpisirsa
	        if (enemyCollidedWithFriend) {
	            enemiesIterator.remove();
	        }
			
		}
		
		for (Enemy enemy : enemies) {
		    for (Projectile projectile : enemy.getProjectiles()) {
		        if (projectile.checkCollison(aircraft.getX(), aircraft.getY(), aircraft.SIZE)) {
		            gameOver = true;
		            break;
		        }
		    }
		    if (gameOver) {
		        break;
		    }
		}

		
	    // tüm enemyler yok olursa oyun biter
	    if (enemies.isEmpty()) {
	        gameOver = true;
	        JOptionPane.showMessageDialog(frame, "Oyunu kazandýnýz");
	        System.exit(0);
	    }


		
		
		
	}
	
	
	
	
	//////////////////// AIRCRAFT ///////////////////
	public class AirCraft extends Thread implements GameObject{
		
	    private int x;
	    private int y;
	    private static final int SIZE = 10;
	    private static final int MOVE_DISTANCE = 10;
	    private ArrayList<Projectile> projectiles;//mermi
	    private boolean active;
	    private static final int UPDATE_INTERVAL = 50; // 50 milliseconds in milliseconds
	    private static final int PROJECTILE_SPEED = 10;
	    
	    
	    public AirCraft() {
	        x = 250;
	        y = 250;
	        projectiles = new ArrayList<>();
	        active = true;
	        
	    }
	    


	    public void move(char direction) {
	        switch (direction) {
	            case 'a':
	                x = Math.max(x - MOVE_DISTANCE, 0);
	                break;
	            case 's':
	                y = Math.min(y + MOVE_DISTANCE, 490);
	                break;
	            case 'd':
	                x = Math.min(x + MOVE_DISTANCE, 490);
	                break;
	            case 'w':
	                y = Math.max(y - MOVE_DISTANCE, 0);
	                break;
	        }
	    }

	    public void fire() {
	        projectiles.add(new Projectile(x - SIZE / 2, y - SIZE / 2, Color.ORANGE, -PROJECTILE_SPEED, 0));
	        projectiles.add(new Projectile(x + SIZE / 2, y - SIZE / 2, Color.ORANGE, PROJECTILE_SPEED, 0));
	        
	    }


	    public boolean checkCollison(int otherX, int otherY, int otherSize) {
	        if (active && otherX >= x - otherSize && otherX <= x + SIZE
	                && otherY >= y - otherSize && otherY <= y + SIZE) {
	            active = false;
	            return true;
	        }

	        return false;
	    }

	    public void draw(Graphics g) {
	        g.setColor(Color.RED);
	        g.fillRect(x, y, SIZE, SIZE);
	        for (Projectile p : projectiles) {
	            p.draw(g);
	        }
	    }

	    public void updateProjectiles() {
	        for (Projectile p : projectiles) {
	            p.update();
	            
	        }
	        
	    }

	    public ArrayList<Projectile> getProjectiles() {
	        return projectiles;
	    }

	    @Override
	    public void run() {
	        while (active) {
	            updateProjectiles();
	            try {
	                Thread.sleep(UPDATE_INTERVAL);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void deactivate() {
	        this.active = false;
	    }

	    public boolean isActive() {
	        return active;
	    }

		@Override
		public int getX() {
			return x;
		}

		@Override
		public int getY() {
			return y;
		}

		@Override
		public int getSize() {
			
			return SIZE;
		}
	}//////// END AIRCRAFT ///////////7
	
	
	
	
	
	
	
	
	//////////////////// ENEMY ///////////////////
	class Enemy extends Thread implements GameObject{
		
		private int x;
		private int y;
		private static final int SIZE=10;
		private static final int MOVE_DISTANCE=10;
		private static final int MOVE_INTERVAL=500;
	    private ArrayList<Projectile> projectiles;
	    private static final int FIRE_INTERVAL = 1000; // 1 second in milliseconds
	    private static final int PROJECTILE_SPEED = 10;
	    
	    private final Random random = new Random();
		private boolean active;
		
		public Enemy() {
			
		    this.active = true;
		    do {
		        this.x = random.nextInt(50) * MOVE_DISTANCE;
		        this.y = random.nextInt(50) * MOVE_DISTANCE;
		    } while (distanceFromAircraft(x, y) < MIN_DISTANCE_FROM_AIRCRAFT);
			projectiles = new ArrayList<>();
			enemies.add(this);
			
		}
		
		public void move() {
			
			int direction= random.nextInt(4);
			int newX=x;
			int newY= y;
			
			
			switch(direction) {
			
			case 0://SAG
				newX+= MOVE_DISTANCE;
				break;
			
			case 1://SOL
				newX-= MOVE_DISTANCE;
				break;
			
			case 2://ASAGI
				newY+= MOVE_DISTANCE;
				break;
			
			case 3://YUKARI
				newY-=MOVE_DISTANCE;
				break;
			
				
			}
			
			//enemy ekran icinde kalmali
			if(newX>=0 && newX<500 && newY>=0 && newY<500) {
				
				x=newX;
				y=newY;
			}
			
		}
		
		
		public boolean checkCollison(int otherX, int otherY, int otherSize) {
		    if (active && otherX >= x - otherSize && otherX <= x + SIZE
		            && otherY >= y - otherSize && otherY <= y + SIZE) {
		        active = false;
		        return true;
		    }

		    return false;
		}
	    
	    
		
		public void fire() {
		    // Fire left
		    projectiles.add(new Projectile(x - SIZE / 2, y + SIZE / 2, Color.blue, -PROJECTILE_SPEED, 0));
		    // Fire right
		    projectiles.add(new Projectile(x + SIZE / 2, y + SIZE / 2, Color.blue, PROJECTILE_SPEED, 0));
		}
		
		
		public void updateProjectiles() {
			
			for(Projectile p: projectiles)
				p.update();
			
		}
		
		
		
		public void draw(Graphics g) {
			
			if(active) {
				
				g.setColor(Color.BLACK);
				g.fillRect(x, y, SIZE, SIZE);
				
				for(Projectile p: projectiles)
					p.draw(g);
				
				
			}
		}
		
	    public void deactivate() {
	        this.active = false;
	    }

	    public boolean isActive() {
	        return active;
	    }
		
		public void run() {
			
			long lastFired = System.currentTimeMillis();
			
			while(active) {
				
				move();
				
				long currentTime = System.currentTimeMillis();
				
	            if (currentTime - lastFired > FIRE_INTERVAL) {
	                fire();
	                lastFired = currentTime;
	            }

	            updateProjectiles();
	            
				try {
					
					Thread.sleep(MOVE_INTERVAL);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}
		
	    public ArrayList<Projectile> getProjectiles() {
	        return projectiles;
	    }

		@Override
		public int getX() {
			
			return x;
		}

		@Override
		public int getY() {
			
			return y;
		}

		@Override
		public int getSize() {
			
			return SIZE;
		}
		
		
	} //////////  END ENEMY   /////////////
	
	
	
	
	
	
 	
	
	///////////////// FRIEND  ////////////////
	class Friend extends Thread implements GameObject{
		
	    private int x;
	    private int y;
	    private static final int SIZE = 10;
	    private static final int MOVE_DISTANCE = 10;
	    private static final int MOVE_INTERVAL = 500; // 0.5 seconds in milliseconds
	    private static final int PROJECTILE_SPEED = 10;
	    private final Random random = new Random();
	    private boolean active;
	    Color purple = new Color(128, 0, 128);//Ates rengini purple yapmak icin
	    private ArrayList<Projectile> projectiles;
	    private static final int FIRE_INTERVAL = 1000; // 1 second in milliseconds
	    
	    
	    public Friend() {
	        this.active = true;
	        //baslangic konumunu tayin eder
	        do {
	        this.x = random.nextInt(50) * MOVE_DISTANCE;
	        this.y = random.nextInt(50) * MOVE_DISTANCE;
	        }while(distanceFromAircraft(x, y) < MIN_DISTANCE_FROM_AIRCRAFT);
	        projectiles = new ArrayList<>();
	        
	        friends.add(this);
	    }
	    
	    
	    public void move() {
	        int direction = random.nextInt(4);
	        int newX = x;
	        int newY = y;

	        switch (direction) {
	            case 0://SAG
	                newX += MOVE_DISTANCE;
	                break;
	            case 1://SOL
	                newX -= MOVE_DISTANCE;
	                break;
	            case 2://ASAGI
	                newY += MOVE_DISTANCE;
	                break;
	            case 3://YUKARI
	                newY -= MOVE_DISTANCE;
	                break;
	        }

	        // Bound icinde kalmasini saglar
	        if (newX >= 0 && newX < 500 && newY >= 0 && newY < 500) {
	            x = newX;
	            y = newY;
	        }
	    }
	    
	    
	    public boolean checkCollision(Projectile projectile) {
	        if (projectile.isActive()) {
	            return intersects(projectile);
	        }
	        return false;
	    }

	    public boolean intersects(Projectile projectile) {
	        return projectile.getX() >= x && projectile.getX() <= x + SIZE
	                && projectile.getY() >= y && projectile.getY() <= y + SIZE;
	    }
	    
	    
	    public void fire() {
	        projectiles.add(new Projectile(x - SIZE / 2, y - SIZE / 2, purple, -PROJECTILE_SPEED, 0));
	        projectiles.add(new Projectile(x + SIZE / 2, y - SIZE / 2, purple, PROJECTILE_SPEED, 0));
	    }
	    
	    
	    //mermi güncelleme
	    public void updateProjectiles() {
	        for (Projectile p : projectiles) {
	            p.update();
	        }
	    }
	    
	    
	    public void draw(Graphics g) {
	        if (active) {
	            g.setColor(Color.GREEN);
	            g.fillRect(x, y, SIZE, SIZE);
	            
	            //mermileri cizdirme
	            for (Projectile p : projectiles) {
	                p.draw(g);
	            }
	        }
	    }
	    
	    public void deactivate() {
	        this.active = false;
	    }

	    public boolean isActive() {
	        return active;
	    }
	    
	    
	    
	    @Override
	    public void run() {
	    	
	    	long lastFired = System.currentTimeMillis();
	    	
	        while (active) {
	        	
	        	
	            move();
	            
	            long currentTime = System.currentTimeMillis();
	            
	            if (currentTime - lastFired > FIRE_INTERVAL) {
	                fire();
	                lastFired = currentTime;
	            }

	            updateProjectiles();
	            
	            
	            try {
	                Thread.sleep(MOVE_INTERVAL);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    public ArrayList<Projectile> getProjectiles() {
	        return projectiles;
	    }


		@Override
		public int getX() {
			// TODO Auto-generated method stub
			return x;
		}


		@Override
		public int getY() {
			// TODO Auto-generated method stub
			return y;
		}


		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return 10;
		}
		
	}////////////END FRIEND/////////////7
	
	
	
	
	
	
	
	
	////////////////////  PROJECTILE  ///////////////////
	class Projectile{
		
		private int x;
		private int y;
		private Color color;
	    private int dx;
	    private int dy;
		private boolean active;
		private static final int SIZE = 5;

		
	    public Projectile(int x, int y, Color color, int dx, int dy) {
	        this.x = x;
	        this.y = y;
	        this.color = color;
	        this.active = true;
	        this.dx = dx;
	        this.dy = dy;
	    }
		
	    public void update() {
	        x += dx;
	        y += dy;
	        
	        
	        

	        // Deactivate if the projectile goes beyond the screen boundaries
	        if (x < 0 || x >= 500 || y < 0 || y >= 500) {
	            active = false;
	        }
	    }
		
		public boolean checkCollison(int otherX, int otherY, int otherSize) {
		    if (active && otherX >= x - otherSize && otherX <= x + SIZE
		            && otherY >= y - otherSize && otherY <= y + SIZE) {
		        active = false;
		        return true;
		    }

		    return false;
		}

		
		
		public void draw(Graphics g) {
			
			if(active) {
				
				g.setColor(color);
				g.fillRect(x,y, SIZE, SIZE);
				
			}
			
		}
		
	    public void deactivate() {
	        this.active = false;
	    }

		
	    public boolean isActive() {
	        return active;
	    }

	    public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }

	    public Color getColor() {
	        return color;
	    }
		
	}/////////END PROJECTILE/////////////







	//Aircrafta olan uzakligi doner
	public double distanceFromAircraft(int x, int y) {
		
	    int aircraftX = 250;
	    int aircraftY = 250;
	    return Math.sqrt(Math.pow(x - aircraftX, 2) + Math.pow(y - aircraftY, 2));
	    
	}
	
	
	
	
	
	

}
