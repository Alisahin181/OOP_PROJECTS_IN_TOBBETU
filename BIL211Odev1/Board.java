import java.io.Serializable;

public class Board extends AbstractBoard implements Serializable{

	Item item2D[][] =new Item[11][10];
	Item items[]=new Item[33];
	
	public Board() {
		
		for(int i=0;i<10;i++)
			item2D[0][i]=null;
		
		for(int i=0;i<11;i++) 
			item2D[i][0]=null;
		
		item2D[1][1]=new Kale("red","a1");			item2D[10][1]=new Kale("black","j1");
		item2D[1][2]=new At("red","a2");			item2D[10][2]=new At("black","j2");
		item2D[1][3]=new Fil("red","a3");			item2D[10][3]=new Fil("black","j3");
		item2D[1][4]=new Vezir("red","a4");			item2D[10][4]=new Vezir("black","j4");
		item2D[1][5]=new Sah("red","a5");			item2D[10][5]=new Sah("black","j5");
		item2D[1][6]=new Vezir("red","a6");			item2D[10][6]=new Vezir("black","j6");
		item2D[1][7]=new Fil("red","a7");			item2D[10][7]=new Fil("black","j7");
		item2D[1][8]=new At("red","a8");			item2D[10][8]=new At("black","j8");
		item2D[1][9]=new Kale("red","a9");			item2D[10][9]=new Kale("black","j9");
		
		item2D[4][1]=new Piyon("red","d1");			item2D[7][1]=new Piyon("black","g1");			
		item2D[4][3]=new Piyon("red","d3");			item2D[7][3]=new Piyon("black","g3");			
		item2D[4][5]=new Piyon("red","d5");			item2D[7][5]=new Piyon("black","g5");			
		item2D[4][7]=new Piyon("red","d7");			item2D[7][7]=new Piyon("black","g7");			
		item2D[4][9]=new Piyon("red","d9");			item2D[7][9]=new Piyon("black","g9");
													
		item2D[3][2]=new Top("red","c2");			item2D[8][2]=new Top("black","h2");
		item2D[3][8]=new Top("red","c8");			item2D[8][8]=new Top("black","h8");
		
		
		
		/*
		items[0]=null;
		items[1]=new Kale("red","a1");items[2]=new At("red","a2");items[3]=new Fil("red","a3");items[4]=new Vezir("red","a4");
		items[5]=new Sah("red","a5");items[6]=new Vezir("red","a6");items[7]=new Fil("red","a7");items[8]=new At("red","a8");
		items[9]=new Kale("red","a9");items[10]=new Piyon("red","d1");items[11]=new Piyon("red","d3");items[12]=new Piyon("red","d5");
		items[13]=new Piyon("red","d7");items[14]=new Piyon("red","d9");items[15]=new Top("red","c2");items[16]=new Top("red","c8");
		items[17]=new Kale("black","j1");items[18]=new At("black","j2");items[19]=new Fil("black","j3");items[20]=new Vezir("black","j4");
		items[21]=new Vezir("black","j5");items[22]=new Fil("black","j6");items[23]=new At("black","j7");items[24]=new Kale("black","j8");
		items[25]=new Kale("black","j9");items[26]=new Piyon("black","g1");items[27]=new Piyon("black","g3");items[28]=new Piyon("black","g5");
		items[29]=new Piyon("black","g7");items[30]=new Piyon("black","g9");items[31]=new Top("black","h2");items[32]=new Top("black","h8");
		*/
		

		int count=1;
		for(int i=1;i<11;i++) {
			
			for(int j=1;j<10;j++) {
				
				if(item2D[i][j]==null)
					item2D[i][j]=new EmptyItem();
				else {
					items[count]=item2D[i][j];
					
					count++;
				}
			}
		}	
		
	}
	
	public void printItem() {
		
		for(int i=1;i<items.length;i++)
			System.out.println(items[i].getName());
	}
	
	public Item getItemAtPos(int row,int column){//Eger oyle bir pozisyon mumkunse pozisyonu doner yoksa exception atar
		

        	return item2D[row][column];
	
	}
	
