import java.io.IOException;
import java.io.Serializable;

public class Item extends AbstractItem implements Serializable{
	
	private String name;
	private String color;
	private double valueOfItem;
	private Item type;
	
	public Item(String color,String Position) {
		
		this.color=color;
		this.setPosition(Position);

		
	}
	
	public Item() {
		
		
	}
	
	
	public void move(String destination) {//Kural diSi bir durum yoksa taSi belirtilen konuma goturur
										
		 this.setPosition(destination);
		
	}
	
	public boolean isValidMove(String pos, Board board) {//Her tas bunu extends edip gecerli hamle olup olmadigini kontrol edecektir
		
		return false;
	}
	
    public void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        
        if (this.getPosition() != null) {
            out.writeUTF(this.getPosition());
        }
        
        out.writeUTF(color);
        out.writeUTF(this.getName());
    }

    public void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        
        String position = in.readUTF();
        if (this.getPosition()!=null) {
            this.setPosition(position);
        }
        
        this.setColor(in.readUTF());
        this.setName(in.readUTF());
        
    }

	
	//Girilen pozisyonun Palace'in içinde olup olmadigini kontrol eder   
	public boolean isInPalace(String toPos) {
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		
        if(this.getColor().equalsIgnoreCase("black")) {
        	
        	if(destRow<8 || destCol<4 || destCol>6) {
        		
        		return false;
        	}
        	
        }else {
        	
        	if(destRow>3 || destCol<4 || destCol>6) {
        		
        		return false;
        	}
        }
        
        return true;
		
	}
	
	//Sahlar karsi karsiya mi diye kontrol eder
	public boolean isSahFaceSah(Board board) {
		
		//Nesneyi çagiran Sahin konumu
		int column=Integer.parseInt(this.getPosition().substring(1));
		int row=(this.getPosition().charAt(0)-96);
		
		//KarSi Sahin konumu
		int column2=-1;
		int row2=-1;
		
		if(this.getClass() != Sah.class) {
			
			return false;
		}
		
		for(int i=1;i<11;i++) {
			
			for(int j=1;i<10;i++) {
				
				if(board.item2D[i][j]!=this && board.item2D[i][j].getClass()==Sah.class) {
					column2=Integer.parseInt(board.item2D[i][j].getPosition().substring(1));
					row2=(board.item2D[i][j].getPosition().charAt(0)-96);
					
					break;
					
				}
				
			}
			
		}

		//Sah yoksa false doner
		if(row2==-1 || column2==-1) {
			//System.out.println("Eroor2");
			return false;
		}
		
		//Sahlarin column degerleri ayni degilse false doner
		if(column!=column2) {
			//System.out.println("Eroor3");
			return false;
		}
		
		//Aralarinda bir taS varsa false doner
		for(int i=Math.min(row,row2)+1 ; i<Math.max(row,row2);i++) {
			
			if(board.isThereItemAt(i,column)) {
				//System.out.println("Eroor4");
				//System.out.println("row: "+i+ "col: "+column);
				return false;
			}
		}
		
		return true;
		
	}
	
	public boolean equals(Item i) {
		
		
		return this.getColor().equals(i.getColor())==this.getName().equals(i.getName())==this.getType().equals(i.getType()) ==this.getPosition().equals(i.getPosition());
	}

	///Getter Setter Method BEGIN
	
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

	public static String returnChrToStrPos(int row,int col) {
		
		String str="";
		char rowChr=(char)(row+96);
		return Character.toString(rowChr)+col;
	}

	public double getValueOfItem() {
		return valueOfItem;
	}


	public void setValueOfItem(double valueOfItem) {
		this.valueOfItem = valueOfItem;
	}
	public Item getType() {
		return type;
	}
	
	public void setType(Item type) {
		this.type = type;
	}
	

	
	///Getter Setter Method END
	

}

//////////////           KALE CLASS     ///////////
class Kale extends Item {

	public Kale(String color, String Pos) {
		super(color, Pos);
		this.setValueOfItem(9);
		this.setType(this);
		if(this.getColor().equalsIgnoreCase("red"))
			this.setName("k");
		else
			this.setName("K");
	}
	
