import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

public class gametest{
	
	//Global Console
	private static Console con = new Console("Simple RPG Game", 1280, 800);
	
	//Global Colours
	private static Color clrWhite = new Color(255, 255, 255);
	
	//Global Fonts
	
	
	//Main Function
	public static void main(String[] args){
		
		//Scene Configuration Variables
		double dblScene = 0;
		
		//LEGEND:
		//0 - Initilization
		//1 - MENU
		//2 - SELECT MAP
		//3 - GAME
			//3.1 - Map
			//3.2 - Fighting
			//3.3 - Regen and Buff
		//4 - HELP
		//5 - END GAME
		
		//Defining Map Properties
		int intMapWidth = 20;
		int intMapHeight = 20;
		
		//Main Function Variables
		String strMap[][];							//0 - Initilization
		String strFileLine;
		String strFileSplit[];
		int intCountRows;
		int intCountColumns;
		char chrMenu;								//1 - Menu

		
		//Main Scene Loop
		while(dblScene >= 0){
			
			//Scene 0 - Initilization
			while(dblScene == 0){
				
				//Importing Map File and Creating Array
				strMap = new String[intMapHeight][intMapWidth];
				TextInputFile mapFile = new TextInputFile("map.csv");
				
				//Loading Array
				for(intCountRows = 0; intCountRows < intMapHeight; intCountRows++){
					strFileLine = mapFile.readLine();
					strFileSplit = strFileLine.split(",");
					for(intCountColumns = 0; intCountColumns < intMapWidth; intCountColumns++){
						strMap[intCountRows][intCountColumns] = strFileSplit[intCountColumns];
						Scene0(intCountRows,intCountColumns);
					}
				}
				
				//Closing File
				mapFile.close();
				
				dblScene = 1;
			}
			
			//Scene 1 - Menu
		}
	}
	
	//Graphics
	
	//Scene 0 - Initilization
	public static void Scene0(int intCountRows, int intCountColumns){
		con.println("Initilizing Row: "+intCountRows+" | Column: "+intCountColumns);
		con.sleep(5);
		con.clear();
	}
	
	//Scene 1 - Menu
	
	//Scene 2 - Select Map
	
	//Scene 3.1 - Map
	public static void Scene3a(){
		BufferedImage imgTree = con.loadImage("tree.png");
		BufferedImage imgGrass = con.loadImage("grass.png");
		BufferedImage imgWater = con.loadImage("water.png");
		
		con.drawImage(imgTree,0,0);
		con.drawImage(imgGrass,0,40);
		con.drawImage(imgWater,0,80);
		con.repaint();
	}
	
}