	public Item getItemAtPos(String pos){//Eger oyle bir pozisyon mumkunse pozisyonu doner yoksa exception atar
		
        if (!isValidPos(pos)) {
        	
            System.out.println("hatali hareket");
            return null;
        }else
        	return item2D[(pos.charAt(0)-96)][Integer.parseInt(pos.substring(1))];
	
	}
	
	public void isSahInCheck(Player p) {
		
		
		
	}
	
	public void setItemAtPos(String pos,Item i) {
		
        if (!isValidPos(pos)) {
        	
        	System.out.println("hatali hareket");
        }else {
        	item2D[(pos.charAt(0)-96)][Integer.parseInt(pos.substring(1))]=i;
        	
        }
	}
	
	
	public static boolean isValidPos(String pos) {
		try {
			int column=Integer.parseInt(pos.substring(1));
			int row=(pos.charAt(0)-96);
			return row>=1 && row<=10 && column>=1 && column<=9;
		}catch(Exception e) {
			
			return false;
		}
		
	}
	
	
	public boolean isAvoidBySameColor(String pos, String color) {
		
		if(item2D[(pos.charAt(0)-96)][Integer.parseInt(pos.substring(1))].equals("-"))
			return false;
		
		return item2D[(pos.charAt(0)-96)][Integer.parseInt(pos.substring(1))].getColor()==color;
		
	}
	
	
	//o colorin sahini doner
	public Sah getSah(String color) {
		
		for(int i=1;i<11;i++) {
			for(int j=1;j<10;j++) {
				
				if(!item2D[i][j].getName().equals("-")) {
					
					if(color.equals(item2D[i][j].getColor()) && item2D[i][j] instanceof Sah)
						return (Sah) item2D[i][j];
				}
			}
		}
		
		return null;
		
	}
	
	
	public boolean isThereItemAt(String pos) {//O pozisyonda tas varsa true yoksa false doner
	    return item2D[(pos.charAt(0)-96)][Integer.parseInt(pos.substring(1))].getName() != "-";
	}
	
	public boolean isThereItemAt(int row, int col) {//O pozisyonda tas varsa true yoksa false doner overloaded form
	    //return item2D[row][col].getName() != "-";
		return item2D[row][col].getName() != "-";
	}
	
	@Override
	public void print() {//Guncel tahtayi print eder

		System.out.println("j\t" + getPrintingColAt(10,item2D));
		System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
		System.out.println("i\t" + getPrintingColAt(9,item2D));
		System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
		System.out.println("h\t" + getPrintingColAt(8,item2D));
		System.out.println(" \t|  |  |  |  |  |  |  |  |");
		System.out.println("g\t" + getPrintingColAt(7,item2D));
		System.out.println(" \t|  |  |  |  |  |  |  |  |");
		System.out.println("f\t" + getPrintingColAt(6,item2D));
		System.out.println(" \t| 			|");
		
		System.out.println("e\t" + getPrintingColAt(5,item2D));
		System.out.println(" \t|  |  |  |  |  |  |  |  |");
		System.out.println("d\t" + getPrintingColAt(4,item2D));
		System.out.println(" \t|  |  |  |  |  |  |  |  |");
		System.out.println("c\t" + getPrintingColAt(3,item2D));
		System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
		System.out.println("b\t" + getPrintingColAt(2,item2D));
		System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
		System.out.println("a\t" + getPrintingColAt(1,item2D));
		System.out.println();
		System.out.println(" \t1--2--3--4--5--6--7--8--9");
		
	}
	
	//Item ve item2D degiskenlerinin get ve set metodlari
	public Item[] getItem() {
		
		return items;
	}
	
	public void setItem(Item[] i) {
		
		this.items=i;
		
	}
	public Item[][] getItem2D(){
		
		
		return item2D;
	}
	public void setItem2D(Item[][] i){
		
		
		this.item2D=i;
	}
	
	public String getPrintingColAt(int pos,Item[][] item2D ) {//Printin yardimci methodu
		
		String str="";
		for(int i=1;i<9;i++) {

				str=str+item2D[pos][i].getName()+"--";

		}


		return str+item2D[pos][9].getName();
		
	}
	

}