	public boolean isValidMove(String toPos,Board board) {//Her tas bunu extends edip gecerli hamle olup olmadigini kontrol edecektir
		
		int column=Integer.parseInt(this.getPosition().substring(1));
		int row=(this.getPosition().charAt(0)-96);
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		
		if(board.isAvoidBySameColor(toPos, this.getColor()))
			return false;
		
		if((destCol != column) &&destRow != row) //eger yatay boyunca gittiyse row sabit eger dikey boyunca gittiyse column sabit olmali ikisi de saglanmazsa invalid hareket
			return false;
		
		//Arasinda herhangi bir taS varsa hareket etmemeli
		//yatay hareket
		if(destRow==row) {
			for(int i=Math.min(destCol, column)+1; i<Math.max(destCol, column); i++) {
				
				if(board.isThereItemAt(row, i)) {
					
					
					return false;
				}
			}
			
		}else {
			for(int i=Math.min(row, destRow)+1; i<Math.max(row, destRow); i++) {

				if(board.isThereItemAt(i, column)) {

					return false;
				}
					
			}
		}
		
		
		return true;
	}

	
	
}

//////////////        AT CLASS     ///////////
class At extends Item {

	public At( String color, String Pos) {
		super(color, Pos);//A: At
		this.setValueOfItem(4);
		this.setType(this);
		if(this.getColor().equalsIgnoreCase("red"))
			this.setName("a");
		else
			this.setName("A");
	}
	
	
	
	//Board içinde atin belirtilen pozisyona gidebilip gidemeyecegini kontrol eder
	public boolean isValidMove(String toPos, Board board) {
		
		int column=Integer.parseInt(this.getPosition().substring(1));
		int row=(this.getPosition().charAt(0)-96);


        if (!board.isValidPos(toPos)) {
            return false;
        }
        
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		
		
		
		
		//gidecegi pozisyonda taS var mi?
        if (board.isAvoidBySameColor(toPos,this.getColor())) {
            return false;
        }
        
		//Hareketini bloklayan bir tas var mi?
        if(board.isThereItemAt((row + (destRow - row) / 2), (column + (destCol - column) / 2)))
        	return false;
        
        
        //At bu hareketi yapabilir mi diye bakiyor
        if((Math.abs(destRow-row)==1 && Math.abs(destCol-column)==2) 
        		|| (Math.abs(destRow-row)==2 && Math.abs(destCol-column)==1))
        	return true;
        
        
		return false;
	}

	
	
}


//////////////        FIL CLASS     ///////////
class Fil extends Item {

	public Fil(String color, String Pos) {
		super( color, Pos);//F: Fil
		this.setValueOfItem(2);
		this.setType(this);
		if(this.getColor().equalsIgnoreCase("red"))
			this.setName("f");
		else
			this.setName("F");
	}
	
	public boolean isValidMove(String toPos, Board board) {//Her tas bunu extends edip gecerli hamle olup olmadigini kontrol edecektir
		
		int column=Integer.parseInt(this.getPosition().substring(1));
		int row=(this.getPosition().charAt(0)-96);
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		
		//Fil gidecegi yer kendi areasinda mi degilse false
		if (!isDestInOwnArea(toPos,this.getColor())) {
			//System.out.println(this.getColor());
			//System.out.println("ERROR1");
            return false;
        }
		
		//Gidecegi yerde ayni renkten tas varsa false doner
        if (board.isAvoidBySameColor(toPos,this.getColor())) {
        	//System.out.println("ERROR2");//dogru calisti
            return false;
        }
		
		//Fil sadece iki hamle gidebilir. Arada taS var mi kontrolu onceki metotta yapilir
        if (Math.abs(column-destCol) != 2 || Math.abs(destRow-row) != 2) {
        	//System.out.println(Math.abs(column-destCol));
        	//System.out.println(Math.abs(destRow-row));
        	//System.out.println(this.getPosition());
        	//System.out.println("ERROR4");
            return false; 
        }
        
		//Gidecegi yerle arasinda tas varsa false doner 
        if (board.isThereItemAt(row + (destRow-row)/2, column + (destCol-column)/2)) {
        	//System.out.println("ERROR3");
            return false; 
        }
        
        

        
        
		return true;
	}
	
