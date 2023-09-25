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
	private static Color clrShield = new Color(65, 105, 225);
	private static Color clrDamage = new Color(255, 87, 51);
	
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
			//2.3 - Died DT Drowning
			//2.4 - Died DT Enemy
		//3 - HELP
		//4 - END GAME
		
		//Defining Map Properties
		int intMapWidth = 20;
		int intMapHeight = 20;
		
		//Defining Character Start Position
		int intCharXStart = 1;
		int intCharYStart = 1;
		
		//Defining Input Delay
		int intDelay = 50;
		
		//Main Function Variables
		String strMap[][] = new String[intMapHeight][intMapWidth];		//0 - INITILIZATION
		String strFileLine;
		String strFileSplit[];
		int intCountRows;
		int intCountColumns;
		int intMouseX = 0;												//1 - MENU
		int intMouseY = 0;
		int intMouseClicked;
		boolean blnClicked;
		char chrMovement;												//2.1 - MAP
		int intCharX = intCharXStart;
		int intCharY = intCharXStart;
		int intCharXLast = intCharX;
		int intCharYLast = intCharY;
		int intDamage;
		int intDefence;
		int intHealth;
		int intMaxDamage;
		int intFullHealth;
		int intFullDefence;
		int intEnemyDamage;
		int intEnemyDefence;
		int intEnemyHealth;
		boolean blnTrans;
		char chrPlayAgain;												//2.4 - DIED DT DROWNING		

		
		//Main Scene Loop
		while(dblScene >= 0){
			
			//Scene 0 - INITILIZATION
			while(dblScene == 0){

				//Importing Map File and Creating Array
				TextInputFile txtMap = new TextInputFile("map.csv");
				
				//Loading Array
				for(intCountRows = 0; intCountRows < intMapHeight; intCountRows++){
					strFileLine = txtMap.readLine();
					strFileSplit = strFileLine.split(",");
					for(intCountColumns = 0; intCountColumns < intMapWidth; intCountColumns++){
						strMap[intCountRows][intCountColumns] = strFileSplit[intCountColumns];
					}
				}
				
				//Resetting Variables
				intCharX = intCharXStart;
				intCharY = intCharYStart;
				
				//Closing File
				txtMap.close();
				
				//Advancing to Next Scene
				dblScene = 1;
				
				//Scene Graphics (OUT)
				Scene0Out();
				
			}
			
			//Scene 1 - MENU
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
					blnTrans = false;
				}
				else if(intMouseX > 850 && intMouseX < 1100 && intMouseY > 425 && intMouseY < 525 && intMouseClicked == 1){
					dblScene = 3;
				}
				else if(intMouseX > 850 && intMouseX < 1100 && intMouseY > 575 && intMouseY < 675 && intMouseClicked == 1){
					dblScene = 4;
				}
				
				Scene1Out(dblScene);
			}
			
			//Scene 2.1 - MAP
			while(dblScene == 2.1){
				
				//Scene Animation In
				//if (blnTrans = false){
					//Scene2aIn(strMap, intCharX, intCharY);
					//blnTrans = true;
				//}
				
				//Scene Input
				chrMovement = con.currentChar();
				
				//Character Movement
				if(chrMovement == 'w'){
					intCharY = intCharY - 1;
				}
				else if(chrMovement == 'a'){
					intCharX = intCharX - 1;
				}
				else if(chrMovement == 's'){
					intCharY = intCharY + 1; 
				}
				else if(chrMovement == 'd'){
					intCharX = intCharX + 1;
				}
				
				//Character Movement Bounding
				if(intCharX < 1){
					intCharX = 1;
				}
				else if(intCharX > 20){
					intCharX = 20;
				}
				else if(intCharY < 1){
					intCharY = 1;
				}
				else if(intCharY > 20){
					intCharY = 20;
				}
				
				//Water Death
				if(strMap[intCharY - 1][intCharX - 1].equals("w")){
					dblScene = 2.3;
				}
				
				//TO PROGRAM
				//ACCURATE STATS HUD DISPLAY
				//BUILDING INTERACTION
				//BUFF CARRY INTERACTION
				//FIGHTING INTERACTION
				//When interacted, change the array value to " ", will result in a grass tile
				
				//MaxDamage represents the maximum amount of damage attainable through all sources of powerups
				//FullHealth represents the current 100% state of health
				//FullShield represents the current 100% state of shield
				
				
				//Scene Graphics
				Scene2a(strMap, intCharX, intCharY, intCharXLast, intCharYLast);
				
				intCharXLast = intCharX;
				intCharYLast = intCharY;
				
				con.sleep(intDelay);
				
			}
			
			//Scene 2.3 - WATER DEATH
			while(dblScene == 2.3){
				
				//Scene Graphics
				Scene2c();
				
				//Scene Input
				chrPlayAgain = con.getChar();
				
				if(chrPlayAgain == 'p'){
					dblScene = 0;
				}
				else{
					dblScene = 4;
				}
				
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
		
		//Drawing Black Screen
		con.setDrawColor(clrBlack);
		con.fillRect(0, 0, 1150, 800);
		
		//Preparing Elements
		con.setDrawColor(clrWhite);
		con.setDrawFont(fnt50);
		
		//Inital Loading
		intCAlign = CAlign(fnt50, "Loading...");
		con.drawString("Loading...", 575 - (intCAlign[0]/2), 375 - (intCAlign[1]/2)); 
		con.repaint();
		con.sleep(500);
		
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
	
	//Scene 1 - MENU CONTENT
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
	
	//Scene 1 (OUT) - MENU CONTENT
	public static void Scene1Out(double dblScene){
		
		//Scene Variables
		int intCount;
		int intPosX = 1150;
		int intVelo = 0;
		
		//Preparing Elements (Based on Input)
		if(dblScene == 2.1){
			con.setDrawColor(clrGreen);
		}
		else if(dblScene == 3){
			con.setDrawColor(clrBlue);
		}
		else if(dblScene == 4){
			con.setDrawColor(clrRed);
		}
		
		//Transition Out if Input Selected
		if(dblScene != 1){
			for(intCount = 0; intCount < 45; intCount++){
				con.fillRect(intPosX, 0, 1150, 800);
				intVelo = intVelo + 3;
				intPosX = intPosX - intVelo;
				con.repaint();
				con.sleep(17);
			}
		}
	}
	
	//Scene 2.1 - MAP
	public static void Scene2a(String strMap[][], int intCharX, int intCharY, int intCharXLast, int intCharYLast){
		
		//Scene Images
		BufferedImage imgTree = con.loadImage("Graphics/tree.png");
		BufferedImage imgGrass = con.loadImage("Graphics/grass.png");
		BufferedImage imgWater = con.loadImage("Graphics/water.png");
		BufferedImage imgBuild = con.loadImage("Graphics/build.png");
		BufferedImage imgHero = con.loadImage("Graphics/hero.png");
		BufferedImage imgHP100 = con.loadImage("Graphics/health100.png");
		BufferedImage imgDamage = con.loadImage("Graphics/damage.png");
		BufferedImage imgShield = con.loadImage("Graphics/shield.png");
		
		//Font Alignment Variable (Coordinate Based)
		int intCAlign[] = new int[2];
		
		//Scene Variables
		int intCount;
		int intCount2;
		int intCount3;
		int intVelo = 0;
		int intMove = 0;
		
		//Drawing HUD Box
		con.setDrawColor(clrBlack);
		con.fillRect(800, 0, 350, 800);
		
		//Drawing HUD Stats Fill
		con.setDrawColor(clrDamage);
		con.fillRect(825, 575, 200, 50);
		con.setDrawColor(clrShield);
		con.fillRect(825, 650, 200, 50);
		con.setDrawColor(clrRed);
		con.fillRect(825, 725, 200, 50);
		
		//Preparing Elements
		con.setDrawColor(clrWhite);
		con.setDrawFont(fnt30);
		
		//Drawing HUD Outline and Elements
		con.drawRect(825, 575, 300, 50);
		con.drawImage(imgDamage, 840, 585);
		con.drawRect(825, 650, 300, 50);
		con.drawImage(imgShield, 840, 660);
		con.drawRect(825, 725, 300, 50);
		con.drawImage(imgHP100, 840, 735);
		
		//Drawing HUD Text
		intCAlign = CAlign(fnt30, "30/30");
		con.drawString("30/30", 975 - (intCAlign[0]/2), 592 - (intCAlign[1]/2));
		intCAlign = CAlign(fnt30, "30/30");
		con.drawString("30/30", 975 - (intCAlign[0]/2), 667 - (intCAlign[1]/2));
		intCAlign = CAlign(fnt30, "30/30");
		con.drawString("30/30", 975 - (intCAlign[0]/2), 742 - (intCAlign[1]/2));
		
		//Hero Movement Animation
		if(intCharX > intCharXLast){
			//Animate Right
			for(intCount3 = 0; intCount3 < 5; intCount3++){
				intVelo = intVelo + 2;
				intMove = intMove + intVelo;
				
				//Re-Drawing Map Background
				for(intCount = 0; intCount < 20; intCount++){
					for(intCount2 = 0; intCount2 < 20; intCount2++){
						if(strMap[intCount][intCount2].equals("w")){
							con.drawImage(imgWater, (intCount2 * 40), (intCount * 40));
						}
						else if(strMap[intCount][intCount2].equals("t")){
							con.drawImage(imgTree, (intCount2 * 40), (intCount * 40));
						}
						else if(strMap[intCount][intCount2].equals("b")){
							con.drawImage(imgBuild, (intCount2 * 40), (intCount * 40));
						}
						else{
							con.drawImage(imgGrass, (intCount2 * 40), (intCount * 40));
						}
					}
				}
				
				//Drawing Hero
				con.drawImage(imgHero, intMove + (intCharXLast * 40) - 40, (intCharYLast * 40) - 40);
				con.repaint();
				con.sleep(17);
			}
		}
		else if(intCharX < intCharXLast){
			//Animate Left
			for(intCount3 = 0; intCount3 < 5; intCount3++){
				intVelo = intVelo + 2;
				intMove = intMove - intVelo;
				
				//Re-Drawing Map Background
				for(intCount = 0; intCount < 20; intCount++){
					for(intCount2 = 0; intCount2 < 20; intCount2++){
						if(strMap[intCount][intCount2].equals("w")){
							con.drawImage(imgWater, (intCount2 * 40), (intCount * 40));
						}
						else if(strMap[intCount][intCount2].equals("t")){
							con.drawImage(imgTree, (intCount2 * 40), (intCount * 40));
						}
						else if(strMap[intCount][intCount2].equals("b")){
							con.drawImage(imgBuild, (intCount2 * 40), (intCount * 40));
						}
						else{
							con.drawImage(imgGrass, (intCount2 * 40), (intCount * 40));
						}
					}
				}
				
				//Drawing Hero
				con.drawImage(imgHero, intMove + (intCharXLast * 40) - 40, (intCharYLast * 40) - 40);
				con.repaint();
				con.sleep(17);
			}
		}
		else if(intCharY > intCharYLast){
			//Animate Down
			for(intCount3 = 0; intCount3 < 5; intCount3++){
				intVelo = intVelo + 2;
				intMove = intMove + intVelo;
				
				//Re-Drawing Map Background
				for(intCount = 0; intCount < 20; intCount++){
					for(intCount2 = 0; intCount2 < 20; intCount2++){
						if(strMap[intCount][intCount2].equals("w")){
							con.drawImage(imgWater, (intCount2 * 40), (intCount * 40));
						}
						else if(strMap[intCount][intCount2].equals("t")){
							con.drawImage(imgTree, (intCount2 * 40), (intCount * 40));
						}
						else if(strMap[intCount][intCount2].equals("b")){
							con.drawImage(imgBuild, (intCount2 * 40), (intCount * 40));
						}
						else{
							con.drawImage(imgGrass, (intCount2 * 40), (intCount * 40));
						}
					}
				}
				
				//Drawing Hero
				con.drawImage(imgHero, (intCharXLast * 40) - 40, intMove + (intCharYLast * 40) - 40);
				con.repaint();
				con.sleep(17);
			}
		}
		else if(intCharY < intCharYLast){
			//Animate Up
			for(intCount3 = 0; intCount3 < 5; intCount3++){
				intVelo = intVelo + 2;
				intMove = intMove - intVelo;
				
				//Re-Drawing Map Background
				for(intCount = 0; intCount < 20; intCount++){
					for(intCount2 = 0; intCount2 < 20; intCount2++){
						if(strMap[intCount][intCount2].equals("w")){
							con.drawImage(imgWater, (intCount2 * 40), (intCount * 40));
						}
						else if(strMap[intCount][intCount2].equals("t")){
							con.drawImage(imgTree, (intCount2 * 40), (intCount * 40));
						}
						else if(strMap[intCount][intCount2].equals("b")){
							con.drawImage(imgBuild, (intCount2 * 40), (intCount * 40));
						}
						else{
							con.drawImage(imgGrass, (intCount2 * 40), (intCount * 40));
						}
					}
				}
				
				//Drawing Hero
				con.drawImage(imgHero, (intCharXLast * 40) - 40, intMove + (intCharYLast * 40) - 40);
				con.repaint();
				con.sleep(17);
			}
		}
		
		//DRAWING MAP BASE
		//Reading Rows
		for(intCount = 0; intCount < 20; intCount++){
			//Reading Columns
			for(intCount2 = 0; intCount2 < 20; intCount2++){
				if(strMap[intCount][intCount2].equals("w")){
					con.drawImage(imgWater, (intCount2 * 40), (intCount * 40));
				}
				else if(strMap[intCount][intCount2].equals("t")){
					con.drawImage(imgTree, (intCount2 * 40), (intCount * 40));
				}
				else if(strMap[intCount][intCount2].equals("b")){
					con.drawImage(imgBuild, (intCount2 * 40), (intCount * 40));
				}
				else{
					con.drawImage(imgGrass, (intCount2 * 40), (intCount * 40));
				}
			}
		}
		
		//Drawing Hero's Final Position
		con.drawImage(imgHero, (intCharX * 40) - 40, (intCharY * 40) - 40);
		con.repaint();
	}
	
	//Scene 2.4 - DIED DT DROWNING
	public static void Scene2c(){
		
		//Scene Images
		BufferedImage imgScene2dBG = con.loadImage("Graphics/Drowning Game Over.png");
		
		//Drawing Image
		con.drawImage(imgScene2dBG, 0, 0);
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
