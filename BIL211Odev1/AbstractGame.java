
public abstract class AbstractGame {
	
	private Board board;
	private Player red;
	private Player black;
	
	public void initializeBoard() {
		
		setBoard(new Board());
	}
	
	public void initializeRedPlayer(String name) {
		
		setRed(new Player("name","red"));
		
	}
	
	public void initializeBlackPlayer(String name) {
		
		setBlack(new Player("name","black"));
		
	}
	
	
	
	/*
	 * from pozisyonundaki taSi to pozisyonuna taSir.
	 * Eger hareket kural diSi ise, ekrana "hatali hareket" mesaji ekrana yazilir ve oyuncunun tekrar oynamasi için sirayi degiStirmez.
	 * Eger hareket sonucu biri oyunu kazandi ise, "SAH MAT! X oyunu kazandi. X'in puani: A, Y'nin puani: B" yazar. X ve Y oyuncularin ismidir. A ve B aldiklari puanlardir.
	 * Eger hareket sonucu pat oldu ise (Sahin hiç bir yere hareket edememesi ve baSka yapacak hareketinin olmamasi durumu), "PAT" mesaji ekrana yazilir ve oyun sonlanir. 
	 * */
	abstract void play(String from, String to);  
	
	/*
	 * Oyunun o anki hali belirtilen dosyaya binary olarak kaydedilir.
	 * */
	abstract void save_binary(String address);  
	
	/*
	 * Oyunun o anki hali belirtilen dosyaya metin dosyasi olarak kaydedilir.
	 * */
	abstract void save_text(String address);  
	
	/*
	 * Belirtilen adreste kaydedilen metin dosyasina gore oyunu yukler ve oyun kaldigi yerden devam eder. 
	 * Dosyanin dogru dosya oldugunu varsayabilirsiniz.
	 * */
	abstract void load_text(String address);  
	
	
	/*
	 * Belirtilen adreste kaydedilen binary dosyaya gore oyunu yukler ve oyun kaldigi yerden devam eder.
	 * Dosyanin dogru dosya oldugunu varsayabilirsiniz.
	 * <
	 * */
	abstract void load_binary(String address);

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getRed() {
		return red;
	}

	public void setRed(Player white) {
		this.red = white;
	}

	public Player getBlack() {
		return black;
	}

	public void setBlack(Player black) {
		this.black = black;
	}  
	
	

}