	//Destinationun kendi Areasinda olup olmadigini kontrol eder
	public boolean isDestInOwnArea(String toPos,String color) {
		
		int coorColumn=Integer.parseInt(toPos.substring(1));
		int coorRow=(toPos.charAt(0)-96);
		
		if(color.equalsIgnoreCase("black")) {
			
			return coorColumn >= 1 && coorColumn <= 9 && coorRow >= 6 && coorRow <= 10;
			
			
		}
		else {
			
			return coorColumn >= 1 && coorColumn <= 9 && coorRow >= 1 && coorRow <= 5;
		}
	}
	
	
	
	

	
	
}


//////////////        VEZIR CLASS     ///////////
class Vezir extends Item {

	public Vezir(String color, String Pos) {
		super(color, Pos);//V: vezir
		this.setValueOfItem(2);
		this.setType(this);
		if(this.getColor().equalsIgnoreCase("red"))
			this.setName("v");
		else
			this.setName("V");
	}
	
	public boolean isValidMove(String toPos, Board board) {//Her tas bunu extends edip gecerli hamle olup olmadigini kontrol edecektir
		
		
		int column=Integer.parseInt(this.getPosition().substring(1));
		int row=(this.getPosition().charAt(0)-96);
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		
		
		//Sadece çapraz hareket edebildigi için destination ile su anki konum arasinda 1 br fark olmali
        if (Math.abs(destCol - column) != 1 || Math.abs(destRow - row) != 1) {
        	//System.out.println("Eroor1");
            return false;
        }
        
        
        //Palasin disina cikip cikmadigini kontrol eder
        if(!isInPalace(toPos)) {
        	//System.out.println("Eroor2");
        	return false;
        }
        
		//Gidecegi yerde tas varsa false doner 
        if (board.isAvoidBySameColor(toPos,this.getColor())) {
        	//System.out.println("Eroor3");
            return false;
        }
        
		
		return true;
	}

	
	
}



//////////////         SAH CLASS     ///////////
class Sah extends Item {

	public Sah(String color, String Pos) {
		super( color, Pos);//S: Sah
		this.setValueOfItem(1000);
		this.setType(this);
		if(this.getColor().equalsIgnoreCase("red"))
			this.setName("s");
		else
			this.setName("S");
		
	}
	
	public boolean isValidMove(String toPos,Board board ) {//Her tas bunu extends edip gecerli hamle olup olmadigini kontrol edecektir
		
		int column=Integer.parseInt(this.getPosition().substring(1));
		int row=(this.getPosition().charAt(0)-96);
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		
		
		//Gidecegi yer Placenin diSinda mi diye kontrol eder
		
	    if (!isInPalace(toPos)) {
	    	
	        return false;
	    }
	    
	    
	    //Sahin belirtilen noktaya gidip gidemeyecegini kontrol eder
	    //Sah palace içinde aSagi ya da yukari gidebilir çapraz gidemez
	    if ((Math.abs(destCol - column) == 1 && destRow == row) || ((Math.abs(destRow - row) == 1 && destCol == column))) {
	    	
	    	//Gidecegi yerde Mat olup olmama durumunu kontrol edelim
	    	if(isSahInCheck(toPos,board)) {
	    		
	    		//System.out.println("Sah o kareye hamle yapamaz.Matta");
	    		return false;
	    	}
	    	
	    	return true;
	    }
	    
	    
		return false;
	}
	
	
	public boolean isSahInCheck(String toPos, Board board ) {
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		String opponentColor;
		
		if(this.getColor().equalsIgnoreCase("red"))
			opponentColor="black";
		else
			opponentColor="red";
		
		for(int i=1;i<11;i++) {
			
			for(int j=1;j<10;j++) {
				
				Item item=board.getItemAtPos(returnChrToStrPos(i,j));
				//System.out.println(item.getColor());
	            if (!item.getName().equals("-")  && item.getColor().equals(opponentColor)) {
	            	//System.out.println(item.getPosition());
	                if (item.isValidMove(toPos,board)) {
	                	
	                    return true;
	                }
	            }

				
			}
			
		}
		
		return false;
		
	}

	
}

//////////////        TOP CLASS     ///////////
class Top extends Item {

	public Top(String color, String Pos) {
		super(color, Pos); //T: Top
		this.setValueOfItem(4.5);
		this.setType(this);
		if(this.getColor().equalsIgnoreCase("red"))
			this.setName("t");
		else
			this.setName("T");
	}
	
