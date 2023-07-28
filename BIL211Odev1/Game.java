import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends AbstractGame implements Serializable{
	
	Player currentPlayer;
	Player opponentPlayer;
	static ArrayList<String> moveHistory=new ArrayList<>();

	
	public Game(String str1,String str2) {
		
		this.setBoard(new Board());
		this.setRed(new Player(str1,"red"));
		this.setBlack(new Player(str2,"black"));
		currentPlayer=getRed();
		opponentPlayer=getBlack();
		
	}
	
	public boolean ifMoveKingOutCheck(String from, String to) {
		
		
		Item item = getBoard().getItemAtPos(from);
		Item capturedItem= getBoard().getItemAtPos(to);
		
		getBoard().setItemAtPos(from, new EmptyItem());
		getBoard().setItemAtPos(to, item);
		
		boolean sahInCheck=isSahInCheck(getBoard().getSah(currentPlayer.getColor()).getPosition());

		
		getBoard().setItemAtPos(from, item);
		getBoard().setItemAtPos(to, capturedItem);
		
		return !sahInCheck;
	}
	
	
	//Sah Check altinda mi diye kontrol eder
	public boolean isSahInCheck(String pos) {
		
	    for (int i = 1; i < 11; i++) {
	        for (int j = 1; j < 10; j++) {
	        	
	        	Item item = getBoard().getItemAtPos(i, j);
	        	
	        	if (item!=null && item.getName() != "-" && item.getColor() != currentPlayer.getColor()) {
	        		
	                if (item.isValidMove(pos, getBoard())) {
	                    
	                    
	                    return true;
	                }
	        	}
	        }
	    }
	    
		return false;
		
	}
	
	public boolean isRepeatitiveMove(String pos) {
		

		if(moveHistory.size()<=11) {
			return false;

		}
		
		String firstMove = pos+ moveHistory.get(moveHistory.size()-1) + moveHistory.get(moveHistory.size()-2) + moveHistory.get(moveHistory.size()-3);
		String secondMove = moveHistory.get(moveHistory.size()-4) + moveHistory.get(moveHistory.size()-5)+moveHistory.get(moveHistory.size()-6) + moveHistory.get(moveHistory.size()-7);
		String thirdMove = moveHistory.get(moveHistory.size()-8) + moveHistory.get(moveHistory.size()-9)+moveHistory.get(moveHistory.size()-10) + moveHistory.get(moveHistory.size()-11);

		
		
		if(firstMove.equals(secondMove) && secondMove.equals(thirdMove))
			return true;
		
		else
			return false;

		
	}
	
	public boolean isCheckMate(Player currentPlayer) {
		
		String sahPos=getBoard().getSah(currentPlayer.getColor()).getPosition();
		
		int sahColumn=Integer.parseInt(sahPos.substring(1));
		int sahRow=(sahPos.charAt(0)-96);
		
		//Sah check altinda mi?
		if(isSahInCheck(sahPos)) {
			
			//Eger sah check altindaysa kaçabilir mi?
			for (int newRow = sahRow - 1; newRow <= sahRow + 1; newRow++) {
                for (int newCol = sahColumn - 1; newCol <= sahColumn + 1; newCol++) {
                	
                	if(!Board.isValidPos(Item.returnChrToStrPos(newRow, newCol)))
                		continue;
                	
                    if (newRow == sahRow && newCol == sahColumn) {
                        continue;
                    }
                    if(!getBoard().getSah(currentPlayer.getColor()).isValidMove(Item.returnChrToStrPos(newRow, newCol), getBoard())) {
                    	
                    	continue;
                    }
                    if(newRow>=1 && newRow<11 && newCol>=1 && newCol<10) {
                    	
                    	Item oldItem = getBoard().item2D[newRow][newCol];
                    	getBoard().item2D[newRow][newCol] = getBoard().item2D[sahRow][sahColumn];
                    	getBoard().item2D[sahRow][sahColumn] = new EmptyItem();
                    	boolean canMove = !isSahInCheck(Item.returnChrToStrPos(newRow, newCol));

                    	getBoard().item2D[sahRow][sahColumn] = getBoard().item2D[newRow][newCol];
                    	getBoard().item2D[newRow][newCol] = oldItem;
                    	
                    	if(canMove) {
                    		
                    		return false;
                    	}
                    }
                    
                	
                }
			}
			
			// Herhangi bir taS Checki onleyebilir mi?
            for (int row = 1; row < 11; row++) {
                for (int col = 1; col < 10; col++) {
                	
                	Item item = getBoard().item2D[row][col];
                	
                	if(item.getName()!="-" && item.getColor().equals(currentPlayer.getColor())) {
                		 
                		 for (int newRow = 1; newRow < 11; newRow++) {
                             for (int newCol = 1; newCol < 10; newCol++) {
                            	 
                            	 String to=Item.returnChrToStrPos(newRow, newCol);
                            	 
                            	 if(getBoard().getItemAtPos(row, col).isValidMove(to, getBoard())) {
                            		 
                                     Item oldPiece = getBoard().item2D[newRow][newCol];
                                     getBoard().item2D[newRow][newCol] = getBoard().item2D[row][col];
                                     getBoard().item2D[row][col] = new EmptyItem();
                                     
                                     boolean canBlock = !isSahInCheck(sahPos);
                                     
                                     getBoard().item2D[row][col] = getBoard().item2D[newRow][newCol];
                                     getBoard().item2D[newRow][newCol] = oldPiece;
                                     if(canBlock)
                                    	 return false;
                            	 }
                             }
                         }
                    }
                }
                	
            }
            
            return true;
        }
			

		return false;	
	}
	
	
	

	boolean isValidMove=false;
	@Override
	void play(String from, String to) {
		
		
		if(from.length()!=2 || to.length()!=2 || !getBoard().isValidPos(from) || !getBoard().isValidPos(to)) {
			System.out.println( "hatali hareket" );
			return;
			
		}
		
		//if(isSahInCheck)
		//O pozisyondaki taS kendi renginde degilse false doner. BoS taSlar white rengindedir
		if(!getBoard().getItemAtPos(from).getColor().equals(this.currentPlayer.getColor())) {
			
			//System.out.println(this.currentPlayer.getColor());
			System.out.println( "hatali hareket" );
			return;
		}
		
		
		Item copyItem2D[][]=new Item[11][10];
		Item copyItem[]=new Item[33];
		
		
		//itemleri kopyaliyoruz ki sonradan sorun çikarsa tekrardan bunu koyalim
		for(int i=0;i<11;i++) {
			for(int j=0;j<10;j++) {
				copyItem2D[i][j]=getBoard().item2D[i][j];
			}
		}
		
		for(int i=0;i<33;i++) {
			copyItem[i]=getBoard().items[i];
		}
		
		
		Item i1=getBoard().getItemAtPos(from);
		Item i2=getBoard().getItemAtPos(to);

		
		if(!ifMoveKingOutCheck(from,to)) {
			
			System.out.println("hatali hareket");
			return;
		}
			
		
		
		if(i1.isValidMove(to, getBoard())) {
			
			if(isRepeatitiveMove(from+to)) {
				System.out.println("hatali hareket");
				return;
			}
			
			moveHistory.add(from+to);
			
			if(i1.getColor().equals("red"))
				getRed().puan=(float)(getRed().puan+i2.getValueOfItem());
			else
				getBlack().puan=(float)(getBlack().puan+i2.getValueOfItem());
			
			//Eger taS yendiyse onu xx sinifi ile degiStirir
			if(i2.getValueOfItem()!=0) {
				
				for(int i=1;i<getBoard().items.length;i++) {
					
					if(getBoard().items[i].getPosition().equals(to)) {
						//getBoard().items[i]=new offItem();
						getBoard().items[i].setPosition("xx");
						break;
					}
				}
			}
			
			
			//tek boyutlu itemin de konumunu degiStirir
			for(int i=1;i<getBoard().items.length;i++) {
				
				if(i1==getBoard().items[i]) {
					getBoard().items[i].setPosition(to);
					
				}
				
			}
			
			getBoard().getItemAtPos(from).setPosition(to);
			getBoard().setItemAtPos(from,new EmptyItem());
			getBoard().setItemAtPos(to,i1);
			
			//Eger Sah matsa oyun biter.
			if(isCheckMate(opponentPlayer)) {
				
				
				System.out.println("Sah Mat " + currentPlayer.getColor()+ " Oyunu kazandi." );
				System.out.println("Red puan: "+getRed().puan+" Black puan: "+getBlack().puan);
				
			}
			
			if(getBoard().getSah(getBoard().getItemAtPos(to).getColor()).isSahFaceSah(getBoard())) {
				
				System.out.println("hatali hareket");
				getBoard().setItem2D(copyItem2D);
				getBoard().setItem(copyItem);
				i1.setPosition(from);
				i2.setPosition(to);
				return;
				
			}else {
				
				isValidMove=true;
			}
				
			
		}else {
			
			System.out.println("hatali hareket");
			return;
		}
		
		if(!isValidMove)
			return;

			
		
		
		currentPlayer=	changePlayer(currentPlayer);
		opponentPlayer= changePlayer(opponentPlayer);
		
		
		
			
	}
	
	/////////// SAVE BINARY //////////////
	@Override
	void save_binary(String address) {
		
        try {
            FileOutputStream fileOut = new FileOutputStream(address);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            
            objOut.writeObject(currentPlayer);
            objOut.writeObject(getRed().puan);
            objOut.writeObject(getBlack().puan);
            //objOut.writeObject(getBoard());
            
	        for (int y = 1; y < 11; y++) {
	            for (int x = 1; x < 10; x++) {
	            	
	               
	                
	                    String type = getBoard().item2D[y][x].getName().toString();
	                    String color = getBoard().item2D[y][x].getColor().toString();
	                    String position = getBoard().item2D[y][x].getPosition();
	                    objOut.writeObject(type + " " + color + " " + position);
	                
	            }
	        }
	        
	        for(int i=1;i<33;i++) {
	        	
                String type = getBoard().items[i].getName().toString();
                String color = getBoard().items[i].getColor().toString();
                String position = getBoard().items[i].getPosition();
                objOut.writeObject(type + " " + color + " " + position);
	        }
	        
            objOut.close();
            fileOut.close();
        } catch (IOException e) {
        }
		
		
		
	}

/////////// SAVE Text //////////////
	@Override
	void save_text(String address) {
		
	    File file = new File(address);
	    try {
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        
	        bw.write("Current Player: "+ currentPlayer.getColor()+"\n");
	        bw.write(Float.toString(this.getRed().puan)+"\n");
	        bw.write(Float.toString(this.getBlack().puan)+"\n");
	        
	        
	        //item2D arrayi için
	        for (int y = 1; y < 11; y++) {
	            for (int x = 1; x < 10; x++) {
	            	
	               
	                
	                    String type = getBoard().item2D[y][x].getName().toString();
	                    String color = getBoard().item2D[y][x].getColor().toString();
	                    String position = getBoard().item2D[y][x].getPosition();
	                    bw.write(type + " " + color + " " + position + "\n");
	                
	            }
	        }
	        
	        //items arrayi için
	        for(int i=1;i<33;i++) {
	        	
                String type = getBoard().items[i].getName().toString();
                String color = getBoard().items[i].getColor().toString();
                String position = getBoard().items[i].getPosition();
                bw.write(type + " " + color + " " + position + "\n");
	        }

	        
	        bw.close();
	        fw.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	

/////////// LOAD TEXT //////////////
	@Override
	void load_text(String address) {
		
	    try {
	        File file = new File(address);
	        Scanner scanner = new Scanner(file);
	        
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            
            // Load player turn
            if(parts[2].equals("red"))
            	this.currentPlayer=getRed();
            else
            	this.currentPlayer=getBlack();
            
            line=scanner.nextLine();
            this.getRed().puan=Float.parseFloat(line);
            line=scanner.nextLine();
            this.getBlack().puan=Float.parseFloat(line);
            
	        // Load item2D
	        for (int i = 1; i < 11; i++) {
	            for (int j = 1; j < 10; j++) {
	                line = scanner.nextLine();
	                /*
	                parts = line.split(" ");
	                System.out.println(line);
                    String type = parts[0];
                    String color = parts[1];
                    String position = parts[2];

	                getBoard().item2D[i][j].setColor(color);
	                getBoard().item2D[i][j].setName(type);
	                getBoard().item2D[i][j].setPosition(position);
	                */
	            }
	        }
	        
	        //load items
	        ArrayList<Item> itemArr=new ArrayList<>();
	        ArrayList<String> posArr=new ArrayList<>();
	        for(int i=1;i<33;i++) {
                line = scanner.nextLine();
                parts = line.split(" ");
                String type = parts[0];
                String color = parts[1];
                String position = parts[2];
                getBoard().items[i].setColor(color);
                getBoard().items[i].setName(type);
                getBoard().items[i].setPosition(position);
                itemArr.add(getBoard().items[i]);
                posArr.add(getBoard().items[i].getPosition());
	        }
	        
	        for(int i=1;i<11;i++) {
	        	
	        	for(int j=1;j<10;j++) {
	        		
	        		char c=(char)(i+96);
	        		String pos="";
	        		pos=String.valueOf(c)+j;
	        		if(posArr.contains(pos)) {
	        			
	        			int index=posArr.indexOf(pos);
	        			getBoard().item2D[i][j]=itemArr.get(index);
	        			
	        		}else {
	        			
	        			getBoard().item2D[i][j]=new EmptyItem();
	        			
	        		}
	        	}
	        	
	        }
			
	        

	        scanner.close();
	    } catch (FileNotFoundException e) {

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		
	}

/////////// LOAD BINARY //////////////
	@Override
	void load_binary(String address) {
	    
	    try {
	        FileInputStream fileIn = new FileInputStream(address);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        currentPlayer = (Player) in.readObject();
	        getRed().puan=(float)in.readObject();
	        getBlack().puan=(float)in.readObject();
	        
	        String[] parts;
	        //setBoard ((Board) in.readObject());
	        ArrayList<Item> itemArr=new ArrayList<>();
	        ArrayList<String> posArr=new ArrayList<>();
	        String line="";
	        
	        // Load item2D
	        for (int i = 1; i < 11; i++) {
	            for (int j = 1; j < 10; j++) {
	                line = (String) in.readObject();

	            }
	        }
	        
	        for(int i=1;i<33;i++) {
	        	
                line = (String) in.readObject();
       
                parts = line.split(" ");
                String type = parts[0];
                String color = parts[1];
                String position = parts[2];
                getBoard().items[i].setColor(color);
                getBoard().items[i].setName(type);
                getBoard().items[i].setPosition(position);
                itemArr.add(getBoard().items[i]);
                posArr.add(getBoard().items[i].getPosition());
	        }
	        
	        for(int i=1;i<11;i++) {
	        	
	        	for(int j=1;j<10;j++) {
	        		
	        		char c=(char)(i+96);
	        		String pos="";
	        		pos=String.valueOf(c)+j;
	        		if(posArr.contains(pos)) {
	        			
	        			int index=posArr.indexOf(pos);
	        			getBoard().item2D[i][j]=itemArr.get(index);
	        			
	        		}else {
	        			
	        			getBoard().item2D[i][j]=new EmptyItem();
	        			
	        		}
	        	}
	        	
	        }

	        in.close();
	        fileIn.close();
	       
	        
	    } catch (IOException | ClassNotFoundException e) {
	        
	    }
	    
		
	}
	
	
	
	public Player changePlayer(Player curr) {
		
		if(curr.getColor().equals("red"))
			return getBlack();
			
		else
			return getRed();
	}
	

}
