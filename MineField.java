import java.util.Random;

class MineField{

	private boolean[][] mines,visible;
	private boolean boom;
	private final int rowMax = 5;
	private final int colMax = 10;

	MineField(){

		mines=new boolean[rowMax][colMax];
		visible=new boolean[rowMax][colMax];
		boom=false;

		initMap();
		
		//Adds mines randomly around the field.
		int numberOfMines=15; //changed variable name to a more suitable one.
		int randomRow,randomCol;
		Random RGenerator=new Random();

		while(numberOfMines>0){

			randomRow=Math.abs(RGenerator.nextInt()%rowMax);
			randomCol=Math.abs(RGenerator.nextInt()%colMax);

			if(trymove(randomRow,randomCol)){
				numberOfMines--;
			}
		}
	}
	
	private void initMap(){
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				mines[row][col]=false;
				visible[row][col]=false;
			}
		}
	}
	
	private boolean trymove(int randomRow, int randomCol) {
		if(mines[randomRow][randomCol]){
			return false;
		}
		else{
			mines[randomRow][randomCol]=true;
			return true;
		}
	}
	
	private void boom() {
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				if(mines[row][col]){
					visible[row][col]=true;
				}
			}
		}
		boom=true;
		show();
	}
	
	private int countMines(int row, int col){
		int count=0;
		for(int irow=row-1;irow<=row+1;irow++){
			for(int icol=col-1;icol<=col+1;icol++){
				if(icol>=0&&icol<colMax&&irow>=0&&irow<rowMax){
					if(mines[irow][icol]) count++;
				}
			}
		}
		return count;
	}
	
	//changed switch to if.
	private char printNumberOfMines(int count){ 
		if(count>=0 && count<9)
			return Character.forDigit(count, 9);
		return 'X';
	}
	
	private char drawChar(int row, int col) {
		if(visible[row][col]){
			if(mines[row][col]) //fixed formatting
				return '*';
			int count=countMines(row,col); //made own method for counting
			return printNumberOfMines(count); //made own method for printing
		}
		else{
			if(boom){
				return '-';
			} //removed curly brackets.
			return '?';
		}
	}
	
	public boolean getBoom(){

		return boom;
	}

	public boolean legalMoveString(String input) {
		String[] separated=input.split(" ");
		int row;
		int col;
		try{ //removed space
			row=Integer.parseInt(separated[0]);
			col=Integer.parseInt(separated[1]);
			if(row<0||col<0||row>=rowMax||col>=colMax){
				throw new java.io.IOException();
			}
		}
		catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}
		return legalMoveValue(row,col); // refactored if and else
	}


	private boolean legalMoveValue(int row, int col) {

		if(visible[row][col]){
			System.out.println("You stepped in allready revealed area!");
			return false;
		}
		else{
			visible[row][col]=true;
		}
		if(mines[row][col]){
			boom();
			return false;
		}
		return true;
	}
	
	public void show() {
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 ");
		System.out.println("   ---------------------");
		for(int row=0;row<rowMax;row++){
			System.out.print(row+" |");
			for(int col=0;col<colMax;col++){
				System.out.print(" "+drawChar(row,col));
			}
			System.out.println(" |");
		}
		System.out.println("   ---------------------");
	}
}