	public boolean isValidMove(String toPos, Board board) {//Her tas bunu extends edip gecerli hamle olup olmadigini kontrol edecektir
		
		int column=Integer.parseInt(this.getPosition().substring(1));
		int row=(this.getPosition().charAt(0)-96);
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		
		
		//Pozisyon valid mi?
        if (!board.isValidPos(toPos)) {
            return false;  
        }
        
        //Gidecegi yerde kendi renginde tas varsa oynayamaz
        if(board.isAvoidBySameColor(toPos, this.getColor())) {
        	return false;
        }
        //yatay giderken row, dikey giderken de column sabit olmali ikisi bir olmazsa false doner
        if((row!=destRow) && (column!=destCol)) {
        	return false;
        }
        int count=0; //Atlama sayisini kontrol eder
        
		if(destRow==row) {//yatay hareket
			
			for(int i=Math.min(destCol, column)+1; i<Math.max(destCol, column); i++) {
				
				if(board.isThereItemAt(row, i))
					count++;
			}
			
		}else {//dikey hareket;
			for(int i=Math.min(row, destRow)+1; i<Math.max(row, destRow); i++) {
				
				if(board.isThereItemAt(i, column)) {
					
					count++;
				} 
					

			}
		}
		
		if(board.isThereItemAt(toPos)){// gittigi yerde tas varsa count sayisi 1 olmali doner
			return count==1;
		}
         else {
        	 return count==0;
        	 
         }

	}
	

	
	
}


//////////////        PIYON CLASS     ///////////


class Piyon extends Item {

	private boolean promote;
	
	public Piyon(String color, String Pos) {
		super(color, Pos); //P: Piyon
		this.setPromote(false);
		this.setType(this);
		this.setValueOfItem(1);
		if(this.getColor().equalsIgnoreCase("red"))
			this.setName("p");
		else
			this.setName("P");
		
	}
	
	
	
	public boolean isValidMove(String toPos, Board board) {//Her tas bunu extends edip gecerli hamle olup olmadigini kontrol edecektir
		
		int column=Integer.parseInt(this.getPosition().substring(1));
		int row=(this.getPosition().charAt(0)-96);
		
		int destCol=Integer.parseInt(toPos.substring(1));
		int destRow=(toPos.charAt(0)-96);
		
		if(board.isAvoidBySameColor(toPos,this.getColor()))//eger ayni renk bir taS tarafindan engelleniyorsa false doner
			return false;
		
		if(!Board.isValidPos(toPos))//Pozisyon valid mi diye bakar
			return false;
		
		if(this.getColor().equalsIgnoreCase("black")) {
			
			//Sadece one dogru hareket etsin
	        if (destRow-row > 0) { // Geriye dogru gidemez
	            return false;
	        }
	        
	        

	        
	        if (row >= 6 && destRow-row == 0 && Math.abs(column-destCol) == 1) { // Nehiri gecmeden yana hareket edemez
	            return false;
	        }
	        
	        
	        if (Math.abs(column-destCol) > 1 || row-destRow>1) { // Bir hamleden fazla hareket edemez
	            return false;
	        }
	        
	        if(row<=5) {
	        	this.setValueOfItem(2);
	        	this.setPromote(true);
	        }
	        
	        
	        
		}else {
			
            if (destRow-row < 0) { // geriye dogru hareket edemez
                return false;
            }


            if (row <= 5 && destRow-row == 0 && Math.abs(column-destCol) == 1) {  // Nehiri gecmeden yana hareket edemez
                return false;
            }
            
	        if (Math.abs(column-destCol) > 1 || destRow-row>1) { // Bir hamleden fazla hareket edemez
	            return false;
	        }
	        if(row>=6) {
	        	this.setValueOfItem(2);
	        	this.setPromote(true);
	        }
			
		}
		
		return true;
		
		
	}



	public boolean isPromote() {
		return promote;
	}



	public void setPromote(boolean promote) {
		this.promote = promote;
	}

	
	
}


//////////////          EMPTYITEM CLASS     ///////////

class EmptyItem extends Item{//BoS taSlari renk: white, 

	public EmptyItem() {
		this.setType(this);
		this.setValueOfItem(0);
		this.setColor("white");
		this.setName("-");
		
	}

	
	
}
class offItem extends Item{
	
	public offItem() {
		this.setType(this);
		this.setValueOfItem(0);
		this.setColor("white");
		this.setName("xx");
		
	}
	
}




