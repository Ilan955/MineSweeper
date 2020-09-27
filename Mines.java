package mines;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Mines {

private int numMines;
public Place[][]bord;
private int countWin=0;
private int height;
private int width;
static Random rand= new Random();
//defining the constructor
//will define the height, the width,and number of mines
//in addition, will make new bord, initialize it, and place the random mines there
//in addition, will save already the neighbors every specific place
	public Mines(int height,int width,int numMines) {
		this.numMines=numMines;
		this.height=height;
		this.width=width;
		bord=new Place[height][width];
		for(int i=0;i<height;i++)
			for(int j=0;j<width;j++)
				bord[i][j]=newPlace(i,j);
		for(int i=0;i<numMines;i++) {
			int m=rand.nextInt(height);
			int n=rand.nextInt(width);
			while(bord[m][n].hasMine())
			{
				m=rand.nextInt(height);
				n=rand.nextInt(width);
			}
			bord[m][n].setMine();
		}
		//getting all the neighnoors of this current place
		for(int m=0;m<height;m++)
			for(int n=0;n<width;n++)
				bord[m][n].SetNeib();
			
		
		}
	//will call the inner class and make new show of the Place class
	public Place newPlace(int i,int j) {
		return new Place(i,j);
	}
	//will add the mine, and add to the total mines the number(for the tests)
	//will call the function setMineRest to add mines for every other place there is
	public boolean addMine(int i,int j) {
		if(bord[i][j].hasMine())
			return false;
		numMines++;
		bord[i][j].setMine();
		setMineRest();
		return true;
		
	}
	//will check for every place if it's not been checked yet if one of his neighbors have mine
	//if has, then add the amount to this specific place in the mineNums place
	public void setMineRest() {
		int flag=0;
		for(int m=0;m<height;m++) {
			for(int n=0;n<width;n++)
			{
				if(bord[m][n].check==false) {
					for(Place p:bord[m][n].arr)
						if(p.hasMine())
							flag++;
							bord[m][n].mineNums=flag;
							flag=0;
							
				}
			}
		}
	}
	//this function will open the specific place in the bord. will check if has mine
	//will check if already opened, if not will open.
	//then will call a recursive function that will open all the neighbors too
	//Accordingly to the conditions of the game.
	public boolean open(int i,int j) {
		int flag=0;
		
		
		if(bord[i][j].hasMine())
			return false;
		if(!(bord[i][j].isOpen())) {
		bord[i][j].setOpen();
		
		
		bord[i][j].openAll();
		}
		
		
		return true;
		
	}
	//will place a flag at the specific place
	//if the flag already exists, then will delete it too(it's boolean so will be changed)
	public void toggleFlag(int x,int y) {
		bord[x][y].setFlag();
	}
	//will go over all the bord, and will check what cells are open.
	//if the amount of cells that open equals to bord size minus the numMines then won.
	public boolean isDone() {
		for(int i=0;i<height;i++)
			for(int j=0;j<width;j++) {
				if(bord[i][j].isOpen())
					countWin++;
			
	}
		
		if(((height*width)-numMines)==countWin)
		{
			return true;
	}
		countWin=0;
		return false;
	}
//------------------------------------The private Class of Place------------------------------------//
	public class Place   {
		HashSet<Place> arr=new HashSet<>();
		private boolean showAll;
		private boolean check;
		private int i,j;
		public int mineNums=0;
		private boolean MineStat;
		private boolean OpenStat;
		private boolean FlagStat;
		
		private Place(int i,int j) {
			this.i=i;
			this.j=j;
		
		}
		
		
		
			public void openAll() {
				int flag=0;
				int count=0;
				check=true;
			//will check if the current place is not a mine
			//if not a mine, enter the condition
				if(!(hasMine())) {
					//go for all of this place neib and check if one of them is a mine.
					//if so, add it to a counter, at line 148 we will add the counter to the mineNums
					for(Place mineC:arr) {
						if(mineC.hasMine())
						{
							flag++;
					}
					}
					mineNums=flag;
					//if there are no mines near, we can go further to check
					//we will go over every neib, and for him check the mines
					if(flag==0) {
					for(Place c:arr) {
						for(Place k:c.arr)
						{			 
							//if he has mine and did'nt checked yet, count it.
							//at line 163 add the counter to the mineNums
							if(k.hasMine()&&!(k.check) )
								
								count++;
						}
						c.setOpen();
						
					c.mineNums=count;
						count=0;
					}
					}
					//at the end, when we passed this current Place, we will go recursivly for every neib
					
				for(Place c:arr) 
				{
					if(!(c.check)&&c.mineNums==0&&flag==0)
					c.openAll();
				}
				}
				
				
			}
//--------------------------------------------------------------------------------------------------//
		//the next 5 methods will be for info and setting
		public void setMine() {
			MineStat=true;
		}
		public void setOpen() {
			OpenStat=true;
		}
		public void setFlag() {
			if(FlagStat)
			FlagStat=false;
			else
			FlagStat=true;
		}
		public boolean hasMine() {
			if (MineStat)
				return true;
			return false;
		}
		public boolean isOpen() {
			if(OpenStat)
				return true;
			return false;
		}
		public void setShowAll(boolean showAll) {
			this.showAll=showAll;
		}
		public boolean hasFlag() {
			if(FlagStat)
				return true;
			return false;
		}
//------------------------------------------------------------------------------------------------//
		
		//will set the neighbors for the specific Place, will
		public void SetNeib() {
			for(int m=-1;m<2;m++) {
				if(m+i<height&&m+i>=0) {
					
				
				for(int n=-1;n<2;n++) {
					
					if(n+j<width&&n+j>=0) {
						
							if(!(m+i==i&&n+j==j)) {
						Place p=bord[m+i][n+j];
						
						arr.add(p);
							}
					
						
					}
					
					}
				}
				}
			}
		
		
		public HashSet<Place> neighboors(){
			return arr;
		}
	}
	//will take the boolean value 'showAll' and insert it to every Place in the bord
	public void setShowAll(boolean showAll) {
		
			for(int i=0;i<height;i++)
				for(int j=0;j<width;j++) {
					bord[i][j].showAll=showAll;
					
	}
				
		
	}
	//the print of one place
	//will check if its open or if the 'showall' is true, if so check if has mine
	//if no, return the mineNums its has on the current place.
	public String get(int x,int y)
	{
		StringBuilder s=new StringBuilder();
		if(bord[x][y].isOpen()||bord[x][y].showAll)
		{
			if(bord[x][y].hasMine())
				return "X";
			
			//if not having a mine in this spot, check for neighbors
			if(bord[x][y].mineNums==0)
				return " ";
			s.append(bord[x][y].mineNums);
			
			return s.toString();
				
		}
	else {
		if(bord[x][y].hasFlag())
			return "F";
		return ".";
	}
}
	
	//go for every place and append it to a string.
	public String toString() {
		StringBuilder s=new StringBuilder();
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++)
				s.append(get(i,j));
			s.append("\n");
		}
		return s.toString();
	}
	
}
