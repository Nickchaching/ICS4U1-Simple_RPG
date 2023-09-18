//Importing the Necessary Libraries
import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Simple_RPG{
	
	//Global Console
	private static Console con = new Console("Simple RPG Game", 1150, 800);
	
	//Global Colours
	private static Color clrWhite = new Color(255, 255, 255);
	private static Color clrBlack = new Color(0, 0, 0);
	private static Color clrRed = new Color(255, 0, 0);
	private static Color clrGreen = new Color(0, 255, 0);
	private static Color clrBlue = new Color(0, 0, 255);
	
	//Global Fonts
	private static Font fnt24 = con.loadFont("BebasNeue.ttf", 24);
	private static Font fnt27 = con.loadFont("BebasNeue.ttf", 27);
	private static Font fnt30 = con.loadFont("BebasNeue.ttf", 30);
	private static Font fnt36 = con.loadFont("BebasNeue.ttf", 36);
	private static Font fnt50 = con.loadFont("BebasNeue.ttf", 50);
	private static Font fnt56 = con.loadFont("BebasNeue.ttf", 56);
	private static Font fnt77 = con.loadFont("BebasNeue.ttf", 77);
	private static Font fnt120 = con.loadFont("BebasNeue.ttf", 120);
	
	//Main Function
	public static void main(String[] args){
		
		//Scene Configuration Variables
		double dblScene = 0;
		
		//LEGEND:
		//0 - Initilization
		//1 - MENU
		//2 - GAME
			//2.1 - Map
			//2.2 - Fighting
			//2.3 - Regen and Buff
		//3 - HELP
		//4 - END GAME
		
		//Defining Map Properties
		int intMapWidth = 20;
		int intMapHeight = 20;
		
		//Main Function Variables
		String strMap[][];							//0 - INITILIZATION
		String strFileLine;
		String strFileSplit[];
		int intCountRows;
		int intCountColumns;
		int intMouseX = 0;								//1 - MENU
		int intMouseY = 0;
		int intMouseClicked;
		boolean blnClicked;
		char chrMovement;							//2.1 - MAP

		
		//Main Scene Loop
		while(dblScene >= 0){
			
			//Scene 0 - Initilization
			while(dblScene == 0){

				//Importing Map File and Creating Array
				strMap = new String[intMapHeight][intMapWidth];
				TextInputFile txtMap = new TextInputFile("map.csv");
				
				//Loading Array
				for(intCountRows = 0; intCountRows < intMapHeight; intCountRows++){
					strFileLine = txtMap.readLine();
					strFileSplit = strFileLine.split(",");
					for(intCountColumns = 0; intCountColumns < intMapWidth; intCountColumns++){
						strMap[intCountRows][intCountColumns] = strFileSplit[intCountColumns];
					}
				}
				
				//Closing File
				txtMap.close();
				
				//Advancing to Next Scene
				dblScene = 1;
				
				//Scene Graphics (OUT)
				Scene0Out();
				
			}
			
			//Scene 1 - Menu
			while(dblScene == 1){
				
				//Scene Graphics
				Scene1(intMouseX, intMouseY);
				
				//Mouse Input
				intMouseX = con.currentMouseX();
				intMouseY = con.currentMouseY();
				intMouseClicked = con.currentMouseButton();
				
				//Conditions
				if(intMouseX > 850 && intMouseX < 1100 && intMouseY > 275 && intMouseY < 375 && intMouseClicked == 1){
					dblScene = 2.1;
				}
				else if(intMouseX > 850 && intMouseX < 1100 && intMouseY > 425 && intMouseY < 525 && intMouseClicked == 1){
					dblScene = 3;
				}
				else if(intMouseX > 850 && intMouseX < 1100 && intMouseY > 575 && intMouseY < 675 && intMouseClicked == 1){
					dblScene = 4;
				}
				
			}
			
			//Scene 2.1 - MAP
			while(dblScene == 2.1){
				
				//Scene Input
				chrMovement = con.currentChar();
			
			}
		}
	}
	
	//Graphics
	
	//Scene 0 (OUT) - INITILIZATION
	public static void Scene0Out(){
		//Scene Images
		BufferedImage imgMenu = con.loadImage("Graphics/Menu Screen.jpg");
		
		//Font Alignment Variable (Coordinate Based)
		int intCAlign[] = new int[2];
		
		//Scene Variables
		int intCount;
		int intSize = 1200;
		int intVelo = 0;
		
		//Preparing Elements
		con.setDrawColor(clrWhite);
		con.setDrawFont(fnt50);
		
		//Inital Loading
		intCAlign = CAlign(fnt50, "Loading...");
		con.drawString("Loading...", 575 - (intCAlign[0]/2), 375 - (intCAlign[1]/2)); 
		con.repaint();
		con.sleep(2000);
		
		//Preparing Elements
		con.setDrawColor(clrBlack);
		
		//Left Animation
		for(intCount = 0; intCount < 45; intCount++){
			intVelo = intVelo + 2;
			intSize = intSize - intVelo;
			con.fillRect(0, 0, 1150, 800);
			con.drawImage(imgMenu, 0, 0);
			con.fillOval(400 - (intSize/2), 400 - intSize/2, intSize, intSize);
			con.repaint();
			con.sleep(17);
		}
		
		//Resetting Animation Variables
		intSize = 800;
		intVelo = 0;
		
		//Right Animation
		for(intCount = 0; intCount < 45; intCount++){
			intVelo = intVelo + 3;
			intSize = intSize - intVelo;
			
			//Drawing Black Background
			con.setDrawColor(clrBlack);
			con.fillRect(800, 0, 350, 800);
			
			//Drawing Menu
			con.setDrawColor(clrWhite);
			con.setDrawFont(fnt120);
			intCAlign = CAlign(fnt120, "MENU");
			con.drawString("MENU", 975 - (intCAlign[0]/2), 100 - (intCAlign[1]/2)); 
			
			con.setDrawFont(fnt77);
			intCAlign = CAlign(fnt77, "PLAY");
			con.drawString("PLAY", 975 - (intCAlign[0]/2), 300 - (intCAlign[1]/2));
			intCAlign = CAlign(fnt77, "HELP");
			con.drawString("HELP", 975 - (intCAlign[0]/2), 450 - (intCAlign[1]/2));
			intCAlign = CAlign(fnt77, "QUIT");
			con.drawString("QUIT", 975 - (intCAlign[0]/2), 600 - (intCAlign[1]/2));
			
			//Drawing Moving Cover
			con.setDrawColor(clrBlack);
			con.fillRect(800, 0, 350, intSize);
			con.repaint();
			con.sleep(17);
		}
	}
	
	//Scene 1 - Menu
	public static void Scene1(int intMouseX, int intMouseY){	
		//Scene Images
		BufferedImage imgMenu = con.loadImage("Graphics/Menu Screen.jpg");
		
		//Font Alignment Variable (Coordinate Based)
		int intCAlign[] = new int[2];
		
		//Preparing Elements
		con.setDrawColor(clrBlack);
		
		//Drawing Background
		con.fillRect(0, 0, 1150, 800);
		con.drawImage(imgMenu, 0, 0);
		
		//Preparing Elements
		con.setDrawColor(clrWhite);
		con.setDrawFont(fnt120);
		
		//Drawing Menu
		intCAlign = CAlign(fnt120, "MENU");
		con.drawString("MENU", 975 - (intCAlign[0]/2), 100 - (intCAlign[1]/2)); 
		
		con.setDrawFont(fnt77);
		intCAlign = CAlign(fnt77, "PLAY");
		con.drawString("PLAY", 975 - (intCAlign[0]/2), 300 - (intCAlign[1]/2));
		intCAlign = CAlign(fnt77, "HELP");
		con.drawString("HELP", 975 - (intCAlign[0]/2), 450 - (intCAlign[1]/2));
		intCAlign = CAlign(fnt77, "QUIT");
		con.drawString("QUIT", 975 - (intCAlign[0]/2), 600 - (intCAlign[1]/2));
		
		//Colour Highlighting Animation
		if(intMouseX > 850 && intMouseX < 1100 && intMouseY > 275 && intMouseY < 375){
			con.setDrawColor(clrGreen);
			intCAlign = CAlign(fnt77, "PLAY");
			con.drawString("PLAY", 975 - (intCAlign[0]/2), 300 - (intCAlign[1]/2));
		}
		else if(intMouseX > 850 && intMouseX < 1100 && intMouseY > 425 && intMouseY < 525){
			con.setDrawColor(clrBlue);
			intCAlign = CAlign(fnt77, "HELP");
			con.drawString("HELP", 975 - (intCAlign[0]/2), 450 - (intCAlign[1]/2));
		}
		else if(intMouseX > 850 && intMouseX < 1100 && intMouseY > 575 && intMouseY < 675){
			con.setDrawColor(clrRed);
			intCAlign = CAlign(fnt77, "QUIT");
			con.drawString("QUIT", 975 - (intCAlign[0]/2), 600 - (intCAlign[1]/2));
		}
		
		con.repaint();
	}
	
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
	
	//Text Display Width Calculator
	public static int[] CAlign(Font fntin, String strtext){
		//Font Alignment Variable (Coordinate Based)
		int intCAlign[] = new int[2];
		//Creates the variables needed for alignment
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		int intX = (int)(fntin.getStringBounds(strtext, frc).getWidth());
		int intY = (int)(fntin.getStringBounds(strtext, frc).getHeight());
		intCAlign[0] = intX;
		intCAlign[1] = intY;
		return intCAlign;
	}
	
}
